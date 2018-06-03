package com.marta.sandbox.spring.newsportal.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company extends AbstractEntity {

    @Column (nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String address;

    @OneToMany
    private List<Ad> adList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }
}
