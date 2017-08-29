package com.codepath.simpletodo;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Create a new object to hold the new item, and set the default priority to low
    itemClass item = new itemClass("todo", "LOW");
    // Declare array list to store different strings.
    //ArrayList<String> items;
    ArrayList<itemClass> items = new ArrayList<itemClass>();
    // Adapter to connect data and listview
    //ArrayAdapter<String> itemsAdapter;
    UsersAdapter itemsAdapter;
    ListView lvItems;

    // Item priority
    String itemPriority = "LOW";

    // Used to determine the result type later
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Recover the items stored in the external txt file
        readItems();
        //items = new ArrayList<>();
        //itemsAdapter = new ArrayAdapter<>(this, R.layout.item_user, items);
        itemsAdapter = new UsersAdapter(this, items);
        // Attach the adapter to a listView
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);
        //items.add("First Item");
        //items.add("Second Item");
        setupListViewListener();
        setupEditListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {

                        // Ask the user before delete
                        // Create a AlertDialog builder
                        final int position = pos;
                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(MainActivity.this);
                        alert_builder.setMessage("Are you sure you want to delete this item?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        items.remove(position);
                                        itemsAdapter.notifyDataSetChanged();
                                        writeItems();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                        // Show the alert
                        AlertDialog alert = alert_builder.create();
                        alert.setTitle("Alert !!!");
                        alert.show();
                        return true;
                    }
                }
        );
    }

    // When click on any item, user is taken to the edit form screen for that item.
    private void setupEditListViewListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Intent goToEditScreenIntent = new Intent(MainActivity.this, EditItemActivity.class);
                        // Declare an object to hold the data
                        itemContent item_content = new itemContent();
                        items.get(position);
                        item_content.setItemText(items.get(position).itemText);
                        item_content.setItemPriority(items.get(position).itemPriority);
                        item_content.setItemPosition(position);
                        // Bundle the data
                        goToEditScreenIntent.putExtra("my_item_content", item_content);
                        // Launch an activity for a result
                        startActivityForResult(goToEditScreenIntent, REQUEST_CODE);
                    }
                }
        );
    }

    // Bring the updated data back to the main list
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE ) {
            // Extract name value from result extras
            itemContent edited_item_content = (itemContent) data.getExtras().getSerializable("my_item_content");
            //Toast.makeText(this, edited_item_content.getItemText(), Toast.LENGTH_SHORT).show();

            // Create a new itemClass object to hold the edited data
            itemClass Editeditems = new itemClass(edited_item_content.getItemText(), edited_item_content.getItemPriority());

            // Update the original item list
            items.set(edited_item_content.getItemPosition(), Editeditems);
            // Notify the adapter
            itemsAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    // Add new item to the list from the editbox, and write to the external file
    public void onAddItem(View v) {
        // Get the new item text
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();

        // Get the priority from the radio group
        RadioGroup priority_radioGroup = (RadioGroup) findViewById(R.id.priority_radioGroup);
        priority_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                RadioButton rb = (RadioButton) findViewById(checkedID);
                View radioButton = radioGroup.findViewById(checkedID);
                // get the selected radio button's number
                int index = radioGroup.indexOfChild(radioButton);
                switch (index) {
                    case 0:
                        itemPriority = "LOW";
                        break;
                    case 1:
                        itemPriority = "MEDIUM";
                        break;
                    case 2:
                        itemPriority = "HIGH";
                        break;
                    default:
                        break;
                }
            }
        });

        // Create a new item object
        itemClass item = new itemClass("todo", "LOW");
        // Set the item text
        item.itemText = itemText;
        // Set the item priority
        item.itemPriority = itemPriority;
        // Add item to items adapter
        itemsAdapter.add(item);
        // Clean the edit text
        etNewItem.setText("");
        // Write the new item to external file.
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
//            List<String> itemRead = new ArrayList<String>();
//            itemRead = FileUtils.readLines(todoFile);
            List<String> itemRead = new ArrayList<String>(FileUtils.readLines(todoFile));
            Log.d("read!!!!!!!!!!", itemRead.get(0));
        } catch (IOException e) {
            items = new ArrayList<itemClass>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            Toast.makeText(getApplicationContext(), items.toString(), Toast.LENGTH_LONG).show();
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
