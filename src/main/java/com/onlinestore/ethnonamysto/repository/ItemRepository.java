package com.onlinestore.ethnonamysto.repository;

import com.onlinestore.ethnonamysto.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Artur May
 */
// Creating a repository for the Item class.
@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Item getItemEntitiesById(Long id);

}
