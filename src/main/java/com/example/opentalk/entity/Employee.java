package com.example.opentalk.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "employee")
@SQLDelete(sql = "UPDATE employee SET enable = false WHERE id = ?")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Opentalk> opentalks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_branch", referencedColumnName = "id",nullable = false)
    private CompanyBranch companyBranch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles", referencedColumnName = "id",nullable = false)
    private Role role;

    @Column(name = "enable",nullable = false)
    private Boolean enable = Boolean.TRUE;
}
