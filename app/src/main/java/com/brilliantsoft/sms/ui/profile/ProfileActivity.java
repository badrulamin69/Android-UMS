package com.brilliantsoft.sms.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.model.StudentProfileResponse;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;
import com.brilliantsoft.sms.session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private TextView tvFullName, tvProgram, tvEmail, tvPhone, tvGpa, tvCredits;
    private Button btnLogout;
    private SessionManager sessionManager;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = SessionManager.getInstance(this);
        apiService = ApiClient.getInstance(this).getApiService();

        tvFullName = findViewById(R.id.tvFullName);
        tvProgram = findViewById(R.id.tvProgram);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvGpa = findViewById(R.id.tvGpa);
        tvCredits = findViewById(R.id.tvCredits);
        btnLogout = findViewById(R.id.btnLogout);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        btnLogout.setOnClickListener(v -> sessionManager.logout(this));

        loadProfileData();
    }

    private void loadProfileData() {
        apiService.getMyProfile().enqueue(new Callback<StudentProfileResponse>() {
            @Override
            public void onResponse(Call<StudentProfileResponse> call, Response<StudentProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StudentProfileResponse profile = response.body();
                    Log.d(TAG, "Profile loaded: " + profile.getStudentName());
                    tvFullName.setText(profile.getStudentName());
                    tvProgram.setText(profile.getProgramName());
                    tvEmail.setText(profile.getEmail());
                    tvPhone.setText(profile.getPhone());
                    
                    // Fetch additional info (GPA/Credits) from transcripts
                    fetchTranscriptInfo();
                } else {
                    Log.e(TAG, "Profile fetch failed: " + response.code() + " " + response.message());
                    try {
                        if (response.errorBody() != null) {
                            Log.e(TAG, "Error body: " + response.errorBody().string());
                        }
                    } catch (Exception ignored) {}
                    Toast.makeText(ProfileActivity.this, "Failed to load profile: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StudentProfileResponse> call, Throwable t) {
                Log.e(TAG, "Profile fetch error", t);
                Toast.makeText(ProfileActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchTranscriptInfo() {
        apiService.getTranscripts(0, 1).enqueue(new retrofit2.Callback<com.brilliantsoft.sms.model.PageResponse<java.util.Map<String, Object>>>() {
            @Override
            public void onResponse(Call<com.brilliantsoft.sms.model.PageResponse<java.util.Map<String, Object>>> call, Response<com.brilliantsoft.sms.model.PageResponse<java.util.Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getContent().isEmpty()) {
                    java.util.Map<String, Object> transcript = response.body().getContent().get(0);
                    tvGpa.setText(String.valueOf(transcript.getOrDefault("cgpa", "N/A")));
                    tvCredits.setText(String.valueOf(transcript.getOrDefault("totalCredits", "N/A")));
                }
            }

            @Override
            public void onFailure(Call<com.brilliantsoft.sms.model.PageResponse<java.util.Map<String, Object>>> call, Throwable t) {
                Log.e(TAG, "Transcript info fetch failed", t);
            }
        });
    }
}
