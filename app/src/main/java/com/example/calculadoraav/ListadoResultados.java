package com.example.calculadoraav;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListadoResultados extends AppCompatActivity {

    private Button btnVolver;
    private TableLayout tablaResultados;
    private ArrayList<Double> listaResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial);

        listaResultados = (ArrayList<Double>) getIntent().getSerializableExtra("ListaResultados");

        tablaResultados = findViewById(R.id.tableNumeros);
        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        if (listaResultados != null && !listaResultados.isEmpty()) {
            for (Double resultado : listaResultados) {
                TableRow fila = new TableRow(this);
                TextView tvResultado = new TextView(this);

                if (resultado == Math.floor(resultado)) {
                    tvResultado.setText(String.valueOf(resultado.intValue()));
                } else {
                    tvResultado.setText(String.valueOf(resultado));
                }

                fila.addView(tvResultado);
                tablaResultados.addView(fila);
            }
        } else {
            TableRow fila = new TableRow(this);
            TextView tvMensaje = new TextView(this);
            tvMensaje.setText("No hay resultados en el historial.");
            fila.addView(tvMensaje);
            tablaResultados.addView(fila);
        }
    }
}
