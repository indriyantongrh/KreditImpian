package com.application.kreditimpian.Notifikasi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelNotifikasi;
import com.application.kreditimpian.Notifikasi.ViewHolder.LoadingViewHolder;
import com.application.kreditimpian.Notifikasi.ViewHolder.NotifikasiViewHolder;
import com.application.kreditimpian.R;

import java.util.ArrayList;

public class NotifikasiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ModelNotifikasi> listNotif;
    private OnSeenClick onSeenClick;

    private boolean loading = true;
    private final int VIEW_TYPE_ITEM = 0,
            VIEW_TYPE_LOADING = 1;

    public NotifikasiAdapter(Context context, OnSeenClick onSeenClick) {
        this.context = context;
        listNotif = new ArrayList<>();
        this.onSeenClick = onSeenClick;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return VIEW_TYPE_LOADING;
        }
        return VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            return new NotifikasiViewHolder(LayoutInflater.from(context).inflate(R.layout.list_notifikasi, parent, false));
        } else if (viewType == VIEW_TYPE_LOADING) {
            return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_loading, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NotifikasiViewHolder) {
            final NotifikasiViewHolder notifikasiViewHolder = (NotifikasiViewHolder) holder;
            ModelNotifikasi modelNotifikasi = listNotif.get(position);
            notifikasiViewHolder.txtMessageNotif.setText(modelNotifikasi.getMessage());
            notifikasiViewHolder.txtTglNotif.setText(modelNotifikasi.getTgl());
            if (modelNotifikasi.getStatus().equals("UNSEEN")) {
                notifikasiViewHolder.layoutKlik.setBackgroundColor(ContextCompat.getColor(context, R.color.colorOrangetransparent));
            } else{
                notifikasiViewHolder.layoutKlik.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }
            notifikasiViewHolder.status.setText(modelNotifikasi.getMetadata());
            notifikasiViewHolder.number.setText(modelNotifikasi.getNumber());
            notifikasiViewHolder.id_product.setText(modelNotifikasi.getId_product());
            notifikasiViewHolder.id_product_category.setText(modelNotifikasi.getId_product_category());
            notifikasiViewHolder.name.setText(modelNotifikasi.getName());
            notifikasiViewHolder.name_merchant.setText(modelNotifikasi.getName_merchant());
            notifikasiViewHolder.tenor.setText(modelNotifikasi.getTenor());
            notifikasiViewHolder.text_image.setText(modelNotifikasi.getFilename());
            notifikasiViewHolder.down_payment.setText(modelNotifikasi.getDown_payment());
            notifikasiViewHolder.note.setText(modelNotifikasi.getNote());
            notifikasiViewHolder.name_company.setText(modelNotifikasi.getName_company());
            notifikasiViewHolder.postal_fee.setText(modelNotifikasi.getPostal_fee());
            /*notifikasiViewHolder.address_label.setText(modelNotifikasi.());
            notifikasiViewHolder.receiver.setText(modelNotifikasi.getTgl());
            notifikasiViewHolder.mobile.setText(modelNotifikasi.getTgl());
            notifikasiViewHolder.city.setText(modelNotifikasi.getTgl());
            notifikasiViewHolder.district.setText(modelNotifikasi.getTgl());
            notifikasiViewHolder.address.setText(modelNotifikasi.getTgl());
            notifikasiViewHolder.postal_code.setText(modelNotifikasi.getTgl());*/
            notifikasiViewHolder.name_city.setText(modelNotifikasi.getName_city());
            notifikasiViewHolder.name_district.setText(modelNotifikasi.getName_district());
            notifikasiViewHolder.payment_method.setText(modelNotifikasi.getPayment_method());
            notifikasiViewHolder.total_pembayaran.setText(modelNotifikasi.getTotal_pembayaran());
            notifikasiViewHolder.courier.setText(modelNotifikasi.getCourier());

            String jsonMember = modelNotifikasi.getInstallment().getJsonMember0();
            notifikasiViewHolder.installment.setText(jsonMember);

            holder.itemView.setOnClickListener(v -> onSeenClick.onSeenClick(modelNotifikasi.getIdNotifikasi(), notifikasiViewHolder));
        }else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
            loadingViewHolder.progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listNotif.size() == 0 ? 0 : listNotif.size() + 1;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    private boolean isPositionFooter(int position) {
        return position == listNotif.size();
    }

    private void add(ModelNotifikasi item) {
        listNotif.add(item);
        notifyItemInserted(listNotif.size());
    }

    public void addAll(ArrayList<ModelNotifikasi> data) {
        for (ModelNotifikasi modelNotifikasi : data) {
            add(modelNotifikasi);
        }
    }

    public void remove(ModelNotifikasi item) {
        int position = listNotif.indexOf(item);
        if (position > -1) {
            listNotif.remove(position);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public ModelNotifikasi getItem(int position) {
        return listNotif.get(position);
    }

    public interface OnSeenClick {
        void onSeenClick(String idNotifikasi, NotifikasiViewHolder holder);
    }
}
