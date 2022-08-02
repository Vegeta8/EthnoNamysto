package com.onlinestore.ethnonamysto.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
    private List<String> images;
    private String mainImage;
    private String extraImage1;
    private String extraImage2;
    private String extraImage3;
    private String type;
    private int length;
    private int width;
}
