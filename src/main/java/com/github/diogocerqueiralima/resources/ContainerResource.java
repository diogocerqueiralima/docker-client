package com.github.diogocerqueiralima.resources;

import com.github.diogocerqueiralima.exceptions.DockerException;
import com.github.diogocerqueiralima.model.Container;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

public class ContainerResource {

    private final ExecutorService executor;
    private final DockerApi dockerApi;

    public ContainerResource(Retrofit retrofit, ExecutorService executor) {
        this.dockerApi = retrofit.create(DockerApi.class);
        this.executor = executor;
    }

    public CompletableFuture<List<Container>> listAll() {
        return executeAsync(() -> dockerApi.list(true, null));
    }

    public CompletableFuture<List<Container>> list(int limit) {
        return executeAsync(() -> dockerApi.list(null, limit));
    }

    public CompletableFuture<List<Container>> listRunning() {
        return executeAsync(() -> dockerApi.list(false, null));
    }

    private <T> CompletableFuture<T> executeAsync(Supplier<Call<T>> supplier) {
        return CompletableFuture.supplyAsync(() -> {

            try {
                Response<T> response = supplier.get().execute();
                return handleResponse(response);
            } catch (Exception e) {
                throw new DockerException("Failed to communicate with Docker API", e);
            }

        }, executor);
    }

    private <T> T handleResponse(Response<T> response) throws IOException {

        if (response.isSuccessful())
            return response.body();

        try (ResponseBody responseBody = response.errorBody()) {

            String message = "An error has occurred";
            if (responseBody != null) {

                String json = responseBody.string();
                Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
                Map<String, Object> map = new Gson().fromJson(json, mapType);

                message = String.valueOf(map.getOrDefault("message", message));
            }

            throw new DockerException(message);
        }

    }

    private interface DockerApi {

        @GET("/containers/json")
        Call<List<Container>> list(@Query("all") Boolean all, @Query("limit") Integer limit);

    }

}
