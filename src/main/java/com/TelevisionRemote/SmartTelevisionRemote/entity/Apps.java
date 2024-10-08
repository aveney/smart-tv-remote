package com.TelevisionRemote.SmartTelevisionRemote.entity;

import com.TelevisionRemote.SmartTelevisionRemote.entity.App;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@Entity
@XmlRootElement(name = "apps")
@Table(name = "apps")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Apps {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "apps", cascade = CascadeType.REMOVE)
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<App> app;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "television_id")
    @JsonIgnore
    private Television television;

    public Apps() {
    }

    public Apps(List<App> app) {
        this.app = app;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<App> getApp() {
        return app;
    }

    public void setApp(List<App> app) {
        this.app = app;
    }

    public Television getTelevision() {
        return television;
    }

    public void setTelevision(Television television) {
        this.television = television;
    }

    @Override
    public String toString() {
        return "Apps{" +
                "app=" + app +
                '}';
    }
}
