package com.codiecon.hungerry.model.dao;

import com.codiecon.hungerry.model.constant.fields.BaseEntityFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity implements Serializable {

    public static final long serialVersionUID = 1487432804732349326L;

    @Column(name = BaseEntityFields.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = BaseEntityFields.CREATED_DATE)
    private Date createdDate;

    @CreatedBy
    @Column(name = BaseEntityFields.CREATED_BY)
    private String createdBy;

    @LastModifiedBy
    @Column(name = BaseEntityFields.UPDATED_BY)
    private String updatedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = BaseEntityFields.UPDATED_DATE)
    private Date updatedDate;

    @Column(name = BaseEntityFields.IS_ACTIVE)
    private Boolean isActive = Boolean.TRUE;

}
