package com.application.kreditimpian.SimulasiKredit.KategoriSimulasiKredit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.kreditimpian.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSimulasiMultiproduct.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSimulasiMultiproduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSimulasiMultiproduct extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_simulasi_multiproduct, container, false);


        return view;
    }


}
