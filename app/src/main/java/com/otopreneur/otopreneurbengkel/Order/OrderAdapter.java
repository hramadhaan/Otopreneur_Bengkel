package com.otopreneur.otopreneurbengkel.Order;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.otopreneur.otopreneurbengkel.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private static final String TAG = "OrderAdapter";

    private Context context;
    private ArrayList<String> nama = new ArrayList<>();
    private ArrayList<String> alamat = new ArrayList<>();
    private ArrayList<String> foto = new ArrayList<>();

    public OrderAdapter(Context context, ArrayList<String> nama, ArrayList<String> alamat, ArrayList<String> foto) {
        this.context = context;
        this.nama = nama;
        this.alamat = alamat;
        this.foto = foto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder");
        holder.order_nama.setText(nama.get(position));
        holder.order_alamat.setText(alamat.get(position));

        Glide.with(context)
                .asBitmap()
                .load(foto.get(position))
                .into(holder.order_foto);

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "On Cliked: "+nama.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,DetailOrderActivity.class);
                intent.putExtra("nama",nama.get(position));
                intent.putExtra("alamat",alamat.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nama.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_nama,order_alamat;
        ImageView order_foto;
        RelativeLayout order;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order = itemView.findViewById(R.id.order);
            order_nama = itemView.findViewById(R.id.item_order_nama);
            order_alamat = itemView.findViewById(R.id.item_order_alamat);
            order_foto = itemView.findViewById(R.id.item_order_foto);
        }
    }

}
