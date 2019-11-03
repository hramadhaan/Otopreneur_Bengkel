package com.otopreneur.otopreneurbengkel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.otopreneur.otopreneurbengkel.Model.Order;
import com.otopreneur.otopreneurbengkel.Order.DetailOrderActivity;
import com.otopreneur.otopreneurbengkel.R;

import java.util.List;

public class DaftarOrderAdapter extends RecyclerView.Adapter<DaftarOrderAdapter.ViewHolder> {
    private static final String TAG = "OrderAdapter";
    private Context context;
    private List<Order> orderList;

    public DaftarOrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public DaftarOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarOrderAdapter.ViewHolder holder, final int position) {
        holder.order_nama.setText(orderList.get(position).getCustomerdata().getName());
        holder.order_alamat.setText(orderList.get(position).getLocation());
        holder.status.setText(orderList.get(position).getStatus());
        if (orderList.get(position).getStatus().equals("waiting")){
            holder.status.setTextColor(Color.BLACK);
        } else {
            holder.status.setTextColor(Color.GREEN);
        }

        Glide.with(context)
                .asBitmap()
                .load(orderList.get(position).getCustomerdata().getAvatar())
                .into(holder.order_foto);

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "On Cliked: "+orderList.get(position).getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailOrderActivity.class);
//                INI BUAT DI BACKEND
                intent.putExtra("invoice",orderList.get(position).getInvoiceNo());
                intent.putExtra("status",orderList.get(position).getStatus());
//                ADA DI LAYOUT
                intent.putExtra("customer",orderList.get(position).getCustomerdata().getName());
                intent.putExtra("tipekendaraan",orderList.get(position).getVenichleSeries());
                intent.putExtra("tipeservice",orderList.get(position).getNote());
                intent.putExtra("lokasi",orderList.get(position).getLocation());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_nama,order_alamat,status;
        ImageView order_foto;
        CardView order;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order = itemView.findViewById(R.id.order);
            order_nama = itemView.findViewById(R.id.item_order_nama);
            order_alamat = itemView.findViewById(R.id.item_order_alamat);
            order_foto = itemView.findViewById(R.id.item_order_foto);
            status = itemView.findViewById(R.id.item_order_status);
        }
    }
}
