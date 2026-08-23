package com.brilliantsoft.sms.ui.profile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
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

    private TextView tvFullName, tvProgram, tvEmail, tvPhone;
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
                    tvFullName.setText(profile.getStudentName());
                    tvProgram.setText(profile.getProgramName());
                    tvEmail.setText(profile.getEmail());
                    tvPhone.setText(profile.getPhone());
                }
            }

            @Override
            public void onFailure(Call<StudentProfileResponse> call, Throwable t) {
                // Error handling
            }
        });
    }
}
