package com.domain.models.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@lombok.Getter
@lombok.Setter

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<T> {

  @CreatedBy
  protected T createdBy;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected String createdDate;

  @LastModifiedBy
  protected T updatedBy;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected String updatedDate;

}
