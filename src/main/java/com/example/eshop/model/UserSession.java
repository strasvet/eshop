package com.example.eshop.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "SESSIONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, name = "SESSIONID", nullable = false, unique = true)
    private String sessionId;

    @OneToOne
    @JoinColumn(name = "USER", nullable = false)
    private User user;

    @Column(name = "ISVALID")
    private Boolean isValid;
}
