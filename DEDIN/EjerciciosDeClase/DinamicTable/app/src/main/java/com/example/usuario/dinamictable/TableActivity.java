package com.example.usuario.dinamictable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author Amador Fernández González
 * @version 1.0
 * */
public class TableActivity extends AppCompatActivity {

    private TableLayout tblHeader;
    private TableLayout tblBody;
    private   TableRow.LayoutParams tbrLayoutId;
    private TableRow.LayoutParams tbrLayoutName;
    private TableRow.LayoutParams tbrLayout;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //Instance the params
        tblHeader = (TableLayout)findViewById(R.id.tableHead);
        tblBody = (TableLayout)findViewById(R.id.tableBody);
        tbrLayoutId = new TableRow.LayoutParams(200, TableRow.LayoutParams.WRAP_CONTENT);
        tbrLayoutId.setMargins(0,10,0,10);
        tbrLayoutName = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        tbrLayout = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        createHeader();
        createBody();

    }

    private void createBody() {

        TableRow tbrBody;
        TextView tbxID;
        TextView tbxName;

        //Dumps data string and creates the table body
        String[] listName = getResources().getStringArray(R.array.listName);

        for(int i = 0; i < listName.length; i++){
            tbrBody = new TableRow(this);
            tbxID = new TextView(this);
            tbxName = new TextView(this);

            tbxID.setText(" " + String.valueOf(i+1) + " ");
            tbxID.setLayoutParams(tbrLayoutId);
            tbxID.setBackgroundResource(R.drawable.shape_body);
            tbrBody.addView(tbxID);

            tbxName.setText(listName[i]);
            tbxName.setLayoutParams(tbrLayoutName);
            tbxName.setBackgroundResource(R.drawable.shape_body);
            tbrBody.addView(tbxName);

            tblBody.addView(tbrBody);

        }




    }

    private void createHeader() {

        //Dumps data string and creates the table header
        TableRow tbrHeader = new TableRow(TableActivity.this);
        TextView txvId = new TextView(TableActivity.this);
        txvId.setText(R.string.txvId);
        txvId.setLayoutParams(tbrLayoutId);
        txvId.setBackgroundResource(R.drawable.shape_header);
        tbrHeader.addView(txvId);
        TextView txvName = new TextView(TableActivity.this);
        txvName.setText(R.string.txvName);
        txvName.setLayoutParams(tbrLayoutId);
        txvName.setBackgroundResource(R.drawable.shape_header);
        tbrHeader.addView(txvName);
        tblHeader.addView(tbrHeader);


    }
}
