from streamlit.delta_generator import DeltaGenerator
import streamlit as st
from enum import Enum
import typing as tp

# Types =========================================================

_Key: tp.TypeAlias = tp.Union[str, int]
T = tp.TypeVar("T")

class RefMixin():
    __slots__ = ('_key', '_class_key')

    def __init__(self, key: str, init: bool = False) -> None:
        if init and (key not in st.session_state):
            st.session_state[key] = None
        self._key = key
        self._class_key = 'st-key-' + key.replace(' ', '-')

    @property
    def class_ref(self) -> str:
        return self._class_key

class RefEnum(RefMixin, Enum):
    
    def __str__(self) -> str:
        return self._key
    
    def __repr__(self) -> str:
        return repr(self._key)
    
class WidgetSpecs:
    __slots__ = ('factory',
                 'f_args',
                 'f_kwargs',
                 'weight',
                 '_factory_name')
    
    def __init__(self, factory: tp.Callable[..., T], weight: float,
                 *args, **kwargs):
        self.factory = factory
        self.f_args = args
        self.f_kwargs = kwargs
        self.weight = weight
        self._factory_name = factory.__qualname__

    def build(self) -> T:
        return self.factory(*self.f_args, **self.f_kwargs)

    def __repr__(self) -> str:
        return ('WidgetSpecs('
                f'factory={self.factory!r}, '
                f'args={self.f_args!r}, '
                f'kwargs={self.f_kwargs!r}, '
                f'weight={self.weight!r}), '
                f'name={self._factory_name!r})')

class HorizontalSection:
    def __init__(self, container: tp.Optional[DeltaGenerator] = None, *,
                 gap: tp.Literal["small", "medium", "large"] = "small",
                 vertical_alignment: tp.Literal["top", "center", "bottom"] = "top") -> None:
        self._container = container or st._main
        self._gap = gap
        self._vertical_alignment = vertical_alignment
        self._widgets_counter = 0
        self._widgets: dict[str, WidgetSpecs] = {}
        self._result_map: dict[_Key, tp.Any] = {}


    def add_widget(self, factory: tp.Callable[..., tp.Any], *args, weight: float = 1, key: tp.Optional[_Key] = None, **kwargs) -> None:
        if key is None:
            key = f'{self._widgets_counter}_['+ factory.__qualname__ + ']'
        else:
            kwargs.update(dict(
                key=key
            ))

        self._widgets[key] = WidgetSpecs(factory, weight,
                                         *args, **kwargs)
        self._widgets_counter += 1

    def __enter__(self) -> tp.Self:
        self._container.__enter__()
        return self
    

    def __exit__(self, exc_type, exc_val, exc_tb):
        n = len(self._widgets)
        if n == 0:
            return

        cols = self._container.columns([wid.weight for wid in self._widgets.values()],
                                       gap=self._gap, vertical_alignment=self._vertical_alignment)

        for key, col in zip(self._widgets.keys(), cols):
            with col:
                self._result_map[key] = self._widgets[key].build()


    def __getitem__(self, key_or_index) -> tp.Any:
        if key_or_index in self._result_map.keys():
            return self._result_map[key_or_index]
        elif isinstance(key_or_index, int):
            return list(self._result_map.values())[key_or_index]


    def list_all(self, factory: tp.Optional[tp.Callable[..., tp.Any]] = None):
        if factory is None:
            return list(self)
        return [self._result_map[k] for k, v in self._widgets.items() if v._factory_name == factory.__qualname__]
