package com.marta.sandbox.spring.newsportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Advertisement {

    @Id
    @Column (length = 40)
    protected String id = UUID.randomUUID().toString();

    @Column(name = "timestamp")
    private Long date;

    @Column (nullable = false, length = 63)
    private String title;

    @Column (columnDefinition = "TEXT")
    private String text;

    @Column (length = 20)
    private String phone;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Category category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
