package com.brilliantsoft.sms.ui.attendance;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;

import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.model.StudentAttendanceResponse;
import com.brilliantsoft.sms.ui.adapter.AttendanceAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity {

    private RecyclerView rvAttendance;
    private TextView tvPresentCount, tvAbsentCount;
    private AttendanceAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        apiService = ApiClient.getInstance(this).getApiService();
        rvAttendance = findViewById(R.id.rvAttendance);
        tvPresentCount = findViewById(R.id.tvPresentCount);
        tvAbsentCount = findViewById(R.id.tvAbsentCount);
        rvAttendance.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AttendanceAdapter();
        rvAttendance.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadAttendance();
    }

    private void loadAttendance() {
        apiService.getStudentAttendance(0, 100, null).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PageResponse<StudentAttendanceResponse>> call, Response<PageResponse<StudentAttendanceResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<StudentAttendanceResponse> data = response.body().getContent();
                    adapter.setAttendanceList(data);
                    updateSummary(data);
                } else {
                    Toast.makeText(AttendanceActivity.this, "Failed to load attendance", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PageResponse<StudentAttendanceResponse>> call, Throwable t) {
                Toast.makeText(AttendanceActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSummary(List<StudentAttendanceResponse> list) {
        int present = 0;
        int absent = 0;
        for (StudentAttendanceResponse item : list) {
            if ("PRESENT".equalsIgnoreCase(item.getStatus())) present++;
            else if ("ABSENT".equalsIgnoreCase(item.getStatus())) absent++;
        }
        tvPresentCount.setText(String.valueOf(present));
        tvAbsentCount.setText(String.valueOf(absent));
    }
}
