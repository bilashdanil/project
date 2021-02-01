package com.example.itcompanies;

import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Fragment_AddCompany extends Fragment implements View.OnClickListener {
    Button btn_add_company;
    EditText et_info_company, et_name_company;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_company, container, false);

        et_info_company = rootView.findViewById(R.id.et_info_company);
        et_name_company = rootView.findViewById(R.id.et_name_company);
        btn_add_company = rootView.findViewById(R.id.btn_add_company);
        btn_add_company.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_add_company:
                writeToFile(); //Запись компании в файл
                break;
        }
    }


    void writeToFile() {
        try {
            String filePath;

            filePath = getActivity().getFilesDir().getPath().toString() + "/" + et_name_company.getText().toString() + ".txt"; //Получение пути с названием компании
            PrintWriter fOut = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath, true)));

            fOut.append(et_info_company.getText().toString()); // Запись в файл
            fOut.flush();
            fOut.close();

            et_name_company.setText("");
            et_info_company.setText("");
        } catch (FileNotFoundException e) {
            Toast.makeText(getActivity(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}