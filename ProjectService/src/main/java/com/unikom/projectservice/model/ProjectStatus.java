package com.unikom.projectservice.model;

import lombok.Data;
//    Err
//import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@Entity
@Table(name = "project_status")
public class ProjectStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
//    Err
//    @NaturalId
    @Column(length = 60)
    private ProjectStatusName projectStatusName;
}
