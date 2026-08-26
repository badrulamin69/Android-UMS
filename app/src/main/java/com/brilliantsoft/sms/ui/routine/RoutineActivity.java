package com.brilliantsoft.sms.ui.routine;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;

import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.ui.adapter.RoutineAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutineActivity extends AppCompatActivity {

    private static final String TAG = "RoutineActivity";
    private RecyclerView rvRoutine;
    private RoutineAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        apiService = ApiClient.getInstance(this).getApiService();
        rvRoutine = findViewById(R.id.rvRoutine);
        rvRoutine.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RoutineAdapter();
        rvRoutine.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        setupDaySelector();
        loadRoutine("MONDAY");
    }

    private void setupDaySelector() {
        TextView btnMon = findViewById(R.id.btnMon);
        TextView btnTue = findViewById(R.id.btnTue);
        TextView btnWed = findViewById(R.id.btnWed);
        TextView btnThu = findViewById(R.id.btnThu);
        TextView btnFri = findViewById(R.id.btnFri);

        btnMon.setOnClickListener(v -> selectDay(btnMon, "MONDAY", btnTue, btnWed, btnThu, btnFri));
        btnTue.setOnClickListener(v -> selectDay(btnTue, "TUESDAY", btnMon, btnWed, btnThu, btnFri));
        btnWed.setOnClickListener(v -> selectDay(btnWed, "WEDNESDAY", btnMon, btnTue, btnThu, btnFri));
        btnThu.setOnClickListener(v -> selectDay(btnThu, "THURSDAY", btnMon, btnTue, btnWed, btnFri));
        btnFri.setOnClickListener(v -> selectDay(btnFri, "FRIDAY", btnMon, btnTue, btnWed, btnThu));
    }

    private void selectDay(TextView selected, String day, TextView... others) {
        selected.setBackgroundResource(R.drawable.neumorphic_card_raised);
        selected.setTextColor(ContextCompat.getColor(this, R.color.electric_primary_blue));
        selected.setTypeface(null, android.graphics.Typeface.BOLD);

        for (TextView other : others) {
            other.setBackground(null);
            other.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
            other.setTypeface(null, android.graphics.Typeface.NORMAL);
        }

        loadRoutine(day);
    }

    private void loadRoutine(String day) {
        apiService.getSemesterRoutines(0, 100).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PageResponse<Map<String, Object>>> call, Response<PageResponse<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Map<String, Object>> all = response.body().getContent();
                    List<Map<String, Object>> filtered = new ArrayList<>();
                    if (all != null) {
                        for (Map<String, Object> item : all) {
                            if (day.equalsIgnoreCase(safeString(item.get("dayOfWeek")))) {
                                filtered.add(item);
                            }
                        }
                    }
                    adapter.setRoutines(filtered);
                } else {
                    Log.e(TAG, "Routine failed: " + response.code() + " " + response.message());
                    Toast.makeText(RoutineActivity.this, "Failed to load routine: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PageResponse<Map<String, Object>>> call, Throwable t) {
                Log.e(TAG, "Routine error", t);
                Toast.makeText(RoutineActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String safeString(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }
}
