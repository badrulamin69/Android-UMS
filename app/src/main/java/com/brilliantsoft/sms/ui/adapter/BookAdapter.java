package com.brilliantsoft.sms.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.model.BookResponse;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<BookResponse> books = new ArrayList<>();

    public void setBooks(List<BookResponse> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookResponse book = books.get(position);
        holder.tvBookTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.tvStatus.setText(book.getAvailableQuantity() > 0 ? "Available" : "Checked Out");
        holder.tvStatus.setTextColor(book.getAvailableQuantity() > 0 ? 
                holder.itemView.getContext().getColor(R.color.electric_success_green) : 
                holder.itemView.getContext().getColor(R.color.electric_alert_red));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBookTitle, tvAuthor, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
