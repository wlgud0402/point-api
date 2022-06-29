DROP
DATABASE IF EXISTS `triplepoint`;

CREATE
DATABASE `triplepoint`;

USE
`triplepoint`;

create table users
(
    uuid        varchar(255) not null COMMENT '유저 uuid',
    total_point bigint COMMENT '포인트 총합',
    created_at  datetime(6) COMMENT '유저 생성시간',
    updated_at  datetime(6) COMMENT '유저 업데이트시간',
    primary key (uuid)
) engine=InnoDB COMMENT '유저';

create table places
(
    uuid         varchar(255) not null COMMENT '장소 uuid',
    name         varchar(255) COMMENT '장소명',
    review_count int(11) NOT NULL DEFAULT '0' COMMENT '리뷰 개수',
    created_at   datetime(6) COMMENT '장소 생성시간',
    updated_at   datetime(6) COMMENT '장소 수정시간',
    primary key (uuid)
) engine=InnoDB COMMENT '장소';

create table reviews
(
    uuid          varchar(255) not null COMMENT '리뷰 uuid',
    awarded_point integer COMMENT '획득한 포인트',
    content       varchar(255) COMMENT '리뷰 내용',
    place_id      varchar(255) COMMENT '장소 id',
    created_at    datetime(6) COMMENT '리뷰 생성시간',
    updated_at    datetime(6) COMMENT '리뷰 수정시간',
    deleted_at    datetime(6) COMMENT '리뷰 삭제시간',
    primary key (uuid)
) engine=InnoDB COMMENT '리뷰';

create table review_photos
(
    id          bigint not null auto_increment COMMENT '리뷰 사진 id',
    photo_uuid  varchar(255) COMMENT '사진 uuid',
    review_uuid varchar(255) COMMENT '리뷰 uuid',
    created_at  datetime(6) COMMENT '사진 생성시간',
    updated_at  datetime(6) COMMENT '사진 수정시간',
    primary key (id)
) engine=InnoDB COMMENT '리뷰 사진';

create table point_historys
(
    id          bigint not null auto_increment COMMENT '포인트 내역 id',
    point       integer COMMENT '포인트',
    review_uuid varchar(255) COMMENT '리뷰 uuid',
    type        varchar(255) COMMENT '리뷰 액션 type',
    created_at  datetime(6) COMMENT '생성 시간',
    updated_at  datetime(6) COMMENT '수정 시간',
    primary key (id)
) engine=InnoDB COMMENT '포인트 히스토리';

insert into users(uuid, created_at, updated_at, total_point)
values ("한글", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
insert into users(uuid, created_at, updated_at, total_point)
values ("3ede0ef2-92b7-4817-a5f3-0c575361f746", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
insert into places(uuid, created_at, updated_at, name)
values ("2e4baf1c-5acb-4efb-a1af-eddada31b00f", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, "good-cafe");