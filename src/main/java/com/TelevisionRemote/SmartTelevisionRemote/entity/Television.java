package com.TelevisionRemote.SmartTelevisionRemote.entity;

import com.TelevisionRemote.SmartTelevisionRemote.enums.TelevisionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "televisions")
public class Television {

    // Attributes
    @Id
    @GeneratedValue
    private Long id;

    private String televisionName;
    private String ipAddress;

    @Enumerated(EnumType.STRING)
    private TelevisionType televisionType;

    // No-args constructor
    public Television() {
    }

    // Constructor
    public Television(String televisionName, String ipAddress, TelevisionType televisionType) {
        this.televisionName = televisionName;
        this.ipAddress = ipAddress;
        this.televisionType = televisionType;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelevisionName() {
        return televisionName;
    }

    public void setTelevisionName(String televisionName) {
        this.televisionName = televisionName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public TelevisionType getTelevisionType() {
        return televisionType;
    }

    public void setTelevisionType(TelevisionType televisionType) {
        this.televisionType = televisionType;
    }

    @Override
    public String toString() {
        return "Television{" +
                "id=" + id +
                ", televisionName='" + televisionName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", televisionType=" + televisionType +
                '}';
    }
}
