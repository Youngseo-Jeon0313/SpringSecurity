package com.example.oauth2.domain;


import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="memberId")
    private Long id;
    @Column
    private String socialId;
    @Column(length=30)
    private String nickName;
    @Column
    @Enumerated(EnumType.STRING)
    private LoginType loginType;
    @Column
    private String introduction;
    @Column
    private int memberStatus;
}
