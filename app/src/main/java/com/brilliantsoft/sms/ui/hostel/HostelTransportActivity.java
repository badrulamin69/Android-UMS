package com.brilliantsoft.sms.ui.hostel;

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

public class HostelTransportActivity extends AppCompatActivity {

    private static final String TAG = "HostelTransportActivity";
    private TextView tvHostelName, tvRoomNo, tvRoute, tvVehicle;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_transport);

        apiService = ApiClient.getInstance(this).getApiService();
        tvHostelName = findViewById(R.id.tvHostelName);
        tvRoomNo = findViewById(R.id.tvRoomNo);
        tvRoute = findViewById(R.id.tvRoute);
        tvVehicle = findViewById(R.id.tvVehicle);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadHostelData();
        loadTransportData();
    }

    private void loadHostelData() {
        apiService.getHostelAllocations(0, 1).enqueue(new Callback<PageResponse<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<PageResponse<Map<String, Object>>> call, Response<PageResponse<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getContent().isEmpty()) {
                    Map<String, Object> allocation = response.body().getContent().get(0);
                    tvHostelName.setText(safeString(allocation.get("hostelName")));
                    tvRoomNo.setText("Room No: " + safeString(allocation.get("roomNumber")));
                } else {
                    Log.d(TAG, "No hostel data: " + response.code());
                    tvHostelName.setText("No Hostel Allocated");
                    tvRoomNo.setText("");
                }
            }

            @Override
            public void onFailure(Call<PageResponse<Map<String, Object>>> call, Throwable t) {
                Log.e(TAG, "Hostel fetch error", t);
                Toast.makeText(HostelTransportActivity.this, "Hostel Data Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTransportData() {
        apiService.getTransportAllocations(0, 1).enqueue(new Callback<PageResponse<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<PageResponse<Map<String, Object>>> call, Response<PageResponse<Map<String, Object>>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getContent().isEmpty()) {
                    Map<String, Object> allocation = response.body().getContent().get(0);
                    tvRoute.setText(safeString(allocation.get("routeName")));
                    tvVehicle.setText("Vehicle: " + safeString(allocation.get("vehicleNumber")));
                } else {
                    Log.d(TAG, "No transport data: " + response.code());
                    tvRoute.setText("No Transport Allocated");
                    tvVehicle.setText("");
                }
            }

            @Override
            public void onFailure(Call<PageResponse<Map<String, Object>>> call, Throwable t) {
                Log.e(TAG, "Transport fetch error", t);
                Toast.makeText(HostelTransportActivity.this, "Transport Data Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String safeString(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }
}
