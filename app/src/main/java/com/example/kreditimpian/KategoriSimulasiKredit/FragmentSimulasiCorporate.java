package com.example.kreditimpian.KategoriSimulasiKredit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kreditimpian.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSimulasiCorporate.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSimulasiCorporate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSimulasiCorporate extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_simulasi_corporate, container, false);


        return view;
    }


}
