package com.example.ishaq_buildingrenttoolsapp.Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishaq_buildingrenttoolsapp.R;
import com.example.ishaq_buildingrenttoolsapp.ToolsFile;
import com.example.ishaq_buildingrenttoolsapp.User.UserRentTool;
import com.example.ishaq_buildingrenttoolsapp.UserToolRent;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterCheckRentedTools extends RecyclerView.Adapter <ImageAdapterCheckRentedTools.ImageViewHolder>{

    private Context mContext;
    private List<UserToolRent> mUploads;

    public ImageAdapterCheckRentedTools(Context context, List<UserToolRent> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_check_rented_tools, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserToolRent uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Rented Tool: "+uploadCurrent.gettName());
        holder.textViewPrice.setText("Rented Price: "+uploadCurrent.gettPrice()+" OMR");
        holder.textViewRName.setText("Rentee Name: "+uploadCurrent.getrName());
        holder.textViewRentDate.setText("Rent Date: "+uploadCurrent.getRentDate());
        holder.textViewReturnDate.setText("Return Date: "+uploadCurrent.getReturnDate());
        Picasso.get()
                .load(uploadCurrent.gettUrl())
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
        public TextView textViewRName;
        public TextView textViewRentDate;
        public TextView textViewReturnDate;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name4);
            textViewPrice= itemView.findViewById(R.id.image_view_price4);
            textViewRName= itemView.findViewById(R.id.image_view_r_name4);
            textViewRentDate= itemView.findViewById(R.id.image_view_rent_date4);
            textViewReturnDate= itemView.findViewById(R.id.image_view_retrun_date4);
            imageView= itemView.findViewById(R.id.image_view_uploaded4);

        }
    }
}
