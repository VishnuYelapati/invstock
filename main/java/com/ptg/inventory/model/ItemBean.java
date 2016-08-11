package com.ptg.inventory.model;


/**
 * Created by Vishnu on 08-09-2015.
 */
public class ItemBean {

    String id;
    int resId;
    String str_item;

    public String getId() {
        return id;
    }

    public void setId(int resId) {
        this.resId = resId;
    }



    public String getStr_item() {
        return str_item;
    }

    public void setStr_item(String str_item) {
        this.str_item = str_item;
    }
}
