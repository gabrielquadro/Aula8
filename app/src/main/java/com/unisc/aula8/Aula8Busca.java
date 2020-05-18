package com.unisc.aula8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aula8Busca extends AppCompatActivity {
    private ListView lista;
    private EditText etAno,etModelo;
    List<Map<String,Object>> ListaMap;
    private DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula8_busca);
        helper = new DataBaseHelper(this);

        lista = findViewById(R.id.listView);
        etAno = findViewById(R.id.etAnoSel);
        etModelo = findViewById(R.id.etmodeloSel);
    }

    public void addPressed(View view) {
        Intent intent = new Intent(this, Aula8Add.class);
        startActivity(intent);
    }

    public void buscarPressed(View view) {
        String query = "";
        if(etAno.getText().toString().isEmpty()){
            query = "SELECT * FROM carro";
        }else{
            query = "SELECT * FROM carro WHERE ano = " + etAno.getText().toString();
        }
        ListaMap = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        for(int i=0;i< c.getCount(); i++){
            Map<String,Object> mapa = new HashMap<>();
            String id = c.getString(0);
            String modelo = c.getString(1);
            String ano = c.getString(2);
            String valor = c.getString(3);
            mapa.put("id",id);
            mapa.put("modelo",modelo);
            mapa.put("ano",ano);
            mapa.put("valor",valor);
            ListaMap.add(mapa);
            c.moveToNext();
        }
        c.close();
        SimpleAdapter adapter = new SimpleAdapter(this,ListaMap,R.layout.lista_item,
                new String[] {"id","modelo","ano","valor"},
                new int[] {R.id.tvId,R.id.tvModelo,R.id.tvAno,R.id.tvValor});
        lista.setAdapter(adapter);
    }

    public void buscarMOdeloPressed(View view) {
        String query = "";
        if(etModelo.getText().toString().isEmpty()){
            query = "SELECT * FROM carro";
        }else{
            query = "SELECT * FROM carro WHERE modelo = '" + etModelo.getText().toString()+"' ORDER BY ano";
        }
        ListaMap = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        for(int i=0;i< c.getCount(); i++){
            Map<String,Object> mapa = new HashMap<>();
            String id = c.getString(0);
            String modelo = c.getString(1);
            String ano = c.getString(2);
            String valor = c.getString(3);
            mapa.put("id",id);
            mapa.put("modelo",modelo);
            mapa.put("ano",ano);
            mapa.put("valor",valor);
            ListaMap.add(mapa);
            c.moveToNext();
        }
        c.close();
        SimpleAdapter adapter = new SimpleAdapter(this,ListaMap,R.layout.lista_item,
                new String[] {"id","modelo","ano","valor"},
                new int[] {R.id.tvId,R.id.tvModelo,R.id.tvAno,R.id.tvValor});
        lista.setAdapter(adapter);
    }
}
