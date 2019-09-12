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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSimulasiKredit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSimulasiKredit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSimulasiKredit extends Fragment {


    CardView btn_handphone,btn_laptop,btn_otomotif,btn_forniture,btn_fashion,btn_hobi,btn_property,btn_elektronik,btn_tourandtravel,btn_haji, btn_umkm, btn_coorporate,btn_pendanaan,btn_multiproduct ;
    ActionBar toolbar;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentSimulasiKredit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSimulasiKredit.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSimulasiKredit newInstance(String param1, String param2) {
        FragmentSimulasiKredit fragment = new FragmentSimulasiKredit();
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

  /*  private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        // bundle.putString("kode", kode);
        //bundle.putString("key", key);
        fragment.setArguments(bundle);
        // load fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_containerkredit, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()){

            case R.id.btn_handphone:
                //  toolbar.setLogo(R.drawable.logoputih);
                //   toolbar.setTitle("Beranda");
                fragment = new FragmentSimulasiGadget();
                loadFragment(fragment);
                return;

        }


    }*/

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
