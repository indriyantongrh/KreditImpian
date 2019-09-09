package com.example.kreditimpian.Beranda;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.kreditimpian.FormPengajuan.CaptureGambar;
import com.example.kreditimpian.FormPengajuan.StepIsiProductCari;
import com.example.kreditimpian.FormPengajuan.UploadGambar;
import com.example.kreditimpian.R;
import com.example.kreditimpian._sliders.FragmentSlider;
import com.example.kreditimpian._sliders.SliderIndicator;
import com.example.kreditimpian._sliders.SliderPagerAdapter;
import com.example.kreditimpian._sliders.SliderView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


import com.example.kreditimpian.FormPengajuan.StepisiProduct;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentBeranda.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentBeranda#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBeranda extends Fragment {


    CardView btn_lainya;
    ImageButton btn_fotoimpian,btnupload, btncari, btnupgrade;
    private BottomSheetBehavior bottomSheetBehavior;

    ImageView imagefoto;
    Bitmap bitmap, decoded;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentBeranda() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBeranda.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBeranda newInstance(String param1, String param2) {
        FragmentBeranda fragment = new FragmentBeranda();
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
        View rootView = inflater.inflate(R.layout.fragment_fragment_beranda, container, false);

        imagefoto = rootView.findViewById(R.id.imagefoto);


        btnupload = rootView.findViewById(R.id.btnupload);
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ///   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //// startActivityForResult(intent, 0);

                Intent intent = new Intent(getActivity(), UploadGambar.class);
                getActivity().startActivity(intent);

            }
        });


        btn_lainya = rootView.findViewById(R.id.btn_lainya);
        btn_lainya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SemuaKategori.class);
                getActivity().startActivity(intent);

            }
        });

        btn_fotoimpian = rootView.findViewById(R.id.btn_fotoimpian);
        btn_fotoimpian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             ///   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               //// startActivityForResult(intent, 0);

               Intent intent = new Intent(getActivity(), CaptureGambar.class);
               getActivity().startActivity(intent);

            }
        });

        btncari = rootView.findViewById(R.id.btncari);
        btncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ///   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //// startActivityForResult(intent, 0);

                Intent intent = new Intent(getActivity(), StepIsiProductCari.class);
                getActivity().startActivity(intent);

            }
        });


        sliderView = (SliderView) rootView.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.pagesContainer);
        setupSlider();


        return rootView;

    }


    //untuk upload image, compress .JPEG ke bitmap
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //untuk memilih gambar
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        //imageView.setImageBitmap(imageBitmap);
        setToImageView(getResizedBitmap(imageBitmap, 512));
    }
    /*Menampilkan gambar*/
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imagefoto.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
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


    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://demo.kreditimpian.id/themes/default/assets/img/banner/banner1.png"));
        fragments.add(FragmentSlider.newInstance("https://demo.kreditimpian.id/themes/default/assets/img/banner/banner-1.png"));
        fragments.add(FragmentSlider.newInstance("https://demo.kreditimpian.id/themes/default/assets/img/banner/banner-2.png"));
        fragments.add(FragmentSlider.newInstance("https://demo.kreditimpian.id/themes/default/assets/img/banner/banner-3.png"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }
}
