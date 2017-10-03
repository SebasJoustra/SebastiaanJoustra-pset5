package com.example.sebastiaan.sebastiaanjoustra_pset5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class NewListActivity extends AppCompatActivity {

    EditText etAddItem;
    DBHelper helper;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = DBHelper.getInstance(this);

        etAddItem = (EditText) findViewById(R.id.etAddItem);
    }

    public void addItem(View view) {
        boolean uniqueName = true;
        String text = etAddItem.getText().toString();
        ArrayList<TodoList> lists = helper.read();
        //TODO check this in manager class instead
        for(TodoList item : lists) {
            if(text.equals(item.getName())) {
                uniqueName = false;
                Snackbar.make(view, "This list name already exists. Pick something unique...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        }
        if(uniqueName) {
            TodoItem todoItem = new TodoItem("Sample item", text);
            helper.addRow(todoItem);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
