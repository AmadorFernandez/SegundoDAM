package com.example.usuario.dinamictable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableActivity extends AppCompatActivity {

    private TableLayout tblHeader;
    private TableLayout tblBody;
    TableRow.LayoutParams tbrLayoutId;
    TableRow.LayoutParams tbrLayoutName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        tblHeader = (TableLayout)findViewById(R.id.tableHead);
        tblBody = (TableLayout)findViewById(R.id.tableBody);
        tbrLayoutId = new TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT);
        tbrLayoutId.setMargins(0,10,0,10);
        tbrLayoutName = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        createHeader();
        createBody();

    }

    private void createBody() {

        TableRow tbrBody = new TableRow(TableActivity.this);
        TextView tbxID = new TextView(TableActivity.this);
        TextView tbxName = new TextView(TableActivity.this);

        String[] listName = getResources().getStringArray(R.array.listName);




    }

    private void createHeader() {

        TableRow tbrHeader = new TableRow(TableActivity.this);
        TextView txvId = new TextView(TableActivity.this);
        txvId.setText(R.string.txvId);
        txvId.setLayoutParams(tbrLayoutId);
        tbrHeader.addView(txvId);

        TextView txvName = new TextView(TableActivity.this);
        txvName.setText(R.string.txvName);
        txvName.setLayoutParams(tbrLayoutId);
        tbrHeader.addView(txvName);
        tblHeader.addView(tbrHeader);


    }
}
