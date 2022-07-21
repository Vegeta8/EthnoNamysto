package com.onlinestore.ethnonamysto.service;

import com.onlinestore.ethnonamysto.dto.ItemDto;
import com.onlinestore.ethnonamysto.entity.ItemEntity;
import com.onlinestore.ethnonamysto.mapper.ItemMapper;
import com.onlinestore.ethnonamysto.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Artur May
 */
@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDto> allItems() {
        return ItemMapper.INSTANCE.toItemDtos(itemRepository.findAll());
    }

    public ItemDto showItem(Long id) {
        return ItemMapper.INSTANCE.toItemDto(itemRepository.getItemEntitiesById(id));
    }

    public void saveItem(ItemDto itemDto) {
        ItemEntity itemEntity = ItemMapper.INSTANCE.toItemEntity(itemDto);
        itemRepository.save(itemEntity);
    }

    public void updateItem(ItemDto itemDto, Long id) {
        ItemEntity itemEntity = itemRepository.getItemEntitiesById(id);
        itemEntity.setName(itemDto.getName());
        itemEntity.setDescription(itemDto.getDescription());
        itemEntity.setColor(itemDto.getColor());
        itemEntity.setPrice(itemDto.getPrice());
        itemRepository.save(itemEntity);
    }

    public void deleteItem(Long id) {
        ItemEntity itemEntity = itemRepository.getItemEntitiesById(id);
        itemRepository.delete(itemEntity);
    }
}
