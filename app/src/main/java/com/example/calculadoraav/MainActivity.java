package com.example.calculadoraav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private TextView tvResultado;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir, btnIgual, btnhistorial;

    private ArrayList<Double> listaResultados = new ArrayList<>();
    private ArrayList<String> operaciones = new ArrayList<>();
    private double resultadoActual = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero = findViewById(R.id.etNumero);
        tvResultado = findViewById(R.id.tvResultado);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        btnIgual = findViewById(R.id.btnIgual);
        btnhistorial = findViewById(R.id.btnhistorial);

        btnSumar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardarOperacion("+");
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardarOperacion("-");
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardarOperacion("*");
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardarOperacion("/");
            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calcularResultado();
            }
        });

        btnhistorial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { historial(); }
        });
    }

    private void guardarOperacion(String operacion) {
        String numeroIngresado = etNumero.getText().toString();
        if (!numeroIngresado.isEmpty()) {
            double numero = Double.parseDouble(numeroIngresado);
            resultadoActual = numero;
            operaciones.add(operacion);
            etNumero.setText("");
        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }

    private void calcularResultado() {
        String numeroIngresado = etNumero.getText().toString();
        if (!numeroIngresado.isEmpty()) {
            double numero = Double.parseDouble(numeroIngresado);

            for (String operacion : operaciones) {
                switch (operacion) {
                    case "+":
                        resultadoActual += numero;
                        break;
                    case "-":
                        resultadoActual -= numero;
                        break;
                    case "*":
                        resultadoActual *= numero;
                        break;
                    case "/":
                        if (numero != 0) {
                            resultadoActual /= numero;
                        } else {
                            Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                }
            }

            if (resultadoActual == (int) resultadoActual) {
                tvResultado.setText("Resultado: " + (int) resultadoActual);
            } else {
                tvResultado.setText("Resultado: " + resultadoActual);
            }

            listaResultados.add(resultadoActual);
            etNumero.setText("");
            operaciones.clear();
        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }

    private void historial() {
        Intent intentH = new Intent(MainActivity.this, ListadoResultados.class);
        intentH.putExtra("ListaResultados", listaResultados); // Enviar solo los resultados
        startActivity(intentH);
    }
}
