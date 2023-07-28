package com.example.cardemo.view;


public class Er1WaveUtil {
    public static float byteTomV(int src2) {
        short src = (short) src2;
        byte a= (byte) (src&0xff);
        byte b= (byte) ((src>>8)&0xff);
        if (a == (byte) 0xff && b == (byte) 0x7f)
            return 0f;
        int n = ((a & 0xFF) | (short) (b << 8));
        return (float) (n * (1.0035 * 1800) / (4096 * 178.74));
    }

    public static float byteTomV2(int src) {
        //src 为 0-0xFFFF 的 16bit 数值，其中 0 值为 0x8000，大于 0x8000 为正值，小于
        //0x8000 为负值。
        int n=0;
        if(src>=0x8000){
            n=src-0x8000;
        }else{
            n=0x8000-src;
        }

        return (float) (n * (1.0035 * 1800) / (4096 * 178.74));
    }

    public static float byteTomV3(int src){
        int n=src;
        if((src & 0x8000)==0x8000){
            src=src-1;
            src=src^0xffff;
            n=-(src&0x7fff);
        }
        return (float) (n * (1.0035 * 1800) / (4096 * 178.74));
    }

    public static float byteTomV4(int src){
        int n=src;

        return (float) (n * (1.0035 * 1800) / (4096 * 178.74));
    }
}
