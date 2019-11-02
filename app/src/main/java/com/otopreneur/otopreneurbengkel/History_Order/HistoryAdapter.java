package com.otopreneur.otopreneurbengkel.History_Order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.otopreneur.otopreneurbengkel.Model.History;
import com.otopreneur.otopreneurbengkel.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private static final String TAG = "HistoryAdapter";

    private Context context;
    List<History> histories;

    public HistoryAdapter(Context context, List<History> histories) {
        this.context = context;
        this.histories = histories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder");
        holder.history_nama.setText(histories.get(position).getCustomerdata().getName());
        holder.history_alamat.setText(histories.get(position).getLocation());
        holder.status.setText(histories.get(position).getStatus());

        if (histories.get(position).getStatus().equals("canceled")){
            holder.status.setTextColor(Color.RED);
        } else {
            holder.status.setTextColor(Color.GREEN);
        }

        Glide.with(context)
                .asBitmap()
                .load(histories.get(position).getCustomerdata().getAvatar())
                .into(holder.history_foto);

        holder.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "On Cliked: "+histories.get(position).getCustomerdata().getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,DetailHistoryActivity.class);
                intent.putExtra("invoice",histories.get(position).getInvoiceNo());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView history_nama,history_alamat,status;
        ImageView history_foto;
        RelativeLayout history;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            history_nama = itemView.findViewById(R.id.item_history_nama);
            history_alamat = itemView.findViewById(R.id.item_history_alamat);
            history_foto = itemView.findViewById(R.id.item_history_foto);
            history = itemView.findViewById(R.id.history);
            status = itemView.findViewById(R.id.item_histoy_status);
        }
    }
}
