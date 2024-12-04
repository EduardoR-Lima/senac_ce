import streamlit as st
from typing import Final
import os

_current_dir = os.path.dirname(__file__)

def _relative(*paths: str) -> str:
    return os.path.join(_current_dir, *paths)

# Constants
LOADING_PAGE: Final = st.Page(
    _relative('loading_page.py'),
    default=True
)

MAIN_PAGE: Final = st.Page(
    _relative('pagina_inicial.py')
)

ANALISE_PAGES: Final = [
    st.Page(_relative('analises', path))
    for path in os.listdir(_relative('analises'))
    if os.path.isfile(_relative('analises', path))
]


# Functions
def place_sidebar_return_button():
    with st.sidebar:
        st.page_link(MAIN_PAGE, label='Voltar')
        st.divider()

__all__ = [
    'MAIN_PAGE',
    'LOADING_PAGE',
    'ANALISE_PAGES',
    'place_sidebar_return_button'
]