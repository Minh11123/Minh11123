package com.vti.rk25finalexam.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@MappedSuperclass
public class CommonEntity {

    @Column(name = "is_deleted" )
    private Integer isDeleted;

    @PrePersist
    public void prePersist() {
        this.isDeleted = 0;
    }

    public CommonEntity isDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
}
