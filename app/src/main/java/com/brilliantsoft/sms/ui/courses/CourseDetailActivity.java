package com.brilliantsoft.sms.ui.courses;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.model.CourseResponse;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE_ID = "extra_course_id";
    private TextView tvCourseName, tvCourseCode, tvDescription, tvCredits, tvDepartment;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        apiService = ApiClient.getInstance(this).getApiService();

        tvCourseName = findViewById(R.id.tvCourseName);
        tvCourseCode = findViewById(R.id.tvCourseCode);
        tvDescription = findViewById(R.id.tvDescription);
        tvCredits = findViewById(R.id.tvCredits);
        tvDepartment = findViewById(R.id.tvDepartment);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        long courseId = getIntent().getLongExtra(EXTRA_COURSE_ID, -1);
        if (courseId != -1) {
            loadCourseDetails(courseId);
        } else {
            Toast.makeText(this, "Invalid Course ID", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadCourseDetails(long id) {
        apiService.getCourseById(id).enqueue(new Callback<CourseResponse>() {
            @Override
            public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CourseResponse course = response.body();
                    tvCourseName.setText(course.getCourseName());
                    tvCourseCode.setText(course.getCourseCode());
                    tvDescription.setText(course.getDescription());
                    tvCredits.setText(String.valueOf(course.getCredit()));
                    tvDepartment.setText(course.getDepartmentName());
                } else {
                    Toast.makeText(CourseDetailActivity.this, "Failed to load course details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CourseResponse> call, Throwable t) {
                Toast.makeText(CourseDetailActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
