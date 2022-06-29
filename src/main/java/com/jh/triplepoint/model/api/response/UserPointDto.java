package com.jh.triplepoint.model.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserPointDto {
    private final String userId;
    private final Long point;
}
