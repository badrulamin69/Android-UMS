package com.brilliantsoft.sms.network;

import android.content.Context;
import com.brilliantsoft.sms.model.LoginResponse;
import com.brilliantsoft.sms.model.RefreshTokenRequest;
import com.brilliantsoft.sms.session.SessionManager;
import com.brilliantsoft.sms.utils.Constants;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

public class TokenAuthenticator implements Authenticator {
    private final Context ctx;
    private final SessionManager session;
    private final Gson gson = new Gson();

    public TokenAuthenticator(Context ctx) {
        this.ctx = ctx.getApplicationContext();
        this.session = SessionManager.getInstance(ctx);
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (responseCount(response) >= 2) return null;
        String refreshToken = session.getRefreshToken();
        if (refreshToken == null) {
            new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> session.logout(ctx));
            return null;
        }
        try {
            ApiService service = ApiClient.getInstance(ctx).createServiceWithoutAuth();
            Call<LoginResponse> call = service.refresh(new RefreshTokenRequest(refreshToken));
            retrofit2.Response<LoginResponse> res = call.execute();
            if (res.isSuccessful() && res.body() != null && res.body().getToken() != null) {
                session.saveLogin(res.body());
                return response.request().newBuilder()
                        .header("Authorization", session.getAuthHeader())
                        .build();
            }
        } catch (Exception ignored) {}
        new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> session.logout(ctx));
        return null;
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) result++;
        return result;
    }
}
