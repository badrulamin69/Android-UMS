package com.brilliantsoft.sms.ui.courses;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;

import com.brilliantsoft.sms.model.CourseResponse;
import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.ui.adapter.CourseAdapter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseListActivity extends AppCompatActivity {

    private RecyclerView rvCourses;
    private CourseAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        apiService = ApiClient.getInstance(this).getApiService();
        rvCourses = findViewById(R.id.rvCourses);
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CourseAdapter();
        rvCourses.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadCourses();
    }

    private void loadCourses() {
        apiService.getCourses(0, 50, null).enqueue(new Callback<PageResponse<CourseResponse>>() {
            @Override
            public void onResponse(Call<PageResponse<CourseResponse>> call, Response<PageResponse<CourseResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setCourses(response.body().getContent());
                }
            }

            @Override
            public void onFailure(Call<PageResponse<CourseResponse>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
