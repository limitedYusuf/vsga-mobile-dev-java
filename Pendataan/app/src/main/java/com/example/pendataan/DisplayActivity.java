package com.example.pendataan;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    private ListView listNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        listNama = findViewById(R.id.listNama);
        memuatList();
    }

    private void memuatList() {
        List<String> data = bacaDataText();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listNama.setAdapter(adapter);
    }

    private List<String> bacaDataText() {
        List<String> result = new ArrayList<>();
        File file = new File(getFilesDir(), MainActivity.FILENAME);
        if(file.exists()) {
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();

                while (line != null) {
                    result.add(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.ni_hapus_semua) {
            if(deleteFile(MainActivity.FILENAME)) {
                Toast.makeText(this,"data telah dihapus", Toast.LENGTH_SHORT).show();
                memuatList();
            }
        }
        return  super.onOptionsItemSelected(item);
    }
}