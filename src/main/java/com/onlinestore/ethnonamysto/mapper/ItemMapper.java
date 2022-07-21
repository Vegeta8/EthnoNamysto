package com.onlinestore.ethnonamysto.mapper;

import com.onlinestore.ethnonamysto.dto.ItemDto;
import com.onlinestore.ethnonamysto.entity.ItemEntity;
import com.onlinestore.ethnonamysto.service.ItemService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Artur May
 */
@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    ItemEntity toItemEntity (ItemDto itemDto);
    ItemDto toItemDto (ItemEntity itemEntity);
    List<ItemDto> toItemDtos(List<ItemEntity> itemEntityList);
}
