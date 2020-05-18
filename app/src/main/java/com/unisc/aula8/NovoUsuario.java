package com.unisc.aula8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NovoUsuario extends AppCompatActivity {

    private DataBaseHelperUsuario helper;
    private EditText usuario,senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        helper = new DataBaseHelperUsuario(this);
        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);

    }

    public void gravarPressed(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("usuario",usuario.getText().toString());
        c.put("senha",senha.getText().toString());

        long res = db.insert("usuario",null,c);
        if(res != -1){
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            usuario.setText("");
            senha.setText("");
        }else{
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
