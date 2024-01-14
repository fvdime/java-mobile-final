package com.example.finalodevvideo.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalodevvideo.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<String> dataList;

    private Context context;

    public Adapter(Context context, List<String> dataList){
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent,false);
        return  new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        String data = dataList.get(position);
        holder.bind(data,position);
    }
    @Override
    public int getItemCount(){
        return dataList.size();
    }
    public void setData(List<String> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textItem);
        }

        public void bind(String data, int position){

            String [] parts = data.split("\n");
            String imageUrl = parts[0].substring(parts[0].indexOf(":") +1 ).trim();
            String labels = parts[1].substring(parts[1].indexOf(":") +1).trim();
            String name = parts[2].substring(parts[2].indexOf(":") +1 ).trim();

            Log.d("Adapter", "position" + position);
            Log.d("Adapter", "image url" + imageUrl);

            if(!imageUrl.isEmpty()){
                RequestOptions requestOptions = new RequestOptions()
                        .centerCrop();

                Glide.with(itemView)
                        .load(imageUrl)
                        .apply(requestOptions)
                        .into(imageView);
            }
            else{
                imageView.setImageResource(R.drawable.ic_menu_camera);
            }

            textView.setText(" " + name + "\n " + labels);
        }
    }
}