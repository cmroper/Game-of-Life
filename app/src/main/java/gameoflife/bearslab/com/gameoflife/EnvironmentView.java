package gameoflife.bearslab.com.gameoflife;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class EnvironmentView extends View {
    private static final int CELL_SIZE = 32; //pixel size of cell
    private static final int tsteps = 1800; //make this change with seekbar?
    private int VIEW_WIDTH;
    private int VIEW_HEIGHT;
    private int GRID_WIDTH;
    private int GRID_HEIGHT;

    private Environment environment;
    private int t = 0;
    private Paint liveCell;
    private Paint background;

    public EnvironmentView(Context context) {
        super(context);
    }

    public EnvironmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EnvironmentView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Creates a new live cell at screen location x, y
     * @param x the x location (pixels)
     * @param y the y location (pixels)
     */
    public void makeCell(float x, float y)
    {
        int index_x = (int)Math.floor(x/CELL_SIZE);
        int index_y = (int)Math.floor(y/CELL_SIZE);
        if (x < CELL_SIZE) {index_x = 0;}
        if (y < CELL_SIZE) {index_y = 0;}
        if (index_x == VIEW_WIDTH) { index_x--; }
        if (index_y == VIEW_HEIGHT) { index_y--; }
        environment.makeCellAlive(index_x, index_y);
        //Toast.makeText(getContext(),"x="+index_x+", y="+index_y, Toast.LENGTH_SHORT).show();
        postInvalidate();
    }

    /**
     * Steps one iteration
     */
    public void step()
    {
        environment.step();
        if (t < tsteps - 1) {
            t++;
        }
        postInvalidate();
    }

    public void reset() {
        environment = new Environment(GRID_WIDTH, GRID_HEIGHT);
        t = 0;
        postInvalidate();
    }

    public void createEnvironment() {
        VIEW_WIDTH = this.getWidth();
        VIEW_HEIGHT = this.getHeight();
        GRID_WIDTH = VIEW_WIDTH / CELL_SIZE;
        GRID_HEIGHT = VIEW_HEIGHT / CELL_SIZE;
        environment = new Environment(GRID_WIDTH, GRID_HEIGHT);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (environment == null) {
            createEnvironment();
        }
        background = new Paint();
        background.setColor(Color.rgb(210, 210, 210));
        liveCell = new Paint();
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);

        // draw cells
        for (int h = 0; h < GRID_HEIGHT; h++) {
            for (int w = 0; w < GRID_WIDTH; w++) {

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
                else if (environment.getState(w,h) == false){
                    liveCell.setColor(Color.rgb(255, 255, 255));
                    canvas.drawRect(
                            w * CELL_SIZE+1,
                            h * CELL_SIZE+1,
                            (w * CELL_SIZE) +CELL_SIZE-1,
                            (h * CELL_SIZE) +CELL_SIZE-1,
                            liveCell);
                }
            }
        }
    }

}
