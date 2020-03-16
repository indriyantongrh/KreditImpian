package com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.R;
import com.xw.repo.BubbleSeekBar;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class FragmentSimulasiGadget extends Fragment {

    SeekBar seekBar;
    TextView txtvalue, txttenor,txttagihan, txtjumlahdp;
    Spinner spinnertenor, spinneruangmuka;

    DecimalFormat kursindonesia;
    Double rupiah,rupiahspinner;
    DecimalFormatSymbols formatRp;
    Double hargabarang,tenor,hargaperbulan,bungakredit,uangmuka,uangadmin,umkurangiharga;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_simulasi_gadget, container, false);

        // initiate  views
       /// seekBar=(SeekBar)view.findViewById(R.id.seekBar);
        txtvalue = view.findViewById(R.id.txtvalue);
        txttagihan= view.findViewById(R.id.txttagihan);
        txtjumlahdp = view.findViewById(R.id.txtjumlahdp);
        BubbleSeekBar bubbleseekBar = view.findViewById(R.id.bubbleseekBar);
        txttenor = view.findViewById(R.id.txttenor);
        txttenor.getText().toString();

        spinnertenor = view.findViewById(R.id.spinnertenor);
        spinneruangmuka = view.findViewById(R.id.spinneruangmuka);

        /// spinner tagihan perbulan
        String [] values =
                {"Tenor","6 bulan","9 bulan","12 bulan","18 bulan","24 bulan",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnertenor.setAdapter(adapter);
        ///method spinner tagihan perbulan
        spinnertenor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            if(spinneruangmuka.getSelectedItem().equals("Uang muka")){
                Toast.makeText(getActivity(), "Uang muka diisi terlebih dahulu", Toast.LENGTH_LONG).show();
            } else if (spinnertenor.getSelectedItem().equals("6 bulan") ) {
                    ///Toast.makeText(getActivity(), "Pilih Unag muka terlebih dahulu", Toast.LENGTH_LONG).show();
                    txttenor.setText("6 bulan");
///                    rupiah  = Double.parseDouble(txtvalue.getText().toString());
///                    TagihanBulanan();
                    umkurangiharga = rupiah - uangmuka;
                    bungakredit = rupiah * 0.01;

                    hargaperbulan = umkurangiharga/6 + bungakredit;  //perhitungan
                    txttagihan.setText(Double.toString(hargaperbulan));  //output


                    hargaperbulan = Double.parseDouble(txttagihan.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txttagihan.setText(String.valueOf(kursindonesia.format(hargaperbulan)));


                }else if (spinnertenor.getSelectedItem().equals("9 bulan")){
                    txttenor.setText("9 bulan");

                    umkurangiharga = rupiah - uangmuka;
                    bungakredit = rupiah * 0.01;
 //                   rupiah  = Double.parseDouble(txtvalue.getText().toString());
///                    TagihanBulanan();
                    hargaperbulan = umkurangiharga/9 + bungakredit;  //perhitungan
                   txttagihan.setText(Double.toString(hargaperbulan));  //output


                    hargaperbulan = Double.parseDouble(txttagihan.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txttagihan.setText(String.valueOf(kursindonesia.format(hargaperbulan)));

                }else if (spinnertenor.getSelectedItem().equals("12 bulan")){
                    txttenor.setText("12 bulan");
                    umkurangiharga = rupiah - uangmuka;
                    bungakredit = rupiah * 0.01;
///                    TagihanBulanan();
                    hargaperbulan = umkurangiharga/12 + bungakredit;  //perhitungan
                    txttagihan.setText(Double.toString(hargaperbulan));  //output


                    hargaperbulan = Double.parseDouble(txttagihan.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txttagihan.setText(String.valueOf(kursindonesia.format(hargaperbulan)));

                }else if (spinnertenor.getSelectedItem().equals("18 bulan")){
                    txttenor.setText("18 bulan");
                    umkurangiharga = rupiah - uangmuka;
                    bungakredit = rupiah * 0.01;
///                    TagihanBulanan();
                    hargaperbulan = umkurangiharga/18+bungakredit;  //perhitungan
                    txttagihan.setText(Double.toString(hargaperbulan));  //output


                    hargaperbulan = Double.parseDouble(txttagihan.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txttagihan.setText(String.valueOf(kursindonesia.format(hargaperbulan)));

                }else if (spinnertenor.getSelectedItem().equals("24 bulan")){
                    txttenor.setText("24 bulan");

                    umkurangiharga = rupiah - uangmuka;
                    bungakredit = rupiah * 0.01;
///                    TagihanBulanan();
                    hargaperbulan = umkurangiharga/24+bungakredit;  //perhitungan
                    txttagihan.setText(Double.toString(hargaperbulan));  //output


                    hargaperbulan = Double.parseDouble(txttagihan.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txttagihan.setText(String.valueOf(kursindonesia.format(hargaperbulan)));


                }else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /// spinner uang muka
        String [] valuesuangmuka =
                {"Uang muka","10%","15%","20%","25%","30%","35%","40","45%",};
        ArrayAdapter<String> adapteruangmuka = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, valuesuangmuka);
        adapteruangmuka.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinneruangmuka.setAdapter(adapteruangmuka);

        spinneruangmuka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
                if (spinneruangmuka.getSelectedItem().equals("10%")) {
                    uangmuka = rupiah*0.1;  //perhitungan

                    uangadmin = uangmuka+150000;
                    txtjumlahdp.setText(Double.toString(uangadmin));  //output


                    uangadmin = Double.parseDouble(txtjumlahdp.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txtjumlahdp.setText(String.valueOf(kursindonesia.format(uangadmin)));


                }else if (spinneruangmuka.getSelectedItem().equals("15%")){
                    uangmuka = rupiah*0.15;  //perhitungan
                    uangadmin = uangmuka+150000;
                    txtjumlahdp.setText(Double.toString(uangadmin));  //output


                    uangadmin = Double.parseDouble(txtjumlahdp.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txtjumlahdp.setText(String.valueOf(kursindonesia.format(uangadmin)));

                }else if (spinneruangmuka.getSelectedItem().equals("20%")){
                    uangmuka = rupiah*0.2;  //perhitungan
                    uangadmin = uangmuka+150000;
                    txtjumlahdp.setText(Double.toString(uangadmin));  //output


                    uangadmin = Double.parseDouble(txtjumlahdp.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txtjumlahdp.setText(String.valueOf(kursindonesia.format(uangadmin)));
                }else if (spinneruangmuka.getSelectedItem().equals("25%")){
                    uangmuka = rupiah*0.25;  //perhitungan
                    uangadmin = uangmuka+150000;
                    txtjumlahdp.setText(Double.toString(uangadmin));  //output


                    uangadmin = Double.parseDouble(txtjumlahdp.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txtjumlahdp.setText(String.valueOf(kursindonesia.format(uangadmin)));

                }else if (spinneruangmuka.getSelectedItem().equals("30%")){
                    uangmuka = rupiah*0.30;  //perhitungan
                    uangadmin = uangmuka+150000;
                    txtjumlahdp.setText(Double.toString(uangadmin));  //output


                    uangadmin = Double.parseDouble(txtjumlahdp.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txtjumlahdp.setText(String.valueOf(kursindonesia.format(uangadmin)));

                }else if (spinneruangmuka.getSelectedItem().equals("35%")){
                    uangmuka = rupiah*0.35;  //perhitungan
                    uangadmin = uangmuka+150000;
                    txtjumlahdp.setText(Double.toString(uangadmin));  //output


                    uangadmin = Double.parseDouble(txtjumlahdp.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txtjumlahdp.setText(String.valueOf(kursindonesia.format(uangadmin)));

                }else if (spinneruangmuka.getSelectedItem().equals("40%")){
                    uangmuka = rupiah*0.40;  //perhitungan
                    uangadmin = uangmuka+150000;
                    txtjumlahdp.setText(Double.toString(uangadmin));  //output


                    uangadmin = Double.parseDouble(txtjumlahdp.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txtjumlahdp.setText(String.valueOf(kursindonesia.format(uangadmin)));

                }else if (spinneruangmuka.getSelectedItem().equals("45%")){
                    uangmuka = rupiah*0.45;  //perhitungan
                    uangadmin = uangmuka+150000;
                    txtjumlahdp.setText(Double.toString(uangadmin));  //output


                    uangadmin = Double.parseDouble(txtjumlahdp.getText().toString());
                    kursindonesia = (DecimalFormat)
                            DecimalFormat.getCurrencyInstance();
                    formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursindonesia.setDecimalFormatSymbols(formatRp);

                    txtjumlahdp.setText(String.valueOf(kursindonesia.format(uangadmin)));

                }else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        bubbleseekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {

                progress = progress / 5000;
                progress = progress * 5000;

                rupiah = Double.parseDouble(String.valueOf((progress)));
                kursindonesia = (DecimalFormat)
                        DecimalFormat.getCurrencyInstance();
                formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp.");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kursindonesia.setDecimalFormatSymbols(formatRp);

                txtvalue.setText(String.valueOf(kursindonesia.format(rupiah)));

         /*       Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                txtvalue.setText(formatRupiah.format(progress));*/
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

        rupiah  = Double.parseDouble(txtvalue.getText().toString());


    }

}
