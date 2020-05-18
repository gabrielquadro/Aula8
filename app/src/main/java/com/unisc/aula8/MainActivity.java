package com.unisc.aula8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private DataBaseHelperUsuario helper;
    private EditText etusuario, etsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DataBaseHelperUsuario(this);

        etusuario = findViewById(R.id.etusuario);
        etsenha = findViewById(R.id.etsenha);

    }

    public void entrarPressed(View view) {
        String query = "SELECT * FROM usuario";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        int count = 0;
        for(int i=0;i< c.getCount(); i++){
            String usuario = c.getString(1);
            String senha = c.getString(2);
            if(usuario.equals(etusuario.getText().toString())){
                if(senha.equals(etsenha.getText().toString())) {
                    Intent intent1 = new Intent(this, Aula8Busca.class);
                    startActivity(intent1);
                    count ++;
                }
            }
            c.moveToNext();
        }
        if(count == 0){
            Toast.makeText(this, "Login Invalido", Toast.LENGTH_SHORT).show();
        }
        c.close();
    }

    public void novoPressed(View view) {
        Intent intent = new Intent(this, NovoUsuario.class);
        startActivity(intent);
    }
}
