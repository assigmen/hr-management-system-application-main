package com.dinsaren.hrmanagementsystemapplication.constants;

import com.dinsaren.hrmanagementsystemapplication.models.request.ItemKeyValue;

import java.util.ArrayList;
import java.util.List;

public abstract class Constant {

    public static final String STATUS_DELETE = "DEL";

    public static List<ItemKeyValue> getAllGenders(){
        List<ItemKeyValue> itemKeyValues = new ArrayList<>();
        itemKeyValues.add(new ItemKeyValue(1,"Male","Male"));
        itemKeyValues.add(new ItemKeyValue(2,"Female","Female"));
        itemKeyValues.add(new ItemKeyValue(3,"Other","Other"));
        return itemKeyValues;
    }

    public static List<ItemKeyValue> getAllStatus(){
        List<ItemKeyValue> itemKeyValues = new ArrayList<>();
        itemKeyValues.add(new ItemKeyValue(1,"ACT","Active"));
        itemKeyValues.add(new ItemKeyValue(2,"DEL","Delete"));
        itemKeyValues.add(new ItemKeyValue(3,"DSL","Disable"));
        return itemKeyValues;
    }

    public static List<String> getAllStatusString(){
        List<String> stringList = new ArrayList<>();
        stringList.add("ACT");
        stringList.add("DEL");
        stringList.add("DSL");
        return stringList;
    }
    public static String STATUS_ACTIVE = "ACT";
}
