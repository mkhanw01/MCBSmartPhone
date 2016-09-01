package com.example.waseem.mcbosp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Waseem on 28-04-2016.
 */
public class AndroidDataBaseActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    private SQLiteHelper SQLHelper;
    Button insert,update,dalete,display,searchb,deletall,select,login;
    EditText username,pass,mailid,contact;
    ArrayList<String[]> DATA = new ArrayList<String[]>();
    ListView lv ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        SQLHelper = new SQLiteHelper(this);
        username=(EditText)findViewById(R.id.editText1);
        pass=(EditText)findViewById(R.id.editText2);
        mailid=(EditText)findViewById(R.id.editText3);
        contact=(EditText)findViewById(R.id.editText4);

        lv = (ListView) findViewById(R.id.srListView);
        insert=(Button)findViewById(R.id.button1);
        display=(Button)findViewById(R.id.button4);
        login=(Button)findViewById(R.id.button2);
        deletall=(Button)findViewById(R.id.button3);
        insert.setOnClickListener(this);
        display.setOnClickListener(this);
        login.setOnClickListener(this);
        deletall.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button1:
                if(SQLHelper.insertData(username.getText().toString(), pass.getText().toString(),mailid.getText().toString(),contact.getText().toString())<0){
                    Toast.makeText(this, "Failed Insert data", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Successfully Inserted", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button4:
                DATA=SQLHelper.selectalldatabase();
                if(!DATA.isEmpty())
                    lv.setAdapter(new MyCustomBaseAdapter(this, DATA));
                break;
            case R.id.button2:
                Intent i = new Intent(AndroidDataBaseActivity.this,
                        Login.class);
                startActivity(i);
                break;
            case R.id.button3:
                SQLHelper.deleteAll();
                DATA=SQLHelper.selectalldatabase();
                lv.setAdapter(new MyCustomBaseAdapter(this, DATA));
                break;
        }
    }
}

