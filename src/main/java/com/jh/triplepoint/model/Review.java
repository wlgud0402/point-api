package com.jh.triplepoint.model;

import com.jh.triplepoint.util.TimeUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Table(name = "reviews")
@Entity
public class Review extends TimeBaseEntity {
    @Id
    private String uuid;

    private String content;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhoto> photos = new ArrayList<>();

    @Column(name = "awarded_point")
    private Integer awardedPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @Builder
    public Review(String uuid, String content, List<String> photoIds, Place place) {
        this.uuid = uuid;
        this.content = content;
        photoIds.forEach(photoId -> {
            ReviewPhoto reviewPhoto = new ReviewPhoto(this, photoId);
            this.photos.add(reviewPhoto);
        });
        this.place = place;
        boolean hasBonus = this.place.getReviewCount() == 0;
        calculateAwardedPoint(hasBonus);
    }

    public void delete() {
        this.photos.clear();
        this.deletedAt = TimeUtil.nowWithSecond();
    }

    public void changeContent(String content) {
        if (!this.content.equals(content)) {
            this.content = content;
            boolean hasBonus = this.place.getReviewCount() == 1;
            calculateAwardedPoint(hasBonus);
        }
    }

    public void changePhotoIds(List<String> photoIds) {
        Map<String, ReviewPhoto> photoMap = new HashMap<>();
        photos.forEach(reviewPhoto -> {
            photoMap.put(reviewPhoto.getPhotoUuid(), reviewPhoto);
        });
        this.photos.clear();
        photoIds.forEach(photoId -> {
            ReviewPhoto photo = photoMap.getOrDefault(photoId, new ReviewPhoto(this, photoId));
            this.photos.add(photo);
        });
        boolean hasBonus = this.place.getReviewCount() == 1;
        calculateAwardedPoint(hasBonus);
    }

    public void calculateAwardedPoint(boolean hasBonus) {
        this.awardedPoint = 0;
        if (hasBonus) {
            this.awardedPoint += 1;
        }
        if (StringUtils.hasText(this.content)) {
            this.awardedPoint += 1;
        }
        if (photos.size() > 0) {
            this.awardedPoint += 1;
        }
    }
}
