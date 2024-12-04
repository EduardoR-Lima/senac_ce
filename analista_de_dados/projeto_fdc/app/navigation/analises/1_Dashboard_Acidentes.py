import streamlit as st
import pandas as pd
import plotly.express as px
from utils import RefEnum, HorizontalSection
from app.resources import get_loaded_data, read_css
from app.navigation import place_sidebar_return_button
from typing import Literal, TypeAlias, get_args

# Variables
_Date: TypeAlias = Literal["Todos", 2018, 2019, 2020]
_Severity: TypeAlias = Literal['Todos', 'Fatal', 'Com Feridos', 'Sem Feridos']
_Province: TypeAlias = Literal['Todos', 'ES', 'MG', 'RJ', 'SP']
_No_Filter = ['Todos', 'Todas']
_DbAcidentes: TypeAlias = pd.DataFrame

# Data
@st.cache_data(show_spinner=False)
def get_data() -> pd.DataFrame:
    uf_codes: pd.DataFrame = get_loaded_data('data/uf.csv')
    df: pd.DataFrame = get_loaded_data('data/BD-PRF-SUDESTE.xlsx')
    df = (
        df
        .merge(uf_codes[['codigo', 'sigla']], left_on='uf', right_on='sigla')
        .drop(columns='sigla')
    )
    df['dia_semana'] = (df['data'].dt.weekday + 1) % 7
    df['mes'] = df['data'].dt.month
    df['intervalo_15m'] = df['hora'].apply(lambda x: pd.Timedelta(hours=x.hour, minutes=x.minute)//pd.Timedelta(minutes=15))
    return df

@st.cache_data(show_spinner=False)
def get_municipio_data():
    df: pd.DataFrame = get_loaded_data('data/municipios.json')
    df['nome'] = df['nome'].apply(lambda a: remover_acentos(str.upper(a)))
    return df


# Utility Functions
def remover_acentos(texto: str):
    # Tabela de mapeamento de caracteres acentuados para suas versões sem acento
    tabela_acentos = str.maketrans(
        'áéíóúãõâêîôûàèìòùäëïöüçÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÄËÏÖÜÇ', 
        'aeiouaoaeiouaeiouaeioucAEIOUAOAEIOUAEIOUACIOUC'
    )
    return texto.translate(tabela_acentos)

def format_number(value: float) -> str:
    if value.is_integer():
        return f'{value:,}'.replace(',', '.')
    
    
    splited = f"{value:,.2f}".replace(',', '.').rsplit('.', 1)
    return ','.join(splited)

fn = format_number

def select_highway(df: pd.DataFrame):
    df = df.reset_index()
    row_list = ss[Keys.HIGHWAY_DF]['selection']['rows']
    if len(row_list) == 0:
        value = None
    else:
        value = int(df.iloc[row_list[0]].br)
    ss[Keys.SELECTED_HIGHWAY] = value

def pop_or_none(l: list):
    if len(l) > 0:
        return l.pop(0)
    else:
        return None

# Filter Functions



def filter_by_date(df: _DbAcidentes, date: _Date):
    if date in _No_Filter:
        return df
    
    return df.loc[df['data'].dt.year == date]

def filter_by_severity(df: _DbAcidentes, severity: _Severity):
    if severity in _No_Filter:
        return df
    
    if severity == 'Fatal':
        return df.loc[df['mortos'] > 0]
    else:
        df = df.loc[df['mortos'] == 0]

    if severity == 'Com Feridos':
        return df.loc[df['feridos'] > 0]
    else:
        df = df.loc[df['feridos'] == 0]
    
    return df

def filter_by_highway(df: _DbAcidentes, highway):
    if highway is None:
        return df
    
    return df.loc[df['br'] == highway]

def filter_by_province(df: _DbAcidentes, province: _Province):
    if province in _No_Filter:
        return df
    
    return df.loc[df['uf'] == province]

def groupby_municipio(df: _DbAcidentes):
    municipios = get_municipio_data()
    merged_df = (
        df[['id', 'municipio', 'codigo', 'uf']]
        .merge(
            municipios[['nome', 'codigo_uf', 'latitude', 'longitude']],
            left_on=['municipio', 'codigo'],
            right_on=['nome', 'codigo_uf'],
            how='inner'
        )
        .drop(columns=['nome', 'codigo_uf'])
    )
    return merged_df.groupby(['municipio', 'latitude', 'longitude', 'uf'], as_index=False)




# charts functions
def acidentes_p_dia_bar(df: _DbAcidentes):
    summarized_df = df[['dia_semana', 'id']].groupby('dia_semana').count()

    fig = px.bar(summarized_df, x='id', orientation='h',
                 height=400).update_layout(
            yaxis=dict(
                title=None,
                tickmode='array',
                tickvals=[*range(7)],
                ticktext=['dom', 'seg', 'ter', 'qua', 'qui', 'sex', 'sab']
            ),
            xaxis=dict(
                title=None
            )
        )

    return fig

def acidentes_p_pista_bar(df: _DbAcidentes):
    summarized_df = df[['tipo_pista', 'id']].groupby('tipo_pista').count()

    fig = px.bar(summarized_df, y='id').update_layout(
            yaxis=dict(
                title=None
            )        
    )

    return fig

def eventos_p_lat_lon_mapbox(df: _DbAcidentes):
    # summarized_df = df[['latitude', 'longitude', 'id']].groupby(['latitude', 'longitude']).count().reset_index()
    summarized_df = groupby_municipio(df).count()
    z_max = summarized_df[['id']].max().values[0]*0.5
    fig = px.density_mapbox(
        data_frame=summarized_df,
        lat='latitude',
        lon='longitude',
        z='id',
        range_color=[1, z_max],
        mapbox_style='open-street-map',
        center=dict(lat=-20,lon=-44),
        zoom=4,
        radius=10,
        color_continuous_scale=px.colors.sequential.Blues_r,
        height=720
    ).update_layout(dict(
        coloraxis_colorbar=dict(
            title=dict(
                text='Eventos',
                side='top'
            ),
            orientation="h",
            yanchor="top",
            xanchor="center",
            y=-0.03
        ),
        margin=dict(
            l=10,
            r=10,
            t=10,
            b=10
        )
    )).update_traces(
        customdata=summarized_df[['municipio']],
        hovertemplate=('Total de Eventos: %{z}<br>'
                       'Municipio  %{customdata[0]}<br>'
                       '<extra></extra>')
    )
    
    return fig

def eventos_p_municipio_scatter_mapbox(df: _DbAcidentes):
    summarized_df = groupby_municipio(df).count()
    color_map = {uf:c for uf, c in zip(get_args(_Province), px.colors.carto.Bold)}
    fig = px.scatter_mapbox(
        data_frame=summarized_df,
        lat='latitude',
        lon='longitude',
        size='id',
        mapbox_style='open-street-map',
        center=dict(lat=-20,lon=-44),
        color_discrete_map=color_map,
        zoom=4,
        height=720,
        color='uf'
    ).update_layout(dict(
        legend=dict(
            title=dict(
                text='Estados',
                side='left'
            ),
            orientation='h',
            x=0.5,
            y=-0.1,
            xanchor="center",
            yanchor="bottom"
        ),
        margin=dict(
            l=10,
            r=10,
            t=10,
            b=10
        )
    )).update_traces(
        customdata=summarized_df[['municipio']],
        hovertemplate=('Total de Eventos: %{marker.size}<br>'
                       'Municipio  %{customdata[0]}<br>'
                       '<extra></extra>')
    )

    return fig

def acidentes_p_pista_rosca(df: _DbAcidentes):
    summarized_df = df[['tipo_pista', 'id']].groupby('tipo_pista').count().reset_index()

    fig = px.pie(summarized_df, names='tipo_pista', height=200)

    return fig

def eventos_e_feridos_p_mes(df: _DbAcidentes):
    summarized_df = (
        df[['mes', 'id', 'feridos']]
            .groupby('mes')
            .agg(
                eventos=('id', 'count'),
                feridos=('feridos', 'sum')
            )
            .reset_index()
    )

    fig = px.line(
        summarized_df,
        x='mes',
        y=['eventos', 'feridos'],
        height=295
    ).update_layout(
        yaxis=dict(
            title=None,
            nticks=3
        ),
        xaxis=dict(
            title='Mêses',
            tickvals=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            ticktext=['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']
        ),
        legend=dict(
            title=dict(
                text=None,
                side='left'
            ),
            orientation='h',
            x=0.12,
            y=1.1,
            xanchor="center",
            yanchor="top",
        ),
        margin=dict(
            l=10,
            r=10,
            t=10,
            b=10
        )
    ).update_traces(
        mode='lines+markers',
        hovertemplate=('Total: %{y}<br>'
                       'Mês: %{x}'
                       '<extra></extra>')
    )

    return fig

#Com a ajuda do chatgpt
def format_ticks(*tick_values, interval_minutes=15):
    """
    Formata os valores dos ticks como intervalos de tempo, ex: "00:00 - 00:15", "00:15 - 00:30", etc.
    
    Args:
        tick_values (list): Lista de valores sequenciais para os ticks.
        interval_minutes (int): Intervalo de tempo em minutos para cada tick (padrão é 15 minutos).
    
    Returns:
        list: Lista de strings formatadas para os ticks.
    """
    # Convertendo a lista de valores para a duração total de minutos
    start_time = pd.to_datetime("00:00", format='%H:%M')  # Começo às 00:00
    tick_labels = []

    for tick in tick_values:
        # Calculando o intervalo de tempo (multiplicando o valor do tick pelo intervalo em minutos)
        start_interval = start_time + pd.Timedelta(minutes=tick * interval_minutes)
        end_interval = start_interval + pd.Timedelta(minutes=interval_minutes)
        
        # Formatando o intervalo de tempo para exibição
        label = f"{start_interval.strftime('%H:%M')} - {end_interval.strftime('%H:%M')}"
        tick_labels.append(label)

    return tick_labels

def eventos_p_intervalo(df: _DbAcidentes):
    summarized_df = df[['intervalo_15m', 'id']].groupby('intervalo_15m').count().reset_index()

    custom_ticks = format_ticks(*range(96))

    fig = px.histogram(
        summarized_df,
        x='intervalo_15m',
        y='id',
        nbins=96,
        height=295
    ).update_layout(
        yaxis=dict(
            title='Total de Eventos'
        ),
        xaxis=dict(
            title='Intervalo de 15 minutos',
            tickvals=[v for v in range(0, 96, 96//5)],
            ticktext=[format_ticks(v) for v in range(0, 96, 96//5)],

        ),
        margin=dict(
            l=10,
            r=10,
            t=10,
            b=10
        )
    ).update_traces(
        customdata=custom_ticks,
        hovertemplate=('Total de Eventos: %{y}<br>'
                       'Intervalo: %{customdata}'
                       '<extra></extra>')
    )
    return fig

available_charts = {
    'Eventos e Feridos por Mês': eventos_e_feridos_p_mes,
    'Eventos por Intervalo': eventos_p_intervalo
}

# Ref Code:
class Keys(RefEnum):
    OPTIONS_PANEL = 'options panel'
    COLUMNS_CONTAINER = 'columns container'
    LEFT_SECTION = 'left section'
    RIGHT_SECTION = 'right section'
    RIGHT_SIDE_BAR = 'right side bar'
    DATE_FILTER = 'data filter'
    SEVERITY_FILTER = 'severity filter'
    PROVINCE_FILTER = 'province filter'
    MAP_TYPE = 'map type'
    CHART_1 = 'chart 1'
    CHART_2 = 'chart 2'
    SELECTED_ANALYSIS = 'selected analysis'
    

# Configurações iniciais
st.set_page_config(page_title="Dashboard Acidentes",
                   page_icon='🚗',
                   layout='wide',
                   initial_sidebar_state='expanded')

place_sidebar_return_button()

st.markdown(
    read_css('style/dash_acidentes.css')
        .parse_cls_attr(Keys, Keys=lambda k: k.class_ref),
    unsafe_allow_html=True
)

ss = st.session_state

# Divisões
_prop = [1.25, 3, 1.5]

side_bar = st.sidebar

_main_section, right_side_bar = st.columns([sum(_prop[:2]), _prop[2]])
right_side_bar = right_side_bar.container(key=Keys.RIGHT_SIDE_BAR, border=True)

options_panel = HorizontalSection(_main_section.container(key=Keys.OPTIONS_PANEL), vertical_alignment='center')

_col_container = _main_section.container(key=Keys.COLUMNS_CONTAINER)
_cols = _col_container.columns([_prop[0], _prop[1]])
_cols_keys = [Keys.LEFT_SECTION, Keys.RIGHT_SECTION]
left_section, right_section = [col.container(key=key, border=True)
                                            for key, col in zip(_cols_keys, _cols)]

# Corpo

with side_bar:
    st.title("Acompanhamento de acidentes em rodovias federais")
    st.selectbox("Ano", options=get_args(_Date), key=Keys.DATE_FILTER)
    st.selectbox("Gravidade do Evento", options=get_args(_Severity), key=Keys.SEVERITY_FILTER)
    st.selectbox("Estado", options=get_args(_Province), key=Keys.PROVINCE_FILTER)
    st.divider()
    st.markdown('### Configurações da Análise')
    st.multiselect(
        'Analises Principais',
        options=available_charts.keys(),
        default=['Eventos e Feridos por Mês', 'Eventos por Intervalo'],
        max_selections=2,
        key=Keys.SELECTED_ANALYSIS
    )
    st.selectbox("Tipo de Mapa", options=['Calor', 'Scatter'], key=Keys.MAP_TYPE)

# Retrieving and filtering data
df = get_data()
df = filter_by_date(df, ss[Keys.DATE_FILTER])
df = filter_by_severity(df, ss[Keys.SEVERITY_FILTER])
df = filter_by_province(df, ss[Keys.PROVINCE_FILTER])

# Measures
total_eventos = int(df['id'].count())
total_eventos_3o = df.loc[df['mortos'] > 3]['id'].count()
total_feridos = df['feridos'].sum()
total_obitos = df['mortos'].sum()

with options_panel as ctx:
    ctx.add_widget(st.metric, "Eventos", fn(total_eventos), "", help='Quantidade total de acidentes', weight=2)
    ctx.add_widget(st.metric, "Eventos 3+ óbitos", fn(total_eventos_3o), "", help='Total de acidentes com mais de 3 óbitos')
    ctx.add_widget(st.metric, "Total Feridos", fn(total_feridos), "", help='Total de feridos dentre o total de acidentes')
    ctx.add_widget(st.metric, "Total Óbitos", fn(total_obitos), "", help='Total de óbitos dentre o total de acidentes')

with left_section:
    st.markdown('### Top Rodovias/Estados')

    br_summ = df[['br', 'id']].groupby('br').count().sort_values('id', ascending=False)
    uf_summ = df[['uf', 'id']].groupby('uf').count().sort_values('id', ascending=False)
    st.dataframe(
        br_summ,
        use_container_width=True,
        height=387,
        column_config={
            'br': st.column_config.TextColumn(
                label='Rodovia',
            ),
            'id': st.column_config.ProgressColumn(
                label='Eventos',
                format='%f',
                min_value=0,
                max_value=max(br_summ['id']),
                width='small'
            )
        },
    )

    st.dataframe(
        uf_summ,
        use_container_width=True,
        height=177,
        column_config={
            'uf': st.column_config.TextColumn(
                label='Estado',
            ),
            'id': st.column_config.ProgressColumn(
                label='Eventos',
                format='%f',
                min_value=0,
                max_value=max(br_summ['id']),
                width='small'
            )
        },
    )
    
with right_section:
    analysis: list = ss[Keys.SELECTED_ANALYSIS]
    chart_1 = pop_or_none(analysis)
    chart_2 = pop_or_none(analysis)
    if chart_1 is not None:
        st.container(key=Keys.CHART_1, border=True).plotly_chart(
            available_charts[chart_1](df),
            use_container_width=True
        )

    if chart_2 is not None:
        st.container(key=Keys.CHART_2, border=True).plotly_chart(
            available_charts[chart_2](df),
            use_container_width=True
        )
        d = pd.Timedelta(minutes=15)
        


    
with right_side_bar:
    opt = {'Calor': eventos_p_lat_lon_mapbox, 'Scatter': eventos_p_municipio_scatter_mapbox}

    st.markdown('### Concentração de Eventos')

    mapa_fig = opt[ss[Keys.MAP_TYPE]](df)
    st.plotly_chart(mapa_fig)
    