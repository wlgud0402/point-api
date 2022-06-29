package com.jh.triplepoint.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "review_photos")
@Entity
public class ReviewPhoto extends TimeBaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_uuid")
    private Review review;

    @Column(name = "photo_uuid")
    private String photoUuid;

    public ReviewPhoto(Review review, String photoUuid) {
        this.review = review;
        this.photoUuid = photoUuid;
    }
}
