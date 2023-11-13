package com.TelevisionRemote.SmartTelevisionRemote.repository;

import com.TelevisionRemote.SmartTelevisionRemote.entity.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    Optional<Television> findByTelevisionName(String televisionName);
    Optional<Television> findByIpAddress(String ipAddress);
}
