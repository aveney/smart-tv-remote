package com.TelevisionRemote.SmartTelevisionRemote.controller;

import com.TelevisionRemote.SmartTelevisionRemote.entity.Apps;
import com.TelevisionRemote.SmartTelevisionRemote.entity.Television;
import com.TelevisionRemote.SmartTelevisionRemote.repository.AppsRepository;
import com.TelevisionRemote.SmartTelevisionRemote.service.AppsService;
import com.TelevisionRemote.SmartTelevisionRemote.service.TelevisionService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.util.List;

@RestController
@RequestMapping("/apps")
public class AppsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TelevisionService televisionService;

    @Autowired
    private AppsService appsService;

    private AppsRepository appsRepository;

    public AppsController(TelevisionService televisionService, AppsService appsService, AppsRepository appsRepository) {
        this.televisionService = televisionService;
        this.appsService = appsService;
        this.appsRepository = appsRepository;
    }

    private final Logger log = LoggerFactory.getLogger(AppsController.class);

    @GetMapping(path = "/get-apps")
    public Apps getXMLOutput() {

        ResponseEntity<Apps> responseEntity
                = this.restTemplate.getForEntity("http://10.0.0.229:8060/query/apps",
                Apps.class);

        Apps apps = responseEntity.getBody();

        //Apps apps1 = (Apps) xmlToObject(apps, Apps.class);
        System.out.println(apps);
        //System.out.println(apps1.toString());
        return appsRepository.save(apps);
    }

    public static Object xmlToObject(String xml, Class<?> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Error while converting xml to object", e);
        }
    }

    // Get apps list for tv
    @PostMapping(path = "/get-apps/{name}")
    public ResponseEntity<Apps> getApps(@PathVariable String name) {
        return appsService.getApps(name);
    }

    // Set apps list for tv
    @PutMapping(path = "/set-apps/{name}")
    public ResponseEntity<Television> setApps(@PathVariable String name) {
        return appsService.setApps(name);
    }

    @GetMapping(path = "/get-all-apps")
    public List<Apps> getAppsList() {
        return appsRepository.findAll();
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
