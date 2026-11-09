package defpackage;

import android.util.Log;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/* JADX INFO: loaded from: classes.dex */
public final class f3 extends Handler {
    public static final f3 a = new f3();

    @Override // java.util.logging.Handler
    public final void publish(LogRecord logRecord) {
        int iMin;
        oy.j(logRecord, "record");
        CopyOnWriteArraySet copyOnWriteArraySet = e3.a;
        String loggerName = logRecord.getLoggerName();
        oy.i(loggerName, "record.loggerName");
        int iIntValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        int i = iIntValue > level.intValue() ? 5 : logRecord.getLevel().intValue() == level.intValue() ? 4 : 3;
        String message = logRecord.getMessage();
        oy.i(message, "record.message");
        Throwable thrown = logRecord.getThrown();
        String strA0 = (String) e3.b.get(loggerName);
        if (strA0 == null) {
            strA0 = vk0.A0(23, loggerName);
        }
        if (Log.isLoggable(strA0, i)) {
            if (thrown != null) {
                message = message + '\n' + Log.getStackTraceString(thrown);
            }
            int length = message.length();
            int i2 = 0;
            while (i2 < length) {
                int iO0 = vk0.o0(message, '\n', i2, 4);
                if (iO0 == -1) {
                    iO0 = length;
                }
                while (true) {
                    iMin = Math.min(iO0, i2 + 4000);
                    String strSubstring = message.substring(i2, iMin);
                    oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    Log.println(i, strA0, strSubstring);
                    if (iMin >= iO0) {
                        break;
                    } else {
                        i2 = iMin;
                    }
                }
                i2 = iMin + 1;
            }
        }
    }

    @Override // java.util.logging.Handler
    public final void close() {
    }

    @Override // java.util.logging.Handler
    public final void flush() {
    }
}
