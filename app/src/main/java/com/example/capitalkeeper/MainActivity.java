package com.example.capitalkeeper;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button_nuevo_movimiento;
    TextView textView_balance_gasto;

    double balance, gasto = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_nuevo_movimiento = (Button) findViewById(R.id.btn_nuevo_movimiento);
        textView_balance_gasto = (TextView) findViewById(R.id.txt_balance_gasto_num);

        SharedPreferences balancePreference = getSharedPreferences("datos", Context.MODE_PRIVATE);
        textView_balance_gasto.setText("$" + balancePreference.getString("balance", ""));
        balance = Double.parseDouble(balancePreference.getString("balance", ""));
    }

    @Override
    protected void onResume() {
        super.onResume();
        balance = getIntent().getDoubleExtra("Balance2", balance);

        SharedPreferences balancePreference = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = balancePreference.edit();
        editor.putString("balance", "" + balance);
        editor.commit();

        textView_balance_gasto.setText("$" + balance);
    }

    public void Nuevo_movimiento (View view){
        Intent nuevo_movimiento_activity = new Intent(this, NewMovement.class);
        nuevo_movimiento_activity.putExtra("Balance", balance);
        startActivity(nuevo_movimiento_activity);
    }

    public void Reiniciar_balance (View view){
        balance = 0;
        SharedPreferences balancePreference = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = balancePreference.edit();
        editor.putString("balance", "" + balance);
        editor.commit();

        textView_balance_gasto.setText("$" + balance);
    }
}