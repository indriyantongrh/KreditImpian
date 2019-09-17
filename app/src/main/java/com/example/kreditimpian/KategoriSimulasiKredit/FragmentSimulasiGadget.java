package com.example.kreditimpian.KategoriSimulasiKredit;

import android.content.Context;
import android.icu.util.Currency;
import android.icu.util.CurrencyAmount;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kreditimpian.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.xw.repo.BubbleSeekBar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class FragmentSimulasiGadget extends Fragment {

    SeekBar seekBar;
    TextView txtvalue, txttenor,txttagihan;
    Spinner spinnertenor;

    Currency hargabarang,tenor,hargaperbulan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_simulasi_gadget, container, false);

        // initiate  views
       /// seekBar=(SeekBar)view.findViewById(R.id.seekBar);
        txtvalue = view.findViewById(R.id.txtvalue);
        txttagihan= view.findViewById(R.id.txttagihan);
        BubbleSeekBar bubbleseekBar = view.findViewById(R.id.bubbleseekBar);
        txttenor = view.findViewById(R.id.txttenor);
        txttenor.getText().toString();

        spinnertenor = view.findViewById(R.id.spinnertenor);


        String [] values =
                {"Tenor","6 bulan","9 bulan","12 bulan","18 bulan","24 bulan",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnertenor.setAdapter(adapter);


        spinnertenor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
                if (spinnertenor.getSelectedItem().equals("6 bulan")) {


                    txttenor.setText("6 bulan");


                }else if (spinnertenor.getSelectedItem().equals("9 bulan")){
                    txttenor.setText("9 bulan");

                }else if (spinnertenor.getSelectedItem().equals("12 bulan")){
                    txttenor.setText("12 bulan");

                }else if (spinnertenor.getSelectedItem().equals("18 bulan")){
                    txttenor.setText("18 bulan");

                }else if (spinnertenor.getSelectedItem().equals("24 bulan")){
                    txttenor.setText("24 bulan");
                }else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




/*        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, R.array.tenor);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnertenor.setAdapter(adapter);*/

/*        List<String> tenor = new ArrayList<>();
        tenor.add(0, "Tenor");
        tenor.add("6 bulan");
        tenor.add("9 bulan");
        tenor.add("12 bulan");
        tenor.add("18 bulan");
        tenor.add("24 bulan");

        ///Style anda populate the adapter
        ArrayAdapter<String> tenorAdapter;
        tenorAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item);

        //Dropdown layot style
        tenorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter
        spinnertenor.setAdapter(tenorAdapter);*/









   /*     // perform seek bar change listener event used for getting the progress value
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                String strValue = txtvalue.getText().toString();

            }
        });

*/

/*
        // perform seek bar change listener event used for getting the progress value
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                txtvalue.setText(formatRupiah.format(progressChangedValue));
            }

        });

*/

        /*Method Bubble SeekBar*/

        bubbleseekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {

                progress = progress / 5000;
                progress = progress * 5000;



                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                txtvalue.setText(formatRupiah.format(progress));
            }

            @Override
            public void getProgressOnActionUp(int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(int progress, float progressFloat) {

            }
        });

        return view;
    }

    private void TagihanBulanan(){

    }

}
