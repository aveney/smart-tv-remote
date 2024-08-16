package com.TelevisionRemote.SmartTelevisionRemote.service;

import com.TelevisionRemote.SmartTelevisionRemote.entity.App;
import com.TelevisionRemote.SmartTelevisionRemote.entity.Apps;
import com.TelevisionRemote.SmartTelevisionRemote.entity.Television;
import com.TelevisionRemote.SmartTelevisionRemote.exceptions.televisionExceptions.TelevisionNotFoundException;
import com.TelevisionRemote.SmartTelevisionRemote.repository.AppRepository;
import com.TelevisionRemote.SmartTelevisionRemote.repository.AppsRepository;
import com.TelevisionRemote.SmartTelevisionRemote.repository.TelevisionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
@Slf4j
public class AppsService {

    private AppsRepository appsRepository;
    private AppRepository appRepository;
    private TelevisionRepository televisionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public AppsService(AppsRepository appsRepository, AppRepository appRepository, TelevisionRepository televisionRepository) {
        this.appsRepository = appsRepository;
        this.appRepository = appRepository;
        this.televisionRepository = televisionRepository;
    }

    // Get apps list for tv
    public ResponseEntity<Apps> getApps(String name) {
        Television tele = televisionRepository.findByTelevisionName(name).orElseThrow(() -> new TelevisionNotFoundException());
        log.info(String.format("Requesting apps for %s via: http://%s:8060/query/apps", name, tele.getIpAddress()));
        ResponseEntity<Apps> responseEntity = this.restTemplate.getForEntity("http://" + tele.getIpAddress() + ":8060/query/apps", Apps.class);
        Apps apps = responseEntity.getBody();
        System.out.println(apps.toString());
        for (App app : apps.getApp()) {
            appRepository.save(app);
        }
        appsRepository.save(apps);

        return ResponseEntity.ok(apps);
    }

    // Set apps list for tv
    public ResponseEntity<Television> setApps(String name) {
        Television tele = televisionRepository.findByTelevisionName(name).orElseThrow(() -> new TelevisionNotFoundException());
        List<Apps> appsList = tele.getApps();
        log.info(String.format("Setting apps for %s", name));

        // Call api to get apps
        ResponseEntity<Apps> responseEntity = this.restTemplate.getForEntity("http://" + tele.getIpAddress() + ":8060/query/apps", Apps.class);
        Apps installedApps = new Apps();
        // If there is already a list of apps
        if (appsList.size() == 1) {
            // Override the current list of apps with newer version of list
            installedApps = tele.getApps().get(0);
            //installedApps.setApp(responseEntity.getBody().getApp());
        } else {
            log.info("This happened");
            installedApps = responseEntity.getBody();
        }
        //installedApps.setTelevision(tele);
        //Apps apps = appsRepository.save(installedApps);

        appsList.add(installedApps);
        tele.setApps(appsList);
        Television newTele = televisionRepository.save(tele);
        return ResponseEntity.ok(newTele);
    }

    /*
    Apps that require methods to launch:
    - Hulu
    - Netflix
    - Disney+
    - Max
    - Prime video

    Algorithm:
    1. Get the television from the repository
    2. If tv's list contains [app], then launch app /launch/[app]
     */
}
