package com.brilliantsoft.sms.ui.hostel;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.brilliantsoft.sms.R;

public class HostelTransportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_transport);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
