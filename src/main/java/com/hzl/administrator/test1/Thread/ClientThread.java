package com.hzl.administrator.test1.Thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author hzl
 * @time 2016/6/16 16:52
 */
public class ClientThread implements Runnable {
    private  int send_data;
    OutputStream outputStream = null;
    InputStream inputStream = null;
    byte buffer_tmp[] = new byte[1024];
    public ClientThread(int i) {
        this.send_data=i;
    }

    @Override
    public void run() {
        setBuffer((byte) (send_data),0);
        buffer_tmp[0] = getBuffer(0);
        Socket socket = new Socket();

        int port=8080;
        String ip_addr="192.168.1.10";

        try {
            socket.connect(new InetSocketAddress( ip_addr, Integer.parseInt(String.valueOf("8080"))), 5000);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            //bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 获取Socket的OutputStream对象用于发送数据。
            // 把数据写入到OuputStream对象中
            outputStream.write(buffer_tmp, 0, 1);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static byte buffer[] = new byte[1024];

    public byte getBuffer(int buffer_x)
    {
        return buffer[buffer_x];
    }

    public void setBuffer(byte buffer_val, int buffer_x)
    {
        buffer[buffer_x] = buffer_val;
    }
}
