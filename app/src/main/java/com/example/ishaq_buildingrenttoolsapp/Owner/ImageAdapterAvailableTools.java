package com.example.ishaq_buildingrenttoolsapp.Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.ToolsFile;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterAvailableTools extends RecyclerView.Adapter <ImageAdapterAvailableTools.ImageViewHolder>{

    private Context mContext;
    private List<ToolsFile> mUploads;

    public ImageAdapterAvailableTools(Context context, List<ToolsFile> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_available_tool, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ToolsFile uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Name: "+uploadCurrent.getToolName());
        holder.textViewPrice.setText("Price per day: "+uploadCurrent.getToolPrice()+" OMR");
        holder.textViewDetail.setText("Tool Details: "+uploadCurrent.getToolDetail());
        Picasso.get()
                .load(uploadCurrent.getToolUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewPrice;
        public TextView textViewDetail;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name1);
            textViewPrice= itemView.findViewById(R.id.image_view_price1);
            textViewDetail= itemView.findViewById(R.id.image_view_detail1);
            imageView= itemView.findViewById(R.id.image_view_uploaded1);

        }
    }
}