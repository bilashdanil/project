package com.example.itcompanies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class addCopany extends AppCompatActivity implements View.OnClickListener {
    Button btn_back, btn_add_company;
    EditText et_info_company, et_name_company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_copany);

        et_info_company = findViewById(R.id.et_info_company);
        et_name_company = findViewById(R.id.et_name_company);

        btn_back = findViewById(R.id.btn_back);
        btn_add_company = findViewById(R.id.btn_add_company);


        btn_back.setOnClickListener(this);
        btn_add_company.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_back:
                Intent anIntent = new Intent(this, MainActivity.class);
                startActivity(anIntent);
                super.finish();
                break;

            case R.id.btn_add_company:
                writeToFile();
                break;
        }
    }

    void writeToFile() {
        try {
            String filePath;

            filePath = getFilesDir().getPath().toString() + "/" + et_name_company.getText().toString() + ".txt";
            PrintWriter fOut = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath, true)));

            fOut.append(et_info_company.getText().toString());
            fOut.flush();
            fOut.close();

            et_name_company.setText("");
            et_info_company.setText("");
        } catch (FileNotFoundException e) {
            Toast.makeText(this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}