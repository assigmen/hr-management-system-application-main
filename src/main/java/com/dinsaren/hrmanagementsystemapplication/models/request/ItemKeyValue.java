package com.dinsaren.hrmanagementsystemapplication.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemKeyValue {
    private int id;
    private String key;
    private String value;
}
