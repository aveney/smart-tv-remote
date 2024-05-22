package com.TelevisionRemote.SmartTelevisionRemote.controller;

import com.TelevisionRemote.SmartTelevisionRemote.entity.Apps;
import com.TelevisionRemote.SmartTelevisionRemote.repository.AppsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/apps")
public class AppsController {

    @Autowired
    private RestTemplate restTemplate;

    private AppsRepository appsRepository;

    public AppsController(AppsRepository appsRepository) {
        this.appsRepository = appsRepository;
    }

    private final Logger log = LoggerFactory.getLogger(AppsController.class);

    @GetMapping(path = "/get-apps")
    public Apps getXMLOutput() {

        ResponseEntity<Apps> responseEntity
                = this.restTemplate.getForEntity("http://10.0.0.229:8060/query/apps",
                Apps.class);

        Apps apps = responseEntity.getBody();

        log.info(apps.toString());
        return appsRepository.save(apps);
    }

    //Get apps list for tv
    // Set apps list for tv

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
