package com.jh.triplepoint.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "places")
@Entity
public class Place extends TimeBaseEntity {
    @Id
    private String uuid;

    private String name;

    @Column(name = "review_count")
    private Integer reviewCount;

    public void increaseReviewCount() {
        this.reviewCount += 1;
    }

    public void decreaseReviewCount() {
        this.reviewCount -= 1;
    }
}
