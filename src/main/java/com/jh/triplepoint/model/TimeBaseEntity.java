package com.jh.triplepoint.model;

import com.jh.triplepoint.util.TimeUtil;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

@Getter
@MappedSuperclass
public abstract class TimeBaseEntity {
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @PrePersist
    public void perPersist() {
        this.createdAt = TimeUtil.nowWithSecond();
        this.updatedAt = TimeUtil.nowWithSecond();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = TimeUtil.nowWithSecond();
    }
}