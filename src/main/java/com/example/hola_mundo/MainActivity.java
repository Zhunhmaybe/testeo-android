package com.example.hola_mundo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etNombre;
    private TextInputEditText etEmail;
    private TextInputEditText etTelefono;
    private Button btnEnviar;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Enlazamos los componentes del XML con Java
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);
        btnEnviar = findViewById(R.id.btnEnviar);
        tvResultado = findViewById(R.id.tvResultado);

        // 2. Configuramos la acción al presionar el botón
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesarFormulario();
            }
        });
    }

    private void procesarFormulario() {
        String nombre = etNombre.getText() != null ? etNombre.getText().toString().trim() : "";
        String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
        String telefono = etTelefono.getText() != null ? etTelefono.getText().toString().trim() : "";

        // Validación simple
        if (nombre.isEmpty()) {
            etNombre.setError("Por favor ingresa tu nombre");
            etNombre.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Por favor ingresa tu correo");
            etEmail.requestFocus();
            return;
        }

        if (telefono.isEmpty()) {
            etTelefono.setError("Por favor ingresa tu teléfono");
            etTelefono.requestFocus();
            return;
        }

        // Mostramos el resultado en pantalla y un mensaje flotante (Toast)
        String resumen = "🎉 ¡Datos guardados con éxito!\n\n"
                + "👤 Nombre: " + nombre + "\n"
                + "📧 Correo: " + email + "\n"
                + "📱 Teléfono: " + telefono;

        tvResultado.setText(resumen);
        tvResultado.setVisibility(View.VISIBLE);

        Toast.makeText(this, "¡Bienvenido, " + nombre + "!", Toast.LENGTH_SHORT).show();
    }
}