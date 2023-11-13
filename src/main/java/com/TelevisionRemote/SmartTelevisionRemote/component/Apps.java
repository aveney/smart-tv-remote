package com.TelevisionRemote.SmartTelevisionRemote.component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement(name = "apps")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apps() {
    }

    public Apps(List<App> app) {
        this.app = app;
    }

    public List<App> getApp() {
        return app;
    }

    public void setApp(List<App> app) {
        this.app = app;
    }

    @Override
    public String toString() {
        return "Apps{" +
                "app=" + app +
                '}';
    }
}
