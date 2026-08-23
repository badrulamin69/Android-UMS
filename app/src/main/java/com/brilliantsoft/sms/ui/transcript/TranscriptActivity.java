package com.brilliantsoft.sms.ui.transcript;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.brilliantsoft.sms.R;

public class TranscriptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
