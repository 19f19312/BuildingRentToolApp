package com.example.ishaq_buildingrenttoolsapp.User;

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
import com.example.ishaq_buildingrenttoolsapp.Worker.ImageAdapterWorkerRentTools;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterUserRentTools extends RecyclerView.Adapter <ImageAdapterUserRentTools.ImageViewHolder>{

    private Context mContext;
    private List<ToolsFile> mUploads;
    private OnItemClickListener mListener;

    public ImageAdapterUserRentTools(Context context, List<ToolsFile> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_user_rent_tool, parent, false);
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

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public TextView textViewPrice;
        public TextView textViewDetail;
        public ImageView imageView;
        public Button btn_rent;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name3);
            textViewPrice= itemView.findViewById(R.id.image_view_price3);
            textViewDetail= itemView.findViewById(R.id.image_view_detail3);
            imageView= itemView.findViewById(R.id.image_view_uploaded3);
            btn_rent= itemView.findViewById(R.id.rent_tool3);

            btn_rent.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }


    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener= listener;
    }
}