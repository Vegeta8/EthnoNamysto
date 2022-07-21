/**
 * It's a simple POJO that represents an item in our online store
 */
package com.onlinestore.ethnonamysto.entity;

/*
  @author Artur May
 */


import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private int price;

    private String description;

    private String color;

    private String images;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-yyyy hh:mm:ss")
    @Column(name = "creation_time", updatable = false)
    private Timestamp creationTime;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-yyyy hh:mm:ss")
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemEntity itemEntity = (ItemEntity) o;
        return id != null && Objects.equals(id, itemEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", images='" + images + '\'' +
                '}';
    }
}
