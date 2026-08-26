package com.brilliantsoft.sms.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.model.ExaminationResponse;
import java.util.ArrayList;
import java.util.List;

public class ExaminationAdapter extends RecyclerView.Adapter<ExaminationAdapter.ViewHolder> {

    private List<ExaminationResponse> exams = new ArrayList<>();

    public void setExams(List<ExaminationResponse> exams) {
        this.exams = exams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExaminationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExaminationResponse exam = exams.get(position);
        holder.tvExamTitle.setText(exam.getExaminationName() != null ? exam.getExaminationName() : "Unnamed Exam");
        String dateStr = exam.getExaminationDate() != null ? exam.getExaminationDate().toString() : "TBA";
        holder.tvExamDate.setText(holder.itemView.getContext().getString(R.string.exam_date_format, dateStr));
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvExamTitle, tvExamDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExamTitle = itemView.findViewById(R.id.tvExamTitle);
            tvExamDate = itemView.findViewById(R.id.tvExamDate);
        }
    }
}
