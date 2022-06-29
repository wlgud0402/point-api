package com.jh.triplepoint.service;

import com.jh.triplepoint.exception.RequiredEntityNotFoundException;
import com.jh.triplepoint.model.User;
import com.jh.triplepoint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PointService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Long getPointByUserUuid(String userUuid) {
        User user = userRepository.findById(userUuid)
                .orElseThrow(RequiredEntityNotFoundException::new);
        return user.getTotalPoint();
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
