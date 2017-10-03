package com.example.sebastiaan.sebastiaanjoustra_pset5;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;

    ArrayList<TodoList> todoLists;
    ArrayList<String> todoListNames;

    ListView lvLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        lvLists = (ListView) findViewById(R.id.lvLists);
        dbHelper = DBHelper.getInstance(this);

        dbHelper.addRow(new TodoItem("Read chapter 1", "Study"));
        
        todoLists = dbHelper.read();

        //System.out.println(todoLists.get(0).getTodoItems().get(0).getTitle());

        setAdapter();

    }

    private void getTodoLists() {
//        todoLists = new ArrayList<>();
//        todoListNames = new ArrayList<>();
//
//        ArrayList<TodoItem> items = dbHelper.read();
//        System.out.println(items.get(0));
//
//        // Loop through all to-do items and add them to their corresponding lists
//        for(int i = 0; i < items.size(); i++) {
//            TodoItem item = items.get(i);
//
//            if(todoListNames.contains(item.getInListName())) {
//                todoLists.get(i).addTodoItem(item);
//            }
//            else {
//                todoListNames.add(item.getInListName());
//
//                // Todolist didn't exist yet in the arraylist
//                TodoList todoList = new TodoList(item.getInListName());
//                todoList.addTodoItem(item);
//                todoLists.add(todoList);
//
//            }
//        }
    }

    private void setAdapter() {
        ListAdapter adapter = new ListsAdapter(this, todoLists);
        lvLists.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
