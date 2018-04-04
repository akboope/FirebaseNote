package com.serverr.firebasenote;


import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Менин Компьютерим on 3-Feb-18.
 */

public class AddToDoDialog extends android.support.v4.app.DialogFragment {

    private Button save;
    private Button cancel;
    private EditText title;
    private FirebaseDatabase database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_todo_item, container, false);

        save = view.findViewById(R.id.save);
        cancel = view.findViewById(R.id.cancel);
        title = view.findViewById(R.id.editText);

        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database = FirebaseDatabase.getInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Todo todo = new Todo();
                todo.setTitle(title.getText().toString());
                todo.setCreated(String.valueOf(System.currentTimeMillis()));

                database.getReference().push().setValue(todo);

                getDialog().dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }
}
