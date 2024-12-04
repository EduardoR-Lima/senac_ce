import streamlit as st
import pandas as pd
import typing as tp
from app.navigation import MAIN_PAGE
from app.resources import get_resource, is_loaded, pre_load_data
import time
import os

# Data functions

def _load_data(path: str, factory: tp.Callable[..., tp.Any], *args, **kwargs) -> None:
    file_name = os.path.basename(path)
    panel = st.empty().warning(f"'{file_name}'")
    if not is_loaded(path):
        pre_load_data(
            path,
            factory(get_resource(path), *args, **kwargs)
        )   
    panel.success(f"'{file_name}' carregado com sucesso")



st.set_page_config(
    page_title='Carregando dados',
    layout='wide'
)



left, mid, right = st.columns([1, 3, 1])

with mid:
    progress_bar = st.progress(0.0)
    with st.status('Carregando dados...', expanded=True) as status:
        _load_data('data/BD-PRF-SUDESTE.xlsx', pd.read_excel)
        progress_bar.progress(0.50)
        _load_data('data/municipios.json', pd.read_json, encoding='utf-8-sig')
        progress_bar.progress(0.90)
        _load_data('data/uf.csv', pd.read_csv, sep=';', encoding='utf-8')
        progress_bar.progress(1.0)
        status.update(label='Dados carregados com sucesso', state='complete')

time.sleep(1)
st.switch_page(MAIN_PAGE)

        
        
            

        


