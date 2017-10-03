package com.example.sebastiaan.sebastiaanjoustra_pset5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTodoItemActivity extends AppCompatActivity {

    EditText etTodo;
    TodoItem todoItem;
    DBHelper helper;

    int listIndex;

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
        setContentView(R.layout.activity_new_todo_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();
        listIndex = intent.getIntExtra("listIndex", 0);

        etTodo = (EditText) findViewById(R.id.etAddItem);

    }

    public void addItem(View view) {
        helper = DBHelper.getInstance(this);
        String listName = helper.read().get(listIndex).getName();
        todoItem = new TodoItem(etTodo.getText().toString(), listName);
        helper.addRow(todoItem);


        Intent intent = new Intent(this, TodoListActivity.class);
        intent.putExtra("listIndex", listIndex);
        startActivity(intent);
        finish();
    }

}
