package com.dinsaren.hrmanagementsystemapplication.models;

import com.dinsaren.hrmanagementsystemapplication.models.request.ItemKeyValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hr_position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String code;
    private String status;
    @Transient
    List<ItemKeyValue> statusList;
}
