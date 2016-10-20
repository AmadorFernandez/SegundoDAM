package com.amador.relacionficheros;

import android.app.ListActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EJOne extends ListActivity {

    ListDiaryAdapter adapter;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej_one);
        init();
    }

    private void init() {

        btnAdd = (FloatingActionButton)findViewById(R.id.btnAddFriend);
        adapter = new ListDiaryAdapter(EJOne.this, Diary.getDiary());
        getListView().setAdapter(adapter);
    }
}
