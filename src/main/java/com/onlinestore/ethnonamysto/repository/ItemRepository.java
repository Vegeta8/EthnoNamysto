package com.onlinestore.ethnonamysto.repository;

import com.onlinestore.ethnonamysto.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Artur May
 */
// Creating a repository for the ItemEntity class.
@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Long> {

}
