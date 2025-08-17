package com.github.diogocerqueiralima;

import okhttp3.OkHttpClient;

public class DockerClient {

    private final OkHttpClient httpClient;

    protected DockerClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

}
