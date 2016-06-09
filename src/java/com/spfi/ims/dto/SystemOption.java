package com.spfi.ims.dto;

import java.io.Serializable;

public class SystemOption implements Serializable{
    
    private int id;
    private String code;
    private String name;
    private String value;
    private int isEditable;
    private int type;

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getIsEditable() {
        return isEditable;
    }

    public int getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setIsEditable(int isEditable) {
        this.isEditable = isEditable;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}
