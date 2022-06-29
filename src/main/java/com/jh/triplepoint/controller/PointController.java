package com.jh.triplepoint.controller;

import com.jh.triplepoint.model.User;
import com.jh.triplepoint.model.api.response.UserPointDto;
import com.jh.triplepoint.model.api.response.UsersPointResponse;
import com.jh.triplepoint.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PointController {
    private final PointService pointService;

    @GetMapping("/api/points")
    public UsersPointResponse getUsersPoint() {
        List<User> users = pointService.getUsers();
        return new UsersPointResponse(users);
    }

    @GetMapping("/api/points/{uuid}")
    public UserPointDto getUserPoint(@PathVariable String uuid) {
        long point = pointService.getPointByUserUuid(uuid);
        return new UserPointDto(uuid, point);
    }
}
