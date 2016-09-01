package com.example.waseem.mcbosp;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Waseem on 28-04-2016.
 */
public class ListAccounts extends Activity {
    ListView listAccounts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaccounts);
        listAccounts = (ListView) this.findViewById(R.id.listAccounts);
        listAccounts.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View selectedView,
                                    int arg2, long arg3) {
                TextView textAccountId = (TextView) selectedView
                        .findViewById(R.id.textAccountId);
                Log.d("Accounts", "Selected Account Id : "
                        + textAccountId.getText().toString());
                Intent intent = new Intent(ListAccounts.this,
                        UpdateAccount.class);
                intent.putExtra("accountid", textAccountId.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return Utils.inflateMenu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return Utils.handleMenuOption(this, item);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            DBHelper dbhelper = new DBHelper(this);
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor accounts = db.query(Database.ACCOUNTS_TABLE_NAME, null,
                    null, null, null, null, null);


            String from[] = { Database.ACCOUNTS_ID, Database.ACCOUNTS_BANK,
                    Database.ACCOUNTS_HOLDERS, Database.ACCOUNTS_BALANCE };
            int to[] = { R.id.textAccountId, R.id.textBank, R.id.textHolder,
                    R.id.textBalance };

            SimpleCursorAdapter ca = new SimpleCursorAdapter(this,
                    R.layout.account, accounts, from, to);

            ListView listAccounts = (ListView) this
                    .findViewById(R.id.listAccounts);
            listAccounts.setAdapter(ca);
            dbhelper.close();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void addLogin(View v) {
        Intent intent = new Intent(this, AndroidDataBaseActivity.class);
        startActivity(intent);
    }

    public void addAccount(View v) {
        Intent intent = new Intent(this, AddAccount.class);
        startActivity(intent);
    }

    public void addTransaction(View v) {
        Intent intent = new Intent(this, AddTransaction.class);
        startActivity(intent);
    }

    public void recentTransactions(View v) {
        Intent intent = new Intent(this, ListRecentTransactions.class);
        startActivity(intent);
    }
    public void showLoanPayments(View v) {
        Intent intent = new Intent(this, LoanCalculatorActivity.class);
        startActivity(intent);
    }
    public void notification(View v) {
        Intent intent = new Intent(this,NotificationExample.class);
        startActivity(intent);
    }

    public void feedback(View v) {
        Intent intent = new Intent(this,NoteList.class);
        startActivity(intent);
    }
    public void sendmail(View v) {
        Intent intent = new Intent(this,SendEmailActivity.class);
        startActivity(intent);
    }

}

