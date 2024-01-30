package com.example.viewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String TAG = "GESTURE VIEW DEMO";
    ImageView imgViewSample; //cannot call findViewById until setContentView is called

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //bunch of statements
        } catch (Exception ex){
            Toast.makeText(this, "some error msg to the user",
                        Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
            Log.d(TAG,"There is an issue");
        }
        TextView txtViewSample = findViewById(R.id.txtViewSample);
        imgViewSample = findViewById(R.id.imgViewSample);
        Button btnShowTextOrImage = findViewById(R.id.btnShowTextOrImage);
        Button btnShowBoth = findViewById(R.id.btnShowBoth);
        Drawable img = ResourcesCompat
                .getDrawable(getResources(),
                        R.drawable.border,getTheme());
        //img.setBounds(0, 0, 80, 80);
        img.setBounds(0, 0,img.getIntrinsicWidth(),img.getIntrinsicHeight());
        txtViewSample.setCompoundDrawables(img,null,img,null);
        txtViewSample.setCompoundDrawablePadding(8);
        txtViewSample.setAlpha(1.0f); //alpha - 1.0f - fully opaque, alpha 0 - fully transparent

        btnShowBoth.setOnClickListener((View view) -> {
            txtViewSample.setVisibility(View.VISIBLE);
            imgViewSample.setVisibility(View.VISIBLE);
        });

        //adding click listener for btnShowTextOrImage.
        //compare the text or button to the string xml
        btnShowTextOrImage.setOnClickListener((View v) ->{

            if(btnShowTextOrImage.getText().toString()
                    .equals(getResources().getString(R.string.txtShowText))){
                txtViewSample.setVisibility(View.VISIBLE);
                imgViewSample.setVisibility(View.INVISIBLE);
                btnShowTextOrImage.setText(R.string.txtShowImage);
            } else{
                imgViewSample.setVisibility(View.VISIBLE);
                txtViewSample.setVisibility(View.GONE);
                btnShowTextOrImage.setText(R.string.txtShowText);
            }
        });

    }
}