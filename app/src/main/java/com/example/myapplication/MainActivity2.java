package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity2.class.getSimpleName();



    @Override
    protected void onStart() {
        super.onStart();
        Log.i("SecondAc","Activity Resumed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("SecondAc","Activity Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("SecondAc","Activity Destroyed");
        Log.d(LOG_TAG, "End SecondActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("SecondAc","Activity Paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SecondAc","Activity Resumed");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onClickBtn(View v)
    {
        startActivity(new Intent(MainActivity2.this, MainActivity.class));
    }


}