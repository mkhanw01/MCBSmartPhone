package com.example.waseem.mcbosp;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Waseem on 28-04-2016.
 */
public class LoanCalculatorActivity  extends Activity {
    private EditText customer_name,mLoanAmount, mInterestRate, mLoanPeriod;
    private TextView mMontlyPaymentResult, mTotalPaymentsResult;
    private Button loan_payments;
    /** Initializes the app when it is first created.*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan_details);
        customer_name = (EditText)findViewById(R.id.customer_name);
        mLoanAmount = (EditText)findViewById(R.id.loan_amount);
        mInterestRate = (EditText)findViewById(R.id.interest_rate);
        mLoanPeriod = (EditText)findViewById(R.id.loan_period);
        mMontlyPaymentResult = (TextView)findViewById(R.id.monthly_payment_result);
        mTotalPaymentsResult = (TextView)findViewById(R.id.total_payments_result);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return Utils.inflateMenu(this,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return  Utils.handleMenuOption(this,item);
    }

    public void showLoanPayments(View clickedButton) {

        double loanAmount = Integer.parseInt(mLoanAmount.getText().toString());
        double interestRate = (Integer.parseInt(mInterestRate.getText().toString()));
        double loanPeriod = Integer.parseInt(mLoanPeriod.getText().toString());
        double r = interestRate/1200;
        double r1 =  Math.pow(r+1,loanPeriod);

        double monthlyPayment = (double) ((r+(r/(r1-1))) * loanAmount);
        double totalPayment = monthlyPayment * loanPeriod;

        mMontlyPaymentResult.setText(new DecimalFormat("##.##").format(monthlyPayment));
        mTotalPaymentsResult.setText(new DecimalFormat("##.##").format(totalPayment));

        try {
            DBHelper dbhelper = new DBHelper(this);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Log.d("Account","Got Writable database");
            // execute insert command

            ContentValues values = new ContentValues();
            values.put( Database.LOANPAYMENTS_CUSTOMERNAME,   customer_name .getText().toString());
            values.put( Database.LOANPAYMENTS_LOANAMOUNT,  mLoanAmount.getText().toString());
            values.put( Database.LOANPAYMENTS_INTERESTRATE,   mInterestRate.getText().toString());
            values.put( Database.LOANPAYMENTS_MONTHS, mLoanPeriod.getText().toString());
            values.put( Database.LOANPAYMENTS_MONTHLYPAYMENTS,mMontlyPaymentResult.getText().toString());
            values.put( Database.LOANPAYMENTS_TOTALYPAYMENTS, mTotalPaymentsResult.getText().toString());


            long rows = db.insert(Database.LOANPAYMENTS_TABLE_NAME, null, values);
            db.close();
            if ( rows > 0)  {
                Toast.makeText(this, "Added Loan Payments Successfully!",	Toast.LENGTH_LONG).show();
                this.finish();

            }
            else
                Toast.makeText(this, "Sorry! Could not add account!",	Toast.LENGTH_LONG).show();

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

//    	public void loanPayments(View v) {
//
//			startActivity(new Intent(this, MainActivity.class));
//		}



    }
}


