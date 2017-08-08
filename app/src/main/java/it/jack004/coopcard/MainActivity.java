package it.jack004.coopcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    
    float oldBrightness;
    WindowManager.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        params = getWindow().getAttributes();
        oldBrightness = params.screenBrightness;
        params.screenBrightness = 1.0f;

        getWindow().setAttributes(params);
    }

    @Override
    protected void onPause(){
        super.onPause();
        params.screenBrightness = oldBrightness;
    }


    @Override
    protected void onStop(){
        super.onStop();
        params.screenBrightness = oldBrightness;
    }

    @Override
    protected void onResume(){
        super.onResume();
        params.screenBrightness = 1.0f;
    }
}

