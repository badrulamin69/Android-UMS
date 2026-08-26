package com.brilliantsoft.sms.ui.transcript;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranscriptActivity extends AppCompatActivity {

    private static final String TAG = "TranscriptActivity";
    private TextView tvTotalCredits, tvCGPA;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript);

        apiService = ApiClient.getInstance(this).getApiService();
        tvTotalCredits = findViewById(R.id.tvTotalCredits);
        tvCGPA = findViewById(R.id.tvCGPA);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnDownloadTranscript).setOnClickListener(v -> 
            Toast.makeText(this, "Starting download...", Toast.LENGTH_SHORT).show());

        loadTranscriptData();
    }

    private void loadTranscriptData() {
        apiService.getTranscripts(0, 1).enqueue(new Callback<PageResponse<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<PageResponse<Map<String, Object>>> call, Response<PageResponse<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getContent().isEmpty()) {
                    Map<String, Object> transcript = response.body().getContent().get(0);
                    tvTotalCredits.setText("Total Credits Earned: " + safeString(transcript.get("totalCredits")));
                    tvCGPA.setText("Current CGPA: " + safeString(transcript.get("cgpa")));
                } else {
                    Log.d(TAG, "No transcript data: " + response.code());
                    tvTotalCredits.setText("No data available");
                    tvCGPA.setText("");
                }
            }

            @Override
            public void onFailure(Call<PageResponse<Map<String, Object>>> call, Throwable t) {
                Log.e(TAG, "Transcript fetch error", t);
                Toast.makeText(TranscriptActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String safeString(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }
}
