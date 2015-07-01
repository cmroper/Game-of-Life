package com.bearslab.gameoflife;

import com.bearslab.gameoflife.EnvironmentView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	//---MY FIELDS---------------------
    //private ToggleButton run;
    private Button step;
    private Button reset;
    //private Button glider;
    private EnvironmentView eView;
    private int mInterval = 100; // in milliseconds
    private Handler mHandler;
    //---------------------------------
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		step = (Button)findViewById(R.id.step);
        eView =  (EnvironmentView)findViewById(R.id.view1);
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
        eView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                // TODO Auto-generated method stub
                float touch_x = event.getX();
                float  touch_y = event.getY();
                
                eView.makeCell(touch_x, touch_y);
                //  int action = event.getAction();


                return true;
            }
        });
        mHandler = new Handler();
        
        //run = (ToggleButton)findViewById(R.id.toggleRun);
        
		
	}

	// ----------------------------------------------------------
    /**
     * checks the toggle button
     * @param view      the view that was clicked
     */
    public void onToggleClicked(View view)
    {
        if(((ToggleButton) view).isChecked()) {
            // handle toggle on
            runme.run();
            Toast.makeText(getApplicationContext(),"On!", Toast.LENGTH_LONG).show();
        } else {
           // handle toggle off
            mHandler.removeCallbacks(runme);
            Toast.makeText(getApplicationContext(),"Off!", Toast.LENGTH_LONG).show();
        }
    }

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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
