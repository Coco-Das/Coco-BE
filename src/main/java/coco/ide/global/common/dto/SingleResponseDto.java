package coco.ide.global.common.dto;

import lombok.Getter;

@Getter
public class SingleResponseDto<T> {
    private final T data;
    public SingleResponseDto(T data) {
        this.data = data;
    }
}
