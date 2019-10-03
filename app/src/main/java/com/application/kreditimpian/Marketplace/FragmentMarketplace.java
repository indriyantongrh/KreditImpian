package com.application.kreditimpian.Marketplace;

import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.kreditimpian.FragKategoriCorporate.KategoriCorporate;
import com.application.kreditimpian.FragKategoriElektronik.KategoriElektronik;
import com.application.kreditimpian.FragKategoriFashion.KategoriFashion;
import com.application.kreditimpian.FragKategoriForniture.KategoriForniture;
import com.application.kreditimpian.FragKategoriHaji.KategoriHaji;
import com.application.kreditimpian.FragKategoriHobi.KategoriHobi;
import com.application.kreditimpian.FragKategoriKomputer.KategoriKomputer;
import com.application.kreditimpian.FragKategoriMultiproduct.KategoriMultiproduct;
import com.application.kreditimpian.FragKategoriOtomotif.KategoriOtomotif;
import com.application.kreditimpian.FragKategoriPendanaan.KategoriPendanaan;
import com.application.kreditimpian.FragKategoriProperty.KategoriProperty;
import com.application.kreditimpian.FragKategoriTravel.KategoriTravel;
import com.application.kreditimpian.FragKategoriUmkm.KategoriUmkm;
import com.application.kreditimpian.FragKategorihandphone.KategoriHandphone;
import com.application.kreditimpian.FragSemuaKategori.FragSemuaKategori;
import com.application.kreditimpian.R;


public class FragmentMarketplace extends Fragment {

    CardView btn_semua,btn_handphone,btn_laptop,btn_otomotif,btn_forniture,btn_fashion,btn_hobi,btn_property,btn_elektronik,btn_tourandtravel,btn_haji, btn_umkm, btn_coorporate,btn_pendanaan,btn_multiproduct ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_fragment_marketplace, container, false);


        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.frame_containermarketplace, new FragSemuaKategori());
        fragmentTransaction.commit();


        btn_semua = view.findViewById(R.id.btn_semua);
        btn_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new FragSemuaKategori());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });


        btn_handphone = view.findViewById(R.id.btn_handphone);
        btn_handphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriHandphone());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });


        btn_laptop = view.findViewById(R.id.btn_laptop);
        btn_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriKomputer());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });



        btn_otomotif = view.findViewById(R.id.btn_otomotif);
        btn_otomotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriOtomotif());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_forniture = view.findViewById(R.id.btn_forniture);
        btn_forniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriForniture());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_fashion = view.findViewById(R.id.btn_fashion);
        btn_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriFashion());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_hobi = view.findViewById(R.id.btn_hobi);
        btn_hobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriHobi());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });


        btn_property = view.findViewById(R.id.btn_property);
        btn_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriProperty());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_elektronik = view.findViewById(R.id.btn_elektronik);
        btn_elektronik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriElektronik());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_tourandtravel = view.findViewById(R.id.btn_tourandtravel);
        btn_tourandtravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriTravel());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_haji = view.findViewById(R.id.btn_haji);
        btn_haji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriHaji());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_umkm = view.findViewById(R.id.btn_umkm);
        btn_umkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriUmkm());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_coorporate = view.findViewById(R.id.btn_coorporate);
        btn_coorporate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriCorporate());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });

        btn_pendanaan = view.findViewById(R.id.btn_pendanaan);
        btn_pendanaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriPendanaan());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });


        btn_multiproduct = view.findViewById(R.id.btn_multiproduct);
        btn_multiproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_containermarketplace, new KategoriMultiproduct());
                fr.commit();

                ///Toast.makeText(getContext(),"Sukses",Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }


}
