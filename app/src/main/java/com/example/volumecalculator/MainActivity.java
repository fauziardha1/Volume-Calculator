package com.example.volumecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText panjang, lebar, tinggi;
    private Button btnHitung;
    private TextView hasil;
    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lebar       = findViewById(R.id.lebar);
        tinggi      = findViewById(R.id.tinggi);
        panjang     = findViewById(R.id.panjang);
        btnHitung   = findViewById(R.id.hitung);
        hasil       = findViewById(R.id.hasil);

        btnHitung.setOnClickListener(this);

        if(savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            hasil.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.hitung) {
            String inputPanjang = panjang.getText().toString().trim();
            String inputLebar = lebar.getText().toString().trim();
            String inputTinggi = tinggi.getText().toString().trim();
            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;
            if (TextUtils.isEmpty(inputPanjang)) {
                isEmptyFields = true;
                panjang.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputLebar)) {
                isEmptyFields = true;
                lebar.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputTinggi)) {
                isEmptyFields = true;
                tinggi.setError("Field ini tidak boleh kosong");
            }
            Double length = toDouble(inputPanjang);
            Double width = toDouble(inputLebar);
            Double height = toDouble(inputTinggi);
            if (length == null) {
                isInvalidDouble = true;
                panjang.setError("Field ini harus berupa nomer yang valid");
            }
            if (width == null) {
                isInvalidDouble = true;
                lebar.setError("Field ini harus berupa nomer yang valid");
            }
            if (height == null) {
                isInvalidDouble = true;
                tinggi.setError("Field ini harus berupa nomer yang valid");
            }
            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length * width * height;
                hasil.setText(String.valueOf(volume));
            }
        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, hasil.getText().toString());
    }
}
