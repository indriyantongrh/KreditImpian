package com.application.kreditimpian.SimulasiKredit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiCorporate;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiElektronik;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiFashion;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiForniture;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiGadget;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiHobi;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiHolytour;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiLaptop;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiMultiproduct;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiOtomotif;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiPendanaan;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiProperty;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiTravel;
import com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit.FragmentSimulasiUMKM;
import com.application.kreditimpian.R;


public class FragmentSimulasiKredit extends Fragment {


    CardView btn_handphone,btn_laptop,btn_otomotif,btn_forniture,btn_fashion,btn_hobi,btn_property,btn_elektronik,btn_tourandtravel,btn_haji, btn_umkm, btn_coorporate,btn_pendanaan,btn_multiproduct ;
    ActionBar toolbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_simulasi_kredit, container, false);




        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.frame_containerkredit, new FragmentSimulasiGadget());
        fragmentTransaction.commit();

        btn_handphone = view.findViewById(R.id.btn_handphone);
        btn_handphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiGadget(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });


        btn_laptop = view.findViewById(R.id.btn_laptop);
        btn_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiLaptop(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });



        btn_otomotif = view.findViewById(R.id.btn_otomotif);
        btn_otomotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiOtomotif(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_forniture = view.findViewById(R.id.btn_forniture);
        btn_forniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiForniture(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_fashion = view.findViewById(R.id.btn_fashion);
        btn_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiFashion(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_hobi = view.findViewById(R.id.btn_hobi);
        btn_hobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiHobi(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });


        btn_property = view.findViewById(R.id.btn_property);
        btn_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiProperty(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_elektronik = view.findViewById(R.id.btn_elektronik);
        btn_elektronik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiElektronik(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_tourandtravel = view.findViewById(R.id.btn_tourandtravel);
        btn_tourandtravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiTravel(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_haji = view.findViewById(R.id.btn_haji);
        btn_haji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiHolytour(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_umkm = view.findViewById(R.id.btn_umkm);
        btn_umkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiUMKM(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_coorporate = view.findViewById(R.id.btn_coorporate);
        btn_coorporate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiCorporate(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_pendanaan = view.findViewById(R.id.btn_pendanaan);
        btn_pendanaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiPendanaan(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_multiproduct = view.findViewById(R.id.btn_multiproduct);
        btn_multiproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiMultiproduct(), null).addToBackStack(null);
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



}
