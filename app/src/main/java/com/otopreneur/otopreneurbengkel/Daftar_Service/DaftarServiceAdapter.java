package com.otopreneur.otopreneurbengkel.Daftar_Service;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.otopreneur.otopreneurbengkel.Model.Service;
import com.otopreneur.otopreneurbengkel.R;

import java.util.List;

public class DaftarServiceAdapter extends RecyclerView.Adapter<DaftarServiceAdapter.ViewHolder> {

    private static final String TAG = "DaftarServiceAdapter";

    private Context context;
    List<Service> serviceList;

    public DaftarServiceAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daftar_service,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.harga.setText(serviceList.get(position).getCost().toString());
        holder.service.setText(serviceList.get(position).getService());
        holder.kendaraan.setText(serviceList.get(position).getVenichle());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailServiceActivity.class);
                intent.putExtra("id_service",serviceList.get(position).getId());
                intent.putExtra("service",serviceList.get(position).getService());
                intent.putExtra("harga",serviceList.get(position).getCost().toString());
                intent.putExtra("kendaraan",serviceList.get(position).getVenichle());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView edit,service,harga,kendaraan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edit = itemView.findViewById(R.id.daftarservice_item_edit);
            kendaraan = itemView.findViewById(R.id.daftarservice_tipeKendaraan);
            service = itemView.findViewById(R.id.daftarservice_item_service);
            harga = itemView.findViewById(R.id.daftarservice_item_harga);
        }
    }
}