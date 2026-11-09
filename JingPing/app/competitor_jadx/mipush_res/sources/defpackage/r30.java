package defpackage;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import org.webrtc.JavaI420Buffer;
import org.webrtc.VideoFrame;

/* JADX INFO: loaded from: classes.dex */
public abstract class r30 {
    public static VideoFrame a(Bitmap bitmap, long j) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        int i = width2 * height2;
        int i2 = (i / 2) + i;
        byte[] bArr = new byte[i2];
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width2, 0, 0, width2, height2);
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int i6 = iArr[i4];
            int[] iArr2 = iArr;
            double d = (i6 >> 16) & 255;
            double d2 = (i6 >> 8) & 255;
            int i7 = i;
            double d3 = i6 & 255;
            int i8 = i4;
            int i9 = (int) ((d3 * 0.5d) + (((-0.1687d) * d) - (0.3313d * d2)) + 128.0d);
            int i10 = (int) ((((0.5d * d) - (d2 * 0.46d)) - (d3 * 0.0402d)) + 128.0d);
            int iMax = Math.max(0, Math.min(255, (int) ((0.114d * d3) + (0.587d * d2) + (0.299d * d))));
            int iMax2 = Math.max(0, Math.min(255, i9));
            int iMax3 = Math.max(0, Math.min(255, i10));
            int i11 = i5 + 1;
            bArr[i5] = (byte) iMax;
            if (i8 % 2 == 0 && (i8 / width2) % 2 == 0 && i3 < i2) {
                int i12 = i3 + 1;
                bArr[i3] = (byte) iMax2;
                i3 += 2;
                bArr[i12] = (byte) iMax3;
            }
            i4 = i8 + 1;
            iArr = iArr2;
            i5 = i11;
            i = i7;
        }
        JavaI420Buffer javaI420BufferAllocate = JavaI420Buffer.allocate(width, height);
        int i13 = width * height;
        int i14 = i13 / 4;
        ByteBuffer dataY = javaI420BufferAllocate.getDataY();
        ByteBuffer dataU = javaI420BufferAllocate.getDataU();
        ByteBuffer dataV = javaI420BufferAllocate.getDataV();
        dataY.put(bArr, 0, i13);
        dataU.put(bArr, i13, i14);
        dataV.put(bArr, i13 + i14, i14);
        return new VideoFrame(javaI420BufferAllocate, 0, j);
    }
}
