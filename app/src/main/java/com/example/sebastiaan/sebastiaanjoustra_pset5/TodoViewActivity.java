package com.example.sebastiaan.sebastiaanjoustra_pset5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

import static com.example.sebastiaan.sebastiaanjoustra_pset5.R.id.checkBox;

public class TodoViewActivity extends AppCompatActivity {

    EditText etTaskTitle;
    CheckBox checkBox;

    DBHelper helper;
    TodoItem todoItem;
    ArrayList<TodoList> todoLists;

    int listIndex;
    int itemIndex;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, TodoListActivity.class);
        intent.putExtra("listIndex", listIndex);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get to-do item values
        Intent intent = this.getIntent();
        listIndex = intent.getIntExtra("listIndex", 0);
        itemIndex = intent.getIntExtra("itemIndex", 0);

        helper = DBHelper.getInstance(this);
        todoLists = helper.read();
        todoItem = todoLists.get(listIndex).getTodoItems().get(itemIndex);

        // Initialize components
        etTaskTitle = (EditText) findViewById(R.id.etTaskTitle);
        etTaskTitle.setText(todoItem.getTitle());
        etTaskTitle.setSelection(todoItem.getTitle().length());
        checkBox = (CheckBox) findViewById(R.id.cbCompleted);
        if(todoItem.isCompleted() == 1) {
            checkBox.setChecked(true);
        }

    }

    public void clickedDone(View view) {
        if(checkBox.isChecked()) {
            todoItem.setCompleted(1);
        } else {
            todoItem.setCompleted(0);
        }
        todoItem.setTitle(etTaskTitle.getText().toString());
        helper.update(todoItem);

        Intent intent = new Intent(this, TodoListActivity.class);
        intent.putExtra("listIndex", listIndex);
        startActivity(intent);
        finish();
    }

    public void clickedDelete(View view) {
        helper.deleteRow(todoItem);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
