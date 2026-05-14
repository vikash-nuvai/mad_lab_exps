package com.example.ex4;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button blink, rotate, fade, move, slide, zoom, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageview);

        blink = findViewById(R.id.BTNblink);
        rotate = findViewById(R.id.BTNrotate);
        fade = findViewById(R.id.BTNfade);
        move = findViewById(R.id.BTNmove);
        slide = findViewById(R.id.BTNslide);
        zoom = findViewById(R.id.BTNzoom);
        stop = findViewById(R.id.BTNstop);

        blink.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.blink);
            imageView.startAnimation(anim);
        });

        rotate.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
            imageView.startAnimation(anim);
        });

        fade.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade);
            imageView.startAnimation(anim);
        });

        move.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.move);
            imageView.startAnimation(anim);
        });

        slide.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide);
            imageView.startAnimation(anim);
        });

        zoom.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.zoom);
            imageView.startAnimation(anim);
        });

        stop.setOnClickListener(v -> imageView.clearAnimation());
    }
}