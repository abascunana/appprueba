package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity<nSwipeTouchListener> extends AppCompatActivity {
    nSwipeTouchListener onSwipeTouchListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se implementa un touch listener que se activa al crear la app
        onSwipeTouchListener = (nSwipeTouchListener) new OnSwipeTouchListener(this, findViewById(R.id.relativeLayout));
    }

    //ONSWIPETOUCH listener de la interfaz Vies.OnTouchListener
    public static class OnSwipeTouchListener implements View.OnTouchListener {
        //Objeto gesture detector
        private final GestureDetector gestureDetector;
        //El contexto de cada app es lo que permite a otras partes de la app saber, como su nombre indica, el contexto en el que la aplicación se encuentra
        Context context;

        //Se activa el listener y es proveído de el view y el contexto
        OnSwipeTouchListener(Context ctx, View mainView) {
            gestureDetector = new GestureDetector(ctx, new GestureListener());
            mainView.setOnTouchListener(this);
            context = ctx;
        }
    //Cuando se toca la pantalla de la app activa el detector de gestos
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

//Creación de la clase gestureListener que extiende su clase padre SimpleOn
        public class GestureListener extends
                GestureDetector.SimpleOnGestureListener {
            //Mínimo de velocidad
            private static final int SWIPE_THRESHOLD = 200;
            private static final int SWIPE_VELOCITY_THRESHOLD = 5;
            //Aunque no sea necesario per se en este contexto, onDown establece el inicio que seguirá al siguiente motionevent en "onFling()" https://developer.android.com/reference/android/view/GestureDetector.OnGestureListener#onFling(android.view.MotionEvent,%20android.view.MotionEvent,%20float,%20float)
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
            //on fling es el método en el que se detectará al hacer un gesto de movimiento "violento" por la pantalla
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    //la diferencia de Y es igual a la y del primer (cuando se pone el dedo en la pantalla) movimiento menos el del movimiento final ()
                    float diffY = e2.getY() - e1.getY();
                    //la diferencia de X es igual a la x del primer movimiento menos el del movimiento final
                    float diffX = e2.getX() - e1.getX();
                  //Si la diferencia de x es mayor que y
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD ) {
                        //restricción para que el usuario haga movimientos más precisos
                            if (diffY < 100 && diffY > -100){
                                //Si la diferencia da un resultado positivo eso significa que el movimiento ha sido de derecha a izquierda
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                        else {
                            Toast.makeText(context, "Pasa el dedo bien por la pantalla", Toast.LENGTH_SHORT).show();
                        }


                        }
                    }
                    //Si "Y" es mayor que la distancia y la velocidad mínima eso significará que el usuario ha tomado la velocidad desde el punto y
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {

//restricción para que el usuario haga movimientos más precisos
                        if (diffX < 100 && diffX > -100) {
                            //Si hay más "Y" en el segundo movimiento que en el primero eso significa que se ha hecho el swipe de arriba a abajo
                            if (diffY > 0) {
                                onSwipeBottom();
                            } else {
                                onSwipeTop();
                            }
                            result = true;
                        }
                        else {
                            Toast.makeText(context, "Pasa el dedo bien por la pantalla", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }


        //Mensajes al acceder a cada método de dirección
        void onSwipeRight() {
            Toast.makeText(context, "Swiped Right", Toast.LENGTH_SHORT).show();
            this.onSwipe.swipeRight();

        }
        void onSwipeLeft() {
            Toast.makeText(context, "Swiped Left", Toast.LENGTH_SHORT).show();
            this.onSwipe.swipeLeft();
        }
        void onSwipeTop() {
            Toast.makeText(context, "Swiped Up", Toast.LENGTH_SHORT).show();
            this.onSwipe.swipeTop();
        }
        void onSwipeBottom() {
            Toast.makeText(context, "Swiped Down", Toast.LENGTH_SHORT).show();
            this.onSwipe.swipeBottom();
        }
        //Esta interfaz ayuda a implementar los métodos de esta clase de forma más ordenada
        interface onSwipeListener {
            void swipeRight();
            void swipeTop();
            void swipeBottom();
            void swipeLeft();
        }
        onSwipeListener onSwipe;
    }
    //el botón que invoca a la segunda actividad (MainActivity2)
    public void onClickBtn(View v)
    {
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }


}