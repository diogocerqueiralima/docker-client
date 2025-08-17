package com.github.diogocerqueiralima;

import com.github.diogocerqueiralima.resources.ContainerResource;
import retrofit2.Retrofit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DockerClient implements AutoCloseable {

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    private final ContainerResource containerResource;

    protected DockerClient(Retrofit retrofit) {
        this.containerResource = new ContainerResource(retrofit, executor);
    }

    public ContainerResource containers() {
        return this.containerResource;
    }

    @Override
    public void close() {
        executor.close();
    }

}
