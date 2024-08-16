package com.TelevisionRemote.SmartTelevisionRemote.repository;

import com.TelevisionRemote.SmartTelevisionRemote.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, String> {
}
