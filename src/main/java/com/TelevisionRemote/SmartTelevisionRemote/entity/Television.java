package com.TelevisionRemote.SmartTelevisionRemote.entity;

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
    private String televisionType;

    @OneToMany(mappedBy = "television", cascade = CascadeType.REMOVE)
    private List<Apps> apps;

    // No-args constructor
    public Television() {
    }

    // Constructor
    public Television(String televisionName, String ipAddress, String televisionType) {
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

    public String getTelevisionType() {
        return televisionType;
    }

    public void setTelevisionType(String televisionType) {
        this.televisionType = televisionType;
    }

    public List<Apps> getApps() {
        return apps;
    }

    public void setApps(List<Apps> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        return "Television{" +
                "id=" + id +
                ", televisionName='" + televisionName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", apps=" + apps +
                ", televisionType=" + televisionType +
                '}';
    }
}
