package com.application.kreditimpian.Beranda;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterMerchant;
import com.application.kreditimpian.Adapter.AdapterMitra;
import com.application.kreditimpian.Adapter.AdapterProductNew;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.ButtomSheetKategori.CustomBottomSheetDialogFragment;
import com.application.kreditimpian.FormPengajuan.StepIsiCariProduct;
import com.application.kreditimpian.FormPengajuan.StepUploadProduct;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.UpgradeImpian;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.Marketplace.FragKategoriFashion.KategoriFashion;
import com.application.kreditimpian.Marketplace.FragKategoriForniture.KategoriForniture;
import com.application.kreditimpian.Marketplace.FragKategoriHobi.KategoriHobi;
import com.application.kreditimpian.Marketplace.FragKategoriKomputer.KategoriKomputer;
import com.application.kreditimpian.Marketplace.FragKategoriOtomotif.KategoriOtomotif;
import com.application.kreditimpian.Marketplace.FragKategoriProperty.KategoriProperty;
import com.application.kreditimpian.Marketplace.FragKategorihandphone.KategoriHandphone;
import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.application.kreditimpian.Model.ModelMerchant.ResponseMerchant;
import com.application.kreditimpian.Model.ModelMerchant.ResultItem;
import com.application.kreditimpian.Model.ModelMitra;

import com.application.kreditimpian.Model.ModelProductNew.ProductResponse;
import com.application.kreditimpian.Notifikasi.NotifikasiActivity;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.Cart;
import com.application.kreditimpian._sliders.FragmentSlider;
import com.application.kreditimpian._sliders.SliderIndicator;
import com.application.kreditimpian._sliders.SliderPagerAdapter;
import com.application.kreditimpian._sliders.SliderView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


import com.application.kreditimpian.FormPengajuan.StepFotoProduct;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.application.kreditimpian.LoginRegister.LoginUser.TAG_ID;


public class FragmentBeranda extends Fragment {

    private ShimmerFrameLayout mShimmerViewContainer;
    CardView btn_lainya, btn_handphone, btn_laptop,btn_otomotif, btn_forniture, btn_fashion, btn_olahraga, btn_property;
    ImageButton btn_fotoimpian,btnupload, btncari, btnupgrade;
    private BottomSheetBehavior bottomSheetBehavior;
    private Activity mActivity;
    RecyclerView rv_mitra;

    ImageView imagefoto;
    Bitmap bitmap, decoded;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100


    ConnectivityManager conMgr;
    AdapterMitra adapterMitra;
    private ArrayList<ModelMitra> mArrayList;

    public static final String BASE_URL = BuildConfig.BASE_URL;

    Dialog pDialog;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int PERMISSION_REQUEST_CODE = 200;
    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    ActionBar toolbar;
    private SliderView sliderView;
    private LinearLayout mLinearLayout;
    SharedPreferences sharedpreferences;
    SharedPrefManager sharedPrefManager;

    @BindView(R.id.rv_merchant)
    RecyclerView rv_merchant;
    BaseApiService mApiService;
    Context mContext;
    List<ResultItem> resultItemList = new ArrayList<>();
    AdapterMerchant adapterMerchant;

    String id, username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_beranda, container, false);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

        }else {
            requestPermission();
        }

        rv_mitra = rootView.findViewById(R.id.rv_mitra);
        imagefoto = rootView.findViewById(R.id.imagefoto);
        btn_handphone  = rootView.findViewById(R.id.btn_handphone);
        btn_laptop  = rootView.findViewById(R.id.btn_laptop);
        btn_otomotif  = rootView.findViewById(R.id.btn_otomotif);
        btn_forniture  = rootView.findViewById(R.id.btn_forniture);
        btn_fashion  = rootView.findViewById(R.id.btn_fashion);
        btn_olahraga  = rootView.findViewById(R.id.btn_olahraga);
        btn_property = rootView.findViewById(R.id.btn_property);
        //mShimmerViewContainer = rootView.findViewById(R.id.shimmer_view_container);


        sharedPrefManager = new SharedPrefManager(getActivity());
        String decode = sharedPrefManager.getSpDecode();
        Gson gson = new Gson();
        String jsonconvert = gson.toJson(decode);
//        try {
//            JSONArray jsonArray = new JSONArray(jsonconvert);
//            JSONObject jsonObject = jsonArray.getJSONObject(1);
//            String id = jsonObject.getString("id");
//            System.out.println("Cek id saya : "+id);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

       /// Toast.makeText(getActivity(), "Identitas mu "+ sharedPrefManager.getSPID(), Toast.LENGTH_SHORT).show();

/*        Result result = SharedPrefManager.getInstance(getActivity()).getResult();

        Toast.makeText(getActivity(), "ini id ke-"+result.getId(), Toast.LENGTH_SHORT).show();*/


        btnupload = rootView.findViewById(R.id.btnupload);
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stopping Shimmer Effect's animation after data is loaded to ListView

                ///   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //// startActivityForResult(intent, 0);

                Intent intent = new Intent(getActivity(), StepUploadProduct.class);
                getActivity().startActivity(intent);

            }
        });

        btn_fotoimpian = rootView.findViewById(R.id.btn_fotoimpian);
        btn_fotoimpian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), StepFotoProduct.class);
                getActivity().startActivity(intent);

            }
        });

        btncari = rootView.findViewById(R.id.btncari);
        btncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ///   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //// startActivityForResult(intent, 0);

                Intent intent = new Intent(getActivity(), StepIsiCariProduct.class);
                getActivity().startActivity(intent);

            }
        });

        btnupgrade = rootView.findViewById(R.id.btnupgrade);
        btnupgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ///   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //// startActivityForResult(intent, 0);

                Intent intent = new Intent(getActivity(), UpgradeImpian.class);
                getActivity().startActivity(intent);

            }
        });

        btn_handphone = rootView.findViewById(R.id.btn_handphone);
        btn_handphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///Toast.makeText(getActivity(), "Ini kategori handphone", Toast.LENGTH_SHORT).show();


                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriHandphone());
                ///fr.addToBackStack(null);
                fr.commit();
            }
        });

        btn_laptop = rootView.findViewById(R.id.btn_laptop);
        btn_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///Toast.makeText(getActivity(), "Ini kategori Laptop", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriKomputer());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        btn_otomotif = rootView.findViewById(R.id.btn_otomotif);
        btn_otomotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "Ini kategori Otomotif", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriOtomotif());
                fr.addToBackStack(null);
                fr.commit();

            }
        });


        btn_forniture = rootView.findViewById(R.id.btn_forniture);
        btn_forniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriForniture());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        btn_olahraga = rootView.findViewById(R.id.btn_olahraga);
        btn_olahraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getActivity(), "Ini kategori Olahraga", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriHobi());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        btn_property = rootView.findViewById(R.id.btn_property);
        btn_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///Toast.makeText(getActivity(), "Ini kategori Property", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriProperty());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        btn_fashion = rootView.findViewById(R.id.btn_fashion);
        btn_fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /// Toast.makeText(getActivity(), "Ini kategori Fashion", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_container, new KategoriFashion());
                fr.addToBackStack(null);
                fr.commit();

            }
        });


        View modal = rootView.findViewById(R.id.btn_lainya);
        modal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogFragment bottomSheetDialogFragment = new CustomBottomSheetDialogFragment();
                bottomSheetDialogFragment.show(getFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });



        sliderView = (SliderView) rootView.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.pagesContainer);

        setupSlider();


        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        mApiService = UtilsApi.getAPIService();

        adapterMerchant = new AdapterMerchant(getActivity(), resultItemList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        rv_merchant.setLayoutManager(mLayoutManager);
        rv_merchant.setItemAnimator(new DefaultItemAnimator());
        getResultList();

        return rootView;

    }


    private void getResultList(){
        ///progressBar = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);

        mApiService.getMerchnat().enqueue(new Callback<ResponseMerchant>() {
            @Override
            public void onResponse(Call<ResponseMerchant> call, Response<ResponseMerchant> response) {
                if (response.isSuccessful()){
                    ///progressBar.dismiss();
                    if (response.body().getStatus()==200) {
                        //swipeRefresh.setRefreshing(false);
                        ///progressBar.dismiss();
                        final List<ResultItem> AllMerchant = response.body().getResult();

                        rv_merchant.setAdapter(new AdapterMerchant(mContext, AllMerchant));
                        adapterMerchant.notifyDataSetChanged();
                        ///empty.setVisibility(View.GONE);
                        //initDataIntent(Allproduct);
                    }else {
                        //progressBar.dismiss();
                        ///empty.setVisibility(View.VISIBLE);
                    }

                } else {
                    //progressBar.dismiss();
                    Toast.makeText(mContext, "Gagal Refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMerchant> call, Throwable t) {
                ///progressBar.dismiss();
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*    private void getListMitra() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.kreditimpian.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<List<ModelMitra>> call = requestInterface.getMitra();

        call.enqueue(new Callback<List<ModelMitra>>() {
            @Override
            public void onResponse(Call<List<ModelMitra>> call, Response<List<ModelMitra>> response) {
                Toast.makeText(getActivity(), "Succes", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ModelMitra>> call, Throwable t) {
                Toast.makeText(getActivity(), "Filed", Toast.LENGTH_SHORT).show();

            }
        });
    }*/

    //////////////////////////////
private void initViewMitra() {
    rv_mitra.setHasFixedSize(true);
    final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
    rv_mitra.setLayoutManager(layoutManager);
}

    public void initJsonMitra(ArrayList<ModelMitra> mArrayList) {
        adapterMitra = new AdapterMitra(getActivity(), mArrayList);
        rv_mitra.setAdapter(adapterMitra);
        adapterMitra.notifyDataSetChanged();
    }


////////////////////////////

/*    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }*/


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

    private void requestPermission() {

        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }


    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://kreditimpian.com/storage/images/posts/media/banner-tambahan3.jpg"));
        fragments.add(FragmentSlider.newInstance("https://kreditimpian.com/storage/images/posts/media/banner.gif"));
        fragments.add(FragmentSlider.newInstance("https://kreditimpian.com/storage/images/posts/media/toga.gif"));
        fragments.add(FragmentSlider.newInstance("https://kreditimpian.com/storage/images/posts/media/banner2.png"));



        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menutopbar, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.notifikasi){
            startActivity(new Intent(getContext(), NotifikasiActivity.class));
            ((Activity)getContext()).finish();
        }
        if (id == R.id.cartshop){
            gotocartshop();
        }
        return super.onOptionsItemSelected(item);



    }

    private void gotocartshop() {
        Intent intent_cart = new Intent(getContext(), Cart.class);
        intent_cart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_cart);
    }

//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        mActivity = getActivity();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mActivity = null;
//    }

}
