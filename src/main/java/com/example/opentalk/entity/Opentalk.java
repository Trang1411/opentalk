package com.example.opentalk.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "opentalk")
public class Opentalk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "meeting")
    private String meeting;

    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "host", referencedColumnName = "id",nullable = false)
    private Employee host;
}