package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="DINO-RAINBOWS";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;



    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES

    int candyXPosition;
    int candyYPosition;
    Rect candyHitbox;

    int rainbowXPosition;
    int rainbowYPosition;
    Rect rainbowHitbox;

    int dinosaurXPosition;
    int dinosaurYPosition;
    Rect dinosaurHitbox;

    int poopXPosition;
    int poopYposition;
    Rect poopHitbox;


    // ----------------------------

    // represent the TOP LEFT CORNER OF THE GRAPHIC

    // ----------------------------
    // ## GAME STATS
    // ----------------------------


    public GameEngine(Context context, int w, int h) {
        super(context);

        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;

        this.printScreenInfo();
    }

//        this.ballXPosition = this.screenWidth / 2;
//        this.ballYPosition = this.screenHeight / 2;

    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen
    }
    private void spawnEnemyShips() {
        Random random = new Random();

        //@TODO: Place the enemies in a random location

    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------
    String directiondinoIsMoving = "up";
    public void updatePositions() {
        if (directiondinoIsMoving == "up") {
            this.dinosaurXPosition = this.dinosaurXPosition + 250;

        }


    }



    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.WHITE);


            // DRAW THE PLAYER HITBOX
            // ------------------------
            // 1. change the paintbrush settings so we can see the hitbox
            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);

            Bitmap playerImage = BitmapFactory.decodeResource(this.getContext().getResources(),
                    R.drawable.dino64);
            canvas.drawBitmap(playerImage, 2150, 350, paintbrush);

            Bitmap playerImage1 = BitmapFactory.decodeResource(this.getContext().getResources(),
                    R.drawable.candy64);
            canvas.drawBitmap(playerImage1, 100, 920, paintbrush);

            Bitmap playerImage2 = BitmapFactory.decodeResource(this.getContext().getResources(),
                    R.drawable.poop64);
            canvas.drawBitmap(playerImage2, 300, 370, paintbrush);

            Bitmap playerImage3 = BitmapFactory.decodeResource(this.getContext().getResources(),
                    R.drawable.rainbow64);
            canvas.drawBitmap(playerImage3, 600, 690, paintbrush);



            // draw lines




            // this.canvas.drawRect(left, top, right, bottom, paintbrush);



            //@TODO: Draw game statistics (lives, score, etc)
            paintbrush.setTextSize(60);
            canvas.drawText("SCORE: 25", 20, 100, paintbrush);

            paintbrush.setTextSize(60);
            canvas.drawText("LIVES: 3", 400, 100, paintbrush);




            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------


    String fingerAction = "";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {

        }
        else if (userAction == MotionEvent.ACTION_UP) {

        }

        return true;
    }
}
