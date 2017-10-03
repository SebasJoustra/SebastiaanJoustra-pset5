package com.example.sebastiaan.sebastiaanjoustra_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper sInstance;
    private static ArrayList<TodoList> todoLists;

    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "todoList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_COMPLETED = "completed";
    private static final String COLUMN_INLIST = "inListName";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TITLE + " TEXT NOT NULL,"
            + COLUMN_COMPLETED + " BOOL NOT NULL,"
            + COLUMN_INLIST + " TEXT NOT NULL" +
            ");";

    public static synchronized DBHelper getInstance(Context context) {

        if(sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }

        return sInstance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // Add new row to database
    public void addRow(TodoItem todoItem) {
        SQLiteDatabase db = getWritableDatabase();
        onUpgrade(db, 1,1);
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, todoItem.getTitle());
        values.put(COLUMN_COMPLETED, todoItem.isCompleted());
        values.put(COLUMN_INLIST, todoItem.getInListName());

        db.insert(TABLE, null, values);
        db.close();
    }

    // Delete row from database
    public void deleteRow(TodoItem todoItem) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE, " " + COLUMN_ID + " = ? ", new String[] { String.valueOf(todoItem.getId()) });

        db.close();
    }

    // Read database
    public ArrayList<TodoList> read() {
        SQLiteDatabase db = getReadableDatabase();

        //ArrayList<TodoItem> todoItems = new ArrayList<>();
        ArrayList<TodoList> todoLists = new ArrayList<>();
        ArrayList<String> todoListNames = new ArrayList<>();

        String query = "SELECT " + COLUMN_ID + ", "
                + COLUMN_TITLE + ", "
                + COLUMN_COMPLETED + ", "
                + COLUMN_INLIST
                + " FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);

        // Set cursor to beginning of databsae
        int i = 0;
        if(cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                int completed = cursor.getInt(cursor.getColumnIndex(COLUMN_COMPLETED));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String inListName = cursor.getString(cursor.getColumnIndex(COLUMN_INLIST));


                TodoItem todoItem = new TodoItem(title, completed, id, inListName);

                // Create todoLists with the newly retrieved data
                if(todoListNames.contains(inListName)) {
                    todoLists.get(i).addTodoItem(todoItem);
                } else {
                    todoListNames.add(todoItem.getInListName());

                    // Todolist didn't exist yet in the arraylist
                    TodoList todoList = new TodoList(todoItem.getInListName());
                    todoList.addTodoItem(todoItem);
                    todoLists.add(todoList);
                }

                i ++;
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return todoLists;
    }

    public int update(TodoItem todoItem) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, todoItem.getTitle());
        values.put(COLUMN_COMPLETED, todoItem.isCompleted());
        values.put(COLUMN_INLIST, todoItem.getInListName());

        return db.update(TABLE, values, COLUMN_ID + " = ? ", new String[] { String.valueOf(todoItem.getId()) });
    }
}
