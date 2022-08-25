package com.onlinestore.ethnonamysto.entity;

import com.onlinestore.ethnonamysto.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 22:26
 */

/**
 * It's a class that represents a confirmation token that is sent to a user's email address when they register
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createAT;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationToken(String token, LocalDateTime createAT, LocalDateTime expiresAt, User user) {
        this.token = token;
        this.createAT = createAT;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
