package com.example.appcentralclim; // Verifique se o nome do seu pacote está correto

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent; // <-- Import necessário para "Intent"
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // <-- Import necessário para "Button"

import com.example.appcentralclim.Telas.TelaServico; // <-- Import da sua TelaServico

public class MainActivity extends AppCompatActivity {

    // 1. Declare o botão
    Button buttonGoToService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Conecte o botão da lógica com o botão do layout pelo ID
        buttonGoToService = findViewById(R.id.buttonGoToService);

        // 3. Crie a ação de clique (OnClickListener)
        buttonGoToService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 4. Crie uma "Intent" para iniciar a nova tela
                // O Intent é uma "intenção" de ir da tela atual (MainActivity.this)
                // para a tela de destino (TelaServico.class)
                Intent intent = new Intent(MainActivity.this, TelaServico.class);

                // 5. Execute a Intent
                startActivity(intent);
            }
        });
    }
}