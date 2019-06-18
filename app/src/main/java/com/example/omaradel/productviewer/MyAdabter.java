package com.example.omaradel.productviewer;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class  MyAdabter extends RecyclerView.Adapter<MyAdabter.ViewHolder> {
    private ArrayList<product>products;
    private Context context;
    private final OnItemClickListener listener;
    public MyAdabter(ArrayList<product> products, Context context, OnItemClickListener listener) {
        this.products = products;
        this.context = context;
        this.listener = listener;
    }




    public interface OnItemClickListener{


        void onItemClick(product item, int adapterPosition);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new  ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(products.get(position), listener);
        product product=products.get(position);
        holder.name.setText(product.getName());
        String stringdouble= Double.toString(product.getPrice());
        holder.price.setText(stringdouble);

//        Glide.with(context)
//                .load(product.getImage_url())
//                .into(holder.imageView);
       Picasso.with(context).load(product.getImage_url()).into(holder.imageView);
      // animate(holder);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView imageView;

        public ViewHolder(View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.image_view);
        }

        public void bind(final product item, final OnItemClickListener listener) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item,getAdapterPosition());
                }
            });
        }
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

}
