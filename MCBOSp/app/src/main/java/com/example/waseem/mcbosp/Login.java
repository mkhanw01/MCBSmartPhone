package com.example.waseem.mcbosp;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by Waseem on 28-04-2016.
 */
public class Login  extends Activity implements OnClickListener{

    private SQLiteHelper SQLHelper;
    Button login,back;
    EditText username,pass;
    ArrayList<String[]> DATA = new ArrayList<String[]>();
    ListView lv ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        SQLHelper = new SQLiteHelper(this);
        username=(EditText)findViewById(R.id.editText1);
        pass=(EditText)findViewById(R.id.editText2);

        lv = (ListView) findViewById(R.id.srListView);
        login=(Button)findViewById(R.id.button1);
        back=(Button)findViewById(R.id.button2);
        login.setOnClickListener(this);

        back.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button1:
                String h=null;
                h=username.getText().toString();
                h=pass.getText().toString();
                String sss=SQLHelper.loginData(h);

                if(sss.equals("fail"))
                {
                    Toast.makeText(this, sss, 10).show();
                }else
                {
                    Toast.makeText(this, sss, 10).show();
                }
                break;
            case R.id.button2:
                Intent i = new Intent(Login.this,
                        ListAccounts.class);
                startActivity(i);
                break;

        }
    }
}

