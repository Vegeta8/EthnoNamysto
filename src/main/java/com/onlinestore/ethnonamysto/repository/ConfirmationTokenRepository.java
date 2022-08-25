package com.onlinestore.ethnonamysto.repository;

import com.onlinestore.ethnonamysto.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 23:53
 */
// A repository for ConfirmationToken entity.
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    /**
     * Update the confirmedAt field of the ConfirmationToken with the given token to the given confirmedAt value.
     *
     * @param token The token to be updated
     * @param confirmedAt The date and time when the token was confirmed.
     * @return The number of rows affected by the update.
     */
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);

}
