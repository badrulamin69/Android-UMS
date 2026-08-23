package com.brilliantsoft.sms.ui.assignment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;

import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.ui.adapter.AssignmentAdapter;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentActivity extends AppCompatActivity {

    private RecyclerView rvAssignments;
    private AssignmentAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        apiService = ApiClient.getInstance(this).getApiService();
        rvAssignments = findViewById(R.id.rvAssignments);
        rvAssignments.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssignmentAdapter();
        rvAssignments.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadAssignments();
    }

    private void loadAssignments() {
        apiService.getAssignments(0, 50).enqueue(new Callback<PageResponse<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<PageResponse<Map<String, Object>>> call, Response<PageResponse<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setAssignments(response.body().getContent());
                }
            }

            @Override
            public void onFailure(Call<PageResponse<Map<String, Object>>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
