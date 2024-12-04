import streamlit as st
from app.resources import read_css
from app.navigation import place_sidebar_return_button

st.set_page_config(
    page_title='analise_2'
)

st.markdown(
    read_css('style/placeholder_page.css'),
    unsafe_allow_html=True
)

place_sidebar_return_button()

st.warning('_placeholder_')