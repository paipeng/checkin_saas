package com.paipeng.saas.checkin.tenant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Component
@Entity
@Table(name = "company")
public class Company extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @Column(name = "expire", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp expire;

    @JsonBackReference("company-users")
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "company")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private Set<User> users;


    @JsonBackReference("company-tasks")
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "company")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private Set<Task> tasks;


    @JsonBackReference("company-devices")
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "company")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private Set<Device> devices;

    @Column(name = "license_count", nullable = false)
    private int licenseCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public int getLicenseCount() {
        return licenseCount;
    }

    public void setLicenseCount(int licenseCount) {
        this.licenseCount = licenseCount;
    }

    public Timestamp getExpire() {
        return expire;
    }

    public void setExpire(Timestamp expire) {
        this.expire = expire;
    }
}
