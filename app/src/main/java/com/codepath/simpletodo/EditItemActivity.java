package com.codepath.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class EditItemActivity extends AppCompatActivity {
    public itemContent item_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        item_content = (itemContent) getIntent().getSerializableExtra("my_item_content");
        EditText item_content_editText = (EditText) findViewById(R.id.item_content_editText);
        item_content_editText.setText(item_content.getItemText());
        // Make sure the cursor is at the end of the editText
        item_content_editText.setSelection(item_content_editText.getText().length());

        Button saveEdit = (Button) findViewById(R.id.save_button);
        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editItemText = (EditText) findViewById(R.id.item_content_editText);
                // Get the updated text
                item_content.setItemText(editItemText.getText().toString());
                // Prepare data intent
                Intent data = new Intent();
                // Pass relevant data back as a result
                data.putExtra("my_item_content", item_content);
                // Activity finished OK, return the data
                setResult(RESULT_OK, data); //bundle data for response
                finish(); // close activity, pass data to parent
            }
        });
    }
}
