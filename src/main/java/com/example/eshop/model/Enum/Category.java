/*
package com.example.eshop.model.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

*/
/**
 * Created by dsrpc on 17.02.2018.
 *//*

@AllArgsConstructor
public enum Category {
    POLITICS("Politics"),
    ECONOMY("Economy"),
    HI_TECH("Hi-Tech"),
    ISRAEL("Israel"),
    WORLD("World");

    private String category;
    @JsonValue
    public String getCategory(){
        return category;
    }
    @JsonCreator
    public static Category getByCategory(String category){
     if(category==null)return null;
        for(Category categoryValue:Category.values()){
            if(categoryValue.getCategory().equals(category))return categoryValue;
        }
        throw new IllegalArgumentException("Entity type CATEGORY is not supported");
    }
}
*/
