package com.example.appgallery_paulo.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.appgallery_paulo.R;
import com.example.appgallery_paulo.models.Imagem;
import com.example.appgallery_paulo.views.DialogImagem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.GaleriaViewHolder> {

    private List<Imagem> listaImagens;
    private Context context;
    private Activity activity;

    public GaleriaAdapter(List<Imagem> listaImagens, Context context, Activity activity) {
        this.listaImagens = listaImagens;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public GaleriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_imagem, parent, false);
        GaleriaAdapter.GaleriaViewHolder viewHolder= new GaleriaViewHolder(view, this.activity);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaViewHolder holder, int position) {
        Imagem imagem = listaImagens.get(position);
        holder.imagemSelecionada = imagem;
        Glide.with(this.context).load(imagem.getUrl()).into(holder.ivImagem);

    }

    @Override
    public int getItemCount() {
        return listaImagens.size();
    }

    public static class GaleriaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagem;
        Imagem imagemSelecionada;
        public GaleriaViewHolder(@NonNull View itemView, Activity activity) {
            super(itemView);
            ivImagem = itemView.findViewById(R.id.ivImagem);
            ivImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DialogImagem(view.getContext(), imagemSelecionada, activity).show();
                }
            });
        }
    }
}
