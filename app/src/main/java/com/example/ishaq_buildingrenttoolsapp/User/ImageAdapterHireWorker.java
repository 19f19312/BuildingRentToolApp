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
import com.example.ishaq_buildingrenttoolsapp.WorkerProfile;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterHireWorker extends RecyclerView.Adapter <ImageAdapterHireWorker.ImageViewHolder>{

    private Context mContext;
    private List<WorkerProfile> mUploads;
    private OnItemClickListener mListener;

    public ImageAdapterHireWorker(Context context, List<WorkerProfile> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item_hire_worker, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        WorkerProfile uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Name: "+uploadCurrent.getWorkerName());
        holder.textViewPhone.setText("Phone: "+uploadCurrent.getWorkerPhone());
        holder.textViewAddress.setText("Address: "+uploadCurrent.getWorkerAddress());
        holder.textViewGender.setText("Gender: "+uploadCurrent.getWorkerGender());
        holder.textViewSkills.setText("Skills: "+uploadCurrent.getWorkerSkills());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public TextView textViewPhone;
        public TextView textViewAddress;
        public TextView textViewGender;
        public TextView textViewSkills;
        public Button btn_hire;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.worker_name);
            textViewPhone= itemView.findViewById(R.id.worker_phone);
            textViewAddress= itemView.findViewById(R.id.worker_Address);
            textViewGender= itemView.findViewById(R.id.worker_gender);
            textViewSkills= itemView.findViewById(R.id.worker_skills);
            btn_hire= itemView.findViewById(R.id.hire_this);

            btn_hire.setOnClickListener(this);

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
