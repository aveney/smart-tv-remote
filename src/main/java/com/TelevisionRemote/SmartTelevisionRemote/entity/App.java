package com.TelevisionRemote.SmartTelevisionRemote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "app")
public class App {

    @Id
    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "version")
    private String version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Apps apps;

    public App() {
    }

    public App(String id, String type, String version) {
        this.id = id;
        this.type = type;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Apps getApps() {
        return apps;
    }

    public void setApps(Apps apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        return "App{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
