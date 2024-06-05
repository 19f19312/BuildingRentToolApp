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
import com.example.ishaq_buildingrenttoolsapp.UserToolRent;
import com.example.ishaq_buildingrenttoolsapp.Worker.ImageAdapterWorkerReturnTool;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterUserReturnTool extends RecyclerView.Adapter <ImageAdapterUserReturnTool.ImageViewHolder>{

    private Context mContext;
    private List<UserToolRent> mUploads;
    private OnItemClickListener mListener;

    public ImageAdapterUserReturnTool(Context context, List<UserToolRent> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_user_return_tool, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserToolRent uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Rented Tool: "+uploadCurrent.gettName());
        holder.textViewPrice.setText("Rented Price: "+uploadCurrent.gettPrice()+" OMR");
        holder.textViewDays.setText("Rented for : "+uploadCurrent.getrDays()+ " days");
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

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public TextView textViewPrice;
        public TextView textViewDays;
        public TextView textViewRentDate;
        public TextView textViewReturnDate;
        public ImageView imageView;
        public Button btn_return;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name9);
            textViewPrice= itemView.findViewById(R.id.image_view_price9);
            textViewDays= itemView.findViewById(R.id.image_view_r_days9);
            textViewRentDate= itemView.findViewById(R.id.image_view_rent_date9);
            textViewReturnDate= itemView.findViewById(R.id.image_view_retrun_date9);
            imageView= itemView.findViewById(R.id.image_view_uploaded9);
            btn_return= itemView.findViewById(R.id.return_item9);

            btn_return.setOnClickListener(this);

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
