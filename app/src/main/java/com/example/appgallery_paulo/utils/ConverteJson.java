package com.example.appgallery_paulo.utils;

import com.example.appgallery_paulo.models.Imagem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConverteJson {

    public static List<Imagem> retornaImagen(String json){
        List<Imagem> listaImagens = new ArrayList<>();
        try{
            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject objArray = array.getJSONObject(i);
                try{
                    JSONArray imgArray = objArray.getJSONArray("images");
                    String nomeExibicao = objArray.getString("title");
                    for (int j = 0; j < imgArray.length(); j++) {
                        JSONObject objImg = imgArray.getJSONObject(j);
                        Imagem imagem = new Imagem();
                        imagem.setNomeExibicao(nomeExibicao);
                        if(objImg.getString("description") != null)
                            imagem.setDescricao(objImg.getString("description"));
                        if(objImg.getString("link") != null)
                            imagem.setUrl(objImg.getString("link"));
                        imagem.setSeguidores(String.valueOf(objImg.getLong("views")));

                        listaImagens.add(imagem);
                    }
                }catch (Exception ex){}



            }
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return listaImagens;
    }
}
