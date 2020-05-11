package com.application.kreditimpian.Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.kreditimpian.Mitra.DetailMitra;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterMitra extends RecyclerView.Adapter<AdapterMitra.ViewHolder> implements Filterable {

    private ArrayList<ModelMitra> mArrayList;
    private ArrayList<ModelMitra> mFilteredList;
    private Context context;

    public AdapterMitra(Context context, ArrayList<ModelMitra> arrayList) {
        this.context = context;
        this.mArrayList = arrayList;
        this.mFilteredList = arrayList;

    }

    @Override
    public AdapterMitra.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_adaper_mitra, viewGroup, false);
        return new AdapterMitra.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMitra.ViewHolder viewHolder, int i) {

        viewHolder.txt_nama_mitra.setText(mFilteredList.get(i).getName());
        /// viewHolder.txt_kota_lomba.setText(mFilteredList.get(i).getKota_lomba());
      /*  Glide.with(context)
                .load(mFilteredList.get(i).getPhoto())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(viewHolder.imagemitra);
*/

        viewHolder.txt_id.setText(mFilteredList.get(i).getId());
        //viewHolder.txt_lokasi_lomba.setText(mFilteredList.get(i).getLokasi_lomba());
        //viewHolder.txt_deskripsi.setText(mFilteredList.get(i).getDeskripsi());
        //viewHolder.txt_foto.setText(mFilteredList.get(i).getPhoto());

    }

    @Override
    public int getItemCount() {
        if (mFilteredList == null)
            return 0;
        else
            return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<ModelMitra> filteredList = new ArrayList<>();

                    for (ModelMitra androidVersion : mArrayList) {

                        if (androidVersion.getName().toLowerCase().contains(charString)) {
                            filteredList.add(androidVersion);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<ModelMitra>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_nama_mitra,
                txt_id, txt_foto;
        private ImageView imagemitra;

        public ViewHolder(View view) {
            super(view);

            txt_nama_mitra = (TextView) view.findViewById(R.id.txt_nama_mitra);
            // txt_tanggal = (TextView)view.findViewById(R.id.txt_tanggal);
            // txt_kota_lomba = (TextView)view.findViewById(R.id.txt_kota_lomba);
            txt_id = (TextView) view.findViewById(R.id.txt_id);
            //// txt_lokasi_lomba = (TextView)view.findViewById(R.id.txt_lokasi_lomba);
            imagemitra = (ImageView) view.findViewById(R.id.imagemitra);

            txt_foto = (TextView) view.findViewById(R.id.txt_foto);
            ///// txt_deskripsi = (TextView)view.findViewById(R.id.txt_deskripsi);


            //// view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent detail = new Intent(view.getContext(), DetailMitra.class);
            ///detail.putExtra("name", txt_nama_mitra.getText());
            ///detail.putExtra("tanggal_lomba", txt_tanggal.getText());
            ///detail.putExtra("kota_lomba", txt_kota_lomba.getText());

            /// detail.putExtra("id", txt_id.getText());
            ///detail.putExtra("lokasi_lomba", txt_lokasi_lomba.getText());
            ////detail.putExtra("deskripsi", txt_deskripsi.getText());
            ///detail.putExtra("foto", txt_foto.getText());

            view.getContext().startActivity(detail);
        }
    }
}
