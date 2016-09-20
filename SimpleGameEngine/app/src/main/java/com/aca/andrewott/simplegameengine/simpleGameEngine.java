package com.aca.andrewott.simplegameengine;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//Typed, not copied. For Study Only

public class SimpleGameEngine extends Activity {

    //gameView is the view of the game
    //also holds the logic(?) of the game
    //responds to screen touches as well
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize gameView and set it as the view
        gameView = new GameView(this);
        setContentView(gameView);

    }

    // below this line is where the GameView class will go

    /*
    this is where we implement GameView. it is an inner class,
    note how the single curly brace is inside Simple game engine.

    notice we implement runnable so we have a thread and so we
    can override the run method.
     */

    class GameView extends SurfaceView implements Runnable {

        // following is our thread, to override the run method
        Thread gameThread = null;

        // this is new. we need a SurfaceHolder when we use
        // Paint and Canvas in a thread. we will see this in action
        // in the draw method soon.
        SurfaceHolder ourHolder;

        // a boolean which we will set or unset
        // when the game is running or is not
        volatile boolean playing;

        // Canvas and Paint objects
        Canvas canvas;
        Paint paint;

        // variable that tracks frame rate
        long fps;

        // this is used to help calculate fps
        private long timeThisFrame;

        // we declare an object of the type Bitmap
        Bitmap bitmapBob;

        // starts off not moving
        boolean isMoving = false;

        // he can walk 150 pixels per second
        float walkSpeedPerSecond = 150;

        // he starts 10 pixels from the left
        float bobXPosition = 10;

        // when we initialize (call new()) on gameView
        // this special constructor method runs.
        public GameView(Context context) {

            // the next line asks the SurfaceView
            // class to set up our object.
            super(context);

            // initialize ourHolder and paint objects
            ourHolder = getHolder();
            paint = new Paint();

            // bring forth the bob from his .png file
            bitmapBob = BitmapFactory.decodeResource(this.getResources(), R.drawable.bob_png);

            playing = true;
        }

            @Override
            public void run() {
                while (playing) {

                    // capture current time in milliseconds in startTimeFrame
                    long startFrameTime = System.currentTimeMillis();

                    // update the frame
                    update();

                    // draw the frame
                    draw();

        /*
        calculate the fps this frame.
        we can then use the result to time animations and more.
         */
                    if (timeThisFrame > 0) {
                        fps = 1000 / timeThisFrame;
                    }

                }
            }

            /*
            All updates go in the following void method.. I think that's what it is called.
            later on i can put dozens (arrays) of objects in here.
            also useful for collision detection.
             */

            public void update() {

        // if bob is moving (player is touching the screen)
        // then move him to the right based on current fps and his target speed.
            if(isMoving) {
                bobXPosition = bobXPosition + (walkSpeedPerSecond / fps);
            }
        }

        // draw the scene
        public void draw() {

        // make sure our drawing surface is valid.
            if (ourHolder.getSurface().isValid()) {

                // lock the canvas ready to draw.
                canvas = ourHolder.lockCanvas();

                // background color
                canvas.drawColor(Color.argb(255, 26, 128, 182));

                // brush color for drawing
                paint.setColor(Color.argb(255, 249, 129, 0));

                // text size increase
                paint.setTextSize(45);

                // display current fps onscreen
                canvas.drawText("FPS: " + fps, 20, 40, paint);

                // draw bob at bobXPosition
                canvas.drawBitmap(bitmapBob, bobXPosition, 200, paint);

                // draw everything to the screen and unlock the drawing surface.
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
        // if SimpleGameEngine Activity is paused/stopped shut down our thread.
        public void pause() {
            playing = false;
            try {
                gameThread.join();
            }
            catch (InterruptedException e) {
                Log.e("Error: ", "Joining Thread");
            }
        }
        // if SimpleGameEngine Activity is started then start our thread.
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        // the SurfaceView class implements onTouchListener so we ca override methods and detect screen touches.
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

        // player has touched the screen
                case MotionEvent.ACTION_DOWN:

        // set isMoving so bob is moved in the update method.
                    isMoving = true;
                    break;

        // player has removed finger from screen
                case MotionEvent.ACTION_UP:

        // set isMoving so bob doesn't move
                    isMoving = false;
                    break;
            }
            return true;
        }

    }
        //More SimpleGameEngine methods will go below this line

        // executes when player starts game
    @Override
    protected void onResume() {
        super.onResume();

        // tell the gameView to resume method in order to execute
        gameView.resume();
    }
        // executes when player leaves game
    @Override
    protected void onPause() {
        super.onPause();

        // tell gameView to pause method in order to execute
        gameView.pause();
    }





















}
