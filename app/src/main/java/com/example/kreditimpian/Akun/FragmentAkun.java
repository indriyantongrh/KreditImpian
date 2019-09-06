package com.example.kreditimpian.Akun;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kreditimpian.Favorite.Favorite;
import com.example.kreditimpian.GantidanRisetPassword.GantiPassword;
import com.example.kreditimpian.HistoryPesanan.HistoryPesanan;
import com.example.kreditimpian.KonfirmasiPembayaran.KonfirmasiPembayaran;
import com.example.kreditimpian.MenuUtama.MenuUtama;
import com.example.kreditimpian.R;
import com.example.kreditimpian.StatusPesanan.StatusPesanan;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAkun.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAkun#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAkun extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    CardView btndetailakun, btnstatuspesanan, btnhistorypesanan,btnfavorite,btnkonfirmasi, btngantipassword,btnlogout ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentAkun() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAkun.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAkun newInstance(String param1, String param2) {
        FragmentAkun fragment = new FragmentAkun();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_akun, container, false);

        btndetailakun = view.findViewById(R.id.btndetailakun);
        btndetailakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DetailAkun.class);
                getActivity().startActivity(intent);
            }

        });


        btnstatuspesanan = view.findViewById(R.id.btnstatuspesanan);
        btnstatuspesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), StatusPesanan.class);
                getActivity().startActivity(intent);
            }

        });


        btnhistorypesanan = view.findViewById(R.id.btnhistorypesanan);
        btnhistorypesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), HistoryPesanan.class);
                getActivity().startActivity(intent);
            }

        });

        btnfavorite = view.findViewById(R.id.btnfavorite);
        btnfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Favorite.class);
                getActivity().startActivity(intent);
            }

        });

        btnkonfirmasi = view.findViewById(R.id.btnkonfirmasi);
        btnkonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), KonfirmasiPembayaran.class);
                getActivity().startActivity(intent);
            }

        });

        btngantipassword = view.findViewById(R.id.btngantipassword);
        btngantipassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), GantiPassword.class);
                getActivity().startActivity(intent);
            }

        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
