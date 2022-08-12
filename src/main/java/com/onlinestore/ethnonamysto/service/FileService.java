package com.onlinestore.ethnonamysto.service;

import com.onlinestore.ethnonamysto.dto.ItemDto;
import com.onlinestore.ethnonamysto.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Artur May
 * Date 29.07.2022
 * Time 15:23
 */
@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    private final String PATH = "/assets/images/itemImages/";
    private final String ABSOLUTE_PATH = "C:\\Users\\navi8\\IdeaProjects\\EthnoNamysto\\src\\main\\webapp\\assets\\images\\itemImages\\";
    private final String ABSOLUTE_PATH_SHORTER = "C:/Users/navi8/IdeaProjects/EthnoNamysto/src/main/webapp";

    public void uploadMultipleFiles(MultipartFile[] multipleFiles, Map<String, String> params, ItemDto itemDto) {
        int count = 1;
        int imageId = 0;
        List<String> images = new ArrayList<>();
        /* multiple multipart files using for loop */
        for (MultipartFile multipleFile : multipleFiles) {

            String name = multipleFile.getOriginalFilename() + "_" + params.get("name") + "_" + count + ".png";
            // Replace space in filename with underscore
            name = name.replace(' ', '_');
            // location to store the received files
            String path = PATH + name;
            String absolute_path = ABSOLUTE_PATH + name;
            count++;
            File file = new File(absolute_path);
            try {
                if (file.createNewFile()) {
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        fos.write(multipleFile.getBytes());
                    }
                }
            } catch (IOException e) {
                logger.debug("Can't upload files" + e.getMessage());
            }
            images.add(path);
            logger.debug("Files uploaded");
        }
        switch (multipleFiles.length) {
            case 1:
                itemDto.setMainImage(images.get(0));
                break;
            case 2:
                itemDto.setMainImage(images.get(0));
                itemDto.setExtraImage1(images.get(1));
                break;
            case 3:
                itemDto.setMainImage(images.get(0));
                itemDto.setExtraImage1(images.get(1));
                itemDto.setExtraImage2(images.get(2));
                break;
            case 4:
                itemDto.setMainImage(images.get(0));
                itemDto.setExtraImage1(images.get(1));
                itemDto.setExtraImage2(images.get(2));
                itemDto.setExtraImage3(images.get(3));
                break;
            default:
                logger.debug("Error! Should be at least 1 image and not more than 4");
        }
    }

    public void deleteImagesFromDir(Item item) {
        List<String> paths = new ArrayList<>();
        paths.add(item.getMainImage());
        paths.add(item.getExtraImage1());
        paths.add(item.getExtraImage2());
        paths.add(item.getExtraImage3());
        for (String path : paths) {
            if (path != null) {
                try {
                    Files.deleteIfExists(Path.of(ABSOLUTE_PATH_SHORTER + Paths.get(path)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
