package com.bearslab.gameoflife;

import android.graphics.Color;
import android.view.MotionEvent;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author kartoon
 *  @version Oct 9, 2014
 */

public class EnvironmentView
    extends View implements OnTouchListener
{
    private static final int CELL_SIZE = 24; //pixel size of cell
    private static int WIDTH = 720 / CELL_SIZE; //num cells
    private static int HEIGHT = 1128 / CELL_SIZE;//num cells
    private static final int tsteps = 1800; //make this change with seekbar?
    private Environment environment;
    private int t = 0;
    private Paint liveCell;
    private Paint background;
    // ----------------------------------------------------------

    /**
     * Create a new GridView object.
     * @param context
     */
    public EnvironmentView(Context context)
    {
        super(context);
        int SCREEN_WIDTH = getContext().getResources().getDisplayMetrics().widthPixels;
        int SCREEN_HEIGHT= getContext().getResources().getDisplayMetrics().heightPixels;
        
        
        //make a new environment with 30x50 cells
        environment = new Environment(WIDTH, HEIGHT);
        background = new Paint();
        background.setColor(Color.rgb(255, 255, 255));
        liveCell = new Paint();
    }

    /**
     * Create a new GridView object.
     * @param context
     * @param attrs
     */
    public EnvironmentView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        environment = new Environment(WIDTH, HEIGHT);
        background = new Paint();
        background.setColor(Color.rgb(200, 200, 200));
        liveCell = new Paint();
    }

    /**
     * Create a new GridView object.
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public EnvironmentView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        environment = new Environment(WIDTH, HEIGHT);
        background = new Paint();
        background.setColor(Color.rgb(255, 255, 255));
        liveCell = new Paint();
    }

    /**
     * Creates a new live cell at screen location x, y
     * @param x the x location (pixels)
     * @param y the y location (pixels)
     */
    public void makeCell(float x, float y)
    {
    	//TODO: fix right end   	
        int index_x = (int)Math.floor(x/CELL_SIZE);
        int index_y = (int)Math.floor(y/CELL_SIZE);
        //if (x < CELL_SIZE) {index_x = 0;}
        if (y < CELL_SIZE) {index_y = 0;}
        if (index_x == WIDTH) { index_x--; }
        if (index_y == HEIGHT) { index_y--; }
        environment.makeCellAlive(index_x, index_y);
        //Toast.makeText(getContext(),"x="+index_x+", y="+index_y, Toast.LENGTH_SHORT).show();
        postInvalidate();
    }

    
    /**
     * Draws a sweet glider
     * might have things already around the glider
     * TODO: make glider repositionable?
     */
    public void drawGlider()
    {
        //environment.drawGlider();
        postInvalidate();
    }

    /**
     * Steps one iteration
     */
    public void step()
    {
        environment.step();
        
        if (t < tsteps - 1)
        {
            t++;
        }
        
        postInvalidate();
    }


    public void reset()
    {
        environment = new Environment(WIDTH, HEIGHT);
        t = 0;
        postInvalidate();
    }

    protected void onDraw(Canvas canvas)
    {

        // draw background
        canvas.drawRect(0, 0, 720, 1128, background);

        // draw cells
        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
            	
                if (environment.getState(w, h))
                {

                    liveCell.setColor(Color.rgb(0, 0, 0));
                    canvas.drawRect(
                        w * CELL_SIZE,
                        h * CELL_SIZE,
                        (w * CELL_SIZE) +CELL_SIZE,
                        (h * CELL_SIZE) +CELL_SIZE,
                        liveCell);
                }
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        // TODO Auto-generated method stub
        return false;
    }


}

