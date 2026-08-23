package com.brilliantsoft.sms.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    private List<Map<String, Object>> assignments = new ArrayList<>();

    public void setAssignments(List<Map<String, Object>> assignments) {
        this.assignments = assignments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AssignmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assignment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, Object> assignment = assignments.get(position);
        holder.tvAssignmentTitle.setText((String) assignment.get("title"));
        holder.tvDueDate.setText(holder.itemView.getContext().getString(R.string.due_date_format, (String) assignment.get("dueDate")));

        // Urgency Dot Logic
        String urgency = (String) assignment.get("urgency"); // Assume backend sends 'URGENT', 'UPCOMING', etc.
        if ("URGENT".equalsIgnoreCase(urgency)) {
            holder.urgencyDot.setBackgroundResource(R.drawable.circle_red);
        } else {
            holder.urgencyDot.setBackgroundResource(R.drawable.circle_amber);
        }

        // Checkbox Behavior: Raised to Sunken transition
        holder.checkboxFrame.setOnClickListener(v -> {
            boolean isChecked = holder.ivCheck.getVisibility() == View.VISIBLE;
            if (isChecked) {
                holder.ivCheck.setVisibility(View.GONE);
                holder.checkboxFrame.setBackgroundResource(R.drawable.neumorphic_button_raised);
            } else {
                holder.ivCheck.setVisibility(View.VISIBLE);
                holder.checkboxFrame.setBackgroundResource(R.drawable.neumorphic_edittext_sunken);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAssignmentTitle, tvDueDate;
        FrameLayout checkboxFrame;
        ImageView ivCheck;
        View urgencyDot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAssignmentTitle = itemView.findViewById(R.id.tvAssignmentTitle);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
            checkboxFrame = itemView.findViewById(R.id.checkboxFrame);
            ivCheck = itemView.findViewById(R.id.ivCheck);
            urgencyDot = itemView.findViewById(R.id.urgencyDot);
        }
    }
}
