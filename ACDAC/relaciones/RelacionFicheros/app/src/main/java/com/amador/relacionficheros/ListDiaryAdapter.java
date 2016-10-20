package com.amador.relacionficheros;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txvName, txvTelephone, txvEmail;
        LayoutInflater inflater = LayoutInflater.from(context);
        View vi = inflater.inflate(R.layout.iten_list_friend, null);
        ImageButton imageButton = (ImageButton)vi.findViewById(R.id.btnDeleteFriend);
        Friend f = data.get(position);
        txvName = (TextView)vi.findViewById(R.id.txvNameFriend);
        txvTelephone = (TextView)vi.findViewById(R.id.txvTelephoneFriend);
        txvEmail = (TextView)vi.findViewById(R.id.txvEmailFriend);
        txvName.setText(f.getName());
        txvTelephone.setText(f.getTelephono());
        txvEmail.setText(f.getEmail());

        return vi;


    }
}
