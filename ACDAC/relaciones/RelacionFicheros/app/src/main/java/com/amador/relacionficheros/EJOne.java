package com.amador.relacionficheros;

import android.app.ListActivity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EJOne extends ListActivity {

    private static final String FILE_NAME = "agenda.txt";
    private ListDiaryAdapter adapter;
    private FloatingActionButton btnAdd;
    private  SerialiceFriend serialiceFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej_one);
        init();
    }

    private void init() {


        //Inicializa el serializador y extrae los datos del fichero
        serialiceFriend = new SerialiceFriend(getApplicationContext().getFilesDir().getAbsolutePath(), FILE_NAME);
        serialiceFriend.extractDiary();
        //Carga el adapter y se lo asigna a la lista
        adapter = new ListDiaryAdapter(EJOne.this, Diary.getDiary());
        getListView().setAdapter(adapter);

        //Memoria y captura de evento
        btnAdd = (FloatingActionButton)findViewById(R.id.btnAddFriend);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(EJOne.this, SaveFriendActivity.class));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Informa a la lista de que puede que halla cambios
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //Salvamos los posibles cambios echos por el usuario
        serialiceFriend.saveFriend();
    }
}
