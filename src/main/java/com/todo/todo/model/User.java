package com.todo.todo.model;

import jakarta.persistence.*;
import lombok.*;

import javax.crypto.interfaces.PBEKey;

@Entity
@Table(name = "TBL_USER")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_user_seq")
    @SequenceGenerator(name = "t_user_seq", sequenceName = "T_USER_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

}
