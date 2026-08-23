package com.brilliantsoft.sms.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;
import com.brilliantsoft.sms.session.SessionManager;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.brilliantsoft.sms.ui.routine.RoutineActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStudentName, tvStudentId;
    private BottomNavigationView bottomNavigation;
    private SessionManager sessionManager;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = SessionManager.getInstance(this);
        apiService = ApiClient.getInstance(this).getApiService();

        tvStudentName = findViewById(R.id.tvStudentName);
        tvStudentId = findViewById(R.id.tvStudentId);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        setupClickListeners();
        setupBottomNavigation();
        loadDashboardData();
    }

    private void setupClickListeners() {
        findViewById(R.id.profileCard).setOnClickListener(v -> 
            startActivity(new Intent(this, com.brilliantsoft.sms.ui.profile.ProfileActivity.class)));
        
        findViewById(R.id.searchBar).setOnClickListener(v -> 
            startActivity(new Intent(this, com.brilliantsoft.sms.ui.courses.CourseListActivity.class)));

        findViewById(R.id.tvViewAllRoutine).setOnClickListener(v -> 
            startActivity(new Intent(this, RoutineActivity.class)));
    }

    private void setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_courses) {
                startActivity(new Intent(this, com.brilliantsoft.sms.ui.courses.CourseListActivity.class));
                return true;
            } else if (id == R.id.nav_grades) {
                startActivity(new Intent(this, com.brilliantsoft.sms.ui.grades.GradesActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, com.brilliantsoft.sms.ui.profile.ProfileActivity.class));
                return true;
            }
            return false;
        });
    }

    private void loadDashboardData() {
        apiService.getStudentDashboard().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Bind data to UI
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load dashboard", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        // Use session data as fallback/initial
        tvStudentName.setText(sessionManager.getUsername());
        tvStudentId.setText(getString(R.string.student_id_format, String.valueOf(sessionManager.getStudentId())));
    }
}
