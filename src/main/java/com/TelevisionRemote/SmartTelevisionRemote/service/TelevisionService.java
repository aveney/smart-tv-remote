package com.TelevisionRemote.SmartTelevisionRemote.service;

import com.TelevisionRemote.SmartTelevisionRemote.entity.Television;
import com.TelevisionRemote.SmartTelevisionRemote.exceptions.televisionExceptions.TelevisionIpExistsException;
import com.TelevisionRemote.SmartTelevisionRemote.exceptions.televisionExceptions.TelevisionNameExistsException;
import com.TelevisionRemote.SmartTelevisionRemote.exceptions.televisionExceptions.TelevisionNotFoundException;
import com.TelevisionRemote.SmartTelevisionRemote.exceptions.userExceptions.UserNotExistsException;
import com.TelevisionRemote.SmartTelevisionRemote.repository.TelevisionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TelevisionService {

    // Repositories
    private TelevisionRepository televisionRepository;

    // Constructor
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    // Add new television for a user
    public ResponseEntity<Television> addTelevision(Television newTelevision) {

        // Check if television exists
        if (!televisionRepository.findByTelevisionName(newTelevision.getTelevisionName()).isEmpty()) {
            throw new TelevisionNameExistsException();
        } else if (!televisionRepository.findByIpAddress(newTelevision.getIpAddress()).isEmpty()) {
            throw new TelevisionIpExistsException();
        }

        // Update the television repository
        televisionRepository.save(newTelevision);
        return ResponseEntity.ok().body(newTelevision);
    }

    // Remove a television
    public ResponseEntity<Television> deleteTelevision(Long id) {

        // Remove television
        Television removedTelevision = televisionRepository.findById(id).orElseThrow(()->new TelevisionNotFoundException());
        televisionRepository.deleteById(id);
        return ResponseEntity.ok().body(removedTelevision);
    }

    // Update user television
    public ResponseEntity<Television> editTelevision(Long id, Television newTelevision) throws URISyntaxException {

        // Find current television by id
        Television currentTelevision = televisionRepository.findById(id).orElseThrow(()->new TelevisionNotFoundException());

        // If new tv info exists and is not equal to current tv, then throw exception
        if (!televisionRepository.findByTelevisionName(newTelevision.getTelevisionName()).isEmpty() &&
            !currentTelevision.getTelevisionName().equals(newTelevision.getTelevisionName())) {
            throw new TelevisionNameExistsException();
        } else if (!televisionRepository.findByIpAddress(newTelevision.getIpAddress()).isEmpty() &&
                    !currentTelevision.getIpAddress().equals(newTelevision.getIpAddress())) {
            throw new TelevisionIpExistsException();
        }

        // Update the tv info
        if (newTelevision.getTelevisionName() != null) {
            currentTelevision.setTelevisionName(newTelevision.getTelevisionName());
        }
        if (newTelevision.getIpAddress() != null) {
            currentTelevision.setIpAddress(newTelevision.getIpAddress());
        }
        if (newTelevision.getTelevisionType() != null) {
            currentTelevision.setTelevisionType(newTelevision.getTelevisionType());
        }

        // Save the updated tv
        televisionRepository.save(currentTelevision);
        return ResponseEntity.created(new URI("tele/username/television")).body(currentTelevision);
    }

    // Get a list of all tvs
    public ResponseEntity<List<Television>> findAllTelevisions() {
        List<Television> televisionList = televisionRepository.findAll();
        return ResponseEntity.ok().body(televisionList);
    }

    // Find a television by its name
    public ResponseEntity<Television> findTelevisionById(Long id) {
        Television foundTele = televisionRepository.findById(id).orElseThrow(() -> new TelevisionNotFoundException());
        return ResponseEntity.ok().body(foundTele);
    }

    // Find a television by its name
    public ResponseEntity<Television> findTelevisionByName(String televisionName) {
        Television foundTele = televisionRepository.findByTelevisionName(televisionName).orElseThrow(() -> new TelevisionNotFoundException());
        return ResponseEntity.ok().body(foundTele);
    }


    //public ResponseEntity<Television> makeSelection()
}
