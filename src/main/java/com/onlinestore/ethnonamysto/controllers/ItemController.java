package com.onlinestore.ethnonamysto.controllers;

import com.onlinestore.ethnonamysto.dto.ItemDto;
import com.onlinestore.ethnonamysto.entity.ItemEntity;
import com.onlinestore.ethnonamysto.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Artur May
 */
@Controller
@RequestMapping("/catalog")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @GetMapping()
    public String viewCatalog(Model model) {
        logger.debug("displaying all items");
        List<ItemDto> itemDtos = itemService.allItems();
        model.addAttribute("itemList", itemDtos);
        return "catalog";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        logger.debug("Displaying chosen itemEntity" + id);
        ItemDto itemDto = itemService.showItem(id);
        model.addAttribute("item", itemDto);
        return "view";
    }

    @GetMapping("/new")
    public String newItem(Model model) {
        model.addAttribute("newItem", new ItemEntity());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("newItem") ItemDto itemDto) {
        itemService.saveItem(itemDto);
        return "redirect:/catalog";
    }

    /*@GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {

    }*/

}
