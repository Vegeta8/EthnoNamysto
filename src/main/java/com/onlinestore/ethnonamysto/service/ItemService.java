package com.onlinestore.ethnonamysto.service;

import com.onlinestore.ethnonamysto.dto.ItemDto;
import com.onlinestore.ethnonamysto.entity.Item;
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
/**
 * It's a service class that contains methods for CRUD operations on the Item entity
 */
@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FileService fileService;

    public List<ItemDto> allItems() {
        return ItemMapper.INSTANCE.toItemDtos(itemRepository.findAll());
    }

    public ItemDto showItem(Long id) {
        return ItemMapper.INSTANCE.toItemDto(itemRepository.getItemEntitiesById(id));
    }

    public void saveItem(ItemDto itemDto) {
        Item item = ItemMapper.INSTANCE.toItemEntity(itemDto);
        itemRepository.save(item);
    }

    public void updateItem(ItemDto itemDto, Long id) {
        Item item = itemRepository.getItemEntitiesById(id);
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setColor(itemDto.getColor());
        item.setPrice(itemDto.getPrice());
        item.setLength(itemDto.getLength());
        item.setWidth(itemDto.getWidth());
        item.setType(itemDto.getType());
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.getItemEntitiesById(id);
        fileService.deleteImagesFromDir(item);
        itemRepository.delete(item);
    }
}
