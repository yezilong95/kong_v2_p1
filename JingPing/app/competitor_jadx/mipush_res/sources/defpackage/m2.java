package defpackage;

import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import androidx.work.WorkRequest;
import java.nio.ByteBuffer;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class m2 {
    public MediaCodec a;
    public MediaMuxer b;
    public byte[] e;
    public int[] f;
    public int c = -1;
    public boolean d = false;
    public boolean g = false;
    public final Object h = new Object();
    public long i = 0;

    public final void a(Bitmap bitmap, byte[] bArr) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = this.f;
        if (iArr == null || iArr.length != i) {
            this.f = new int[i];
        }
        bitmap.getPixels(this.f, 0, width, 0, 0, width, height);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < height; i4++) {
            int i5 = 0;
            while (i5 < width) {
                int i6 = i3 + 1;
                int i7 = this.f[i3];
                int i8 = (i7 >> 16) & 255;
                int i9 = (i7 >> 8) & 255;
                int i10 = i7 & 255;
                int i11 = ((((i10 * 25) + ((i9 * 129) + (i8 * 66))) + Uuid.SIZE_BITS) >> 8) + 16;
                int i12 = ((((i10 * 112) + ((i8 * (-38)) - (i9 * 74))) + Uuid.SIZE_BITS) >> 8) + Uuid.SIZE_BITS;
                int i13 = (((((i8 * 112) - (i9 * 94)) - (i10 * 18)) + Uuid.SIZE_BITS) >> 8) + Uuid.SIZE_BITS;
                int i14 = i2 + 1;
                bArr[i2] = (byte) Math.max(0, Math.min(255, i11));
                if ((i4 & 1) == 0 && (i5 & 1) == 0) {
                    int i15 = i + 1;
                    bArr[i] = (byte) i12;
                    i += 2;
                    bArr[i15] = (byte) i13;
                }
                i5++;
                i3 = i6;
                i2 = i14;
            }
        }
    }

    public final void b() {
        if (this.a == null) {
            return;
        }
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        while (true) {
            try {
                int iDequeueOutputBuffer = this.a.dequeueOutputBuffer(bufferInfo, 0L);
                if (iDequeueOutputBuffer == -1) {
                    return;
                }
                if (iDequeueOutputBuffer == -2) {
                    if (this.d) {
                        return;
                    }
                    MediaFormat outputFormat = this.a.getOutputFormat();
                    MediaMuxer mediaMuxer = this.b;
                    if (mediaMuxer != null) {
                        this.c = mediaMuxer.addTrack(outputFormat);
                        this.b.start();
                        this.d = true;
                    }
                } else if (iDequeueOutputBuffer < 0) {
                    continue;
                } else {
                    ByteBuffer outputBuffer = this.a.getOutputBuffer(iDequeueOutputBuffer);
                    if (outputBuffer == null) {
                        this.a.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    } else {
                        if ((bufferInfo.flags & 2) != 0) {
                            bufferInfo.size = 0;
                        }
                        if (bufferInfo.size > 0 && this.d && this.b != null) {
                            outputBuffer.position(bufferInfo.offset);
                            outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                            try {
                                this.b.writeSampleData(this.c, outputBuffer, bufferInfo);
                            } catch (Exception unused) {
                            }
                        }
                        this.a.releaseOutputBuffer(iDequeueOutputBuffer, false);
                        if ((bufferInfo.flags & 4) != 0) {
                            return;
                        }
                    }
                }
            } catch (Exception unused2) {
                return;
            }
        }
    }

    public final void c(Bitmap bitmap) {
        ByteBuffer inputBuffer;
        synchronized (this.h) {
            try {
                if (!this.g || this.a == null) {
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.i < 100) {
                    return;
                }
                this.i = jCurrentTimeMillis;
                int width = ((bitmap.getWidth() * bitmap.getHeight()) * 3) / 2;
                byte[] bArr = this.e;
                if (bArr == null || bArr.length != width) {
                    this.e = new byte[width];
                }
                a(bitmap, this.e);
                try {
                    int iDequeueInputBuffer = this.a.dequeueInputBuffer(WorkRequest.MIN_BACKOFF_MILLIS);
                    if (iDequeueInputBuffer >= 0 && (inputBuffer = this.a.getInputBuffer(iDequeueInputBuffer)) != null) {
                        inputBuffer.clear();
                        inputBuffer.put(this.e);
                        this.a.queueInputBuffer(iDequeueInputBuffer, 0, this.e.length, System.nanoTime() / 1000, 0);
                    }
                    b();
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void d(int i, int i2, String str) {
        synchronized (this.h) {
            if (this.g) {
                return;
            }
            try {
                this.a = MediaCodec.createEncoderByType("video/avc");
                MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
                mediaFormatCreateVideoFormat.setInteger("bitrate", 2000000);
                mediaFormatCreateVideoFormat.setInteger("frame-rate", 10);
                mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 1);
                mediaFormatCreateVideoFormat.setInteger("color-format", 21);
                this.a.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                this.a.start();
                this.b = new MediaMuxer(str, 0);
                this.g = true;
            } catch (Exception e) {
                e.printStackTrace();
                this.g = false;
            }
        }
    }

    public final void e() {
        MediaMuxer mediaMuxer;
        synchronized (this.h) {
            try {
                if (this.g) {
                    this.g = false;
                    try {
                        MediaCodec mediaCodec = this.a;
                        if (mediaCodec != null) {
                            try {
                                int iDequeueInputBuffer = mediaCodec.dequeueInputBuffer(WorkRequest.MIN_BACKOFF_MILLIS);
                                if (iDequeueInputBuffer >= 0) {
                                    this.a.queueInputBuffer(iDequeueInputBuffer, 0, 0, System.nanoTime() / 1000, 4);
                                }
                            } catch (Exception unused) {
                            }
                            b();
                            try {
                                this.a.stop();
                            } catch (Exception unused2) {
                            }
                            try {
                                this.a.release();
                            } catch (Exception unused3) {
                            }
                            this.a = null;
                        }
                        if (this.d && (mediaMuxer = this.b) != null) {
                            try {
                                mediaMuxer.stop();
                            } catch (Exception unused4) {
                            }
                            try {
                                this.b.release();
                            } catch (Exception unused5) {
                            }
                            this.b = null;
                            this.d = false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
