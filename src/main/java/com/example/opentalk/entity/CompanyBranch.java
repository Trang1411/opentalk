package com.example.opentalk.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "company_branch")
@SQLDelete(sql = "UPDATE company_branch SET enable = false WHERE id = ?")
public class CompanyBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "enable", nullable = false)
    private Boolean enable = Boolean.TRUE;

}