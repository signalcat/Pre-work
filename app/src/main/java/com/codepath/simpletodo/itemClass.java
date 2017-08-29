package com.codepath.simpletodo;

/**
 * Create a custom class for holding two data types in one todoitem
 * Created by hezhang on 8/28/17.
 */

public class itemClass {
    public String itemText;
    public String itemPriority;

    public itemClass(String itemText, String priority) {
        this.itemText = itemText;
        this.itemPriority = priority;
    }
}
