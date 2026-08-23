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

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private List<Map<String, Object>> notices = new ArrayList<>();

    public void setNotices(List<Map<String, Object>> notices) {
        this.notices = notices;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, Object> notice = notices.get(position);
        holder.tvNoticeTitle.setText((String) notice.get("title"));
        holder.tvNoticeDate.setText((String) notice.get("createdAt"));
        holder.tvNoticeSnippet.setText((String) notice.get("content"));
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNoticeTitle, tvNoticeDate, tvNoticeSnippet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoticeTitle = itemView.findViewById(R.id.tvNoticeTitle);
            tvNoticeDate = itemView.findViewById(R.id.tvNoticeDate);
            tvNoticeSnippet = itemView.findViewById(R.id.tvNoticeSnippet);
        }
    }
}
