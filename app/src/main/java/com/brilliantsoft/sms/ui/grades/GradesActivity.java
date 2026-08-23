package com.brilliantsoft.sms.ui.grades;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;
import com.brilliantsoft.sms.session.SessionManager;

import com.brilliantsoft.sms.model.ExaminationResultResponse;
import com.brilliantsoft.sms.ui.adapter.ResultAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GradesActivity extends AppCompatActivity {

    private RecyclerView rvResults;
    private ResultAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        apiService = ApiClient.getInstance(this).getApiService();
        rvResults = findViewById(R.id.rvResults);
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ResultAdapter();
        rvResults.setAdapter(adapter);

        setupTermSelector();
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadGrades();
    }

    private void setupTermSelector() {
        TextView term1 = findViewById(R.id.term1);
        TextView term2 = findViewById(R.id.term2);
        TextView term3 = findViewById(R.id.term3);

        term1.setOnClickListener(v -> selectTerm(term1, term2, term3));
        term2.setOnClickListener(v -> selectTerm(term2, term1, term3));
        term3.setOnClickListener(v -> selectTerm(term3, term1, term2));
    }

    private void selectTerm(TextView selected, TextView... others) {
        selected.setBackgroundResource(R.drawable.neumorphic_edittext_sunken);
        selected.setTextColor(ContextCompat.getColor(this, R.color.electric_primary_blue));
        for (TextView other : others) {
            other.setBackground(null);
            other.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
        }
        // Load data for the selected term
        loadGrades();
    }

    private void loadGrades() {
        apiService.getExaminationResults().enqueue(new Callback<List<ExaminationResultResponse>>() {
            @Override
            public void onResponse(Call<List<ExaminationResultResponse>> call, Response<List<ExaminationResultResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setResults(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ExaminationResultResponse>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
