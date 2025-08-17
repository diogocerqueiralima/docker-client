package com.github.diogocerqueiralima;

import com.github.diogocerqueiralima.sockets.UnixSocketFactory;
import okhttp3.OkHttpClient;

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

        return new DockerClient(okHttpClient);
    }

}
