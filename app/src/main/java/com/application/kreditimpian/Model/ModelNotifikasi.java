package com.application.kreditimpian.Model;

import android.os.Parcel;
import android.os.Parcelable;
import com.application.kreditimpian.Model.Shipping;
import com.application.kreditimpian.Model.Installment;
import com.google.gson.annotations.SerializedName;

public class ModelNotifikasi implements Parcelable {
    private String idMember;
    private String idNotifikasi;
    private String message;
    private String metadata;
    private String tgl;
    private String status;
    private String id_product;
    private String id_product_category;
    private String name;
    private String price_capital;
    private String price_sale;
    private String filename;
    private String name_merchant;
    private String tenor;
    private String down_payment;
    private String note;
    private String name_company;
    private String postal_fee;
    private String name_city;
    private String name_district;
    private String payment_method;
    private String total_pembayaran;
    private String number;

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Installment getInstallment() {
        return installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
    }

    @SerializedName("shipping")
    private Shipping shipping;

    @SerializedName("installment")
    private Installment installment;

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getId_product_category() {
        return id_product_category;
    }

    public void setId_product_category(String id_product_category) {
        this.id_product_category = id_product_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice_capital() {
        return price_capital;
    }

    public void setPrice_capital(String price_capital) {
        this.price_capital = price_capital;
    }

    public String getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(String price_sale) {
        this.price_sale = price_sale;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getName_merchant() {
        return name_merchant;
    }

    public void setName_merchant(String name_merchant) {
        this.name_merchant = name_merchant;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getDown_payment() {
        return down_payment;
    }

    public void setDown_payment(String down_payment) {
        this.down_payment = down_payment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName_company() {
        return name_company;
    }

    public void setName_company(String name_company) {
        this.name_company = name_company;
    }

    public String getPostal_fee() {
        return postal_fee;
    }

    public void setPostal_fee(String postal_fee) {
        this.postal_fee = postal_fee;
    }

    public String getName_city() {
        return name_city;
    }

    public void setName_city(String name_city) {
        this.name_city = name_city;
    }

    public String getName_district() {
        return name_district;
    }

    public void setName_district(String name_district) {
        this.name_district = name_district;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getTotal_pembayaran() {
        return total_pembayaran;
    }

    public void setTotal_pembayaran(String total_pembayaran) {
        this.total_pembayaran = total_pembayaran;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    private String courier;

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getIdNotifikasi() {
        return idNotifikasi;
    }

    public void setIdNotifikasi(String idNotifikasi) {
        this.idNotifikasi = idNotifikasi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ModelNotifikasi() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idMember);
        dest.writeString(this.idNotifikasi);
        dest.writeString(this.message);
        dest.writeString(this.metadata);
        dest.writeString(this.tgl);
        dest.writeString(this.status);
        dest.writeString(this.id_product);
        dest.writeString(this.id_product_category);
        dest.writeString(this.name);
        dest.writeString(this.price_capital);
        dest.writeString(this.price_sale);
        dest.writeString(this.filename);
        dest.writeString(this.name_merchant);
        dest.writeString(this.tenor);
        dest.writeString(this.down_payment);
        dest.writeString(this.note);
        dest.writeString(this.name_company);
        dest.writeString(this.postal_fee);
        dest.writeString(this.name_city);
        dest.writeString(this.name_district);
        dest.writeString(this.payment_method);
        dest.writeString(this.total_pembayaran);
        dest.writeString(this.number);
        dest.writeString(this.courier);
    }

    protected ModelNotifikasi(Parcel in) {
        this.idMember = in.readString();
        this.idNotifikasi = in.readString();
        this.message = in.readString();
        this.metadata = in.readString();
        this.tgl = in.readString();
        this.status = in.readString();
        this.id_product = in.readString();
        this.id_product_category = in.readString();
        this.name = in.readString();
        this.price_capital = in.readString();
        this.price_sale = in.readString();
        this.filename = in.readString();
        this.name_merchant = in.readString();
        this.tenor = in.readString();
        this.down_payment = in.readString();
        this.note = in.readString();
        this.name_company = in.readString();
        this.postal_fee = in.readString();
        this.name_city = in.readString();
        this.name_district = in.readString();
        this.payment_method = in.readString();
        this.total_pembayaran = in.readString();
        this.number = in.readString();
        this.courier = in.readString();
    }

    public static final Creator<ModelNotifikasi> CREATOR = new Creator<ModelNotifikasi>() {
        @Override
        public ModelNotifikasi createFromParcel(Parcel source) {
            return new ModelNotifikasi(source);
        }

        @Override
        public ModelNotifikasi[] newArray(int size) {
            return new ModelNotifikasi[size];
        }
    };
}
