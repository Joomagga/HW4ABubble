package com.example.jooma.hw4abubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

/**
 * Created by jooma on 2016-06-03.
 */
public class Bubble {
    private boolean[][] bubble; // true means bubble, false means pop.
    private int number_column; // number of bubbles in a column.
    private int number_row;    // number of bubbles in a row.
    private int scr_width;  // the width of device screen.
    private int scr_height; // the height of device screen.
    private int bubble_width;   // the width of bubble. equals scr_width / number_column.
    private int bubble_height;  // the height of bubble. equals scr_height / number_row.
    private Context context;

    // Constructor Methods
    public Bubble(Context context, int scr_wid, int scr_hei, int col, int row)
    {
        // initialize member variables.
        this.context = context;
        this.scr_width = scr_wid;
        this.scr_height = scr_hei;
        this.number_column = col;
        this.number_row = row;
        this.bubble_width = (int)(scr_width / number_column);
        this.bubble_height = (int)(scr_height / number_row);
        this.bubble = new boolean[number_column][number_row];
        resetBubble();
    }

    // General Methods
    public void pop(float point_x, float point_y)   // pops the touched bubble.
    {
        int touched_x = (int)(point_x / bubble_width);  // get the bubble's x point.
        int touched_y = (int)(point_y / bubble_height); // get the bubble's y point.

        bubble[touched_x][touched_y] = false;   // pop the bubble.
    }

    public void resetBubble()   // initialize the bubbles to true.
    {
        for (int i=0; i<number_column; i++)
            for (int j=0; j<number_row; j++)
                bubble[i][j] = true;
    }

    public void draw(Canvas canvas) // draw bubbles into canvas.
    {
        Paint paint_bubble = new Paint();   // paint for drawing bubble.
        Paint paint_pop = new Paint();  // paint for drawing popped bubble.
        paint_bubble.setColor(ContextCompat.getColor(context, R.color.bubble));
        paint_pop.setColor(ContextCompat.getColor(context, R.color.bubble));
        paint_pop.setStyle(Paint.Style.STROKE);
        canvas.drawColor(ContextCompat.getColor(context, R.color.board));

        for (int i=0; i<number_column; i=i+1)
            for (int j=0; j<number_row; j=j+1)
                if (bubble[i][j])   // draw bubbles.
                    canvas.drawCircle(i*bubble_width+bubble_width/2, j*bubble_height+bubble_height/2, bubble_width/2, paint_bubble);
                else    // draw popped bubbles.
                    canvas.drawCircle(i*bubble_width+bubble_width/2, j*bubble_height+bubble_height/2, bubble_width/2, paint_pop);
    }
}
