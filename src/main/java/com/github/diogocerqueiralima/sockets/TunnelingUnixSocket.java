package com.github.diogocerqueiralima.sockets;

import jnr.unixsocket.UnixSocket;
import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.UnixSocketChannel;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class TunnelingUnixSocket extends UnixSocket {

    private final File path;
    private InetSocketAddress inetSocketAddress;

    public TunnelingUnixSocket(File path, UnixSocketChannel chan) {
        super(chan);
        this.path = path;
    }

    @Override
    public void connect(SocketAddress endpoint) throws IOException {
        this.inetSocketAddress = (InetSocketAddress) endpoint;
        super.connect(new UnixSocketAddress(path), 0);
    }

    @Override
    public void connect(SocketAddress endpoint, int timeout) throws IOException {
        this.inetSocketAddress = (InetSocketAddress) endpoint;
        super.connect(new UnixSocketAddress(path), timeout);
    }

    @Override
    public InetAddress getInetAddress() {
        return inetSocketAddress.getAddress();
    }

}
