package com.example.sebastiaan.sebastiaanjoustra_pset5;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ListsAdapter extends ArrayAdapter<TodoList> {

    private Context appContext;
    private DBHelper helper;

    public ListsAdapter(Context context, ArrayList<TodoList> todoLists) {
        super(context, R.layout.list_item_row, todoLists);
        appContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(R.layout.list_item_row, parent, false);

        helper = DBHelper.getInstance(appContext);
        TodoList item = getItem(position);

        assert item != null;

        TextView tvName = row.findViewById(R.id.tvRowTitle);
        TextView tvNumberTodos = row.findViewById(R.id.tvNumberTodos);

        tvName.setText(item.getName());
        tvNumberTodos.setText("Completed "+item.getCompletedNumber()+"/"+String.valueOf(item.getTodoItems().size()));

        return row;
    }
}
