package com.paipeng.saas.checkin.tenant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Component
@Entity
@Table(name = "task")
public class Device extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "latitude", precision = 11, scale = 8)
    private BigDecimal latitude;

    @JsonBackReference("device-records")
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "device")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private Set<Record> records;


    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    @LazyToOne(value = LazyToOneOption.FALSE)
    private Company company;
}
