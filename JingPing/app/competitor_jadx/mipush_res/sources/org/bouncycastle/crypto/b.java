package org.bouncycastle.crypto;

import defpackage.c30;
import defpackage.cq0;
import defpackage.jx0;
import defpackage.la0;
import defpackage.z91;
import defpackage.zn;
import io.nats.client.Options;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final Logger a = Logger.getLogger(b.class.getName());
    public static boolean b = false;
    public static boolean c = false;
    public static final boolean d = true;
    public static String e = "Driver load not attempted";
    public static String f = null;
    public static boolean g = true;

    public static File a(String str, File file, String str2) throws IOException {
        InputStream resourceAsStream = b.class.getResourceAsStream(str);
        if (resourceAsStream == null) {
            throw new JarException(zn.m(str, " lib not found in jar"));
        }
        File file2 = new File(file, str2);
        if (file2.exists()) {
            Level level = Level.FINE;
            Logger logger = a;
            if (logger.isLoggable(level)) {
                logger.fine("installation target exists: " + file2.getAbsolutePath());
            }
            FileInputStream fileInputStream = new FileInputStream(file2);
            byte[] bArrD = d(fileInputStream);
            fileInputStream.close();
            if (logger.isLoggable(level)) {
                logger.fine("existing file digest: ".concat(jx0.a(c30.c(bArrD, 0, 32))));
            }
            byte[] bArrD2 = d(resourceAsStream);
            resourceAsStream.close();
            if (logger.isLoggable(level)) {
                logger.fine("new file digest: ".concat(jx0.a(c30.c(bArrD2, 0, 32))));
            }
            if (z91.q(bArrD, bArrD2)) {
                if (logger.isLoggable(level)) {
                    logger.fine("existing file already exists and is the same");
                }
                return file2;
            }
            if (logger.isLoggable(level)) {
                logger.fine("existing file is different and will be replaced");
            }
            resourceAsStream = b.class.getResourceAsStream(str);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[Options.DEFAULT_MAX_CONTROL_LINE];
        while (true) {
            int i = resourceAsStream.read(bArr, 0, Options.DEFAULT_MAX_CONTROL_LINE);
            if (i < 0) {
                fileOutputStream.flush();
                fileOutputStream.close();
                resourceAsStream.close();
                return file2;
            }
            fileOutputStream.write(bArr, 0, i);
        }
    }

    public static File b(String str, String str2, String str3, File file, HashSet hashSet) throws IOException {
        String strA;
        List<String> listUnmodifiableList;
        String strMapLibraryName = System.mapLibraryName(str);
        Level level = Level.FINE;
        Logger logger = a;
        if (logger.isLoggable(level)) {
            logger.fine("attempting to install: " + strMapLibraryName);
        }
        try {
            InputStream resourceAsStream = b.class.getResourceAsStream(str3.concat("/deps.list"));
            strA = jx0.a(la0.Z(resourceAsStream));
            resourceAsStream.close();
        } catch (Exception unused) {
            strA = null;
        }
        if (strA == null) {
            listUnmodifiableList = Collections.EMPTY_LIST;
        } else {
            ArrayList arrayList = new ArrayList();
            for (String str4 : strA.split("\n")) {
                String[] strArrSplit = str4.trim().split(":");
                if (strArrSplit[0].trim().equals(strMapLibraryName)) {
                    arrayList.add(strArrSplit[1].trim());
                }
            }
            listUnmodifiableList = Collections.unmodifiableList(arrayList);
        }
        for (String str5 : listUnmodifiableList) {
            hashSet.remove(a(str3 + "/" + str5, file, str5));
        }
        File fileA = a(str2 + "/" + strMapLibraryName, file, strMapLibraryName);
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("installed " + fileA);
        }
        hashSet.remove(fileA);
        return fileA;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0116 A[Catch: all -> 0x003c, TRY_LEAVE, TryCatch #4 {all -> 0x003c, blocks: (B:4:0x0019, B:9:0x0034, B:12:0x003f, B:14:0x004a, B:17:0x0065, B:19:0x006b, B:20:0x0070, B:23:0x007a, B:26:0x0088, B:28:0x0092, B:29:0x00a0, B:40:0x00c3, B:43:0x00e9, B:45:0x00f1, B:47:0x00f9, B:50:0x0102, B:56:0x0116, B:59:0x013c, B:61:0x0142, B:62:0x0150, B:64:0x0156, B:65:0x015b, B:69:0x016b, B:71:0x0171, B:72:0x0184, B:74:0x0192, B:75:0x019a, B:77:0x01a4, B:79:0x01aa, B:80:0x01ae, B:82:0x01b4, B:83:0x01d7, B:85:0x01fa, B:86:0x020e, B:88:0x0232, B:90:0x0248, B:97:0x0277, B:99:0x0297, B:102:0x02d1, B:103:0x02d4, B:105:0x02e7, B:107:0x02ef, B:111:0x0313, B:112:0x0315, B:113:0x0338, B:115:0x0355, B:116:0x035e, B:118:0x0364, B:120:0x0370, B:123:0x0379, B:124:0x0381, B:127:0x03c0, B:128:0x03c9, B:130:0x03d5, B:133:0x0412, B:135:0x0426, B:138:0x0432, B:146:0x049a, B:142:0x0466, B:93:0x0257, B:32:0x00ac, B:34:0x00b4, B:104:0x02e1), top: B:159:0x0019, inners: #0, #1, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x013c A[Catch: all -> 0x003c, TRY_ENTER, TryCatch #4 {all -> 0x003c, blocks: (B:4:0x0019, B:9:0x0034, B:12:0x003f, B:14:0x004a, B:17:0x0065, B:19:0x006b, B:20:0x0070, B:23:0x007a, B:26:0x0088, B:28:0x0092, B:29:0x00a0, B:40:0x00c3, B:43:0x00e9, B:45:0x00f1, B:47:0x00f9, B:50:0x0102, B:56:0x0116, B:59:0x013c, B:61:0x0142, B:62:0x0150, B:64:0x0156, B:65:0x015b, B:69:0x016b, B:71:0x0171, B:72:0x0184, B:74:0x0192, B:75:0x019a, B:77:0x01a4, B:79:0x01aa, B:80:0x01ae, B:82:0x01b4, B:83:0x01d7, B:85:0x01fa, B:86:0x020e, B:88:0x0232, B:90:0x0248, B:97:0x0277, B:99:0x0297, B:102:0x02d1, B:103:0x02d4, B:105:0x02e7, B:107:0x02ef, B:111:0x0313, B:112:0x0315, B:113:0x0338, B:115:0x0355, B:116:0x035e, B:118:0x0364, B:120:0x0370, B:123:0x0379, B:124:0x0381, B:127:0x03c0, B:128:0x03c9, B:130:0x03d5, B:133:0x0412, B:135:0x0426, B:138:0x0432, B:146:0x049a, B:142:0x0466, B:93:0x0257, B:32:0x00ac, B:34:0x00b4, B:104:0x02e1), top: B:159:0x0019, inners: #0, #1, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized void c() {
        /*
            Method dump skipped, instruction units count: 1231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.b.c():void");
    }

    public static byte[] d(InputStream inputStream) {
        try {
            byte[] bArr = new byte[65535];
            cq0 cq0Var = new cq0();
            while (true) {
                int i = inputStream.read(bArr);
                if (i < 0) {
                    byte[] bArr2 = new byte[32];
                    cq0Var.d(0, bArr2);
                    return bArr2;
                }
                cq0Var.e(bArr, 0, i);
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }
}
