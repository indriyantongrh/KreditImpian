package com.application.kreditimpian.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelMitraSelected.DataItem;
import com.application.kreditimpian.Model.ModelMitraSelected.ResponseMitraSelected;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.application.kreditimpian.Api.network.interceptor.MyApp.getContext;

/**
 * Created by indriyanto Nugroho on 31 Jan 2020.
 */
public class AdapterMitraSelected extends RecyclerView.Adapter<AdapterMitraSelected.HolderMitrSelected> implements Filterable {
    private static int checkBoxCounter = 0;
    private HashMap<Integer, Boolean> isChecked = new HashMap<>();
    private List<DataItem> dataItemList;
    Context mContext;
    private int selectedPosition = -1;// no selection by default = -1;// no selection by default


    public AdapterMitraSelected(Context context) {
        this.mContext = context;
        dataItemList = new ArrayList<>();
    }

    public void setDataItemList(List<DataItem> dataItemList) {
        this.dataItemList = dataItemList;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public AdapterMitraSelected.HolderMitrSelected onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mitra, parent, false);
        return new HolderMitrSelected(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMitraSelected.HolderMitrSelected holder, int position) {
        final DataItem dataItem = dataItemList.get(position);
        holder.checkBoxMitra.setText(dataItem.getIdCompany());
        holder.checkBoxMitra.setText(dataItem.getName());
        Log.v("Bismillah", dataItem.isCheck() + " a");
        holder.checkBoxMitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckbox(position);
                notifyDataSetChanged();
            }
        });
//        holder.checkBoxMitra.setChecked(selectedPosition == position);
//        if(selectedPosition == position){
//                    holder.checkBoxMitra.setChecked(true);
//                }
//                else{
//                    holder.checkBoxMitra.setChecked(false);
//                }

//                holder.checkBoxMitra.setOnClickListener(new View.OnClickListener() {
//                    String id_company = dataItem.getIdCompany();
//                    @Override
//                    public void onClick(View v) {
//                        if(((CheckBox)v).isChecked()){
//                            Toast.makeText(mContext, "id comany" + id_company, Toast)
//                        }else{
//
//                        }
//
//                    }
//                });
        ////  holder.checkBoxMitra.setText(dataItem.getName());
//        if (isChecked.containsKey(position)){
//            holder.checkBoxMitra.setChecked(isChecked.get(position));
//        } else {
//            holder.checkBoxMitra.setChecked(false);
//        }

        ///holder.checkBoxMitra.setText(dataItem.getIdProductCategory());
        //holder.checkBoxMitra.setText(dataItem.getIdCompany());
        /// holder.checkBoxMitra.setText(dataItem.getName());
//        holder.checkBoxMitra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked && checkBoxCounter==3){
//                    dataItemList.get(position).getName();
//                    holder.checkBoxMitra.setChecked(false);
//
//                }else {
//                    holder.checkBoxMitra.setEnabled(true);
//                }
//            }
//        });
//        holder.checkBoxMitra.setText("" +dataItem.getName());
//        holder.checkBoxMitra.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int getPosition = (Integer) v.getTag();
//
//                if (holder.checkBoxMitra.isChecked()) {
//
//                    l.add(dataItem(getPosition).getName());
//                    Toast toast=  Toast.makeText(getContext(),String.valueOf(l.size()), Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
//                    toast.show();
//
//                    if (l.size() > 4) {
//                        holder.checkBoxMitra.setChecked(false);
//                        l.remove(getItem(getPosition).getName());
//
//
//
//                    }
//
//                }
//                else if (!holder.checkBoxMitra.isChecked()) {
//                    l.remove(getItem(getPosition).getName());
//
//                }
//
//                data.get(getPosition).setSelected(holder.checkBoxMitra.isChecked());
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class HolderMitrSelected extends RecyclerView.ViewHolder {

        @BindView(R.id.checkboxMitra)
        CheckBox checkBoxMitra;


        public HolderMitrSelected(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            checkBoxMitra = itemView.findViewById(R.id.checkboxMitra);
//
//            checkBoxMitra.setOnClickListener(new View.OnClickListener() {
//
//                String id_company = dataItemList.
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

//            checkBoxMitra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(isChecked){
//
//                    }else{
//                       /// Toast.makeText(mContext, "unchecklist", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });


        }
    }


    private void setCheckbox(int position) {
        DataItem dataItem = dataItemList.get(position);
        dataItem.setCheck(!dataItem.isCheck());
    }

    public List<DataItem> getDataItemList() {
        return dataItemList;
    }

    //    private void setCheckbox(int position) {
//        final DataItem dataItem = dataItemList.get(position);
//        modelMitra.setChecked(!modelMitra.isChecked());
//
//        // bila mitra lebih dari 3. digunakan untuk memilih mitra maksimal 3
//        /*if (modelMitra.isChecked()) {
//            count++;
//        } else {
//            count--;
//        }*/
//    }
}
