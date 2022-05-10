package com.example.appgallery_paulo.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.appgallery_paulo.R;
import com.example.appgallery_paulo.adapters.GaleriaAdapter;
import com.example.appgallery_paulo.conexao.Conexao;
import com.example.appgallery_paulo.models.Imagem;
import com.example.appgallery_paulo.utils.ConverteJson;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Imagem> listaImagens;
    private TextInputEditText tvFiltro;
    private RecyclerView rvGaleria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFiltro = findViewById(R.id.tvFiltro);
        rvGaleria = findViewById(R.id.rvGaleria);

    }

    public void buscarImagens(View view) {
        Integer num = 0;
        new DownloadImagens().execute(tvFiltro.getText().toString().trim());
    }

    public void atualizaLista(){
        GaleriaAdapter adapter = new GaleriaAdapter(listaImagens, this, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvGaleria.setLayoutManager(llm);
        rvGaleria.setAdapter(adapter);
    }

    private class DownloadImagens extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String json = Conexao.retornaJSON(strings[0]);
            listaImagens = ConverteJson.retornaImagen(json);

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            atualizaLista();
        }
    }
}