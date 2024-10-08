package com.TelevisionRemote.SmartTelevisionRemote.application;

import com.TelevisionRemote.SmartTelevisionRemote.constants.TelevisionConstants;
import com.TelevisionRemote.SmartTelevisionRemote.entity.Television;
import com.TelevisionRemote.SmartTelevisionRemote.repository.TelevisionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);

    @Autowired
    TelevisionRepository televisionRepository;

    @Override
    public void run(String... args) throws Exception {

        Television tv1 = new Television("AdriaansTv", "10.0.0.34", TelevisionConstants.ROKU_TV);
        Television tv2 = new Television("AshTv", "10.0.0.50", TelevisionConstants.ANDROID_TV);
        log.info(televisionRepository.save(tv1).toString());
        log.info(televisionRepository.save(tv2).toString());

    }
}
