package com.example.testproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    List<Combatant> combatantList;
    Context context;
    MyApplication myApplication;

    public RecycleViewAdapter(List<Combatant> combatantList, Context context) {
        this.combatantList = combatantList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.combatant_temp,parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // set background colour
        if (position == myApplication.currInitPos){
            //highlight
            holder.parentLayout.setBackgroundColor(Color.parseColor("#28336E"));
        }
        else{
            //grey
            holder.parentLayout.setBackgroundColor(Color.TRANSPARENT);
        }

        // Get Combatant Info
        holder.tv_name.setText(combatantList.get(position).getM_name());
        holder.tv_cond.setText(combatantList.get(position).getM_cond());
        holder.tv_hp.setText(String.valueOf(combatantList.get(position).getM_hp()));
        holder.tv_hpMax.setText(String.valueOf(combatantList.get(position).getM_hpMax()));
        holder.tv_init.setText(String.valueOf(combatantList.get(position).getM_ini()));
        holder.tv_ac.setText(String.valueOf(combatantList.get(position).getM_ac()));

        // Logic to determine combatant image selection
        String _url = combatantList.get(position).getM_url();

        switch (_url){
            case "boss":
                holder.iv_picture.setImageResource(R.drawable.boss_img);
                break;
            case "enemy":
                holder.iv_picture.setImageResource(R.drawable.enemy_img);
                break;
            case "player":
                holder.iv_picture.setImageResource(R.drawable.player_img);
                break;
            case "neutral":
                holder.iv_picture.setImageResource(R.drawable.neutral_img);
                break;
            default:
                Glide.with(this.context).load(combatantList.get(position).getM_url()).into(holder.iv_picture);
                break;
        }

        /**
         * Tracker click event for combatant - send to edit combatant
         */
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send to EDIT item view
                Intent intent = new Intent(context, EditEntity.class);
                intent.putExtra("id", combatantList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return combatantList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_picture;
        TextView tv_name, tv_cond;
        TextView tv_hp, tv_hpMax, tv_init, tv_ac;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_picture = itemView.findViewById(R.id.iv_picture);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_hp = itemView.findViewById(R.id.tv_hp);
            tv_hpMax = itemView.findViewById(R.id.tv_hpMax);
            tv_cond = itemView.findViewById(R.id.tv_cond);
            tv_init = itemView.findViewById(R.id.tv_init);
            tv_ac = itemView.findViewById(R.id.tv_ac);

            parentLayout = itemView.findViewById(R.id.combatantLayout);
        }
    }
}
