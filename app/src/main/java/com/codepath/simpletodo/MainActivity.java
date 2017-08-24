package com.codepath.simpletodo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Declare array list to store different strings.
    ArrayList<String> items;
    //ArrayList<UserItem> items;
    // Adapter to connect data and listview
    //ArrayAdapter<String> itemsAdapter;
    UsersAdapter itemsAdapter;
    ListView lvItems;

    // Used to determine the result type later
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)findViewById(R.id.lvItems);

        // Recover the items stored in the external txt file
        readItems();
        //items = new ArrayList<>();
        //itemsAdapter = new ArrayAdapter<>(this, R.layout.item_user, items);
        itemsAdapter = new UsersAdapter(this, items);
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
                        item_content.setItemText(items.get(position));
                        item_content.setItemPosition(position);
                        // Bundle the data
                        goToEditScreenIntent.putExtra("my_item_content", item_content);
                        // Launch an activity for a result
                        startActivityForResult(goToEditScreenIntent, REQUEST_CODE);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE ) {
            // Extract name value from result extras
            itemContent edited_item_content = (itemContent) data.getExtras().getSerializable("my_item_content");
            //Toast.makeText(this, edited_item_content.getItemText(), Toast.LENGTH_SHORT).show();
            // Update the text in the arraylist
            items.set(edited_item_content.getItemPosition(), edited_item_content.getItemText());
            // Notify the adapter
            itemsAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
