package com.example.pendataan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText txtNama;
    public static final String FILENAME = "list_nama.txt";
    private final String lineSeparator = System.getProperty("line.separator");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNama = (EditText) findViewById(R.id.txtNama);

        Button buttonSimpan = findViewById(R.id.buttonOne);

        buttonSimpan.setOnClickListener(v -> simpanNama());

        findViewById(R.id.buttonTwo).setOnClickListener(v -> tampilNama());
    }

    private void tampilNama() {
        startActivity(new Intent(this, DisplayActivity.class));
    }

    private void simpanNama() {
        try (FileOutputStream fos = openFileOutput(FILENAME, MODE_APPEND)) {
            fos.write(txtNama.getText().toString().getBytes());
            fos.write(lineSeparator.getBytes());
            fos.flush();
            txtNama.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}