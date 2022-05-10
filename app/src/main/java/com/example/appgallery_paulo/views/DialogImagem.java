package com.example.appgallery_paulo.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appgallery_paulo.R;
import com.example.appgallery_paulo.models.Imagem;

public class DialogImagem extends Dialog {

    private TextView tvNomeExibicao;
    private TextView tvSeguidores;
    private TextView tvDescricao;
    private ImageView ivImagemExibicao;
    private Context context;
    private Imagem imagem;

    public DialogImagem(Context context, Imagem imagem, Activity activity) {
        super(context);
        this.context = context;
        this.imagem = imagem;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.dialog_imagem);
        Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_imagem, null);
        layout.setMinimumWidth((int) (rect.width() * 0.9f));
        layout.setMinimumHeight((int) (rect.height() * 0.5f));
        setContentView(layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvNomeExibicao = findViewById(R.id.tvNomeExibicao);
        tvSeguidores = findViewById(R.id.tvSeguidores);
        tvDescricao = findViewById(R.id.tvDescricao);
        ivImagemExibicao = findViewById(R.id.ivImagemExibicao);

        Glide.with(this.getContext()).load(imagem.getUrl()).into(ivImagemExibicao);
        tvNomeExibicao.setText(imagem.getNomeExibicao());
        if(!imagem.getDescricao().equals("null"))
            tvDescricao.setText(imagem.getDescricao());
        tvSeguidores.setText(getContext().getString(R.string.seguidores, imagem.getSeguidores()));

    }
}
