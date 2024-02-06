package com.example.viewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean bigger = false;
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
                    .equals(getString(R.string.txtShowText))){
                txtViewSample.setVisibility(View.VISIBLE);
                imgViewSample.setVisibility(View.INVISIBLE);
                btnShowTextOrImage.setText(R.string.txtShowImage);
            } else{
                imgViewSample.setVisibility(View.VISIBLE);
                txtViewSample.setVisibility(View.GONE);
                btnShowTextOrImage.setText(R.string.txtShowText);
            }
        });

        //new CustomTouchListener() calling the class that just created
        txtViewSample.setOnTouchListener(new CustomTouchListener(MainActivity.this){
            @Override
            public void onSingleClick() {

                super.onSingleClick();

                if(txtViewSample.getCurrentTextColor() != getColor(R.color.purple_200)){
                    txtViewSample.setTextColor(getColor(R.color.purple_200));
                } else{
                    //txtViewSample.setTextColor(Color.WHITE);
                    txtViewSample.setTextColor(Color.rgb(255,255,255));
                    //txtViewSample.setTextColor(Color.parseColor(#));
                }
            }

            @Override
            public void onDoubleClick() {
                //if not bigger change it to bigger
                if(!bigger){ //pixels - setTextSize
                    txtViewSample.
                            setTextSize(
                                    txtViewSample.getTextSize()/
                                            getResources().getDisplayMetrics().density + 10); // return sp
                    bigger = true;
                } else{
                    txtViewSample.
                            setTextSize(
                                    txtViewSample.getTextSize()/
                                            getResources().getDisplayMetrics().density - 10); // return sp
                    bigger = false;
                }

                super.onDoubleClick();


            }

            @Override
            public void onLongClick() {
                super.onLongClick();
                if(txtViewSample.getPaint().isStrikeThruText()){
                    txtViewSample.setPaintFlags(txtViewSample.getPaintFlags() & ~paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    txtViewSample.setPaintFlags(txtViewSample.getPaintFlags() | ~paint.STRIKE_THRU_TEXT_FLAG);
                }
            }

            @Override
            public void onUpSwipe() {
                super.onUpSwipe();
                int horzGravity = txtViewSample.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK;
                txtViewSample.setGravity(verGravity | Gravity.TOP);
            }

            @Override
            public void onDownSwipe() {
                super.onDownSwipe();
                int horzGravity = txtViewSample.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK;
                txtViewSample.setGravity(verGravity | Gravity.BOTTOM);
            }

            @Override
            public void onLeftSwipe() {
                super.onLeftSwipe();
                int verGravity = txtViewSample.getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
                txtViewSample.setGravity(verGravity | Gravity.LEFT);
            }

            @Override
            public void onRightSwipe() {
                super.onRightSwipe();

                int verGravity = txtViewSample.getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
                txtViewSample.setGravity(verGravity | Gravity.RIGHT);
            }
        });

        imgViewSample.setOnTouchListener(new CustomTouchListener(MainActivity.this){
            @Override
            public void onDoubleClick() {
                //double click fuctionality for imgView
                super.onDoubleClick();
                if(imgViewSample.getScaleType() == ImageView.ScaleType.FIT_CENTER){
                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_XY);
                }else{
                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
            }
            @Override
            public void onSingleClick() {
                //double click fuctionality for imgView
                super.onSingleClick();
                if (imgViewSample.getDrawable().getConstanstState() !=
                      ResoucesCompat.getDrawable(getResouces(), R.drawable.bird,getTheme().getConstantState()   ) ) {
                    imgViewSample.setImageResouce(R.drawable.bird);
                }else{
                    imgViewSample.setImageResource(R.drawable.fire);
                    //imgViewSample.setImageResource(0); //no image setup
                }
            }
        });

    }

}