package com.brilliantsoft.sms.network;

import android.content.Context;
import com.brilliantsoft.sms.session.SessionManager;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final SessionManager session;
    public AuthInterceptor(Context ctx) { this.session = SessionManager.getInstance(ctx); }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request req = chain.request();
        String path = req.url().encodedPath();
        if (path.contains("/api/auth/") || path.contains("/api/pre-admission") || path.contains("/api/preadmission")) {
            return chain.proceed(req);
        }
        String header = session.getAuthHeader();
        if (header != null) {
            req = req.newBuilder().header("Authorization", header).build();
        }
        return chain.proceed(req);
    }
}
