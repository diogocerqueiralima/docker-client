package com.github.diogocerqueiralima;

import java.io.File;

public class DockerClientConfig {

    private final Connection connection;
    private final File socketFile;

    private DockerClientConfig(Connection connection, File socketFile) {

        if (connection == null)
            throw new IllegalArgumentException("connection cannot be null");

        if (connection == Connection.UNIX && socketFile == null)
            throw new IllegalArgumentException("socketFile cannot be null");

        this.connection = connection;
        this.socketFile = socketFile;
    }

    public Connection getConnection() {
        return connection;
    }

    public File getSocketFile() {
        return socketFile;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public enum Connection {

        TCP,
        UNIX

    }

    public static class Builder {

        private Connection connection;
        private File socketFile;

        private Builder() {}

        public Builder connection(Connection connection) {
            this.connection = connection;
            return this;
        }

        public Builder socketFile(File socketFile) {
            this.socketFile = socketFile;
            return this;
        }

        public DockerClientConfig build() {
            return new DockerClientConfig(connection, socketFile);
        }

    }

}
