package com.example.consentify_v20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {
    Button generateBtn;
    EditText input;
    ImageView qr_output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateBtn = (Button) findViewById(R.id.generateBtn);
        input = (EditText) findViewById(R.id.et_input);
        qr_output = (ImageView) findViewById(R.id.iv_output);
        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String in = input.getText().toString().trim();
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix matrix = writer.encode(in, BarcodeFormat.QR_CODE,
                            350, 350);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    qr_output.setImageBitmap(bitmap);

                    InputMethodManager manager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    manager.hideSoftInputFromWindow(input.getApplicationWindowToken(), 0);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}