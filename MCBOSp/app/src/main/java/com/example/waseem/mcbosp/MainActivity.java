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
import android.widget.TextView;

public class MainActivity extends  Activity {
    private String _Id;
    private String accountId;
    private TextView textAcno;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan_paymentdetails);

        _Id = this.getIntent().getStringExtra("transid");
        Log.d("Account", "Trans id : " + _Id);

        textAcno = (TextView) this.findViewById(R.id.textAcno1);
        TextView textTransDate = (TextView) this
                .findViewById(R.id.textTransDate1);
        TextView textTransType = (TextView) this
                .findViewById(R.id.textTransType1);
        TextView textTransAmount = (TextView) this
                .findViewById(R.id.textTransAmount1);
        TextView textChequeNo = (TextView) this.findViewById(R.id.textChequeNo1);
        TextView textChequeParty = (TextView) this
                .findViewById(R.id.textChequeParty1);


        DBHelper dbhelper = new DBHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor tran = db
                .rawQuery(
                        "select _id,customer_name,loan_amount,interest_rate,month,month_pay,totaly_pay  from loanpayments ",
                        new String[] { _Id });

        if (tran.moveToFirst()) {
            accountId = tran.getString(tran
                    .getColumnIndex(Database.LOANPAYMENTS_ID));
            textAcno.setText(tran.getString(tran
                    .getColumnIndex(Database.ACCOUNTS_ACNO)));
            textTransDate.setText(tran.getString(tran
                    .getColumnIndex(Database.LOANPAYMENTS_CUSTOMERNAME)));
            textTransType.setText(tran.getString(tran
                    .getColumnIndex(Database.LOANPAYMENTS_LOANAMOUNT)));
            textTransAmount.setText(tran.getString(tran
                    .getColumnIndex(Database.LOANPAYMENTS_INTERESTRATE)));
            textChequeNo.setText(tran.getString(tran
                    .getColumnIndex(Database.LOANPAYMENTS_MONTHS)));
            textChequeParty.setText(tran.getString(tran
                    .getColumnIndex(Database.LOANPAYMENTS_MONTHLYPAYMENTS)));

        } else
            Log.d("Accounts", "No transaction found!");

        db.close();
        dbhelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return Utils.inflateMenu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return Utils.handleMenuOption(this, item);
    }


    public void showAccountDetails(View v) {
        Intent intent = new Intent(this, UpdateAccount.class);
        intent.putExtra("accountid", accountId);
        startActivity(intent);
    }
}


