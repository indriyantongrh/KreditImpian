package com.application.kreditimpian.Beranda;

import com.application.kreditimpian.Marketplace.FragKategoriCorporate.KategoriCorporate;
import com.application.kreditimpian.Marketplace.FragKategoriElektronik.KategoriElektronik;
import com.application.kreditimpian.Marketplace.FragKategoriFashion.KategoriFashion;
import com.application.kreditimpian.Marketplace.FragKategoriHaji.KategoriHaji;
import com.application.kreditimpian.Marketplace.FragKategoriMultiproduct.KategoriMultiproduct;
import com.application.kreditimpian.Marketplace.FragKategoriPendanaan.KategoriPendanaan;
import com.application.kreditimpian.Marketplace.FragKategoriTravel.KategoriTravel;
import com.application.kreditimpian.Marketplace.FragKategoriUmkm.KategoriUmkm;
import com.application.kreditimpian.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

public class ButtonSheet_Kategori extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    CardView btn_tourandtravel, btn_haji, btn_umkm, btn_coorporate, btn_pendanaan, btn_multiproduct, btn_elektronik;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.buttonsheet_kategori, container, false);

        btn_tourandtravel = rootView.findViewById(R.id.btn_tourandtravel);
        btn_tourandtravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// Toast.makeText(getActivity(), "Ini kategori Fashion", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriTravel());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        btn_haji = rootView.findViewById(R.id.btn_haji);
        btn_haji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// Toast.makeText(getActivity(), "Ini kategori Fashion", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriHaji());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        btn_umkm = rootView.findViewById(R.id.btn_umkm);
        btn_umkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// Toast.makeText(getActivity(), "Ini kategori Fashion", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriUmkm());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        btn_pendanaan = rootView.findViewById(R.id.btn_pendanaan);
        btn_pendanaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// Toast.makeText(getActivity(), "Ini kategori Fashion", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriPendanaan());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        btn_coorporate = rootView.findViewById(R.id.btn_coorporate);
        btn_coorporate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// Toast.makeText(getActivity(), "Ini kategori Fashion", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriCorporate());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        btn_elektronik = rootView.findViewById(R.id.btn_elektronik);
        btn_elektronik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// Toast.makeText(getActivity(), "Ini kategori Fashion", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriElektronik());
                fr.addToBackStack(null);
                fr.commit();

            }
        });
        btn_multiproduct = rootView.findViewById(R.id.btn_multiproduct);
        btn_multiproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// Toast.makeText(getActivity(), "Ini kategori Fashion", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriMultiproduct());
                fr.addToBackStack(null);
                fr.commit();

            }
        });


        return rootView;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}
