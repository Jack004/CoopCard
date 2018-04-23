package it.jack004.coopcard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    float oldBrightness;
    WindowManager.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap myBitmap;
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
        File imgFile = new  File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+ "/screenshots/screenshot.png");
        if(imgFile.exists()){


            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Bitmap croppedBmp = Bitmap.createBitmap(myBitmap, 0, 70, myBitmap.getWidth(), myBitmap.getHeight()-180);


            ImageView sfondo = (ImageView) findViewById(R.id.imageViewSfondo);
            sfondo.setImageBitmap(croppedBmp);
        }

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

