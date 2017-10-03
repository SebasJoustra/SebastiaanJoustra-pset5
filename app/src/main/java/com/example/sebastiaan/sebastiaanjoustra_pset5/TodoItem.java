package com.example.sebastiaan.sebastiaanjoustra_pset5;

class TodoItem {

    private int _id;
    private String title;
    private int completed;
    private String inListName;

    TodoItem(String todoTitle, String listName) {
        title = todoTitle;
        completed = 0;
        inListName = listName;
    }

    TodoItem(String todoTitle, int isCompleted, int todoId, String listName) {
        title = todoTitle;
        completed = isCompleted;
        _id = todoId;
        inListName = listName;
    }

    public int isCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getInListName() {
        return inListName;
    }

    public void setInListName(String inListName) {
        this.inListName = inListName;
    }
}
