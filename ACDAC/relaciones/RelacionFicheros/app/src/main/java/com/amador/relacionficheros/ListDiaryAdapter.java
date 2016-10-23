package com.amador.relacionficheros;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by amador on 20/10/16.
 */

public class ListDiaryAdapter extends ArrayAdapter {


    Context context;
    Diary data;

    public ListDiaryAdapter(Context context, Diary data) {
        super(context, R.layout.iten_list_friend, data);

        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //Instanciamos y asignamos los valores a los campos
        TextView txvName, txvTelephone, txvEmail;
        LayoutInflater inflater = LayoutInflater.from(context);
        View vi = inflater.inflate(R.layout.iten_list_friend, null);
        ImageButton imageButton = (ImageButton)vi.findViewById(R.id.btnDeleteFriend);
        ImageButton btnEliminar = (ImageButton)vi.findViewById(R.id.btnDeleteFriend);
        Friend f = data.get(position);
        txvName = (TextView)vi.findViewById(R.id.txvNameFriend);
        txvTelephone = (TextView)vi.findViewById(R.id.txvTelephoneFriend);
        txvEmail = (TextView)vi.findViewById(R.id.txvEmailFriend);
        txvName.setText(f.getName());
        txvTelephone.setText(f.getTelephono());
        txvEmail.setText(f.getEmail());

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Pedimos confirmación para el borrado
                aviseDelete(context.getResources().getString(R.string.text_alert),
                        context.getResources().getString(R.string.text_ms_delete_dic), position).show();

            }
        });

        return vi;


    }

    private Dialog aviseDelete(String titulo, String msg, final int posData){

        final AlertDialog.Builder popup = new AlertDialog.Builder(context);
        popup.setTitle(titulo);
        popup.setMessage(msg);


        popup.setPositiveButton(context.getResources().getString(R.string.text_acept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                data.remove(posData);
                notifyDataSetChanged(); //Notifica los cambios en el contenido del adapter al ListView
                dialog.cancel();
            }
        });

        popup.setNegativeButton(context.getResources().getString(R.string.text_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        return popup.create();

    }


}
