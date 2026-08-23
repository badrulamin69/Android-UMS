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

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    private List<Map<String, Object>> invoices = new ArrayList<>();

    public void setInvoices(List<Map<String, Object>> invoices) {
        this.invoices = invoices;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, Object> invoice = invoices.get(position);
        holder.tvInvoiceTitle.setText((String) invoice.get("invoiceNumber"));
        holder.tvInvoiceDate.setText((String) invoice.get("invoiceDate"));
        holder.tvInvoiceAmount.setText("$" + invoice.get("totalAmount"));
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInvoiceTitle, tvInvoiceDate, tvInvoiceAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInvoiceTitle = itemView.findViewById(R.id.tvInvoiceTitle);
            tvInvoiceDate = itemView.findViewById(R.id.tvInvoiceDate);
            tvInvoiceAmount = itemView.findViewById(R.id.tvInvoiceAmount);
        }
    }
}
