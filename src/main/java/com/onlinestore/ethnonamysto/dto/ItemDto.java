package com.onlinestore.ethnonamysto.dto;

import lombok.Data;

import java.io.Serializable;

/*
  @author Artur May
 */
/**
 * A DTO class.
 */
@Data
public class ItemDto implements Serializable {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String color;
    private Byte images;

}
