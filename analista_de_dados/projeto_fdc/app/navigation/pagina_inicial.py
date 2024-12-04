import streamlit as st
from streamlit.navigation.page import StreamlitPage
from app.resources import read_css, get_resource
from app.navigation import ANALISE_PAGES
import textwrap

st.set_page_config(
    page_title="Página Inicial",
    page_icon="💻",
    layout='wide'
)

st.markdown(
    read_css(r'style/main_page.css'),
    unsafe_allow_html=True
)

def render_members(members_names: list) -> None:
    for name in members_names:
        st.image(get_resource('img/profile-circle.svg'), caption=name, width=100)

class DashInfo:
    __slots__ = ('_pill_name',
                 '_page',
                 '_members',
                 '_markdown',
                 '_dialog_markdown')
    
    def __init__(self, pill_name: str, page: StreamlitPage,
                 members: list[str] = [], markdown: str | None = None,
                 dialog_markdown: str | None = None) -> None:
        self._pill_name = pill_name
        self._page = page
        self._members = members
        self._markdown = textwrap.dedent(markdown) if markdown is not None else ''
        self._dialog_markdown = markdown if dialog_markdown is None else dialog_markdown

    @property
    def markdown(self) -> str:
        markdown_title = textwrap.dedent(
            f"""\
            ## {self.title}
            #####"""
        )
        markdown = markdown_title + self._markdown
        return markdown
    
    @property
    def dialog_markdown(self) -> str:
        return self._dialog_markdown
    
    @property
    def pill_name(self) -> str:
        return self._pill_name
    
    @property
    def members(self) -> list[str]:
        return self._members
    
    @property
    def title(self) -> str:
        return self._page.title
    
    @property
    def page(self) -> StreamlitPage:
        return self._page

text_mid = '''
# Projeto Dashboards
***
## Criado através da ferramenta 
# :streamlit: :red[Streamlit]
***
'''

st.session_state['bom dia'] = 12

dash_infos = [
    DashInfo(
        page=ANALISE_PAGES[0],
        pill_name='Dash 1',
        members=['Aleksandr Cleofas', 'Alysson Maciel', 'Eduardo Ribeiro', 'Pedro Henrique Vitorino'],
        markdown=(
            """
            ### Descrição:
            > Acompanhamento dos acidentes de trânsito em vias federais nos anos
            de 2018 a 2020
            ###
            ### Dados:
             - Foram obtidos em sala, durante atividades guiadas pelo
             professor
             - São referentes a região Sudeste do país
            """
        )
    ),
    DashInfo(
        page=ANALISE_PAGES[1],
        pill_name='Dash 2',
        members=['Gabryel Aarão', 'Lucas Galeno', 'Pablo Coitinho'],
        markdown=(
            """
            ### Descrição:
            > Em respeito à autoria do código desenvolvido pela equipe,
            a análise original foi removida.
            ###
            ### Dados:
             - [observações]
            """
        )
    ),
    DashInfo(
        page=ANALISE_PAGES[2],
        pill_name='Dash 3',
        members=['Bruno dos Santos', 'Lídia Carolina', 'Lourival Ramos', 'Pollyanna Moreira'],
        markdown=(
            """
            ### Descrição:
            > Em respeito à autoria do código desenvolvido pela equipe,
            a análise original foi removida.
            ###
            ### Dados:
             - [observações]
            """
        )
    )
]



_cols = st.columns([1, 3, 1], gap='large')
_keys = ['left', 'mid', 'right']


left, mid, right = [c.container(key=k) for k, c in zip(_keys, _cols)]
mid_error_section = mid.container()
mid_top = mid.container(key='mtop') #mid center 1

dash_selection_panel = mid.container(key='dash selection')


with mid_top:
    st.markdown(text_mid)

with dash_selection_panel:
    st.markdown("### Análises:")
    pills_value = st.container(key='pills').pills('Titulo',
                                                  selection_mode='single',
                                                  options=range(len(dash_infos)),
                                                  format_func=lambda i: dash_infos[i].title,
                                                  default=st.session_state.last_page,
                                                  )

current_dash = None if pills_value is None else dash_infos[pills_value]

with mid_error_section:
    if pills_value is None:
        st.warning("Selecione uma análise para poder obter mais informações")


if pills_value is not None:
    with mid:
        if st.button("Seguir para a Análise ->", use_container_width=True, help=f"Seguir para '{current_dash.title}'"):
            st.session_state.last_page = pills_value
            st.switch_page(current_dash.page)

    dash_info_panel = left.container(key='dash info', border=True, height=1)

    with dash_info_panel:
        st.markdown(current_dash.markdown)

    with left:
        if st.button('Visualizar', use_container_width=True, help='Visualizar descrição em janela suspensa'):
            st.dialog(current_dash.title, width='large')(lambda: st.markdown(current_dash.dialog_markdown))()
        

    with right:
        # st.image(r'imgs/senac-logo.svg')
        st.title(":blue[Equipe:]")
        render_members(current_dash.members)

