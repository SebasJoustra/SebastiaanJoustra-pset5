package com.example.sebastiaan.sebastiaanjoustra_pset5;

import java.util.ArrayList;

class TodoManager {
    private static ArrayList<TodoList> todoLists;
    private static TodoManager sInstance = new TodoManager();

    public static TodoManager getInstance() {
        if(sInstance == null) {
            sInstance = new TodoManager();
        }
        return sInstance;
    }

    private TodoManager() {

    }

    public static ArrayList<TodoList> getTodoLists() {
        return todoLists;
    }

    public static void setTodoLists(ArrayList<TodoList> todoLists) {
        TodoManager.todoLists = todoLists;
    }

    public static void addTodoList(TodoList todoList) {
        todoLists.add(todoList);
    }
}
