package com.example.perpusonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perpusonline.R;
import com.example.perpusonline.Model.Message;

import java.util.Vector;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private Context context;
    private Vector<Message> vMessage;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setvMessage(Vector<Message> vMessage) {
        this.vMessage = vMessage;
    }

    public MessageAdapter(Context context, Vector<Message> vMessage) {
        this.context = context;
        this.vMessage = vMessage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = vMessage.get(position);
        if (message.isFromMe()){
        //tampilin kanan
        holder.tvMessageLeft.setVisibility(View.GONE);
        holder.tvMessageRight.setText(message.getMessage());
        }
        else {
        //tampilin kiri
        holder.tvMessageRight.setVisibility(View.GONE);
        holder.tvMessageLeft.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return vMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvMessageLeft, tvMessageRight;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessageLeft = itemView.findViewById(R.id.tv_message_left);
            tvMessageRight = itemView.findViewById(R.id.tv_message_right);
        }
    }
}
