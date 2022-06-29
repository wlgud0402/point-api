package com.jh.triplepoint.repository;

import com.jh.triplepoint.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Place p WHERE p.uuid = :uuid")
    Optional<Place> findByIdWithLock(@Param("uuid") String uuid);
}
