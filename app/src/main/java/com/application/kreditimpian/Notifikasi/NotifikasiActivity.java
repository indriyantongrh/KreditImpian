package com.application.kreditimpian.Notifikasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.UpgradeImpianViewModel;
import com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel.ViewModelFactory;
import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.application.kreditimpian.Model.ModelNotifikasi;
import com.application.kreditimpian.Notifikasi.ViewHolder.NotifikasiViewHolder;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.Objects;

public class NotifikasiActivity extends AppCompatActivity implements View.OnClickListener, NotifikasiAdapter.OnSeenClick {
    private Context context;
    private NotifikasiAdapter notifikasiAdapter;
    private ModelNotifikasi modelNotifikasi;
    private UpgradeImpianViewModel upgradeImpianViewModel;
    ImageView empty;

    private ImageView imgBack;
    private RecyclerView rvNotifikasi;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progress_bar;

    private String idMember;
    private static final int PAGE_SIZE = 10;
    private boolean isLastPage = false,
            isLoading = false;

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = linearLayoutManager.getChildCount();
            int totalItemCount = linearLayoutManager.getItemCount();
            int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                    isLoading = true;
                    new Handler().postDelayed(() -> loadPaging(), 2000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);
        Objects.requireNonNull(getSupportActionBar()).hide();
        context = NotifikasiActivity.this;
        upgradeImpianViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelFactory(context)).get(UpgradeImpianViewModel.class);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        idMember = sharedPrefManager.getSpIdMember();

        View includeToolbar = findViewById(R.id.includeToolbar);
        imgBack = includeToolbar.findViewById(R.id.imgBack);
        rvNotifikasi = findViewById(R.id.rvNotifikasi);
        progress_bar = findViewById(R.id.progress_bar);
        SwipeRefreshLayout swipeRefresh = findViewById(R.id.swipeRefresh);
        empty = findViewById(R.id.empty);

        linearLayoutManager = new LinearLayoutManager(context);
        rvNotifikasi.setLayoutManager(linearLayoutManager);

        notifikasiAdapter = new NotifikasiAdapter(context, this);
        rvNotifikasi.setAdapter(notifikasiAdapter);
        rvNotifikasi.addOnScrollListener(recyclerViewOnScrollListener);

        imgBack.setOnClickListener(this);

        swipeRefresh.setColorSchemeResources(R.color.colorOrange, R.color.colorOrange);
        swipeRefresh.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            swipeRefresh.setRefreshing(false);
            getNotifikasi();
        }, 2000));
        getNotifikasi();
//        if (getIntent().hasExtra("listnotif")) {
//            ArrayList<ModelNotifikasi> modelNotifikasis = getIntent().getParcelableArrayListExtra("listnotif");
//            if (modelNotifikasis != null) {
//                loadNotifikasi(modelNotifikasis);
//            }
//        }
    }

    @Override
    public void onClick(View v) {
        if (v == imgBack) {
            startActivity(new Intent(context, MenuUtama.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(context, MenuUtama.class));
        finish();
        super.onBackPressed();
    }

    private void loadNotifikasi(ArrayList<ModelNotifikasi> modelNotifikasis) {
        notifikasiAdapter.clear();
        if (modelNotifikasis != null) {
            int batas;
            if (modelNotifikasis.size() >= PAGE_SIZE) {
                batas = PAGE_SIZE;
            } else {
                batas = modelNotifikasis.size();
            }
            ArrayList<ModelNotifikasi> modelNotifikasiArrayList = new ArrayList<>();
            for (int i = 0; i < batas; i++) {
                modelNotifikasi = new ModelNotifikasi();
                modelNotifikasi.setIdNotifikasi(modelNotifikasis.get(i).getIdNotifikasi());
                modelNotifikasi.setMessage(modelNotifikasis.get(i).getMessage());
                modelNotifikasi.setStatus(modelNotifikasis.get(i).getStatus());
                modelNotifikasi.setTgl(modelNotifikasis.get(i).getTgl());
                modelNotifikasiArrayList.add(modelNotifikasi);
            }
            notifikasiAdapter.addAll(modelNotifikasiArrayList);
            if (batas == modelNotifikasis.size()) {
                notifikasiAdapter.setLoading(false);
            }
        } else {
            notifikasiAdapter.setLoading(false);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPaging() {
        isLoading = false;
        modelNotifikasi = new ModelNotifikasi();
        modelNotifikasi.setIdMember(idMember);

        upgradeImpianViewModel.setModelNotifikasi(modelNotifikasi);
        upgradeImpianViewModel.getNotifikasi().observe(this, hashMap -> {
            if (hashMap.get("code").toString().equals("200")) {
                ArrayList<ModelNotifikasi> modelNotifikasis = (ArrayList<ModelNotifikasi>) hashMap.get("list");
                if (modelNotifikasis != null) {
                    int index = notifikasiAdapter.getItemCount() - 1;
                    int end;
                    if (index + PAGE_SIZE <= modelNotifikasis.size()) {
                        end = index + PAGE_SIZE;
                    } else {
                        end = modelNotifikasis.size();
                    }
                    if (end <= modelNotifikasis.size()) {
                        ArrayList<ModelNotifikasi> modelNotifikasiArrayList = new ArrayList<>();
                        for (int i = index; i < end; i++) {
                            ModelNotifikasi modelNotifikasi = new ModelNotifikasi();
                            modelNotifikasi.setIdNotifikasi(modelNotifikasis.get(i).getIdNotifikasi());
                            modelNotifikasi.setMessage(modelNotifikasis.get(i).getMessage());
                            modelNotifikasi.setStatus(modelNotifikasis.get(i).getStatus());
                            modelNotifikasi.setTgl(modelNotifikasis.get(i).getTgl());
                            modelNotifikasiArrayList.add(modelNotifikasi);
                        }
                        notifikasiAdapter.addAll(modelNotifikasiArrayList);
                        if (end >= modelNotifikasis.size()) {
                            notifikasiAdapter.setLoading(false);
                        }
                    } else {
                        notifikasiAdapter.setLoading(false);
                    }
                } else {
                    notifikasiAdapter.setLoading(false);
                }
            }
        });
    }


    private void getNotifikasi() {
        progress_bar.setVisibility(View.VISIBLE);
        notifikasiAdapter.clear();
        modelNotifikasi = new ModelNotifikasi();
        modelNotifikasi.setIdMember(idMember);


        upgradeImpianViewModel.setModelNotifikasi(modelNotifikasi);
        upgradeImpianViewModel.getNotifikasi().observe(this, hashMap -> {
            if (hashMap.get("code").toString().equals("200")) {
                ArrayList<ModelNotifikasi> modelNotifikasis = (ArrayList<ModelNotifikasi>) hashMap.get("list");
                if (modelNotifikasis != null) {

                    int batas;
                    if (modelNotifikasis.size() >= PAGE_SIZE) {
                        batas = PAGE_SIZE;
                    } else {
                        batas = modelNotifikasis.size();
                    }
                    ArrayList<ModelNotifikasi> modelNotifikasiArrayList = new ArrayList<>();
                    for (int i = 0; i < batas; i++) {
                        modelNotifikasi = new ModelNotifikasi();
                        modelNotifikasi.setIdNotifikasi(modelNotifikasis.get(i).getIdNotifikasi());
                        modelNotifikasi.setMessage(modelNotifikasis.get(i).getMessage());
                        modelNotifikasi.setStatus(modelNotifikasis.get(i).getStatus());
                        modelNotifikasi.setTgl(modelNotifikasis.get(i).getTgl());
                        modelNotifikasiArrayList.add(modelNotifikasi);
                    }
                    notifikasiAdapter.addAll(modelNotifikasiArrayList);
                    if (batas == modelNotifikasis.size()) {
                        notifikasiAdapter.setLoading(false);
                    }
                } else {
                    notifikasiAdapter.setLoading(false);
                }
            }else{
                empty.setVisibility(View.VISIBLE);
            }

            progress_bar.setVisibility(View.GONE);
        });
    }

    @Override
    public void onSeenClick(String idNotifikasi, NotifikasiViewHolder holder) {
        Log.v("jajal", idNotifikasi + " a");
        ModelNotifikasi modelNotifikasi = new ModelNotifikasi();
        modelNotifikasi.setIdNotifikasi(idNotifikasi);

        upgradeImpianViewModel.setModelNotifikasi(modelNotifikasi);
        upgradeImpianViewModel.updateNotifikasi().observe(this, s -> {
            if (s.equals("200")) {
                holder.layoutKlik.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }
        });
    }
}
