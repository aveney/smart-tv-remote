package com.TelevisionRemote.SmartTelevisionRemote.controller;

import com.TelevisionRemote.SmartTelevisionRemote.entity.Television;
import com.TelevisionRemote.SmartTelevisionRemote.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/tele")
public class TelevisionController {

    @Autowired
    private TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @PostMapping()
    public ResponseEntity<Television> createTelevision(@RequestBody Television television) {
        return televisionService.addTelevision(television);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> removeTelevision(@PathVariable Long id) {
        return televisionService.deleteTelevision(id);
    }

    @PutMapping("/edit-tv/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody Television television) throws URISyntaxException {
        return televisionService.editTelevision(id, television);
    }

    @GetMapping("/get-tvs")
    public ResponseEntity<List<Television>> getTelevisions() {
        return televisionService.findAllTelevisions();
    }

    @GetMapping("/get-tv/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable Long id) {
        return televisionService.findTelevisionById(id);
    }

    @GetMapping("/get-tv")
    public ResponseEntity<Television> getTelevisionByName(@RequestParam(value="televisionName") String televisionName) {
        return televisionService.findTelevisionByName(televisionName);
    }

    //@PostMapping("/key-selection/")
}
