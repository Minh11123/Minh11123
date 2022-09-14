package com.vti.rk25finalexam.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_member")
    private Integer totalMember;

    @Column(name = "type")
    private String type;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "department")
    private List<Account> accountList;

    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }

    public Department id(Integer id) {
        this.id = id;
        return this;
    }

    public Department name(String name) {
        this.name = name;
        return this;
    }

    public Department totalMember(Integer totalMember) {
        this.totalMember = totalMember;
        return this;
    }

    public Department type(String type) {
        this.type = type;
        return this;
    }

    public Department createdDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }
}
