package com.brilliantsoft.sms.ui.fees;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.model.StudentFeeResponse;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;
import com.brilliantsoft.sms.session.SessionManager;

import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.ui.adapter.InvoiceAdapter;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeesActivity extends AppCompatActivity {

    private RecyclerView rvInvoices;
    private TextView tvTotalDueAmount;
    private InvoiceAdapter adapter;
    private ApiService apiService;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);

        sessionManager = SessionManager.getInstance(this);
        apiService = ApiClient.getInstance(this).getApiService();
        rvInvoices = findViewById(R.id.rvInvoices);
        tvTotalDueAmount = findViewById(R.id.tvTotalDueAmount);
        rvInvoices.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InvoiceAdapter();
        rvInvoices.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadInvoices();
        loadFeeSummary();
    }

    private void loadInvoices() {
        long studentId = sessionManager.getStudentId();
        Call<PageResponse<Map<String, Object>>> call;
        if (studentId != -1) {
            call = apiService.getInvoicesByStudent(studentId, 0, 50);
        } else {
            call = apiService.getInvoices(0, 50, null);
        }
        
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PageResponse<Map<String, Object>>> call, Response<PageResponse<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setInvoices(response.body().getContent());
                } else {
                    Toast.makeText(FeesActivity.this, "Failed to load invoices", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PageResponse<Map<String, Object>>> call, Throwable t) {
                Toast.makeText(FeesActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFeeSummary() {
        apiService.getStudentFees(0, 100).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PageResponse<StudentFeeResponse>> call, Response<PageResponse<StudentFeeResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    double totalOutstanding = 0;
                    for (StudentFeeResponse fee : response.body().getContent()) {
                        if (fee.getAmount() != null) {
                            double paid = fee.getPaidAmount() != null ? fee.getPaidAmount() : 0;
                            totalOutstanding += (fee.getAmount() - paid);
                        }
                    }
                    tvTotalDueAmount.setText(String.format("$%,.2f", totalOutstanding));
                }
            }

            @Override
            public void onFailure(Call<PageResponse<StudentFeeResponse>> call, Throwable t) {
                // Silent failure for summary
            }
        });
    }
}
