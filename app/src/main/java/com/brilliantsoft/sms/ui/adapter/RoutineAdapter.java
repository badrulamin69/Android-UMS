package com.brilliantsoft.sms.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> {

    private List<Map<String, Object>> routines = new ArrayList<>();

    public void setRoutines(List<Map<String, Object>> routines) {
        this.routines = routines;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoutineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_routine_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, Object> routine = routines.get(position);
        holder.tvTime.setText((String) routine.get("startTime"));
        holder.tvDuration.setText(holder.itemView.getContext().getString(R.string.duration_mins_format, String.valueOf(routine.get("durationMinutes"))));
        holder.tvSubject.setText((String) routine.get("courseName"));
        holder.tvRoom.setText((String) routine.get("roomName"));
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvDuration, tvSubject, tvRoom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvRoom = itemView.findViewById(R.id.tvRoom);
        }
    }
}
