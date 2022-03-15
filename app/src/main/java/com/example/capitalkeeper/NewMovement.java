package com.example.capitalkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class NewMovement extends AppCompatActivity {

    TextView textView_balance1, textView_balance2;
    Button button_ingresar;
    RadioButton radio_ingreso, radio_egreso;
    EditText edit_cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Nuevo movimiento");
        setContentView(R.layout.activity_new_movement);

        double balance = getIntent().getDoubleExtra("Balance", 0);

        textView_balance1 = (TextView) findViewById(R.id.txt_balance1);
        textView_balance2 = (TextView) findViewById(R.id.txt_balance2);
        button_ingresar = (Button) findViewById(R.id.btn_ingresar);
        radio_ingreso = (RadioButton) findViewById(R.id.rdbtn_ingreso);
        radio_egreso = (RadioButton) findViewById(R.id.rdbtn_egreso);
        edit_cantidad = (EditText) findViewById(R.id.edit_cantidad);

        textView_balance1.setText("Balance actual: $" + balance);
    }

    public void Movimiento (View view){
        String cantidadMovimientoS = edit_cantidad.getText().toString();
        Intent devolver_balance = new Intent(NewMovement.this, MainActivity.class);
        double cantidadMovimientoD = Double.parseDouble(cantidadMovimientoS);
        double balance = getIntent().getDoubleExtra("Balance", 0);

        if (cantidadMovimientoS.isEmpty()){
            Toast.makeText(this, "El campo de cantidad está vacío", Toast.LENGTH_SHORT).show();
        }
        else{

            if (radio_ingreso.isChecked()){
                double balanceFinal = balance + cantidadMovimientoD;
                textView_balance2.setText("Balance final: $" + balanceFinal);
                devolver_balance.putExtra("Balance2", balanceFinal);
                devolver_balance.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(this, "Movimiento registrado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(devolver_balance);
            }

            if (radio_egreso.isChecked()){
                double balanceFinal = balance - cantidadMovimientoD;
                textView_balance2.setText("Balance final: $" + balanceFinal);
                devolver_balance.putExtra("Balance2", balanceFinal);
                devolver_balance.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(this, "Movimiento registrado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(devolver_balance);
            }

            if (!radio_ingreso.isChecked() && !radio_egreso.isChecked()){
                Toast.makeText(this, "Seleccione el tipo de movimiento a realizar", Toast.LENGTH_SHORT).show();
            }
        }
    }
}