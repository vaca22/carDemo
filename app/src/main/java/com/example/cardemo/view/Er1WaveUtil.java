package com.example.cardemo.view;


public class Er1WaveUtil {
    public static float byteTomV(int src) {
        byte a= (byte) (src&0xff);
        byte b= (byte) ((src>>8)&0xff);
        if (a == (byte) 0xff && b == (byte) 0x7f)
            return 0f;
        int n = ((a & 0xFF) | (short) (b << 8));
        return (float) (n * (1.0035 * 1800) / (4096 * 178.74));
    }
}
