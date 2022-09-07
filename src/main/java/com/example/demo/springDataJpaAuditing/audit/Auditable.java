package com.example.demo.springDataJpaAuditing.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Auditable {

    /*
    * En DemoApplication.class agregamos @EnableJpaAuditing
    * */

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected LocalDateTime lastModifiedDate;


    /*
    * Con spring security, se puede auditar el usuario que lo creó
    * y el último que modifico
    */

//    @CreatedBy
//    @Column(name = "created_by", updatable = false)
//    protected Long createdBy;
//
//    @LastModifiedBy
//    @Column(name = "last_modified_by")
//    protected Long lastModifiedBy;

}
