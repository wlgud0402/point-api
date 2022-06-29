package com.jh.triplepoint.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "users")
@Entity
public class User extends TimeBaseEntity {
    @Id
    private String uuid;

    @Column(name = "total_point")
    private Long totalPoint;

    public void plusPoint(int point) {
        this.totalPoint += point;
    }

    public void minusPoint(int point) {
        this.totalPoint -= point;
    }
}
