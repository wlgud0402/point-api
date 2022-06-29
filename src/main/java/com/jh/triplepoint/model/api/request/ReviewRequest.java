package com.jh.triplepoint.model.api.request;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class ReviewRequest {
    private final List<String> attachedPhotoIds = new ArrayList<>();
    private String type;
    private String action;
    private String reviewId;
    private String content;
    private String userId;
    private String placeId;
}
