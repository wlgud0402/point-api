package com.jh.triplepoint.controller;

import com.jh.triplepoint.constant.ReviewAction;
import com.jh.triplepoint.model.api.request.ReviewRequest;
import com.jh.triplepoint.model.api.response.OkResponse;
import com.jh.triplepoint.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/events")
    public OkResponse events(@RequestBody ReviewRequest reviewRequest) {
        ReviewAction reviewAction = ReviewAction.of(reviewRequest.getAction());
        switch (reviewAction) {
            case ADD:
                reviewService.createReview(reviewRequest);
                break;
            case MOD:
                reviewService.updateReview(reviewRequest);
                break;
            case DELETE:
                reviewService.deleteReview(reviewRequest);
                break;
        }

        return new OkResponse();
    }
}
