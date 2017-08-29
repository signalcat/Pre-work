package com.codepath.simpletodo;

import java.io.Serializable;

/**
 * Created by hezhang on 8/21/17.
 */
public class itemContent implements Serializable {
    private String itemText;
    private String itemPriority;
    private int itemPosition;

    public void setItemText(String item_text) {
        itemText = item_text;
    }

    public void setItemPosition (int item_position) {
        itemPosition = item_position;
    }

    public String getItemText () {
        return itemText;
    }

    public int getItemPosition () {
        return itemPosition;
    }

    public void setItemPriority(String priority) {
        itemPriority = priority;
    }

    public String getItemPriority() {
        return itemPriority;
    }

}
