package com.brilliantsoft.sms.ui.attendance;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;

import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.model.StudentAttendanceResponse;
import com.brilliantsoft.sms.ui.adapter.AttendanceAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity {

    private RecyclerView rvAttendance;
    private AttendanceAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        apiService = ApiClient.getInstance(this).getApiService();
        rvAttendance = findViewById(R.id.rvAttendance);
        rvAttendance.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AttendanceAdapter();
        rvAttendance.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadAttendance();
    }

    private void loadAttendance() {
        apiService.getStudentAttendance(0, 50, null).enqueue(new Callback<PageResponse<StudentAttendanceResponse>>() {
            @Override
            public void onResponse(Call<PageResponse<StudentAttendanceResponse>> call, Response<PageResponse<StudentAttendanceResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setAttendanceList(response.body().getContent());
                }
            }

            @Override
            public void onFailure(Call<PageResponse<StudentAttendanceResponse>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
