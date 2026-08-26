package com.brilliantsoft.sms.ui.exam;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;

import com.brilliantsoft.sms.model.ExaminationResponse;
import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.ui.adapter.ExaminationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExaminationActivity extends AppCompatActivity {

    private RecyclerView rvExams;
    private ExaminationAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);

        apiService = ApiClient.getInstance(this).getApiService();
        rvExams = findViewById(R.id.rvExams);
        rvExams.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExaminationAdapter();
        rvExams.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadExams();
    }

    private void loadExams() {
        apiService.getExaminations(0, 50, null).enqueue(new Callback<PageResponse<ExaminationResponse>>() {
            @Override
            public void onResponse(Call<PageResponse<ExaminationResponse>> call, Response<PageResponse<ExaminationResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setExams(response.body().getContent());
                } else {
                    Toast.makeText(ExaminationActivity.this, "Failed to load exams", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PageResponse<ExaminationResponse>> call, Throwable t) {
                Toast.makeText(ExaminationActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
