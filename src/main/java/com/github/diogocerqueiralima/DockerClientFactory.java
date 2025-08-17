package com.github.diogocerqueiralima;

import com.github.diogocerqueiralima.sockets.UnixSocketFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DockerClientFactory {

    public static DockerClient create(DockerClientConfig config) {

        OkHttpClient okHttpClient = switch (config.getConnection()) {
            case UNIX ->
               new OkHttpClient.Builder()
                       .socketFactory(new UnixSocketFactory(config.getSocketFile()))
                       .build();
            case TCP ->
                new OkHttpClient.Builder()
                        .build();
        };

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return new DockerClient(retrofit);
    }

}
