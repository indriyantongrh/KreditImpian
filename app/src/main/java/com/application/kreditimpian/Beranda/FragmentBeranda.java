package com.application.kreditimpian.Beranda;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
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
import android.widget.AdapterViewFlipper;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.kreditimpian.Adapter.AdapterCart;
import com.application.kreditimpian.Adapter.AdapterMerchant;
import com.application.kreditimpian.Adapter.AdapterMitra;
import com.application.kreditimpian.Adapter.AdapterMitraKami;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.BuildConfig;
import com.application.kreditimpian.ButtomSheetKategori.CustomBottomSheetDialogFragment;
import com.application.kreditimpian.Constan.ConstansProductMitra;
import com.application.kreditimpian.DetailProductMitra.DetailProductMitra;
import com.application.kreditimpian.FormPengajuan.StepIsiCariProduct;
import com.application.kreditimpian.FormPengajuan.StepUploadProduct;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.UpgradeImpian;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.UpgradeImpianViewModel;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.ViewModelFactory;
import com.application.kreditimpian.Marketplace.FragKategoriElektronik.KategoriElektronik;
import com.application.kreditimpian.Marketplace.FragKategoriFashion.KategoriFashion;
import com.application.kreditimpian.Marketplace.FragKategoriForniture.KategoriForniture;
import com.application.kreditimpian.Marketplace.FragKategoriHobi.KategoriHobi;
import com.application.kreditimpian.Marketplace.FragKategoriKomputer.KategoriKomputer;
import com.application.kreditimpian.Marketplace.FragKategoriOtomotif.KategoriOtomotif;
import com.application.kreditimpian.Marketplace.FragKategoriProperty.KategoriProperty;
import com.application.kreditimpian.Marketplace.FragKategorihandphone.KategoriHandphone;
import com.application.kreditimpian.Marketplace.FragSemuaKategori.RecyclerItemClickListener;
import com.application.kreditimpian.Model.ModelImagePromo.DataItem;
import com.application.kreditimpian.Model.ModelImagePromo.ResponseImagePromo;
import com.application.kreditimpian.Model.ModelMerchant.ResponseMerchant;
import com.application.kreditimpian.Model.ModelMerchant.ResultItem;
import com.application.kreditimpian.Model.ModelMitra;

import com.application.kreditimpian.Model.ModelMitraKami.ResponseMitraKami;
import com.application.kreditimpian.Model.ModelNotifFitur.ResponseNotifFitur;
import com.application.kreditimpian.Model.ModelNotifikasi.ModelNotifikasi;
import com.application.kreditimpian.Model.ModelNotifikasiFeature.ResponseNotifikasiFeature;
import com.application.kreditimpian.Model.ModelNotifikasiFeatures.ResponseNotifikasiFeatures;
import com.application.kreditimpian.Model.ModelNotifikasiProducts.ResponseNotifikasiProducts;
import com.application.kreditimpian.Model.ModelOnShoppingCart.ResponseOnShoppingCart;
import com.application.kreditimpian.Notifikasi.Notifikasi;
import com.application.kreditimpian.Notifikasi.NotifikasiActivity;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.Cart;
import com.application.kreditimpian._flipper.FlipperAdapter;
import com.application.kreditimpian._sliders.FragmentSlider;
import com.application.kreditimpian._sliders.SliderIndicator;
import com.application.kreditimpian._sliders.SliderPagerAdapter;
import com.application.kreditimpian._sliders.SliderView;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


import com.application.kreditimpian.FormPengajuan.StepFotoProduct;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBeranda extends Fragment {
    private AVLoadingIndicatorView Loading;
    private AVLoadingIndicatorView LoadingMitra;
    private AVLoadingIndicatorView LoadingMerchant;
    private ShimmerFrameLayout mShimmerViewContainer;
    CardView btn_lainya, btn_handphone, btn_laptop, btn_otomotif, btn_forniture, btn_fashion, btn_olahraga, btn_property,
            btnFotoimpian,btnUploadImpian,btnCariImpian,btnUpgradeImpian ,btn_elektronik;
    ImageButton btn_fotoimpian, btnupload, btncari, btnupgrade;
    String datalist;
    TextView textCartItemCount;
    ImageView imagefoto, btnCart;
    Bitmap bitmap, decoded;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100
    AdapterCart adapterCart;
    ConnectivityManager conMgr;
    AdapterMitra adapterMitra;
    private ArrayList<ModelMitra> mArrayList;

    public static final String BASE_URL = BuildConfig.BASE_URL;
    AlertDialog alertDialog;

    Dialog pDialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int PERMISSION_REQUEST_CODE = 200;
    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private SliderView sliderView;
    private LinearLayout mLinearLayout;
    private View modal;
    SharedPrefManager sharedPrefManager;
    private String idMember;
    private int totalnotif = 0;
    private Menu menu;

    @BindView(R.id.rv_merchant)
    RecyclerView rv_merchant;
    @BindView(R.id.rv_mitra)
    RecyclerView rv_mitra;

    BaseApiService mApiService;
    Context mContext;
    List<ResultItem> resultItemList = new ArrayList<>();
    AdapterMerchant adapterMerchant;

    List<com.application.kreditimpian.Model.ModelMitraKami.ResultItem> resultItemList1 = new ArrayList<>();
    AdapterMitraKami adapterMitraKami;

    List<DataItem> getImagePromo = new ArrayList<>();
    String id, username;
    private AdapterViewFlipper adapterViewFlipper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_beranda, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
        } else {
            Toast.makeText(getActivity(), "Tidak ada akses Internet",
                    Toast.LENGTH_LONG).show();
        }

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestPermission();
        }

        //rv_mitra = rootView.findViewById(R.id.rv_mitra);
        imagefoto = rootView.findViewById(R.id.imagefoto);
        btn_handphone = rootView.findViewById(R.id.btn_handphone);
        btn_laptop = rootView.findViewById(R.id.btn_laptop);
        btn_otomotif = rootView.findViewById(R.id.btn_otomotif);
        btn_forniture = rootView.findViewById(R.id.btn_forniture);
        btn_fashion = rootView.findViewById(R.id.btn_fashion);
        btn_olahraga = rootView.findViewById(R.id.btn_olahraga);
        btn_elektronik = rootView.findViewById(R.id.btn_elektronik);
        btnUploadImpian = rootView.findViewById(R.id.btnUploadImpian);
        btnFotoimpian = rootView.findViewById(R.id.btnFotoimpian);
        btnCariImpian = rootView.findViewById(R.id.btnCariImpian);
        btnUpgradeImpian = rootView.findViewById(R.id.btnUpgradeImpian);
        btn_handphone = rootView.findViewById(R.id.btn_handphone);
        btn_laptop = rootView.findViewById(R.id.btn_laptop);
        btn_otomotif = rootView.findViewById(R.id.btn_otomotif);
        btn_forniture = rootView.findViewById(R.id.btn_forniture);
        btn_olahraga = rootView.findViewById(R.id.btn_olahraga);
        //btn_property = rootView.findViewById(R.id.btn_property);
        btn_fashion = rootView.findViewById(R.id.btn_fashion);
        modal = rootView.findViewById(R.id.btn_lainya);
        sliderView = rootView.findViewById(R.id.sliderView);
        mLinearLayout = rootView.findViewById(R.id.pagesContainer);
        Loading = rootView.findViewById(R.id.Loading);
        LoadingMitra = rootView.findViewById(R.id.LoadingMitra);
        LoadingMerchant = rootView.findViewById(R.id.LoadingMerchant);
        //mShimmerViewContainer = rootView.findViewById(R.id.shimmer_view_container);
        adapterViewFlipper = rootView.findViewById(R.id.adapterViewFlipper);
       /* shimmer_view_container = rootView.findViewById(R.id.shimmer_view_container);
        shimmer_view_container.startShimmer();*/

        sharedPrefManager = new SharedPrefManager(getActivity());
        String decode = sharedPrefManager.getSpDecode();
        idMember = sharedPrefManager.getSpIdMember();
        Gson gson = new Gson();
        String jsonconvert = gson.toJson(decode);



        btnUploadImpian.setOnClickListener(view -> {
            // Stopping Shimmer Effect's animation after data is loaded to ListView

            ///   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //// startActivityForResult(intent, 0);

            Intent intent = new Intent(getActivity(), StepUploadProduct.class);
            getActivity().startActivity(intent);

        });

        btnFotoimpian.setOnClickListener(view -> {

            Intent intent = new Intent(getActivity(), StepFotoProduct.class);
            getActivity().startActivity(intent);

        });

        btnCariImpian.setOnClickListener(view -> {

            ///   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //// startActivityForResult(intent, 0);

            Intent intent = new Intent(getActivity(), StepIsiCariProduct.class);
            getActivity().startActivity(intent);

        });

        btnUpgradeImpian.setOnClickListener(view -> {

            ///   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //// startActivityForResult(intent, 0);

            Intent intent = new Intent(getActivity(), UpgradeImpian.class);
            getActivity().startActivity(intent);

        });

        btn_handphone.setOnClickListener(view -> {
            ///Toast.makeText(getActivity(), "Ini kategori handphone", Toast.LENGTH_SHORT).show();


            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.frame_container, new KategoriHandphone(), null).addToBackStack(null);
            ///fr.addToBackStack(null);
            fr.commit();
        });

        btn_laptop.setOnClickListener(view -> {
            ///Toast.makeText(getActivity(), "Ini kategori Laptop", Toast.LENGTH_SHORT).show();
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.frame_container, new KategoriKomputer(), null).addToBackStack(null);
            fr.addToBackStack(null);
            fr.commit();
        });

        btn_otomotif.setOnClickListener(view -> {
            //Toast.makeText(getActivity(), "Ini kategori Otomotif", Toast.LENGTH_SHORT).show();
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.frame_container, new KategoriOtomotif(), null).addToBackStack(null);
            fr.addToBackStack(null);
            fr.commit();

        });


        btn_forniture.setOnClickListener(view -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.frame_container, new KategoriForniture(), null).addToBackStack(null);
            fr.addToBackStack(null);
            fr.commit();
        });

        btn_olahraga.setOnClickListener(view -> {

            //Toast.makeText(getActivity(), "Ini kategori Olahraga", Toast.LENGTH_SHORT).show();
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.frame_container, new KategoriHobi(), null).addToBackStack(null);
            fr.addToBackStack(null);
            fr.commit();

        });

        btn_elektronik.setOnClickListener(view -> {
            ///Toast.makeText(getActivity(), "Ini kategori Property", Toast.LENGTH_SHORT).show();
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.frame_container, new KategoriElektronik(), null).addToBackStack(null);
            fr.addToBackStack(null);
            fr.commit();
        });

        btn_fashion.setOnClickListener(view -> {
            /// Toast.makeText(getActivity(), "Ini kategori Fashion", Toast.LENGTH_SHORT).show();
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.frame_container, new KategoriFashion(), null).addToBackStack(null);
            fr.addToBackStack(null);
            fr.commit();

        });


        modal.setOnClickListener(v -> {

            BottomSheetDialogFragment bottomSheetDialogFragment = new CustomBottomSheetDialogFragment();
            bottomSheetDialogFragment.show(getFragmentManager(), bottomSheetDialogFragment.getTag());
        });

        //setupSlider();


        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        mApiService = UtilsApi.getAPIService();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        adapterMerchant = new AdapterMerchant(getActivity(), resultItemList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        rv_merchant.setLayoutManager(mLayoutManager);
        rv_merchant.setItemAnimator(new DefaultItemAnimator());

        adapterMitraKami = new AdapterMitraKami(getActivity(), resultItemList1);
        GridLayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        rv_mitra.setLayoutManager(mLayoutManager1);
        rv_mitra.setItemAnimator(new DefaultItemAnimator());
        getResultList();
        getMitra();
        //getNotifikasi();
       // getNotifikasiProduct();
        getNotifikasiFitur();
        getOnShoppingCart();
        getPromotion();

    }

    private void getPromotion() {

        mApiService.getImageSlider().enqueue(new Callback<ResponseImagePromo>() {
            @Override
            public void onResponse(Call<ResponseImagePromo> call, Response<ResponseImagePromo> response) {
                Loading.setVisibility(View.GONE);
                if (response.body().getResponseCode() == 200) {
                  //  shimmer_view_container.stopShimmer();
                    FlipperAdapter adapter = new FlipperAdapter(getActivity(), (ArrayList<DataItem>) response.body().getData());
                    adapterViewFlipper.setAdapter(adapter);
                    adapterViewFlipper.setFlipInterval(700);
                    adapterViewFlipper.startFlipping();

                    sliderView.setDurationScroll(800);
                    final List<com.application.kreditimpian.Model.ModelImagePromo.DataItem> getImage = response.body().getData();

                    mAdapter = new SliderPagerAdapter(getFragmentManager(), response.body().getData());
                    sliderView.setAdapter(mAdapter);
                    sliderView.setClickable(true);
                    mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
                    mIndicator.setPageCount(getImage.size());
                    mIndicator.show();

                } else {

                }


            }

            @Override
            public void onFailure(Call<ResponseImagePromo> call, Throwable t) {

            }
        });
    }

    @SuppressWarnings("unchecked")
    private void getNotifikasi() {
        ModelNotifikasi modelNotifikasi = new ModelNotifikasi();
        modelNotifikasi.setIdMember(idMember);

        UpgradeImpianViewModel upgradeImpianViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelFactory(getContext())).get(UpgradeImpianViewModel.class);
        upgradeImpianViewModel.setModelNotifikasi(modelNotifikasi);
        upgradeImpianViewModel.getNotifikasi().observe(getViewLifecycleOwner(), hashMap -> {
            if (hashMap.get("code").toString().equals("200") || hashMap.get("data").toString().equals(null)) {
                Log.d("Notif", "get notif gagal");
            }
            else if (hashMap.get("code").toString().equals("200")) {
                ArrayList<ModelNotifikasi> modelNotifikasis = (ArrayList<ModelNotifikasi>) hashMap.get("list");
                for (int i = 0; i < modelNotifikasis.size(); i++) {
                    if (modelNotifikasis.get(i).getStatus().equals("UNSEEN")) {
                        totalnotif++;
                    }
                }
                if (totalnotif > 0) {
                    menu.getItem(1).setIcon(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.notification_unseen));
                }
            }
        });
    }

    private void getNotifikasiProduct(){
        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getNotifikasiProduct(params).enqueue(new Callback<ResponseNotifikasiProducts>() {
            @Override
            public void onResponse(Call<ResponseNotifikasiProducts> call, Response<ResponseNotifikasiProducts> response) {
                if(response.body().getStatus() == 200){

                    final List<com.application.kreditimpian.Model.ModelNotifikasiProducts.ResultItem> NotifFitur = response.body().getResult();
                    if( NotifFitur==null) {
                        Log.d("Notif", "get notif gagal");

                    }
                    else if(NotifFitur.get(0).getStatus().equals("UNSEEN")){
                        totalnotif++;
                    }
                    if (totalnotif > 0) {
                        menu.getItem(1).setIcon(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.notification_unseen));
                    }

                    /*          *//*untuk jika responsecode 200 dengan data null*//*
                }else if(response.body().getResponseCode() == 200){
                    final List<com.application.kreditimpian.Model.ModelNotifFitur.DataItem> NotifFitur2 = response.body().getData();
                    if (NotifFitur2 == null){
                        Log.d("Notif", "get notif gagal");

                    }*/
                }else {
                    Log.d("Notif", "get notif gagal");

                }
            }

            @Override
            public void onFailure(Call<ResponseNotifikasiProducts> call, Throwable t) {

            }
        });

    }

    private void getNotifikasiFitur(){
        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", sharedPrefManager.getSpIdMember());

        mApiService.getNotifikasiFeature(params).enqueue(new Callback<ResponseNotifikasiFeatures>() {
            @Override
            public void onResponse(Call<ResponseNotifikasiFeatures> call, Response<ResponseNotifikasiFeatures> response) {
                if(response.body().getStatus() == 200){

                    final List<com.application.kreditimpian.Model.ModelNotifikasiFeatures.ResultItem> NotifFitur = response.body().getResult();
                    if( NotifFitur==null) {
                        Log.d("Notif", "get notif gagal");

                    }
                    else if(NotifFitur.get(0).getStatus().equals("UNSEEN")){
                        totalnotif++;
                    }
                    if (totalnotif > 0) {
                        menu.getItem(1).setIcon(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.notification_unseen));
                    }

          /*          *//*untuk jika responsecode 200 dengan data null*//*
                }else if(response.body().getResponseCode() == 200){
                    final List<com.application.kreditimpian.Model.ModelNotifFitur.DataItem> NotifFitur2 = response.body().getData();
                    if (NotifFitur2 == null){
                        Log.d("Notif", "get notif gagal");

                    }*/
                }else {
                    Log.d("Notif", "get notif gagal");

                }
            }

            @Override
            public void onFailure(Call<ResponseNotifikasiFeatures> call, Throwable t) {

            }
        });

    }

    private void getMitra(){

        mApiService.getMitraKami().enqueue(new Callback<ResponseMitraKami>() {
            @Override
            public void onResponse(Call<ResponseMitraKami> call, Response<ResponseMitraKami> response) {
                LoadingMitra.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    ///progressBar.dismiss();
                    if (response.body().getStatus() == 200) {
                        //swipeRefresh.setRefreshing(false);
                        ///progressBar.dismiss();
                        final List<com.application.kreditimpian.Model.ModelMitraKami.ResultItem> AllMitra = response.body().getResult();

                        rv_mitra.setAdapter(new AdapterMitraKami(mContext, AllMitra));
                        adapterMitraKami.notifyDataSetChanged();
                        ///empty.setVisibility(View.GONE);
                        //initDataIntent(Allproduct);
                    } else {
                        //progressBar.dismiss();
                        ///empty.setVisibility(View.VISIBLE);
                    }

                } else {
                    //progressBar.dismiss();
                    LoadingMitra.setVisibility(View.GONE);
                    Toast.makeText(mContext, "Gagal Refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMitraKami> call, Throwable t) {

            }
        });
    }

    private void getResultList() {
        ///progressBar = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);

        mApiService.getMerchnat().enqueue(new Callback<ResponseMerchant>() {
            @Override
            public void onResponse(Call<ResponseMerchant> call, Response<ResponseMerchant> response) {
                LoadingMerchant.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {

                        final List<ResultItem> AllMerchant = response.body().getResult();

                        rv_merchant.setAdapter(new AdapterMerchant(mContext, AllMerchant));
                        adapterMerchant.notifyDataSetChanged();
                       //// initDataIntent(AllMerchant);
                    } else {

                    }

                } else {
                    LoadingMerchant.setVisibility(View.GONE);
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

    private void initDataIntent(final List<ResultItem> detailListMerchant) {
        rv_merchant.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String idMerchant = detailListMerchant.get(0).getId();
                        String nameMerchant = detailListMerchant.get(0).getName();
                        String cityMerchant = detailListMerchant.get(0).getCity();
                        String imageMerchant = detailListMerchant.get(0).getImage();

                        Toast.makeText(getActivity(), "id merchant"+idMerchant, Toast.LENGTH_LONG);

                        Intent detatailMerchant = new Intent(mContext , DetailProductMitra.class);
                        detatailMerchant.putExtra(ConstansProductMitra.KEY_ID_MERCHANT, idMerchant);
                        detatailMerchant.putExtra(ConstansProductMitra.KEY_NAME_MERCHANT, nameMerchant);
                        detatailMerchant.putExtra(ConstansProductMitra.KEY_IMAGE_MERCHANT, imageMerchant);
                        detatailMerchant.putExtra(ConstansProductMitra.KEY_CITYP_MERCHANT, cityMerchant);
                        startActivity(detatailMerchant);

                    }
                })
        );

    }


    //////////////////////////////
/*
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
*/


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


   /* private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://kreditimpian.id/images/banners/banner-web-ramadhan-1-1.png"));
        fragments.add(FragmentSlider.newInstance("https://kreditimpian.id/images/banners/hitung2-1.png"));
        fragments.add(FragmentSlider.newInstance("https://kreditimpian.id/images/banners/banner-web-ramadhan-1.png"));
        fragments.add(FragmentSlider.newInstance("https://kreditimpian.id/images/banners/bca-2-2.png"));


        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);

        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();


    }*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menutopbar, menu);

        super.onCreateOptionsMenu(menu, inflater);
        this.menu = menu;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.notifikasi) {
            ///startActivity(new Intent(getContext(), NotifikasiActivity.class));
            startActivity(new Intent(getContext(), Notifikasi.class));
            ((Activity) Objects.requireNonNull(getContext())).finish();
        }
        if (id == R.id.cartshop) {
            gotocartshop();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        final MenuItem menuItem = menu.findItem(R.id.cartshop);
        View actionView = menuItem.getActionView();
        btnCart = actionView.findViewById(R.id.btnCart);
        textCartItemCount = actionView.findViewById(R.id.cart_badge);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotocartshop();
            }
        });

        ///menuItem.setOnMenuItemClickListener(this::onOptionsItemSelected);

        super.onPrepareOptionsMenu(menu);
    }

    private void gotocartshop() {
        Intent intent_cart = new Intent(getContext(), Cart.class);
        intent_cart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_cart);
    }


    private void getOnShoppingCart() {

        mApiService.getOnShoppingCart(sharedPrefManager.getSpIdMember()).enqueue(new Callback<ResponseOnShoppingCart>() {
            @Override
            public void onResponse(Call<ResponseOnShoppingCart> call, Response<ResponseOnShoppingCart> response) {

                if (response.body().getResponseCode() == 200) {

                    final List<com.application.kreditimpian.Model.ModelOnShoppingCart.DataItem> OnShippingCart = response.body().getData();

                    int itemCount = OnShippingCart.size();


                    ///textCartItemCount.setText(String.valueOf(itemCount));
                    if (textCartItemCount != null) {
                        if (itemCount == 0) {
                            if (textCartItemCount.getVisibility() != View.GONE) {
                                textCartItemCount.setVisibility(View.GONE);
                            }
                        } else {
                            textCartItemCount.setText(String.valueOf(itemCount));
                            if (textCartItemCount.getVisibility() != View.VISIBLE) {
                                textCartItemCount.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    ///Toast.makeText(getActivity(), "Jumlah List "+itemCount, Toast.LENGTH_SHORT).show();
                } else {

                    ////Toast.makeText(Cart.this, "Koneksi internet terputus", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<ResponseOnShoppingCart> call, Throwable t) {


            }
        });

    }



}
