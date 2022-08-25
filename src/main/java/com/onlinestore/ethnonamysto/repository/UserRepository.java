package com.onlinestore.ethnonamysto.repository;

import com.onlinestore.ethnonamysto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 17:31
 */

// A repository for the User entity.
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    /**
     * Enable a user by email.
     *
     * @param email the email of the user to be enabled
     * @return The number of rows affected by the update.
     */
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);

}
