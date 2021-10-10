package com.example.qr_code;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    Button btn_scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_scan = findViewById(R.id.btn_scan);

        btn_scan.setOnClickListener(v -> {
            //Initialize intentIntegrator
            IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);

            //set promp text
            intentIntegrator.setPrompt("For Flash use volume up key");

            //set beep
            intentIntegrator.setBeepEnabled(true);

            //locked orientation
            intentIntegrator.setOrientationLocked(true);

            //set capture activity
            intentIntegrator.setCaptureActivity(Capture.class);

            //initiate scan
            intentIntegrator.initiateScan();
        });

    }

    //still on bar code scan

//oar

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //initiate intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(resultCode,data);

        //check condition
        //Todo: still not getting correct response if null
        if(intentResult.getContents() != null){
            //when result is not null
            //initiate alert dialog
            String result = intentResult.getContents();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            //set title
            builder.setTitle("Results");

            //set message
            builder.setMessage(intentResult.getContents());

            if(result.equals("")){
                Toast.makeText(getApplicationContext(), "Alaa no result", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }

            //set positive btn
            builder.setPositiveButton("OK", (dialog, which) -> {
                //dismiss Dialog
                dialog.dismiss();
            });
            //show alert dialog
            builder.show();
        }else{
            //when result is null
            //display Toast
            Toast.makeText(getApplicationContext(), "OPPS...You did not scan anything", Toast.LENGTH_SHORT).show();
        }
    }
}