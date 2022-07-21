package com.onlinestore.ethnonamysto.service;

import com.onlinestore.ethnonamysto.dto.ItemDto;
import com.onlinestore.ethnonamysto.entity.ItemEntity;
import com.onlinestore.ethnonamysto.mapper.ItemMapper;
import com.onlinestore.ethnonamysto.repository.ItemRepository;
import org.joda.time.DateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Artur May
 */
@Service
public class ItemService {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-yyyy hh:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDto> allItems() {
        return ItemMapper.INSTANCE.toItemDtos(itemRepository.findAll());
    }

    public ItemDto showItem(Long id) {
        return ItemMapper.INSTANCE.toItemDto(itemRepository.getOne(id));
    }

    public void saveItem(ItemDto itemDto) {
        ItemEntity itemEntity = ItemMapper.INSTANCE.toItemEntity(itemDto);
        /*itemEntity.setCreationTime(DateTime.now());*/
        logger.debug("Creating new item");
        itemRepository.save(itemEntity);
    }

    public boolean deleteItem(Long id) {
        itemRepository.deleteById(id);
        return true;
    }

}
