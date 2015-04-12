package edu.ius.rwisman.ViewFlipButton;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ViewFlipper;

public class ViewFlipButtonActivity extends Activity implements OnTouchListener{
    private float downXValue;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set main.XML as the layout for this Activity
        setContentView(R.layout.main);

        LinearLayout layMain = (LinearLayout) findViewById(R.id.layout_main);
        layMain.setOnTouchListener((OnTouchListener) this);

        // Add a few countries to the spinner
        Spinner spinnerCountries = (Spinner) findViewById(R.id.spinner_country);
        ArrayAdapter countryArrayAdapter = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    new String[] { "Canada", "USA" });
        spinnerCountries.setAdapter(countryArrayAdapter);
	}

    public boolean onTouch(View arg0, MotionEvent arg1) {

        // Get the action that was done on this touch event
        switch (arg1.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                // store the X value when the user's finger was pressed down
                downXValue = arg1.getX();
                Log.i("onTouch", "downXValue:" + downXValue);
                break;
            }

            case MotionEvent.ACTION_UP:
            {
                // Get the X value when the user released his/her finger
                float currentX = arg1.getX();            
                Log.i("onTouch", "currentX:" + currentX);

                // going backwards: pushing stuff to the right
                if (downXValue < currentX)
                {
                    // Get a reference to the ViewFlipper
                     ViewFlipper vf = (ViewFlipper) findViewById(R.id.details);
                     // Set the animation
                      vf.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_right));
                      // Flip!
                      vf.showNext();
                }

                // going forwards: pushing stuff to the left
                if (downXValue > currentX)
                {
                    // Get a reference to the ViewFlipper
                    ViewFlipper vf = (ViewFlipper) findViewById(R.id.details);
                     // Set the animation
                     vf.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_left));
                      // Flip!
                     vf.showPrevious();
                }
                break;
            }
        }

        // if you return false, these actions will not be recorded
        return true;
    }
}