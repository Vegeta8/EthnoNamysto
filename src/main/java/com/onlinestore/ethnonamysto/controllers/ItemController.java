package com.onlinestore.ethnonamysto.controllers;

import com.onlinestore.ethnonamysto.dto.ItemDto;
import com.onlinestore.ethnonamysto.service.FileService;
import com.onlinestore.ethnonamysto.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Artur May
 */
/**
 * > This class is a Spring MVC controller that handles requests for the `/items` endpoint
 */
@Controller
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private FileService fileService;

    @GetMapping()
    public String viewCatalog(Model model) {
        logger.debug("Displaying all items");
        List<ItemDto> itemDtos = itemService.allItems();
        model.addAttribute("itemList", itemDtos);
        return "index";
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
        model.addAttribute("newItem", new ItemDto());
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("newItem") ItemDto itemDto, @RequestParam("files") MultipartFile[] files, @RequestParam Map<String, String> params) {
        logger.debug("Creating new item" + itemDto.getId());
        fileService.uploadMultipleFiles(files, params, itemDto);
        itemService.saveItem(itemDto);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {

        model.addAttribute("item", itemService.showItem(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("item") ItemDto itemDto, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        logger.debug("Updating item" + id);
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        itemService.updateItem(itemDto, id);
        return "redirect:" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        logger.debug("Deleting item" + id);
        itemService.deleteItem(id);
        return "redirect:/";
    }

}
