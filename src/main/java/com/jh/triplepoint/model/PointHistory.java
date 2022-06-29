package com.jh.triplepoint.model;

import com.jh.triplepoint.constant.PointHistoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "point_historys")
@Entity
public class PointHistory extends TimeBaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Integer point;

    @Column(name = "review_uuid")
    private String reviewUuid;

    @Enumerated(EnumType.STRING)
    private PointHistoryType type;
}
