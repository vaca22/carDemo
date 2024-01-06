package com.example.cardemo.view;


import android.content.Context;
import android.util.TypedValue;

import com.viatom.gqsdk.EcgAnalysis;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EcgWaveUtil {
    public static float byteTomV(int src2) {
        short src = (short) src2;
        byte a= (byte) (src&0xff);
        byte b= (byte) ((src>>8)&0xff);
        if (a == (byte) 0xff && b == (byte) 0x7f)
            return 0f;
        int n = ((a & 0xFF) | (short) (b << 8));
        return (float)(n * 0.00215517);
    }
    public static float byteToFilter(int src2) {
        short src = (short) src2;
        byte a= (byte) (src&0xff);
        byte b= (byte) ((src>>8)&0xff);
        int n=0;
        if (a == (byte) 0xff && b == (byte) 0x7f){
            n=0;
        }else{
            n = ((a & 0xFF) | (short) (b << 8));
        }

        float result= EcgAnalysis.INSTANCE.inputEcg(n*200/464,0)/200.0f;
        return result ;
    }

    public static float byteToFilterSimu(int src2) {
        float result= EcgAnalysis.INSTANCE.inputEcg(src2*200/464,0)/200.0f;
        return result ;
    }

    public static float convertMM2Px(Context context, float mm) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mm,
                context.getResources().getDisplayMetrics());
    }

    @NotNull
    public static float convertPx2MM(@Nullable Context context, float toFloat) {
        return toFloat/TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 1,
                context.getResources().getDisplayMetrics());
    }
}
