package com.example.viewdemo;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

//the job of this class is only to define gestures
public class CustomTouchListener implements View.OnTouchListener{

    Context context; //passing the activity vieww as context
    GestureDetectorCompat gestureDetectorCompat;
    //GestureDetectorCompat  - needs 2 things for creatiom
    //content, gesture listener.
    //After creating CustomerGestureLIstener
    //Generate Constructor for CustomTouchListener

    public CustomTouchListener(Context context){
        this.context = context;
        gestureDetectorCompat = new GestureDetectorCompat(context, new
                CustomGestureListener());
    }
    public class CustomGestureListener
        extends GestureDetector.SimpleOnGestureListener{
        public CustomGestureListener() {
            super();
        }

        @Override
        public boolean onDown(@NonNull MotionEvent e) {
            //return super.onDown(e); //base class onDown return false
            //passing the call to confirm
            return true;
        }


        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            onDoubleClick();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
            onSingleClick();//generate in the outer class and change the scope from private to public
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            onLongClick();
            super.onLongPress(e);
        }




        //need tho identify how fast is the swipe (velocitythrehosld)
        //horizontal swipe: abs(x_off) > abs(y_off) && abs(x_off) > dis_threhold && abs(vel x) > vel_threshold
        @Override
        public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            float SWIPE_DIST_THRESHOLD = 10;
            float SWIPE_VEL_THRESHOLD = 20;

            float yOff = e2.getX() - e1.getX();
            float xOff = e2.getY() - e2.getY();

            if (Math.abs(xOff) > Math.abs(yOff)
            && Math.abs(xOff)> SWIPE_DIST_THRESHOLD
            && Math.abs(velocityX) > SWIPE_VEL_THRESHOLD){
                //horizontal swipe
                if(xOff > 0){
                    //right swipe
                    onRightSwipe();
                } else {
                    //left swipe
                    onLeftSwipe();
                }
            } else if (Math.abs(yOff) > Math.abs(xOff)
                    && Math.abs(yOff)> SWIPE_DIST_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VEL_THRESHOLD) {
                if(yOff > 0){
                    //down swipe
                    onDownSwipe();
                } else {
                    //up swipe
                    onUpSwipe();
                }
            }


            return super.onFling(e1, e2, velocityX, velocityY);
        }


    }

    public void onUpSwipe() { Log.d("GESTUREDEMO","Swipe Up dectected");
    }

    public void onDownSwipe() {
        Log.d("GESTUREDEMO","Swipe Down dectected");
    }

    public void onLeftSwipe() { Log.d("GESTUREDEMO","Swipe Left dectected");
    }

    public void onRightSwipe() { Log.d("GESTUREDEMO","Swipe Right dectected");
    }

    public void onLongClick() {
        Log.d("GESTUREDEMO","Long click dectected");
    }

    public void onDoubleClick() {
        Log.d("GESTUREDEMO","Double click dectected");
    }

    public void onSingleClick() {
        Log.d("GESTUREDEMO","Single click dectected");
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //return false;
        return gestureDetectorCompat.onTouchEvent(event);
        //connect to gestureDetector
        //return to onDown first, so must be true to call other gestures
    }
}
