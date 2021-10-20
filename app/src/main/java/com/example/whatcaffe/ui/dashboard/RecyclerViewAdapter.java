package com.example.whatcaffe.ui.dashboard;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatcaffe.ItemActivity;
import com.example.whatcaffe.MainActivity;
import com.example.whatcaffe.R;
import com.example.whatcaffe.databinding.ItemBinding;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Item> mPersons;
    private LayoutInflater mInflate;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Item> persons) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mPersons = persons;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //데이터오 뷰를 바인딩
        holder.name.setText(mPersons.get(position).name);
        holder.address.setText(mPersons.get(position).address);
        holder.phone.setText(mPersons.get(position).phone);
        holder.image.setImageResource(mPersons.get(position).image);

        holder.Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //클릭시 엑티비티 이동
                Intent intent = new Intent(mContext , ItemActivity.class);
                intent.putExtra("number", position);
                intent.putExtra("cafeName", mPersons.get(position).getName());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }


    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public TextView bean;
        public TextView address;
        public TextView phone;
        CardView Card;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.cafeImage);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            bean = (TextView) itemView.findViewById(R.id.item_bean);
            address = (TextView) itemView.findViewById(R.id.tv_summary);
            phone = (TextView) itemView.findViewById(R.id.item_bean);
            Card = (CardView) itemView.findViewById(R.id.CardView_list);


        }
    }

    public interface mClickListener {
        public void mClick(View v, int position);
    }
}

