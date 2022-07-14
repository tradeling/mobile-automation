package com.tradeling.mobile.driver;

import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.util.List;

public class DeviceManager {
    String deviceName;
    String deviceId;
    JadbConnection jadbConnection;
    JadbDevice jadbDevice;
    List<JadbDevice> devices;


    public DeviceManager(){

        this.jadbConnection = new JadbConnection();
    }
    String GetActiveDevice() throws IOException, InterruptedException, JadbException {
        Process process = Runtime.getRuntime().exec("adb devices");
        return this.jadbConnection.getDevices().get(0).getSerial();
    }
    List<JadbDevice> GetActiveDevices()throws IOException, InterruptedException, JadbException{
        if (this.jadbConnection.getDevices().size()>1){
            devices = this.jadbConnection.getDevices();
            for (JadbDevice device:devices
            ) {
                devices.add(device);
            }
        }
        return devices;
    }

    String GetDeviceName() throws IOException, JadbException {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        this.jadbConnection.getDevices().get(0).execute("getprop ro.product.model").transferTo(out);
        return out.toString();
    }
    String getDeviceId() throws IOException, InterruptedException, JadbException {
        return GetActiveDevice();
    }

    public String getDeviceId(int device) throws IOException, InterruptedException, JadbException {
        return GetActiveDevices().get(device).getSerial();
    }

    public String getDeviceName() throws IOException, JadbException {
        return GetDeviceName();
    }

    public String getDeviceName(int device) throws IOException, InterruptedException, JadbException {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        GetActiveDevices().get(device).execute("getprop ro.product.model").transferTo(out);
        return out.toString();
    }
}
