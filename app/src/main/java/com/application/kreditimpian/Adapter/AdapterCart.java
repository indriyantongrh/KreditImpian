package com.application.kreditimpian.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Akun.DataDiri;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.UtilsApi;
import com.application.kreditimpian.Constan.ConstanTransaction;
import com.application.kreditimpian.Model.ModelDeleteShopingCart.ResponseDeleteShopingCart;
import com.application.kreditimpian.Model.ModelOnShoppingCart.DataItem;
import com.application.kreditimpian.Model.ModelOnShoppingCart.ResponseOnShoppingCart;
import com.application.kreditimpian.R;
import com.application.kreditimpian.TransactionProcess.Cart;
import com.application.kreditimpian.TransactionProcess.TransactionSelectMitra;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Tasks;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.application.kreditimpian.Api.network.interceptor.MyApp.getContext;

/**
 * Created by indriyanto Nugroho on 30 Jan 2020.
 */
public class AdapterCart extends RecyclerView.Adapter<AdapterCart.HolderCart> {
    BaseApiService mApiService;
    ProgressDialog pDialog;
    String ImageProduct;
    List<DataItem> dataItemList;
    Context mContext;
    String number;
    AdapterCart adapterCart;
    public AdapterCart(Context context, List<DataItem> dataList) {
        this.mContext = context;
        dataItemList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cart, parent, false);
        return new HolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCart holder, int position) {
        final DataItem dataItem = dataItemList.get(position);

        holder.txt_id.setText(dataItem.getId_product());
        holder.txt_id_product_category.setText(dataItem.getIdProductCategory());
        holder.txt_name_product.setText(dataItem.getName());
        holder.txt_reference_id.setText(dataItem.getReferenceId());
        holder.txt_status.setText(dataItem.getStatus());
        holder.txt_number.setText(dataItem.getNumber());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int Price_Capital = (Integer.parseInt(dataItem.getPriceCapital()));
        int Price_Sale = (Integer.parseInt(dataItem.getPriceSale()));

        if (Price_Capital == Price_Sale) {
            holder.txt_price_capital.setVisibility(View.GONE);
        } else {
            holder.txt_price_capital.setVisibility(View.VISIBLE);
        }
        holder.txt_price_capital.setText(formatRupiah.format(Price_Capital));
        holder.txt_price_sale.setText(formatRupiah.format(Price_Sale));
        holder.txt_image.setText(dataItem.getFilename());
        //// String ImageProduct =  (dataItem.getFilename());
//
//            if (dataItem.getFilename().equals("default")) {
//                holder.image.setImageResource(R.drawable.no_image);
//            } else {
//                Glide.with(getContext()).load(dataItem.getFilename()).into(holder.image);
//            }

        Glide.with(mContext)
                .load(dataItem.getFilename())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image);

        holder.itemView.setTag(dataItem.getNumber());


    }

    @Override
    public int getItemCount() {
        return dataItemList.size();

    }

//    public void removeDataItems(int position){
//        dataItemList.remove(position);
//        notifyItemRemoved(position);
//    }



    public class HolderCart extends RecyclerView.ViewHolder {

        @BindView(R.id.btnclick)
        CardView btnclick;
        @BindView(R.id.txt_name_product)
        TextView txt_name_product;
        @BindView(R.id.txt_price_capital)
        TextView txt_price_capital;
        @BindView(R.id.txt_price_sale)
        TextView txt_price_sale;
        @BindView(R.id.txt_id)
        TextView txt_id;
        @BindView(R.id.txt_reference_id)
        TextView txt_reference_id;
        @BindView(R.id.txt_id_product_category)
        TextView txt_id_product_category;
        @BindView(R.id.txt_number)
        TextView txt_number;
        @BindView(R.id.txt_image)
        TextView txt_image;
        @BindView(R.id.txt_status)
        TextView txt_status;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.btnDelete)
        ImageView btnDelete;


        public HolderCart(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mApiService = UtilsApi.getAPIService();



            btnclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DataItem detaiList = dataItemList.get(0);

                    String id_product = detaiList.getId_product();
                    String id_product_category = detaiList.getIdProductCategory();
                    String nameProduct = detaiList.getName();
                    String reference_id = detaiList.getReferenceId();
                    String id_transaction = detaiList.getId_transaction();
                    String nomor_invoice = detaiList.getNumber();
                    String price_capital = detaiList.getPriceCapital();
                    String price_sale = detaiList.getPriceSale();
                    String imageProduct = detaiList.getFilename();
                    String weight = detaiList.getWeight();
                    String origin = detaiList.getOrigin();
                    String destination = detaiList.getDestination();

                    Intent detailproduct = new Intent(v.getContext(), TransactionSelectMitra.class);
                    detailproduct.putExtra(ConstanTransaction.KEY_ID_PRODUCT, id_product);
                    detailproduct.putExtra(ConstanTransaction.KEY_ID_PRODUCT_CATEGORY_TRANSACTION, id_product_category);
                    detailproduct.putExtra(ConstanTransaction.KEY_NAME_PRODUCT_TRANSACTION, nameProduct);
                    detailproduct.putExtra(ConstanTransaction.KEY_REFERENCE_ID, reference_id);
                    detailproduct.putExtra(ConstanTransaction.KEY_ID_TRANSACTION, id_transaction);
                    detailproduct.putExtra(ConstanTransaction.KEY_NUMBER, nomor_invoice);
                    detailproduct.putExtra(ConstanTransaction.KEY_PRICE_CAPITAL_TRANSACTION, price_capital);
                    detailproduct.putExtra(ConstanTransaction.KEY_PRICE_SALE_TRANSACTION, price_sale);
                    detailproduct.putExtra(ConstanTransaction.KEY_FILENAME_TRANSACTION, imageProduct);
                    detailproduct.putExtra(ConstanTransaction.KEY_WEIGHT_TRANSACTION, weight);
                    detailproduct.putExtra(ConstanTransaction.KEY_ORIGIN_TRANSACTION, origin);
                    detailproduct.putExtra(ConstanTransaction.KEY_DESTINATION_TRANSACTION, destination);

                    v.getContext().startActivity(detailproduct);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                    /// builder.setTitle("Keluar ");
                    builder.setMessage("Apakah kamu yakin ingin menghapus ?");

                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            deleteCart();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }

                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();


                    /*final DataItem detaiList = dataItemList.get(0);
                    String nomor_invoice = detaiList.getNumber();*/

                    /*String number = txt_number.getText().toString();
                    Toast.makeText(mContext, ""+number, Toast.LENGTH_LONG).show();*/

                    //deleteCart();
                    ///Toast.makeText(mContext, "Klikked", Toast.LENGTH_LONG).show();
                }
            });






        }

        private void deleteCart(){
            String number = txt_number.getText().toString();


            mApiService.deleteCart(number).enqueue(new Callback<ResponseDeleteShopingCart>() {
                @Override
                public void onResponse(Call<ResponseDeleteShopingCart> call, Response<ResponseDeleteShopingCart> response) {
                    if(response.body().getResponseCode()==200){
                        Toast.makeText(mContext, "Barang dihapus", Toast.LENGTH_LONG).show();

                         notifyDataSetChanged();
                    }else {
                        Toast.makeText(mContext, "Gagal hapus", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseDeleteShopingCart> call, Throwable t) {
                   //// progressBar.dismiss();
                    Toast.makeText(mContext, "Gagal Refresh", Toast.LENGTH_SHORT).show();
                }
            });

        }



    }



}
