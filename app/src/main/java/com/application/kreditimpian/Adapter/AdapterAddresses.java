package com.application.kreditimpian.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Akun.AlamatPengiriman;
import com.application.kreditimpian.Akun.DataDiri;
import com.application.kreditimpian.Akun.DetailAlamat;
import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Model.ModelAddress.ResponseAddress;
import com.application.kreditimpian.Model.ModelListAlamat.DataItem;
import com.application.kreditimpian.R;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by indriyanto Nugroho on 28 Jan 2020.
 */
public class AdapterAddresses extends RecyclerView.Adapter<AdapterAddresses.HolderAdresses> {

    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    List<DataItem> dataItemList;
    Context mContext;

    //This will add all the items in the adapter's list
    public void addAllItems(List<DataItem> items) {
        dataItemList.addAll(items);
        notifyDataSetChanged();
    }

    public AdapterAddresses(Context context, List<DataItem> dataList) {
        this.mContext = context;
        dataItemList = dataList;
    }

    @NonNull
    @Override
    public HolderAdresses onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_addresses, parent, false);
        return new HolderAdresses(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdresses holder, int position) {
        final DataItem dataItem = dataItemList.get(position);

        holder.txt_id.setText(dataItem.getId());
        holder.txt_member.setText(dataItem.getIdMember());
        holder.txt_nama_alamat.setText(dataItem.getAddressName());
        holder.txt_name_penerima.setText(dataItem.getReceiver());
        holder.txt_nomor_handphone.setText(dataItem.getPhone());
        holder.txt_alamat.setText(dataItem.getAddress());
        holder.txt_main_address.setText(dataItem.getMainAddress());
        holder.txt_kodepost.setText(dataItem.getPostalCode());
        holder.txt_alamat_utama.setText(dataItem.getMainAddress());
        holder.id_geodirectory.setText(dataItem.getIdGeodirectory());
        holder.district.setText(dataItem.getDistrict());


        /*Jika alamat utama value nya YES maka Switch Checked*/
        String AlamatUtama = dataItem.getMainAddress();
        if (AlamatUtama.equals("YES")) {
            /// holder.SwitchAddress.setChecked(true);
            holder.SwitchAddress.setVisibility(View.GONE);
            holder.tvAlamatUtama.setVisibility(View.VISIBLE);
            holder.textMainAdress.setVisibility(View.GONE);

        } else if (AlamatUtama.equals("NO")) {
            holder.SwitchAddress.setChecked(false);

        }

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class HolderAdresses extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_nama_alamat)
        TextView txt_nama_alamat;
        @BindView(R.id.txt_name_penerima)
        TextView txt_name_penerima;
        @BindView(R.id.txt_nomor_handphone)
        TextView txt_nomor_handphone;
        @BindView(R.id.txt_alamat)
        TextView txt_alamat;
        @BindView(R.id.txt_kodepost)
        TextView txt_kodepost;
        @BindView(R.id.txt_id)
        TextView txt_id;
        @BindView(R.id.txt_member)
        TextView txt_member;
        @BindView(R.id.id_geodirectory)
        TextView id_geodirectory;
        @BindView(R.id.district)
        TextView district;
        @BindView(R.id.txt_main_address)
        TextView txt_main_address;
        @BindView(R.id.txt_alamat_utama)
        TextView txt_alamat_utama;
        @BindView(R.id.text_alamat_utama)
        TextView text_alamat_utama;
        @BindView(R.id.SwitchAddress)
        Switch SwitchAddress;
        @BindView(R.id.tvAlamatUtama)
        TextView tvAlamatUtama;
        @BindView(R.id.textMainAdress)
        TextView textMainAdress;


        public HolderAdresses(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mApiService = UtilsApi.getAPIService();
            /// sharedPrefManager = new SharedPrefManager(mContext);

            /*unutk Switch Alamat utama*/
            SwitchAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        UpdateAddreses();
                    } else {
                        UpdateAddreses();

                    }
                }
            });
        }

        private void UpdateAddreses() {
            HashMap<String, String> params = new HashMap<>();
            /*params.put("address_name", txt_nama_alamat.getText().toString());
            params.put("phone", txt_nomor_handphone.getText().toString());
            params.put("receiver", txt_name_penerima.getText().toString());
            params.put("id_geodirectory", id_geodirectory.getText().toString());
            params.put("district", district.getText().toString());
            params.put("postal_code", txt_kodepost.getText().toString());
            params.put("address", txt_alamat.getText().toString());*/
            params.put("id_member", txt_member.getText().toString());
            String AddressMain;
            if (SwitchAddress.isChecked())
                AddressMain = SwitchAddress.getTextOn().toString();
            else
                AddressMain = SwitchAddress.getTextOff().toString();
            params.put("main_address", AddressMain.trim());

            mApiService.updateMainAddress(txt_id.getText().toString(), params).enqueue(new Callback<ResponseAddress>() {
                @Override
                public void onResponse(Call<ResponseAddress> call, Response<ResponseAddress> response) {
                    if (response.body().getResponseCode() == 200) {
                        // Toast.makeText(mContext, response.body().getMessage() , Toast.LENGTH_LONG).show();
                        Log.v("Jajal ", "Sukses Switch On");


                    } else {
                        Log.v("Jajal ", "Sukses Switch Of");

                        ///Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAddress> call, Throwable t) {

                }
            });

        }

    }
}
