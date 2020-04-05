package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.notes);
        String archivos[]  = fileList();
        if (ArchivoExiste (archivos, "nota.txt")){
            try{
                InputStreamReader archivo = new InputStreamReader(openFileInput("nota.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String nota = "";
                while (linea != null){
                    nota = nota + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                text.setText(nota);
            }
            catch (IOException ex){

            }
        }
    }

    private boolean ArchivoExiste(String archivo [] , String nombre){
      for (int i = 0; i < archivo.length ; i++)
          if (nombre.equals(archivo[i]))
              return true;
          return false;
    }

    public void save (View view)  {
        try{
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("nota.txt", Activity.MODE_PRIVATE));
            osw.write(text.getText().toString());
            osw.flush();
            osw.close();
        }
        catch (IOException ex){

        }
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        finish();

    }
}