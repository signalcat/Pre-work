package com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by hezhang on 8/23/17.
 */

public class UsersAdapter extends ArrayAdapter<itemClass> {
    public UsersAdapter(Context context, ArrayList<itemClass> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        itemClass items = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.itemName_textView);
        TextView itemPriority = (TextView) convertView.findViewById(R.id.priority_textView);
        // Populate the data into the template view using the data object
        itemName.setText(items.itemText);
        itemPriority.setText(items.itemPriority);
        // Return the completed view to render on screen
        return convertView;

    }
}
