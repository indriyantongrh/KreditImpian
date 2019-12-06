//package com.application.kreditimpian.Adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.application.kreditimpian.DetailProduct;
//import com.application.kreditimpian.FragSemuaKategori.DetailSemuaKategori;
//import com.application.kreditimpian.Model.ModelAllProduct.AllProductResponse;
//import com.application.kreditimpian.Model.ModelAllProduct.ResultItem;
//import com.application.kreditimpian.R;
//
//import java.util.ArrayList;
//
//public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> implements Filterable {
////    private ArrayList<ResultItem> mArrayList;
////    private ArrayList<ResultItem> mFilteredList;
////
////    private Context context;
////
////
////    public AllProductAdapter(Context context, ArrayList<ResultItem> arrayList) {
////        this.context = context;
////        this.mArrayList = arrayList;
////        this.mFilteredList = arrayList;
////
////    }
////    @Override
////    public AllProductAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
////        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_product, viewGroup, false);
////        return new AllProductAdapter.ViewHolder(view);
////    }
////
////
////
////    @Override
////    public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolder viewHolder, int i) {
////        viewHolder.txt_id.setText(mFilteredList.get(i).getId());
////        viewHolder.txt_id_product_category.setText(mFilteredList.get(i).getIdProductCategory());
////        viewHolder.txt_id_currency.setText(mFilteredList.get(i).getIdCurrency());
////        viewHolder.txt_name_product.setText((mFilteredList.get(i).getName()));
////        viewHolder.txt_price_capital.setText(mFilteredList.get(i).getPriceCapital());  //untuk mengirim url gambar ke berbagai activity
////        viewHolder.txt_price_sale.setText(mFilteredList.get(i).getPriceSale());
////        viewHolder.txt_description.setText(mFilteredList.get(i).getDescription());
////        ///  viewHolder.txt_nama_perusahaan.setText(mFilteredList.get(i).getNama_perusahaan());
////        viewHolder.txt_sku.setText(mFilteredList.get(i).getSku());
////        ///  viewHolder.txt_gambar.setText(mFilteredList.get(i).getGambar());
////        viewHolder.txt_stock.setText(mFilteredList.get(i).getSku());
////        viewHolder.txt_condition.setText(mFilteredList.get(i).getCondition());
////        viewHolder.txt_deliverable.setText(mFilteredList.get(i).getDeliverable());
////        viewHolder.txt_downloadable.setText(mFilteredList.get(i).getDownloadable());
////        viewHolder.txt_target_gender.setText(mFilteredList.get(i).getTargetGender());
////        viewHolder.txt_target_age.setText(mFilteredList.get(i).getTargetAge());
////        viewHolder.txt_visibility.setText(mFilteredList.get(i).getVisibility());
////
////
////    }
////
////    @Override
////    public int getItemCount() {
////        return mFilteredList.size();
////    }
////
////
////    @Override
////    public Filter getFilter() {
////
////            return new Filter() {
////                @Override
////                protected FilterResults performFiltering(CharSequence charSequence) {
////
////                    String charString = charSequence.toString();
////
////                    if (charString.isEmpty()) {
////
////                        mFilteredList = mArrayList;
////                    } else {
////
////                        ArrayList<ResultItem> filteredList = new ArrayList<>();
////
////                        for (ResultItem androidVersion : mArrayList) {
////
////                            if (androidVersion.getId().toLowerCase().contains(charString) || androidVersion.getName().toLowerCase().contains(charString) ) {
////                                filteredList.add(androidVersion);
////                            }
////                        }
////                        mFilteredList = filteredList;
////                    }
////                    FilterResults filterResults = new FilterResults();
////                    filterResults.values = mFilteredList;
////                    return filterResults;
////                }
////                @Override
////                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
////                    mFilteredList = (ArrayList<ResultItem>) filterResults.values;
////                    notifyDataSetChanged();
////                }
////            };
////        }
////
////
////    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
////        private TextView txt_id,txt_id_product_category,txt_id_currency,txt_name_product,txt_price_capital,txt_price_sale,txt_description,
////                        txt_sku,txt_stock,txt_condition,txt_deliverable,txt_downloadable,txt_target_gender,txt_target_age,txt_visibility;
////        private ImageView image;
////
////        public ViewHolder(View view) {
////            super(view);
////
////            txt_id = (TextView) view.findViewById(R.id.txt_id);
////            txt_id_product_category = (TextView) view.findViewById(R.id.txt_id_product_category);
////            txt_id_currency = (TextView)view.findViewById(R.id.txt_id_currency);
////            txt_name_product = (TextView)view.findViewById(R.id.txt_name_product);
////            txt_price_capital = (TextView)view.findViewById(R.id.txt_price_capital);
////            txt_price_sale = (TextView)view.findViewById(R.id.txt_price_sale);
////            ////image = (ImageView)view.findViewById(R.id.image);
////            txt_description = (TextView)view.findViewById(R.id.txt_description);
////            txt_sku = (TextView)view.findViewById(R.id.txt_sku);
////            txt_stock = (TextView)view.findViewById(R.id.txt_stock);
////            txt_condition = (TextView) view.findViewById(R.id.txt_condition);
////            txt_deliverable = (TextView) view.findViewById(R.id.txt_deliverable);
////            txt_downloadable = (TextView) view.findViewById(R.id.txt_downloadable);
////            txt_target_gender = (TextView) view.findViewById(R.id.txt_target_gender);
////            txt_target_age = (TextView) view.findViewById(R.id.txt_target_age);
////            txt_visibility = (TextView) view.findViewById(R.id.txt_visibility);
////
////
////
////
////
////        }
////
////        @Override
////        public void onClick(View view) {
////            Intent detail = new Intent(view.getContext(), DetailProduct.class);
////
/////*            detail.putExtra("id_pelamarmasuk", txt_id_pelamarmasuk.getText());
////            detail.putExtra("id_user", txt_id_user.getText());
////            detail.putExtra("id_perusahaan", txt_id_perusahaan.getText());
////            detail.putExtra("nama_perusahaan", txt_nama_perusahaan.getText());
////            detail.putExtra("id_lowongankerja", txt_id_lowongan.getText());
////            detail.putExtra("judul_lowongan", txt_judul_lowongan.getText());
////            detail.putExtra("deskripsi", txt_deskripsi.getText());
////            detail.putExtra("gaji", txt_gaji.getText());
////            detail.putExtra("deadline", txt_deadline.getText());
////            detail.putExtra("kategori", txt_kategori.getText());
////            detail.putExtra("status_pelamar", txt_statuspelamar.getText());
////            detail.putExtra("keterangan", txt_keterangan.getText());*/
////
////            view.getContext().startActivity(detail);
////
////        }
////    }
////
////
//////    public AllProductAdapter(Context context, ArrayList<ResultItem> arrayList) {
//////        this.context = context;
//////        this.mArrayList = arrayList;
//////        this.mFilteredList = arrayList;
//////    }
//////
//////    @Override
//////    public Filter getFilter() {
//////
//////            return new Filter() {
//////                @Override
//////                protected FilterResults performFiltering(CharSequence charSequence) {
//////
//////                    String charString = charSequence.toString();
//////
//////                    if (charString.isEmpty()) {
//////
//////                        mFilteredList = mArrayList;
//////                    } else {
//////
//////                        ArrayList<ResultItem> filteredList = new ArrayList<>();
//////
//////                        for (ResultItem androidVersion : mArrayList) {
//////
//////                            if (androidVersion.getId().toLowerCase().contains(charString) || androidVersion.getName().toLowerCase().contains(charString) ) {
//////                                filteredList.add(androidVersion);
//////                            }
//////                        }
//////                        mFilteredList = filteredList;
//////                    }
//////                    FilterResults filterResults = new FilterResults();
//////                    filterResults.values = mFilteredList;
//////                    return filterResults;
//////                }
//////                @Override
//////                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//////                    mFilteredList = (ArrayList<ResultItem>) filterResults.values;
//////                    notifyDataSetChanged();
//////                }
//////            };
//////        }
//////
//////
//////    @NonNull
//////    @Override
//////    public AllProductAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//////        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_product, viewGroup, false);
//////        return new AllProductAdapter.ViewHolder(view);
//////    }
//////
//////    @Override
//////    public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolder holder, int position) {
//////        ViewHolder.txt_id.setText(mFilteredList.get(position).getId());
//////        ViewHolder.txt_id_product_category.setText(mFilteredList.get(position).getIdProductCategory());
//////        ViewHolder.txt_id_currency.setText(mFilteredList.get(position).getIdCurrency());
//////        ViewHolder.txt_name_product.setText((mFilteredList.get(position).getName()));
//////        ViewHolder.txt_price_capital.setText(mFilteredList.get(position).getPriceCapital());  //untuk mengirim url gambar ke berbagai activity
//////        ViewHolder.txt_price_sale.setText(mFilteredList.get(position).getPriceSale());
//////        ViewHolder.txt_description.setText(mFilteredList.get(position).getDescription());
//////        ViewHolder.txt_sku.setText(mFilteredList.get(position).getSku());
//////        ViewHolder.txt_stock.setText(mFilteredList.get(position).getStock());
//////        ViewHolder.txt_condition.setText(mFilteredList.get(position).getCondition());
//////        ViewHolder.txt_deliverable.setText(mFilteredList.get(position).getDeliverable());
//////        ViewHolder.txt_downloadable.setText(mFilteredList.get(position).getDownloadable());
//////        ViewHolder.txt_target_gender.setText(mFilteredList.get(position).getTargetGender());
//////        ViewHolder.txt_target_age.setText(mFilteredList.get(position).getTargetAge());
//////        ViewHolder.txt_visibility.setText(mFilteredList.get(position).getVisibility());
////////        ViewHolder.txt_weight_value.setText(mFilteredList.get(position).get());
////////        ViewHolder.txt_weight.setText(mFilteredList.get(position).getId());
////////        ViewHolder.txt_length_value.setText(mFilteredList.get(position).getId());
////////        ViewHolder.txt_length.setText(mFilteredList.get(position).getId());
////////        ViewHolder.txt_width_value.setText(mFilteredList.get(position).getId());
////////        ViewHolder.txt_width.setText(mFilteredList.get(position).getId());
////////        ViewHolder.txt_height_value.setText(mFilteredList.get(position).getId());
////////        ViewHolder.txt_height.setText(mFilteredList.get(position).getId());
//////
//////
//////
//////
//////    }
//////
//////    @Override
//////    public int getItemCount() {
//////        return mFilteredList.size();
//////    }
//////
//////    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//////        private TextView txt_id,txt_id_product_category,txt_id_currency,txt_name_product,txt_price_capital,txt_price_sale,txt_description,
//////                txt_sku,txt_stock,txt_condition,txt_deliverable,txt_downloadable,txt_target_gender,txt_target_age,txt_visibility;
//////        private ImageView image;
//////
//////        public ViewHolder(View view) {
//////            super(view);
//////
//////            txt_id = (TextView) view.findViewById(R.id.txt_id);
//////            txt_id_product_category = (TextView) view.findViewById(R.id.txt_id_product_category);
//////            txt_id_currency = (TextView)view.findViewById(R.id.txt_id_currency);
//////            txt_name_product = (TextView)view.findViewById(R.id.txt_name_product);
//////            txt_price_capital = (TextView)view.findViewById(R.id.txt_price_capital);
//////            txt_price_sale = (TextView)view.findViewById(R.id.txt_price_sale);
//////            ////image = (ImageView)view.findViewById(R.id.image);
//////            txt_description = (TextView)view.findViewById(R.id.txt_description);
//////            txt_sku = (TextView)view.findViewById(R.id.txt_sku);
//////            txt_stock = (TextView)view.findViewById(R.id.txt_stock);
//////            txt_condition = (TextView) view.findViewById(R.id.txt_condition);
//////            txt_deliverable = (TextView) view.findViewById(R.id.txt_deliverable);
//////            txt_downloadable = (TextView)view.findViewById(R.id.txt_downloadable);
//////            txt_target_gender = (TextView)view.findViewById(R.id.txt_target_gender);
//////            txt_target_age = (TextView) view.findViewById(R.id.txt_target_age);
//////            txt_visibility = (TextView) view.findViewById(R.id.txt_visibility);
//////
//////
//////
//////
//////        }
//////
//////        @Override
//////        public void onClick(View view) {
//////            Intent detail = new Intent(view.getContext(), DetailProduct.class);
//////
///////*            detail.putExtra("id_pelamarmasuk", txt_id_pelamarmasuk.getText());
//////            detail.putExtra("id_user", txt_id_user.getText());
//////            detail.putExtra("id_perusahaan", txt_id_perusahaan.getText());
//////            detail.putExtra("nama_perusahaan", txt_nama_perusahaan.getText());
//////            detail.putExtra("id_lowongankerja", txt_id_lowongan.getText());
//////            detail.putExtra("judul_lowongan", txt_judul_lowongan.getText());
//////            detail.putExtra("deskripsi", txt_deskripsi.getText());
//////            detail.putExtra("gaji", txt_gaji.getText());
//////            detail.putExtra("deadline", txt_deadline.getText());
//////            detail.putExtra("kategori", txt_kategori.getText());
//////            detail.putExtra("status_pelamar", txt_statuspelamar.getText());
//////            detail.putExtra("keterangan", txt_keterangan.getText());*/
//////
//////            view.getContext().startActivity(detail);
//////
//////        }
//////    }
//
//}
