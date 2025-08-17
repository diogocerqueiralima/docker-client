package com.github.diogocerqueiralima;

import com.github.diogocerqueiralima.resources.ContainersResource;
import retrofit2.Retrofit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DockerClient implements AutoCloseable {

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    private final ContainersResource containersResource;

    protected DockerClient(Retrofit retrofit) {
        this.containersResource = new ContainersResource(retrofit, executor);
    }

    public ContainersResource containers() {
        return this.containersResource;
    }

    @Override
    public void close() {
        executor.close();
    }

}
