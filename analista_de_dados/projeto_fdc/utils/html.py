import typing as tp
import re

class _Tag:
    def __init__(self, tag_name: str):
        self._stag = f'<{tag_name}>'
        self._ctag = f'</{tag_name}>'

        tag_size = len(tag_name)
        self._stag_size = tag_size + 2
        self._ctag_size = tag_size + 3

    def _has_tag(self, text: str) -> bool:
        s_idx = self._stag_size
        c_idx = -self._ctag_size
        return self._stag in text[:s_idx] or self._ctag in text[c_idx:]
    
    def _remove_tag(self, text: str) -> str:
        return re.sub(fr'({self._stag}|{self._ctag})', '', text)

    def _apply_tag(self, text: str) -> str:
        if self._has_tag(text):
            return text

        return f'{self._stag}{text}{self._ctag}'

class StyleTag(_Tag):
    _CUSTOM_ATTR_PATTERN = r'PTY\[(?:{})=[\'\"]([\w_\.]*)[\'\"]\]'

    def __init__(self, body: str):
        super().__init__('style')
        self._body = self._remove_tag(body) if self._has_tag(body) else body

    @staticmethod
    def _cls_attr_repl(match: re.Match[bytes], obj_refs: dict[str, object]) -> str:
        cls_name, *cls_attrs = match.group(1).split('.')
    
        last_obj = obj_refs[cls_name]['class']
        map_f = obj_refs[cls_name]['map_func']
        for attr in cls_attrs:
            last_obj = getattr(last_obj, attr)
        
        if map_f is None:
            return '.' + str(last_obj)
        else:
            return '.' + map_f(last_obj)

    def parse_cls_attr(self, *classes: object, **map_funcs: dict[str, tp.Callable[..., str]]) -> tp.Self:
        cls_refs = {}
        for cls in classes:
            
            cls_refs[cls.__qualname__] = {
                'class': cls,
                'map_func': map_funcs.get(cls.__qualname__, None)
            }
        

        self._body = re.sub(
            self._CUSTOM_ATTR_PATTERN.format('cls_attr'),
            lambda match: self._cls_attr_repl(match, cls_refs),
            self._body
        )

        return self
    
    def build_tag(self):
        return self._apply_tag(self._body)

    def __str__(self) -> str:
        return self.build_tag()


if __name__ == "__main__":
    pass
            