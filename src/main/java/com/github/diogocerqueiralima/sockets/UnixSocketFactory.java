package com.github.diogocerqueiralima.sockets;

import jnr.unixsocket.UnixSocketChannel;
import javax.net.SocketFactory;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class UnixSocketFactory extends SocketFactory {

    private final File socketPath;

    public UnixSocketFactory(File socketPath) {
        this.socketPath = socketPath;
    }

    @Override
    public Socket createSocket() throws IOException {
        UnixSocketChannel channel = UnixSocketChannel.open();
        return new TunnelingUnixSocket(socketPath, channel);
    }

    @Override
    public Socket createSocket(String s, int i) throws IOException {

        Socket result = createSocket();

        try {
            result.connect(new InetSocketAddress(s, i));
        } catch (Exception e){
            result.close();
            throw e;
        }

        return result;
    }

    @Override
    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException {

        Socket result = createSocket();

        try {
            result.connect(new InetSocketAddress(s, i));
        } catch (Exception e){
            result.close();
            throw e;
        }

        return result;

    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {

        Socket result = createSocket();

        try {
            result.connect(new InetSocketAddress(inetAddress, i));
        } catch (Exception e){
            result.close();
            throw e;
        }

        return result;

    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {

        Socket result = createSocket();

        try {
            result.connect(new InetSocketAddress(inetAddress, i));
        } catch (Exception e){
            result.close();
            throw e;
        }

        return result;

    }

}
