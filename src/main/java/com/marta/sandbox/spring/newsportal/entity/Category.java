package com.marta.sandbox.spring.newsportal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Category extends AbstractEntity {

    @Column(length = 31, nullable = false, unique = true)
    private String name;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Ad> adList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }
}
