package com.example.kreditimpian.SimulasiKredit;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiCorporate;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiElektronik;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiFashion;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiForniture;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiGadget;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiHobi;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiHolytour;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiLaptop;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiMultiproduct;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiOtomotif;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiPendanaan;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiProperty;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiTravel;
import com.example.kreditimpian.KategoriSimulasiKredit.FragmentSimulasiUMKM;
import com.example.kreditimpian.R;


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
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiGadget());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });


        btn_laptop = view.findViewById(R.id.btn_laptop);
        btn_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiLaptop());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });



        btn_otomotif = view.findViewById(R.id.btn_otomotif);
        btn_otomotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiOtomotif());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_forniture = view.findViewById(R.id.btn_forniture);
        btn_forniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiForniture());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_fashion = view.findViewById(R.id.btn_fashion);
        btn_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiFashion());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_hobi = view.findViewById(R.id.btn_hobi);
        btn_hobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiHobi());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });


        btn_property = view.findViewById(R.id.btn_property);
        btn_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiProperty());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_elektronik = view.findViewById(R.id.btn_elektronik);
        btn_elektronik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiElektronik());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_tourandtravel = view.findViewById(R.id.btn_tourandtravel);
        btn_tourandtravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiTravel());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_haji = view.findViewById(R.id.btn_haji);
        btn_haji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiHolytour());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_umkm = view.findViewById(R.id.btn_umkm);
        btn_umkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiUMKM());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_coorporate = view.findViewById(R.id.btn_coorporate);
        btn_coorporate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiCorporate());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_pendanaan = view.findViewById(R.id.btn_pendanaan);
        btn_pendanaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiPendanaan());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_multiproduct = view.findViewById(R.id.btn_multiproduct);
        btn_multiproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containerkredit, new FragmentSimulasiMultiproduct());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



}
