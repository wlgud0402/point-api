package com.jh.triplepoint.service;

import com.jh.triplepoint.constant.PointHistoryType;
import com.jh.triplepoint.exception.RequiredEntityNotFoundException;
import com.jh.triplepoint.model.Place;
import com.jh.triplepoint.model.PointHistory;
import com.jh.triplepoint.model.Review;
import com.jh.triplepoint.model.User;
import com.jh.triplepoint.model.api.request.ReviewRequest;
import com.jh.triplepoint.repository.PlaceRepository;
import com.jh.triplepoint.repository.PointHistoryRepository;
import com.jh.triplepoint.repository.ReviewRepository;
import com.jh.triplepoint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {
    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createReview(ReviewRequest reviewRequest) {
        Place place = placeRepository.findByIdWithLock(reviewRequest.getPlaceId())
                .orElseThrow(RequiredEntityNotFoundException::new);

        User user = userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(RequiredEntityNotFoundException::new);

        Review review = Review.builder()
                .uuid(reviewRequest.getReviewId())
                .content(reviewRequest.getContent())
                .photoIds(reviewRequest.getAttachedPhotoIds())
                .place(place)
                .build();
        reviewRepository.save(review);

        PointHistory pointHistory = PointHistory.builder()
                .point(review.getAwardedPoint())
                .reviewUuid(review.getUuid())
                .type(PointHistoryType.CREATE_REVIEW)
                .build();
        pointHistoryRepository.save(pointHistory);

        user.plusPoint(review.getAwardedPoint());
        place.increaseReviewCount();
    }

    @Transactional
    public void deleteReview(ReviewRequest reviewRequest) {
        Place place = placeRepository.findByIdWithLock(reviewRequest.getPlaceId())
                .orElseThrow(RequiredEntityNotFoundException::new);

        User user = userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(RequiredEntityNotFoundException::new);

        Review review = reviewRepository.findById(reviewRequest.getReviewId())
                .orElseThrow(RequiredEntityNotFoundException::new);

        PointHistory pointHistory = PointHistory.builder()
                .point(review.getAwardedPoint() * -1)
                .reviewUuid(review.getUuid())
                .type(PointHistoryType.DELETE_REVIEW)
                .build();
        pointHistoryRepository.save(pointHistory);

        review.delete();
        user.minusPoint(review.getAwardedPoint());
        place.decreaseReviewCount();
    }

    @Transactional
    public void updateReview(ReviewRequest reviewRequest) {
        User user = userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(RequiredEntityNotFoundException::new);

        Review review = reviewRepository.findById(reviewRequest.getReviewId())
                .orElseThrow(RequiredEntityNotFoundException::new);
        int prevAwardedPoint = review.getAwardedPoint();

        review.changeContent(reviewRequest.getContent());
        review.changePhotoIds(reviewRequest.getAttachedPhotoIds());

        if (prevAwardedPoint != review.getAwardedPoint()) {
            PointHistory pointHistory;
            int additionalAwardedPoint = review.getAwardedPoint() - prevAwardedPoint;
            if (additionalAwardedPoint > 0) {
                pointHistory = PointHistory.builder()
                        .point(additionalAwardedPoint)
                        .reviewUuid(review.getUuid())
                        .type(PointHistoryType.ADD_CONTENTS)
                        .build();
                pointHistoryRepository.save(pointHistory);
            } else {
                pointHistory = PointHistory.builder()
                        .point(additionalAwardedPoint)
                        .reviewUuid(review.getUuid())
                        .type(PointHistoryType.DELETE_CONTENTS)
                        .build();
                pointHistoryRepository.save(pointHistory);
            }
            user.plusPoint(additionalAwardedPoint);
        }
    }
}
