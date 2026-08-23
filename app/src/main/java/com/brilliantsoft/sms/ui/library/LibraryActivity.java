package com.brilliantsoft.sms.ui.library;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brilliantsoft.sms.R;
import com.brilliantsoft.sms.network.ApiClient;
import com.brilliantsoft.sms.network.ApiService;

import com.brilliantsoft.sms.model.BookResponse;
import com.brilliantsoft.sms.model.PageResponse;
import com.brilliantsoft.sms.ui.adapter.BookAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryActivity extends AppCompatActivity {

    private RecyclerView rvBooks;
    private BookAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        apiService = ApiClient.getInstance(this).getApiService();
        rvBooks = findViewById(R.id.rvBooks);
        rvBooks.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new BookAdapter();
        rvBooks.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadBooks();
    }

    private void loadBooks() {
        apiService.getBooks(0, 50, null).enqueue(new Callback<PageResponse<BookResponse>>() {
            @Override
            public void onResponse(Call<PageResponse<BookResponse>> call, Response<PageResponse<BookResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setBooks(response.body().getContent());
                }
            }

            @Override
            public void onFailure(Call<PageResponse<BookResponse>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
