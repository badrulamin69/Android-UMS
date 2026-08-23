package com.brilliantsoft.sms.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.model.StudentAttendanceResponse;
import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {

    private List<StudentAttendanceResponse> attendanceList = new ArrayList<>();

    public void setAttendanceList(List<StudentAttendanceResponse> attendanceList) {
        this.attendanceList = attendanceList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AttendanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentAttendanceResponse item = attendanceList.get(position);
        holder.tvDate.setText(item.getAttendanceDate().toString());
        holder.tvStatus.setText(item.getStatus());
        
        if ("PRESENT".equalsIgnoreCase(item.getStatus())) {
            holder.tvStatus.setTextColor(Color.parseColor("#00CC66"));
        } else {
            holder.tvStatus.setTextColor(Color.parseColor("#FF3B30"));
        }
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
