package com.serverr.firebasenote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Менин Компьютерим on 4-Feb-18.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.Holder> {
    private List<Todo> todos = new ArrayList<>();
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
         Todo todo = todos.get(position);
        holder.textView.setText(todo.getTitle());
        holder.txtHeader.setText(getFirstWord(todo.getTitle()));
        try
        {
            holder.created.setText(formatDayTimeStamp(holder.itemView.getContext(), Long.parseLong(todo.getCreated())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    private  String getFirstWord(String title){
        return  title.substring(0,1).toUpperCase();
    }
    @Override
    public int getItemCount() { return todos.size();}

    public class Holder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView created;
        private TextView txtHeader;
        public Holder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.title_todo);
            created = itemView.findViewById(R.id.created);
            txtHeader = itemView.findViewById(R.id.txt_header);

        }
    }

    public void add(List<Todo> todos){
        this.todos.clear();
        this.todos.addAll(todos);
        notifyDataSetChanged();

    }

    public void addOne(Todo todo){
        this.todos.add(todo);
        notifyDataSetChanged();
    }

     @SuppressLint("SimpleDateFormat")
    public static String formatDayTimeStamp(Context context, long created) throws ParseException {
        Date dateTime = new Date(created);
        @SuppressLint("SimpleDateFormat")
//        Date dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(simpleDateFormat);
                Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        Calendar today = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        DateFormat timeFormatter = new SimpleDateFormat("HH:mm");

        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
            return context.getString(R.string.today)+ " " + timeFormatter.format(dateTime);
        } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {
            return context.getString(R.string.yesterday)+ " " + timeFormatter.format(dateTime);
        } else {
            return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(dateTime);
        }
    }
}

