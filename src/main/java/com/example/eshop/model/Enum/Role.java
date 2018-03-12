package com.example.eshop.model.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * Created by dsrpc on 12.03.2018.
 */
@AllArgsConstructor
public enum Role {
    BUYER("ROLE_BUYER"),
    SELLER("ROLE_SELLER"),
    ADMIN("ROLE_ADMIN");

    private String role;

    @JsonValue
    public String getRole(){
        return role;
    }
 /*   @JsonCreator
    public static Role getByRole(String role){
        if(role==null)return null;
        for(Role roleValue: Role.values()){
            if(roleValue.getRole().equals(role)) return roleValue;
        }
        throw new IllegalArgumentException("Entity type ROLE by ROLE_NAME is not supported");
    }*/
    @JsonCreator
    public static Role getById(Integer id){
        if(id == null)return null;
        for(Role role:Role.values()){
            if(role.ordinal()==id)return role;
        }
        throw new IllegalArgumentException("Entity type ROLE by ID is not supported");
    }

}
