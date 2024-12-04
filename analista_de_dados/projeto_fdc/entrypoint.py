import streamlit as st
from app.navigation import MAIN_PAGE, LOADING_PAGE, ANALISE_PAGES

_pages = [LOADING_PAGE, MAIN_PAGE, *ANALISE_PAGES]

if 'last_page' not in st.session_state:
    st.session_state.last_page = 0

main_page = st.navigation(_pages, position='hidden')
main_page.run()