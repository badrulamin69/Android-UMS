package com.brilliantsoft.sms.ui.notice;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;

import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.ui.adapter.NoticeAdapter;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeActivity extends AppCompatActivity {

    private RecyclerView rvNotices;
    private NoticeAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        apiService = ApiClient.getInstance(this).getApiService();
        rvNotices = findViewById(R.id.rvNotices);
        rvNotices.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoticeAdapter();
        rvNotices.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadNotices();
    }

    private void loadNotices() {
        apiService.getNotices(0, 50, null).enqueue(new Callback<PageResponse<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<PageResponse<Map<String, Object>>> call, Response<PageResponse<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setNotices(response.body().getContent());
                }
            }

            @Override
            public void onFailure(Call<PageResponse<Map<String, Object>>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
