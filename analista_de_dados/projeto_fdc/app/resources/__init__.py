from utils.html import StyleTag
from typing import TypeAlias, Union, Any
import streamlit as st
import os

_StrPath: TypeAlias = Union[str, os.PathLike[str]]

_current_dir = os.path.dirname(__file__)


# Functions
def get_resource(path: str) -> _StrPath:
    return os.path.join(_current_dir, path)

def _get_resource_key(path: str) -> int:
    return f'path-key:{get_resource(path)}'

def is_loaded(path: str) -> bool:
    return _get_resource_key(path) in st.session_state

def pre_load_data(path: str, data) -> None:
    key = _get_resource_key(path)
    st.session_state[key] = data

def get_loaded_data(path: str) -> Any:
    key = _get_resource_key(path)
    return st.session_state[key]

def read_css(path: str) -> StyleTag:
    with open(get_resource(path), 'r') as css_file:
        return StyleTag(css_file.read())


__all__ = [
    'get_resource',
    'pre_load_data',
    'get_loaded_data',
    'read_css'
]