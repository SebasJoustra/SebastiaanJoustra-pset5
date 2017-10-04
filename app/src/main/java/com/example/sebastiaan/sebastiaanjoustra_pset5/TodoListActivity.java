package com.example.sebastiaan.sebastiaanjoustra_pset5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    DBHelper helper;
    ArrayList<TodoList> todoLists;
    ListView lvItems;
    int listIndex;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabList);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewTodoItemActivity.class);
                intent.putExtra("listIndex", listIndex);
                startActivity(intent);
                finish();
            }
        });

        lvItems = (ListView) findViewById(R.id.lvItems);

        Intent intent = this.getIntent();
        listIndex = intent.getIntExtra("listIndex", 0);
        helper = DBHelper.getInstance(this);
        todoLists = helper.read();
        TodoList todoList = todoLists.get(listIndex);
        setTitle("To-do List: " + todoList.getName());

        TodoListAdapter adapter = new TodoListAdapter(this, todoList.getTodoItems());
        lvItems.setAdapter(adapter);
        lvItems.setOnItemClickListener(new ItemClickListener());
    }

    public class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
            Intent intent = new Intent(getApplicationContext(), TodoViewActivity.class);
            intent.putExtra("listIndex", listIndex);
            intent.putExtra("itemIndex", itemIndex);
            startActivity(intent);
            finish();
        }
    }

}
