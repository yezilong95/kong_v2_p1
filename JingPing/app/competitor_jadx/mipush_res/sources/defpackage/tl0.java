package defpackage;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class tl0 {
    public static final HashMap d = new HashMap();
    public final File a;
    public final Lock b;
    public FileChannel c;

    public tl0(File file, String str) {
        Lock lock;
        File file2 = new File(file, str.concat(".lck"));
        this.a = file2;
        String absolutePath = file2.getAbsolutePath();
        t40.m(absolutePath, "lockFile.absolutePath");
        HashMap map = d;
        synchronized (map) {
            try {
                Object reentrantLock = map.get(absolutePath);
                if (reentrantLock == null) {
                    reentrantLock = new ReentrantLock();
                    map.put(absolutePath, reentrantLock);
                }
                lock = (Lock) reentrantLock;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.b = lock;
    }

    public final void a(boolean z) {
        this.b.lock();
        if (z) {
            File file = this.a;
            try {
                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                FileChannel channel = new FileOutputStream(file).getChannel();
                channel.lock();
                this.c = channel;
            } catch (IOException e) {
                this.c = null;
                Log.w("SupportSQLiteLock", "Unable to grab file lock.", e);
            }
        }
    }

    public final void b() {
        try {
            FileChannel fileChannel = this.c;
            if (fileChannel != null) {
                fileChannel.close();
            }
        } catch (IOException unused) {
        }
        this.b.unlock();
    }
}
