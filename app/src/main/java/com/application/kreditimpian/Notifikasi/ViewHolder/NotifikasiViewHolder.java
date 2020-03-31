package com.application.kreditimpian.Notifikasi.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.R;

public class NotifikasiViewHolder extends RecyclerView.ViewHolder {
    public TextView txtMessageNotif,
            txtTglNotif,status,number,
            id_product,
            id_product_category,
            id_currency,
            name,
            price_capital,price_sale,
            filename,
            tenor,
            down_payment,
            note,
            name_company,
            postal_fee,
            address_label,
            receiver,
            mobile, city,
            district,
            address,
            postal_code,
            name_city,
            name_district,
            installment,
            payment_method,
            total_pembayaran,
            courier,text_image,
            name_merchant;
    public ConstraintLayout layoutKlik;
    public NotifikasiViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTglNotif = itemView.findViewById(R.id.txtTglNotif);
        txtMessageNotif = itemView.findViewById(R.id.txtMessageNotif);
        status = itemView.findViewById(R.id.status);
        number = itemView.findViewById(R.id.number);
        id_product = itemView.findViewById(R.id.id_product);
        id_product_category = itemView.findViewById(R.id.id_product_category);
        id_currency = itemView.findViewById(R.id.id_currency);
        name = itemView.findViewById(R.id.name);
        price_capital = itemView.findViewById(R.id.price_capital);
        price_sale = itemView.findViewById(R.id.price_sale);
        filename = itemView.findViewById(R.id.filename);
        tenor = itemView.findViewById(R.id.tenor);
        down_payment = itemView.findViewById(R.id.down_payment);
        note = itemView.findViewById(R.id.note);
        name_company = itemView.findViewById(R.id.name_company);
        postal_fee = itemView.findViewById(R.id.postal_fee);
        address_label = itemView.findViewById(R.id.address_label);
        receiver = itemView.findViewById(R.id.receiver);
        mobile = itemView.findViewById(R.id.mobile);
        city = itemView.findViewById(R.id.city);
        address = itemView.findViewById(R.id.address);
        postal_code = itemView.findViewById(R.id.postal_code);
        name_city = itemView.findViewById(R.id.name_city);
        name_district = itemView.findViewById(R.id.name_district);
        payment_method = itemView.findViewById(R.id.payment_method);
        total_pembayaran = itemView.findViewById(R.id.total_pembayaran);
        courier = itemView.findViewById(R.id.courier);
        name_merchant = itemView.findViewById(R.id.name_merchant);
        text_image = itemView.findViewById(R.id.text_image);
        installment = itemView.findViewById(R.id.installment);


        layoutKlik = itemView.findViewById(R.id.layoutKlik);
    }
}
