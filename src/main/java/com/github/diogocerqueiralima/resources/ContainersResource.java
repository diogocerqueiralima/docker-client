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

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ContainersResource {

    private final ExecutorService executor;
    private final DockerApi dockerApi;

    public ContainersResource(Retrofit retrofit, ExecutorService executor) {
        this.dockerApi = retrofit.create(DockerApi.class);
        this.executor = executor;
    }

    public CompletableFuture<List<Container>> listAll() {

        return CompletableFuture.supplyAsync(() -> {

            try {

                Response<List<Container>> response = dockerApi.listAll().execute();

                if (response.isSuccessful())
                    return response.body();

                try (ResponseBody responseBody = response.errorBody()) {

                    String message = null;

                    if (responseBody != null) {

                        String json = responseBody.string();
                        Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
                        Map<String, Object> map = new Gson().fromJson(json, mapType);

                        message = map.get("message").toString();
                    }

                    if (message == null)
                        message = "An error has occurred";

                    throw new DockerException(message);
                }

            } catch (IOException e) {
                throw new DockerException("Failed to communicate with Docker API");
            }

        }, executor);

    }

    private interface DockerApi {

        @GET("/containers/json?all=true")
        Call<List<Container>> listAll();

    }

}
