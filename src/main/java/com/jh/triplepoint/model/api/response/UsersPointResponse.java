package com.jh.triplepoint.model.api.response;

import com.jh.triplepoint.model.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UsersPointResponse {
    private final List<UserPointDto> items;

    public UsersPointResponse(List<User> users) {
        items = users
                .stream()
                .map((user) -> new UserPointDto(user.getUuid(), user.getTotalPoint()))
                .collect(Collectors.toList());
    }
}
