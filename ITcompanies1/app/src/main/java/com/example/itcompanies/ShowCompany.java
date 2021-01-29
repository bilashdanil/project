package com.example.itcompanies;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowCompany#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowCompany extends Fragment implements View.OnClickListener {
    Context context;
    AssetManager myAssetManager;
    String LOG = "qwert";

    Button btn_add;
    TextView tv_res;
    public ArrayList<String> listFileOnDevice = new ArrayList<>();

    GridView gvMain;
    ArrayAdapter<String> adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShowCompany() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowCompany.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowCompany newInstance(String param1, String param2) {
        ShowCompany fragment = new ShowCompany();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_show_company, container, false);



        context = getActivity();
        myAssetManager =  getActivity().getAssets();

        btn_add = rootView.findViewById(R.id.btn_add);
        tv_res = rootView.findViewById(R.id.tv_res);

        ImportFiles imFile = new ImportFiles();
        imFile.listAsset(context, myAssetManager);

        listNameCompany();

        btn_add.setOnClickListener(this);


        adapter = new ArrayAdapter<String>(getActivity(), R.layout.item, R.id.tvText, listFileOnDevice);
        gvMain = (GridView) rootView.findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG, String.valueOf(position));
                withdrawInfo(listFileOnDevice.get(position));

            }
        });







        return rootView;
    }

    @Override
    public void onClick(View v) {

    }





    void listNameCompany(){
        File yourDir = new File( getActivity().getFilesDir().getPath());
        for (File f : yourDir.listFiles()) {
            if (f.isFile()) {
                String name = f.getName();
                Log.d(LOG, "List");
                Log.d(LOG, name);
                listFileOnDevice.add(name.substring(0, name.length() - 4));
            }
        }
    }



    void withdrawInfo(String FileName) {

        String filePath, text = "";
        tv_res.setText("");
        filePath = getActivity().getFilesDir().getPath().toString() + "/" + FileName + ".txt";
        text += " Компания: " + FileName + "\n\n О компании: ";
        try {
            Scanner fIn = new Scanner(
                    new BufferedInputStream(
                            new FileInputStream(filePath)));
            while (fIn.hasNextLine())
                text += fIn.nextLine() + "\n";
            fIn.close();
            tv_res.setText(text);
        } catch (FileNotFoundException e) {
            Toast.makeText(getActivity(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }


}