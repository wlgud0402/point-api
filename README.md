# README
# 포인트 지급, 조회 API

### 프로젝트
- Spring Boot를 사용하여 개발되었습니다.
- DB - MySQL (도커사용)


### 실행방법
1. docker-compose up
    - 도커를 사용해 mysql을 띄웁니다.
    - mysql의 경우 test/resources/schema/triplepoint_ddl.sql을 통해 기본 테이블을 생성합니다.
2. Spring Boot 서버를 실행합니다.

### 테이블 DDL

```sql
create table users
(
   uuid        varchar(255) not null COMMENT '유저 uuid',
   total_point bigint COMMENT '포인트 총합',
   created_at  datetime(6) COMMENT '유저 생성시간',
   updated_at  datetime(6) COMMENT '유저 업데이트시간',
   primary key (uuid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '유저';

create table places
(
   uuid         varchar(255) not null COMMENT '장소 uuid',
   name         varchar(255) COMMENT '장소명',
   review_count int(11) NOT NULL DEFAULT '0' COMMENT '리뷰 개수',
   created_at   datetime(6) COMMENT '장소 생성시간',
   updated_at   datetime(6) COMMENT '장소 수정시간',
   primary key (uuid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '장소';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '리뷰';

create table review_photos
(
   id          bigint not null auto_increment COMMENT '리뷰 사진 id',
   photo_uuid  varchar(255) COMMENT '사진 uuid',
   review_uuid varchar(255) COMMENT '리뷰 uuid',
   created_at  datetime(6) COMMENT '사진 생성시간',
   updated_at  datetime(6) COMMENT '사진 수정시간',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '리뷰 사진';

create table point_historys
(
   id          bigint not null auto_increment COMMENT '포인트 내역 id',
   point       integer COMMENT '포인트',
   review_uuid varchar(255) COMMENT '리뷰 uuid',
   type        varchar(255) COMMENT '리뷰 액션 type',
   created_at  datetime(6) COMMENT '생성 시간',
   updated_at  datetime(6) COMMENT '수정 시간',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '포인트 히스토리';

```
