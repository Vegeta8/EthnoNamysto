package com.onlinestore.ethnonamysto.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Artur May
 */
@Data
public class ItemDto implements Serializable {
    private String name;
    private int price;
    private String description;
    private String color;
    private Byte images;

}
