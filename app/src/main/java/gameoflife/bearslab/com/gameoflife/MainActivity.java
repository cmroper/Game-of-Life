package gameoflife.bearslab.com.gameoflife;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    //---MY FIELDS---------------------
    private ToggleButton run;
    private Button step;
    private Button reset;
    //private Button glider;
    private EnvironmentView eView;
    private int mInterval = 180; // in milliseconds
    private Handler mHandler;
    //---------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        step = (Button)findViewById(R.id.step);
        eView =  (EnvironmentView)findViewById(R.id.view1);
        run = (ToggleButton)findViewById(R.id.run);
        step.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                eView.step();
            }
        });

        reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                eView.reset();
                //Toast.makeText(getApplicationContext(),"Reset clicked", Toast.LENGTH_LONG).show();

            }
        });
        eView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                float touch_x = event.getX();
                float  touch_y = event.getY();

                eView.makeCell(touch_x, touch_y);
                //  int action = event.getAction();


                return true;
            }
        });
        mHandler = new Handler();
        run.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // handle toggle on
                    runme.run();
                } else {
                    // handle toggle off
                    mHandler.removeCallbacks(runme);
                }
            }
        });
    }

    /**
     * checks the toggle button
     * @param view      the view that was clicked
     */
//    public void onToggleClicked(View view)
//    {
//        if(((ToggleButton) view).isChecked()) {
//            // handle toggle on
//            runme.run();
//            //Toast.makeText(getApplicationContext(),"On!", Toast.LENGTH_LONG).show();
//        } else {
//            // handle toggle off
//            mHandler.removeCallbacks(runme);
//            //Toast.makeText(getApplicationContext(),"Off!", Toast.LENGTH_LONG).show();
//        }
//    }

    private Runnable runme = new Runnable()
    {
        public void run()
        {
            //repeat after 180 milliseconds
            eView.step();
            //Toast.makeText(getApplicationContext(),"Is running ", Toast.LENGTH_LONG).show();
            mHandler.postDelayed(this, mInterval );
        }
    };


}
