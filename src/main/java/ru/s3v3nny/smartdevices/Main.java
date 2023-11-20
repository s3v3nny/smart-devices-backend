package ru.s3v3nny.smartdevices;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        SmartDevicesServer server = new SmartDevicesServer();
        server.start(port);
    }
}