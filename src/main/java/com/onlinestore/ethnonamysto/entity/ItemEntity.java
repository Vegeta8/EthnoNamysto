/**
 * It's a simple POJO that represents an item in our online store
 */
package com.onlinestore.ethnonamysto.entity;

/*
  @author Artur May
 */

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
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

    private int length;

    private int width;
    private String description;
    private String color;

    private String type;

    private String mainImage;

    private String extraImage1;

    private String extraImage2;

    private String extraImage3;

    @CreationTimestamp
    @Column(name = "creation_time", updatable = false)
    private Timestamp creationTime;

    @UpdateTimestamp
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
                '}';
    }
}
