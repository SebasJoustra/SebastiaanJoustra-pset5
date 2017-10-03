package com.example.sebastiaan.sebastiaanjoustra_pset5;

import java.util.ArrayList;

class TodoList {

    private ArrayList<TodoItem> todoItems;
    private String name;

    public TodoList(String _name) {
        this.todoItems = new ArrayList<>();
        this.name = _name;
    }

    public ArrayList<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(ArrayList<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTodoItem(TodoItem todoItem) {
        todoItems.add(todoItem);
    }

    public int getCompletedNumber() {
        int completedNr = 0;
        for(TodoItem item : todoItems) {
            if(item.isCompleted() == 1) {
                completedNr ++;
            }
        }
        return completedNr;
    }
}
