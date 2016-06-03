package com.example.jooma.hw4abubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Board board = new Board(this);
        setContentView(board);
    }

    protected class Board extends View
    {
        Bubble bubble;
        public Board(Context context)
        {
            super(context);
            bubble = new Bubble(context, 320, 480, 6, 8);
        }

        public void onDraw(Canvas canvas)
        {
            bubble.draw(canvas);
        }

        public boolean onTouchEvent(MotionEvent event)
        {
            // if it's an up (“release”) action
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // get the coordinates
                float x = event.getX();
                float y = event.getY();
                // see if they clicked on the box
                bubble.pop(x,y);
                invalidate();
            }
            // indicates that the event was handled
            return true;
        }
    }
}
