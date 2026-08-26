package com.brilliantsoft.sms.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;
import com.brilliantsoft.sms.session.SessionManager;
import com.brilliantsoft.sms.ui.routine.RoutineActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView tvStudentName, tvStudentId, tvAttendancePercent;
    private ProgressBar attendanceProgress;
    private LinearLayout llScheduleContainer;
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
        tvAttendancePercent = findViewById(R.id.tvAttendancePercent);
        attendanceProgress = findViewById(R.id.attendanceProgress);
        llScheduleContainer = findViewById(R.id.llScheduleContainer);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        setupClickListeners();
        setupBottomNavigation();
        loadDashboardData();
    }

    private void setupClickListeners() {
        findViewById(R.id.profileCard).setOnClickListener(v -> 
            startActivity(new Intent(this, com.brilliantsoft.sms.ui.profile.ProfileActivity.class)));
        
        View searchBar = findViewById(R.id.searchBar);
        if (searchBar != null) {
            searchBar.setOnClickListener(v -> 
                startActivity(new Intent(this, com.brilliantsoft.sms.ui.courses.CourseListActivity.class)));
        }

        View viewAllRoutine = findViewById(R.id.tvViewAllRoutine);
        if (viewAllRoutine != null) {
            viewAllRoutine.setOnClickListener(v -> 
                startActivity(new Intent(this, RoutineActivity.class)));
        }

        // Quick Actions
        setClickListener(R.id.btnAttendance, com.brilliantsoft.sms.ui.attendance.AttendanceActivity.class);
        setClickListener(R.id.btnFees, com.brilliantsoft.sms.ui.fees.FeesActivity.class);
        setClickListener(R.id.btnNotice, com.brilliantsoft.sms.ui.notice.NoticeActivity.class);
        setClickListener(R.id.btnAssignments, com.brilliantsoft.sms.ui.assignment.AssignmentActivity.class);
        setClickListener(R.id.btnExams, com.brilliantsoft.sms.ui.exam.ExaminationActivity.class);
        setClickListener(R.id.btnLibrary, com.brilliantsoft.sms.ui.library.LibraryActivity.class);
        setClickListener(R.id.btnHostel, com.brilliantsoft.sms.ui.hostel.HostelTransportActivity.class);
        setClickListener(R.id.btnTranscript, com.brilliantsoft.sms.ui.transcript.TranscriptActivity.class);
    }

    private void setClickListener(int id, Class<?> activityClass) {
        View view = findViewById(id);
        if (view != null) {
            view.setOnClickListener(v -> startActivity(new Intent(this, activityClass)));
        }
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
                    Map<String, Object> data = response.body();

                    // Update Student Info if returned
                    if (data.containsKey("studentName")) {
                        tvStudentName.setText(safeString(data.get("studentName")));
                    }
                    if (data.containsKey("studentCode")) {
                        tvStudentId.setText(getString(R.string.student_id_format, safeString(data.get("studentCode"))));
                    }

                    // Attendance
                    if (data.containsKey("attendancePercentage")) {
                        Object val = data.get("attendancePercentage");
                        int progress = 0;
                        if (val instanceof Number) {
                            progress = ((Number) val).intValue();
                        } else if (val instanceof String) {
                            try {
                                progress = (int) Double.parseDouble((String) val);
                            } catch (Exception ignored) {}
                        }
                        attendanceProgress.setProgress(progress);
                        tvAttendancePercent.setText(getString(R.string.percent_format, progress));
                    }

                    // Today's Schedule
                    if (data.containsKey("todayClasses")) {
                        Object classesObj = data.get("todayClasses");
                        if (classesObj instanceof List) {
                            List<?> rawList = (List<?>) classesObj;
                            llScheduleContainer.removeAllViews();
                            for (Object item : rawList) {
                                if (item instanceof Map) {
                                    addScheduleItem((Map<String, Object>) item);
                                }
                            }
                        }
                    }
                } else {
                    Log.e(TAG, "Dashboard failed: " + response.code() + " " + response.message());
                    Toast.makeText(MainActivity.this, "Failed to load dashboard: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Log.e(TAG, "Dashboard error", t);
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Use session data as fallback/initial
        tvStudentName.setText(sessionManager.getUsername());
        long studentId = sessionManager.getStudentId();
        if (studentId != -1) {
            tvStudentId.setText(getString(R.string.student_id_format, String.valueOf(studentId)));
        }
    }

    private void addScheduleItem(Map<String, Object> cls) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_routine_row, llScheduleContainer, false);
        TextView tvTime = view.findViewById(R.id.tvTime);
        TextView tvDuration = view.findViewById(R.id.tvDuration);
        TextView tvSubject = view.findViewById(R.id.tvSubject);
        TextView tvRoom = view.findViewById(R.id.tvRoom);

        tvTime.setText(safeString(cls.get("startTime")));
        tvDuration.setText(getString(R.string.duration_mins_format, safeString(cls.get("durationMinutes"))));
        tvSubject.setText(safeString(cls.get("courseName")));
        tvRoom.setText(safeString(cls.get("roomName")));

        llScheduleContainer.addView(view);
    }

    private String safeString(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }
}
