package defpackage;

import android.R;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R$attr;
import com.google.android.material.R$integer;
import com.google.android.material.appbar.AppBarLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import org.java_websocket.extensions.ExtensionRequestData;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public abstract class jq0 {
    public static iq0 a = null;
    public static boolean b = false;
    public static final Object[] c = new Object[0];
    public static final os d = new os(0);
    public static final byte[] e = {112, 114, 111, 0};
    public static final byte[] f = {112, 114, 109, 0};
    public static final int[] g = {R.attr.stateListAnimator};
    public static Field h = null;
    public static boolean i = false;
    public static Class j = null;
    public static boolean k = false;
    public static Field l = null;
    public static boolean m = false;
    public static Field n = null;
    public static boolean o = false;
    public static Field p = null;
    public static boolean q = false;
    public static boolean r = true;

    public static File E(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i2 = 0; i2 < 100; i2++) {
            File file = new File(cacheDir, str + i2);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    public static final boolean J(char c2) {
        return Character.isWhitespace(c2) || Character.isSpaceChar(c2);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x009b A[Catch: RuntimeException -> 0x00a1, TRY_LEAVE, TryCatch #1 {RuntimeException -> 0x00a1, blocks: (B:59:0x0095, B:61:0x009b), top: B:72:0x0095 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.e5 K(android.content.Context r4, defpackage.jt r5, boolean r6, boolean r7) {
        /*
            ft r0 = r5.I
            r1 = 0
            if (r0 != 0) goto L7
            r2 = r1
            goto L9
        L7:
            int r2 = r0.f
        L9:
            if (r7 == 0) goto L1a
            if (r6 == 0) goto L14
            if (r0 != 0) goto L11
        Lf:
            r7 = r1
            goto L27
        L11:
            int r7 = r0.d
            goto L27
        L14:
            if (r0 != 0) goto L17
            goto Lf
        L17:
            int r7 = r0.e
            goto L27
        L1a:
            if (r6 == 0) goto L22
            if (r0 != 0) goto L1f
            goto Lf
        L1f:
            int r7 = r0.b
            goto L27
        L22:
            if (r0 != 0) goto L25
            goto Lf
        L25:
            int r7 = r0.c
        L27:
            r5.A(r1, r1, r1, r1)
            android.view.ViewGroup r0 = r5.E
            r1 = 0
            if (r0 == 0) goto L3e
            int r3 = androidx.fragment.R$id.visible_removing_fragment_view_tag
            java.lang.Object r0 = r0.getTag(r3)
            if (r0 == 0) goto L3e
            android.view.ViewGroup r0 = r5.E
            int r3 = androidx.fragment.R$id.visible_removing_fragment_view_tag
            r0.setTag(r3, r1)
        L3e:
            android.view.ViewGroup r5 = r5.E
            if (r5 == 0) goto L4a
            android.animation.LayoutTransition r5 = r5.getLayoutTransition()
            if (r5 == 0) goto L4a
            goto Lb1
        L4a:
            if (r7 != 0) goto L75
            if (r2 == 0) goto L75
            r5 = 4097(0x1001, float:5.741E-42)
            if (r2 == r5) goto L6d
            r5 = 4099(0x1003, float:5.744E-42)
            if (r2 == r5) goto L65
            r5 = 8194(0x2002, float:1.1482E-41)
            if (r2 == r5) goto L5d
            r5 = -1
        L5b:
            r7 = r5
            goto L75
        L5d:
            if (r6 == 0) goto L62
            int r5 = androidx.fragment.R$animator.fragment_close_enter
            goto L5b
        L62:
            int r5 = androidx.fragment.R$animator.fragment_close_exit
            goto L5b
        L65:
            if (r6 == 0) goto L6a
            int r5 = androidx.fragment.R$animator.fragment_fade_enter
            goto L5b
        L6a:
            int r5 = androidx.fragment.R$animator.fragment_fade_exit
            goto L5b
        L6d:
            if (r6 == 0) goto L72
            int r5 = androidx.fragment.R$animator.fragment_open_enter
            goto L5b
        L72:
            int r5 = androidx.fragment.R$animator.fragment_open_exit
            goto L5b
        L75:
            if (r7 == 0) goto Lb1
            android.content.res.Resources r5 = r4.getResources()
            java.lang.String r5 = r5.getResourceTypeName(r7)
            java.lang.String r6 = "anim"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L95
            android.view.animation.Animation r6 = android.view.animation.AnimationUtils.loadAnimation(r4, r7)     // Catch: android.content.res.Resources.NotFoundException -> L93 java.lang.RuntimeException -> L95
            if (r6 == 0) goto Lb1
            e5 r0 = new e5     // Catch: android.content.res.Resources.NotFoundException -> L93 java.lang.RuntimeException -> L95
            r0.<init>(r6)     // Catch: android.content.res.Resources.NotFoundException -> L93 java.lang.RuntimeException -> L95
            return r0
        L93:
            r4 = move-exception
            throw r4
        L95:
            android.animation.Animator r6 = android.animation.AnimatorInflater.loadAnimator(r4, r7)     // Catch: java.lang.RuntimeException -> La1
            if (r6 == 0) goto Lb1
            e5 r0 = new e5     // Catch: java.lang.RuntimeException -> La1
            r0.<init>(r6)     // Catch: java.lang.RuntimeException -> La1
            return r0
        La1:
            r6 = move-exception
            if (r5 != 0) goto Lb0
            android.view.animation.Animation r4 = android.view.animation.AnimationUtils.loadAnimation(r4, r7)
            if (r4 == 0) goto Lb1
            e5 r5 = new e5
            r5.<init>(r4)
            return r5
        Lb0:
            throw r6
        Lb1:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jq0.K(android.content.Context, jt, boolean, boolean):e5");
    }

    public static MappedByteBuffer L(Context context, Uri uri) {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        try {
            parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", null);
        } catch (IOException unused) {
        }
        if (parcelFileDescriptorOpenFileDescriptor == null) {
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                parcelFileDescriptorOpenFileDescriptor.close();
                return null;
            }
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
            try {
                FileChannel channel = fileInputStream.getChannel();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                fileInputStream.close();
                parcelFileDescriptorOpenFileDescriptor.close();
                return map;
            } finally {
            }
        } finally {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.ca M(defpackage.tv r26) {
        /*
            Method dump skipped, instruction units count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jq0.M(tv):ca");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long N(int r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jq0.N(int, java.lang.String):long");
    }

    public static final boolean O(String str) {
        oy.j(str, "method");
        return (str.equals("GET") || str.equals("HEAD")) ? false : true;
    }

    public static void P(Context context, String str) {
        if (str.equals(ExtensionRequestData.EMPTY_VALUE)) {
            context.deleteFile("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");
            return;
        }
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file", 0);
            XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
            try {
                try {
                    try {
                        xmlSerializerNewSerializer.setOutput(fileOutputStreamOpenFileOutput, null);
                        xmlSerializerNewSerializer.startDocument("UTF-8", Boolean.TRUE);
                        xmlSerializerNewSerializer.startTag(null, "locales");
                        xmlSerializerNewSerializer.attribute(null, "application_locales", str);
                        xmlSerializerNewSerializer.endTag(null, "locales");
                        xmlSerializerNewSerializer.endDocument();
                        Log.d("AppLocalesStorageHelper", "Storing App Locales : app-locales: " + str + " persisted successfully.");
                        if (fileOutputStreamOpenFileOutput != null) {
                            fileOutputStreamOpenFileOutput.close();
                        }
                    } catch (Exception e2) {
                        Log.w("AppLocalesStorageHelper", "Storing App Locales : Failed to persist app-locales: ".concat(str), e2);
                        if (fileOutputStreamOpenFileOutput != null) {
                            fileOutputStreamOpenFileOutput.close();
                        }
                    }
                } catch (Throwable th) {
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException unused) {
                        }
                    }
                    throw th;
                }
            } catch (IOException unused2) {
            }
        } catch (FileNotFoundException unused3) {
            Log.w("AppLocalesStorageHelper", "Storing App Locales : FileNotFoundException: Cannot open file androidx.appcompat.app.AppCompatDelegate.application_locales_record_file for writing ");
        }
    }

    public static byte[] Q(InputStream inputStream, int i2) throws IOException {
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i3, i2 - i3);
            if (i4 < 0) {
                throw new IllegalStateException(hk.g(i2, "Not enough bytes to read: "));
            }
            i3 += i4;
        }
        return bArr;
    }

    public static int[] R(ByteArrayInputStream byteArrayInputStream, int i2) {
        int[] iArr = new int[i2];
        int iY = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            iY += (int) Y(byteArrayInputStream, 2);
            iArr[i3] = iY;
        }
        return iArr;
    }

    public static byte[] S(FileInputStream fileInputStream, int i2, int i3) {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i3];
            byte[] bArr2 = new byte[2048];
            int i4 = 0;
            int iInflate = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i4 < i2) {
                int i5 = fileInputStream.read(bArr2);
                if (i5 < 0) {
                    throw new IllegalStateException("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i2 + " bytes");
                }
                inflater.setInput(bArr2, 0, i5);
                try {
                    iInflate += inflater.inflate(bArr, iInflate, i3 - iInflate);
                    i4 += i5;
                } catch (DataFormatException e2) {
                    throw new IllegalStateException(e2.getMessage());
                }
            }
            if (i4 == i2) {
                if (inflater.finished()) {
                    return bArr;
                }
                throw new IllegalStateException("Inflater did not finish");
            }
            throw new IllegalStateException("Didn't read enough bytes during decompression. expected=" + i2 + " actual=" + i4);
        } finally {
            inflater.end();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        r2 = r4.getAttributeValue(null, "application_locales");
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0045 A[EXC_TOP_SPLITTER, PHI: r2
      0x0045: PHI (r2v2 java.lang.String) = (r2v0 java.lang.String), (r2v4 java.lang.String) binds: [B:25:0x004e, B:21:0x0043] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String T(android.content.Context r9) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = "AppLocalesStorageHelper"
            java.lang.String r2 = ""
            java.io.FileInputStream r3 = r9.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L6b
            org.xmlpull.v1.XmlPullParser r4 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            java.lang.String r5 = "UTF-8"
            r4.setInput(r3, r5)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            int r5 = r4.getDepth()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
        L17:
            int r6 = r4.next()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            r7 = 1
            if (r6 == r7) goto L43
            r7 = 3
            if (r6 != r7) goto L2a
            int r8 = r4.getDepth()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            if (r8 <= r5) goto L43
            goto L2a
        L28:
            r9 = move-exception
            goto L65
        L2a:
            if (r6 == r7) goto L17
            r7 = 4
            if (r6 != r7) goto L30
            goto L17
        L30:
            java.lang.String r6 = r4.getName()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            java.lang.String r7 = "locales"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            if (r6 == 0) goto L17
            java.lang.String r5 = "application_locales"
            r6 = 0
            java.lang.String r2 = r4.getAttributeValue(r6, r5)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
        L43:
            if (r3 == 0) goto L51
        L45:
            r3.close()     // Catch: java.io.IOException -> L51
            goto L51
        L49:
            java.lang.String r4 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r4)     // Catch: java.lang.Throwable -> L28
            if (r3 == 0) goto L51
            goto L45
        L51:
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L61
            java.lang.String r9 = "Reading app Locales : Locales read from file: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file , appLocales: "
            java.lang.String r9 = r9.concat(r2)
            android.util.Log.d(r1, r9)
            goto L64
        L61:
            r9.deleteFile(r0)
        L64:
            return r2
        L65:
            if (r3 == 0) goto L6a
            r3.close()     // Catch: java.io.IOException -> L6a
        L6a:
            throw r9
        L6b:
            java.lang.String r9 = "Reading app Locales : Locales record file not found: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jq0.T(android.content.Context):java.lang.String");
    }

    public static pk[] U(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, pk[] pkVarArr) throws IOException {
        byte[] bArr3 = nv0.o;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, nv0.p)) {
                throw new IllegalStateException("Unsupported meta version");
            }
            int iY = (int) Y(fileInputStream, 2);
            byte[] bArrS = S(fileInputStream, (int) Y(fileInputStream, 4), (int) Y(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                throw new IllegalStateException("Content found after the end of file");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrS);
            try {
                pk[] pkVarArrW = W(byteArrayInputStream, bArr2, iY, pkVarArr);
                byteArrayInputStream.close();
                return pkVarArrW;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(nv0.j, bArr2)) {
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (!Arrays.equals(bArr, bArr3)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int iY2 = (int) Y(fileInputStream, 1);
        byte[] bArrS2 = S(fileInputStream, (int) Y(fileInputStream, 4), (int) Y(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrS2);
        try {
            pk[] pkVarArrV = V(byteArrayInputStream2, iY2, pkVarArr);
            byteArrayInputStream2.close();
            return pkVarArrV;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static pk[] V(ByteArrayInputStream byteArrayInputStream, int i2, pk[] pkVarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new pk[0];
        }
        if (i2 != pkVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i2];
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int iY = (int) Y(byteArrayInputStream, 2);
            iArr[i3] = (int) Y(byteArrayInputStream, 2);
            strArr[i3] = new String(Q(byteArrayInputStream, iY), StandardCharsets.UTF_8);
        }
        for (int i4 = 0; i4 < i2; i4++) {
            pk pkVar = pkVarArr[i4];
            if (!pkVar.b.equals(strArr[i4])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i5 = iArr[i4];
            pkVar.e = i5;
            pkVar.h = R(byteArrayInputStream, i5);
        }
        return pkVarArr;
    }

    public static pk[] W(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i2, pk[] pkVarArr) throws IOException {
        if (byteArrayInputStream.available() == 0) {
            return new pk[0];
        }
        if (i2 != pkVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            Y(byteArrayInputStream, 2);
            String str = new String(Q(byteArrayInputStream, (int) Y(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long jY = Y(byteArrayInputStream, 4);
            int iY = (int) Y(byteArrayInputStream, 2);
            pk pkVar = null;
            if (pkVarArr.length > 0) {
                int iIndexOf = str.indexOf("!");
                if (iIndexOf < 0) {
                    iIndexOf = str.indexOf(":");
                }
                String strSubstring = iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
                int i4 = 0;
                while (true) {
                    if (i4 >= pkVarArr.length) {
                        break;
                    }
                    if (pkVarArr[i4].b.equals(strSubstring)) {
                        pkVar = pkVarArr[i4];
                        break;
                    }
                    i4++;
                }
            }
            if (pkVar == null) {
                throw new IllegalStateException("Missing profile key: ".concat(str));
            }
            pkVar.d = jY;
            int[] iArrR = R(byteArrayInputStream, iY);
            if (Arrays.equals(bArr, nv0.n)) {
                pkVar.e = iY;
                pkVar.h = iArrR;
            }
        }
        return pkVarArr;
    }

    public static pk[] X(FileInputStream fileInputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, nv0.k)) {
            throw new IllegalStateException("Unsupported version");
        }
        int iY = (int) Y(fileInputStream, 1);
        byte[] bArrS = S(fileInputStream, (int) Y(fileInputStream, 4), (int) Y(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrS);
        try {
            pk[] pkVarArrZ = Z(byteArrayInputStream, str, iY);
            byteArrayInputStream.close();
            return pkVarArrZ;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long Y(InputStream inputStream, int i2) throws IOException {
        byte[] bArrQ = Q(inputStream, i2);
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j2 += ((long) (bArrQ[i3] & 255)) << (i3 * 8);
        }
        return j2;
    }

    public static pk[] Z(ByteArrayInputStream byteArrayInputStream, String str, int i2) throws IOException {
        TreeMap treeMap;
        if (byteArrayInputStream.available() == 0) {
            return new pk[0];
        }
        pk[] pkVarArr = new pk[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int iY = (int) Y(byteArrayInputStream, 2);
            int iY2 = (int) Y(byteArrayInputStream, 2);
            pkVarArr[i3] = new pk(str, new String(Q(byteArrayInputStream, iY), StandardCharsets.UTF_8), Y(byteArrayInputStream, 4), iY2, (int) Y(byteArrayInputStream, 4), (int) Y(byteArrayInputStream, 4), new int[iY2], new TreeMap());
        }
        for (int i4 = 0; i4 < i2; i4++) {
            pk pkVar = pkVarArr[i4];
            int iAvailable = byteArrayInputStream.available() - pkVar.f;
            int iY3 = 0;
            while (true) {
                int iAvailable2 = byteArrayInputStream.available();
                treeMap = pkVar.i;
                if (iAvailable2 <= iAvailable) {
                    break;
                }
                iY3 += (int) Y(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(iY3), 1);
                for (int iY4 = (int) Y(byteArrayInputStream, 2); iY4 > 0; iY4--) {
                    Y(byteArrayInputStream, 2);
                    int iY5 = (int) Y(byteArrayInputStream, 1);
                    if (iY5 != 6 && iY5 != 7) {
                        while (iY5 > 0) {
                            Y(byteArrayInputStream, 1);
                            for (int iY6 = (int) Y(byteArrayInputStream, 1); iY6 > 0; iY6--) {
                                Y(byteArrayInputStream, 2);
                            }
                            iY5--;
                        }
                    }
                }
            }
            if (byteArrayInputStream.available() != iAvailable) {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
            pkVar.h = R(byteArrayInputStream, pkVar.e);
            int i5 = pkVar.g;
            BitSet bitSetValueOf = BitSet.valueOf(Q(byteArrayInputStream, (((i5 * 2) + 7) & (-8)) / 8));
            for (int i6 = 0; i6 < i5; i6++) {
                int i7 = bitSetValueOf.get(i6) ? 2 : 0;
                if (bitSetValueOf.get(i6 + i5)) {
                    i7 |= 4;
                }
                if (i7 != 0) {
                    Integer num = (Integer) treeMap.get(Integer.valueOf(i6));
                    if (num == null) {
                        num = 0;
                    }
                    treeMap.put(Integer.valueOf(i6), Integer.valueOf(i7 | num.intValue()));
                }
            }
        }
        return pkVarArr;
    }

    public static final void a0(String str) {
        System.err.println("SLF4J: " + str);
    }

    public static final void b0(ma maVar, fh fhVar, boolean z) {
        Object obj = ma.h.get(maVar);
        Throwable thD = maVar.d(obj);
        Object objM3constructorimpl = Result.m3constructorimpl(thD != null ? a.a(thD) : maVar.e(obj));
        if (!z) {
            fhVar.resumeWith(objM3constructorimpl);
            return;
        }
        oy.h(fhVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
        yk ykVar = (yk) fhVar;
        ContinuationImpl continuationImpl = ykVar.f;
        nh context = continuationImpl.getContext();
        Object objC = wn0.c(context, ykVar.h);
        if (objC != wn0.a) {
            xi.T(continuationImpl, context);
        }
        try {
            continuationImpl.resumeWith(objM3constructorimpl);
        } finally {
            wn0.a(context, objC);
        }
    }

    public static boolean c(pb0[] pb0VarArr, pb0[] pb0VarArr2) {
        if (pb0VarArr == null || pb0VarArr2 == null || pb0VarArr.length != pb0VarArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < pb0VarArr.length; i2++) {
            pb0 pb0Var = pb0VarArr[i2];
            char c2 = pb0Var.a;
            pb0 pb0Var2 = pb0VarArr2[i2];
            if (c2 != pb0Var2.a || pb0Var.b.length != pb0Var2.b.length) {
                return false;
            }
        }
        return true;
    }

    public static void c0(AppBarLayout appBarLayout, float f2) {
        int integer = appBarLayout.getResources().getInteger(R$integer.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j2 = integer;
        stateListAnimator.addState(new int[]{R.attr.state_enabled, R$attr.state_liftable, -R$attr.state_lifted}, ObjectAnimator.ofFloat(appBarLayout, "elevation", 0.0f).setDuration(j2));
        stateListAnimator.addState(new int[]{R.attr.state_enabled}, ObjectAnimator.ofFloat(appBarLayout, "elevation", f2).setDuration(j2));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(appBarLayout, "elevation", 0.0f).setDuration(0L));
        appBarLayout.setStateListAnimator(stateListAnimator);
    }

    public static void d(int i2) {
        if (2 > i2 || i2 >= 37) {
            throw new IllegalArgumentException("radix " + i2 + " was not in valid range " + new my(2, 36, 1));
        }
    }

    public static void d0(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            uo0.a(view, charSequence);
            return;
        }
        wo0 wo0Var = wo0.l;
        if (wo0Var != null && wo0Var.b == view) {
            wo0.b(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new wo0(view, charSequence);
            return;
        }
        wo0 wo0Var2 = wo0.m;
        if (wo0Var2 != null && wo0Var2.b == view) {
            wo0Var2.a();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public static void e(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] f(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } finally {
            }
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    public static float[] g(float[] fArr, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int iMin = Math.min(i2, length);
        float[] fArr2 = new float[i2];
        System.arraycopy(fArr, 0, fArr2, 0, iMin);
        return fArr2;
    }

    public static boolean h(File file, Resources resources, int i2) throws Throwable {
        InputStream inputStreamOpenRawResource;
        try {
            inputStreamOpenRawResource = resources.openRawResource(i2);
        } catch (Throwable th) {
            th = th;
            inputStreamOpenRawResource = null;
        }
        try {
            boolean zI = i(file, inputStreamOpenRawResource);
            e(inputStreamOpenRawResource);
            return zI;
        } catch (Throwable th2) {
            th = th2;
            e(inputStreamOpenRawResource);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long h0(java.lang.String r23, long r24, long r26, long r28) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jq0.h0(java.lang.String, long, long, long):long");
    }

    public static boolean i(File file, InputStream inputStream) throws Throwable {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i2 = inputStream.read(bArr);
                        if (i2 == -1) {
                            e(fileOutputStream2);
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                            return true;
                        }
                        fileOutputStream2.write(bArr, 0, i2);
                    }
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
                    e(fileOutputStream);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    e(fileOutputStream);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    public static int i0(String str, int i2, int i3) {
        return (int) h0(str, i2, 1, (i3 & 8) != 0 ? Integer.MAX_VALUE : 2097150);
    }

    public static byte[] j(pk[] pkVarArr, byte[] bArr) throws IOException {
        int i2 = 0;
        int length = 0;
        for (pk pkVar : pkVarArr) {
            length += ((((pkVar.g * 2) + 7) & (-8)) / 8) + (pkVar.e * 2) + p(pkVar.a, pkVar.b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + pkVar.f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        if (Arrays.equals(bArr, nv0.l)) {
            int length2 = pkVarArr.length;
            while (i2 < length2) {
                pk pkVar2 = pkVarArr[i2];
                p0(byteArrayOutputStream, pkVar2, p(pkVar2.a, pkVar2.b, bArr));
                o0(byteArrayOutputStream, pkVar2);
                i2++;
            }
        } else {
            for (pk pkVar3 : pkVarArr) {
                p0(byteArrayOutputStream, pkVar3, p(pkVar3.a, pkVar3.b, bArr));
            }
            int length3 = pkVarArr.length;
            while (i2 < length3) {
                o0(byteArrayOutputStream, pkVarArr[i2]);
                i2++;
            }
        }
        if (byteArrayOutputStream.size() == length) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + length);
    }

    public static final Object[] j0(Collection collection) {
        int size = collection.size();
        Object[] objArr = c;
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArrCopyOf = new Object[size];
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    objArrCopyOf[i2] = it.next();
                    if (i3 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i4 = ((i3 * 3) + 1) >>> 1;
                        if (i4 <= i3) {
                            i4 = 2147483645;
                            if (i3 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                        oy.i(objArrCopyOf, "copyOf(...)");
                    } else if (!it.hasNext()) {
                        Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i3);
                        oy.i(objArrCopyOf2, "copyOf(...)");
                        return objArrCopyOf2;
                    }
                    i2 = i3;
                }
            }
        }
        return objArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0096 A[Catch: NumberFormatException -> 0x00aa, LOOP:3: B:25:0x0068->B:44:0x0096, LOOP_END, TryCatch #0 {NumberFormatException -> 0x00aa, blocks: (B:22:0x0054, B:25:0x0068, B:27:0x006e, B:31:0x007a, B:44:0x0096, B:46:0x009c, B:52:0x00b1, B:53:0x00b4), top: B:68:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009c A[Catch: NumberFormatException -> 0x00aa, TryCatch #0 {NumberFormatException -> 0x00aa, blocks: (B:22:0x0054, B:25:0x0068, B:27:0x006e, B:31:0x007a, B:44:0x0096, B:46:0x009c, B:52:0x00b1, B:53:0x00b4), top: B:68:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b1 A[Catch: NumberFormatException -> 0x00aa, TryCatch #0 {NumberFormatException -> 0x00aa, blocks: (B:22:0x0054, B:25:0x0068, B:27:0x006e, B:31:0x007a, B:44:0x0096, B:46:0x009c, B:52:0x00b1, B:53:0x00b4), top: B:68:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0095 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.pb0[] k(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jq0.k(java.lang.String):pb0[]");
    }

    public static final Object[] k0(Collection collection, Object[] objArr) {
        Object[] objArrCopyOf;
        int size = collection.size();
        int i2 = 0;
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                if (size <= objArr.length) {
                    objArrCopyOf = objArr;
                } else {
                    Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
                    oy.h(objNewInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                    objArrCopyOf = (Object[]) objNewInstance;
                }
                while (true) {
                    int i3 = i2 + 1;
                    objArrCopyOf[i2] = it.next();
                    if (i3 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i4 = ((i3 * 3) + 1) >>> 1;
                        if (i4 <= i3) {
                            i4 = 2147483645;
                            if (i3 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                        oy.i(objArrCopyOf, "copyOf(...)");
                    } else if (!it.hasNext()) {
                        if (objArrCopyOf == objArr) {
                            objArr[i3] = null;
                            return objArr;
                        }
                        Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i3);
                        oy.i(objArrCopyOf2, "copyOf(...)");
                        return objArrCopyOf2;
                    }
                    i2 = i3;
                }
            } else if (objArr.length > 0) {
                objArr[0] = null;
            }
        } else if (objArr.length > 0) {
            objArr[0] = null;
            return objArr;
        }
        return objArr;
    }

    public static Path l(String str) {
        Path path = new Path();
        try {
            pb0.b(k(str), path);
            return path;
        } catch (RuntimeException e2) {
            throw new RuntimeException("Error in parsing ".concat(str), e2);
        }
    }

    /* JADX WARN: Finally extract failed */
    public static boolean l0(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, pk[] pkVarArr) throws IOException {
        long j2;
        ArrayList arrayList;
        int length;
        byte[] bArr2 = nv0.j;
        int i2 = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = nv0.k;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] bArrJ = j(pkVarArr, bArr3);
                s0(byteArrayOutputStream, pkVarArr.length, 1);
                s0(byteArrayOutputStream, bArrJ.length, 4);
                byte[] bArrF = f(bArrJ);
                s0(byteArrayOutputStream, bArrF.length, 4);
                byteArrayOutputStream.write(bArrF);
                return true;
            }
            byte[] bArr4 = nv0.m;
            if (Arrays.equals(bArr, bArr4)) {
                s0(byteArrayOutputStream, pkVarArr.length, 1);
                for (pk pkVar : pkVarArr) {
                    int size = pkVar.i.size() * 4;
                    String strP = p(pkVar.a, pkVar.b, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    t0(byteArrayOutputStream, strP.getBytes(charset).length);
                    t0(byteArrayOutputStream, pkVar.h.length);
                    s0(byteArrayOutputStream, size, 4);
                    s0(byteArrayOutputStream, pkVar.c, 4);
                    byteArrayOutputStream.write(strP.getBytes(charset));
                    Iterator it = pkVar.i.keySet().iterator();
                    while (it.hasNext()) {
                        t0(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        t0(byteArrayOutputStream, 0);
                    }
                    for (int i3 : pkVar.h) {
                        t0(byteArrayOutputStream, i3);
                    }
                }
                return true;
            }
            byte[] bArr5 = nv0.l;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] bArrJ2 = j(pkVarArr, bArr5);
                s0(byteArrayOutputStream, pkVarArr.length, 1);
                s0(byteArrayOutputStream, bArrJ2.length, 4);
                byte[] bArrF2 = f(bArrJ2);
                s0(byteArrayOutputStream, bArrF2.length, 4);
                byteArrayOutputStream.write(bArrF2);
                return true;
            }
            byte[] bArr6 = nv0.n;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            t0(byteArrayOutputStream, pkVarArr.length);
            for (pk pkVar2 : pkVarArr) {
                String strP2 = p(pkVar2.a, pkVar2.b, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                t0(byteArrayOutputStream, strP2.getBytes(charset2).length);
                TreeMap treeMap = pkVar2.i;
                t0(byteArrayOutputStream, treeMap.size());
                t0(byteArrayOutputStream, pkVar2.h.length);
                s0(byteArrayOutputStream, pkVar2.c, 4);
                byteArrayOutputStream.write(strP2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    t0(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i4 : pkVar2.h) {
                    t0(byteArrayOutputStream, i4);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            t0(byteArrayOutputStream2, pkVarArr.length);
            int i5 = 2;
            int i6 = 2;
            for (pk pkVar3 : pkVarArr) {
                s0(byteArrayOutputStream2, pkVar3.c, 4);
                s0(byteArrayOutputStream2, pkVar3.d, 4);
                s0(byteArrayOutputStream2, pkVar3.g, 4);
                String strP3 = p(pkVar3.a, pkVar3.b, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = strP3.getBytes(charset3).length;
                t0(byteArrayOutputStream2, length2);
                i6 = i6 + 14 + length2;
                byteArrayOutputStream2.write(strP3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i6 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i6 + ", does not match actual size " + byteArray.length);
            }
            kv0 kv0Var = new kv0(1, byteArray, false);
            byteArrayOutputStream2.close();
            arrayList2.add(kv0Var);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i7 = 0;
            int i8 = 0;
            while (i7 < pkVarArr.length) {
                try {
                    pk pkVar4 = pkVarArr[i7];
                    t0(byteArrayOutputStream3, i7);
                    t0(byteArrayOutputStream3, pkVar4.e);
                    i8 = i8 + 4 + (pkVar4.e * i5);
                    int[] iArr = pkVar4.h;
                    int length3 = iArr.length;
                    int i9 = i2;
                    int i10 = i5;
                    int i11 = i9;
                    while (i11 < length3) {
                        int i12 = iArr[i11];
                        t0(byteArrayOutputStream3, i12 - i9);
                        i11++;
                        i9 = i12;
                    }
                    i7++;
                    i5 = i10;
                    i2 = 0;
                } catch (Throwable th) {
                }
            }
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i8 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i8 + ", does not match actual size " + byteArray2.length);
            }
            kv0 kv0Var2 = new kv0(3, byteArray2, true);
            byteArrayOutputStream3.close();
            arrayList2.add(kv0Var2);
            byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i13 = 0;
            int i14 = 0;
            while (i13 < pkVarArr.length) {
                try {
                    pk pkVar5 = pkVarArr[i13];
                    Iterator it3 = pkVar5.i.entrySet().iterator();
                    int iIntValue = 0;
                    while (it3.hasNext()) {
                        iIntValue |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                    try {
                        q0(byteArrayOutputStream4, iIntValue, pkVar5);
                        byte[] byteArray3 = byteArrayOutputStream4.toByteArray();
                        byteArrayOutputStream4.close();
                        byteArrayOutputStream4 = new ByteArrayOutputStream();
                        try {
                            r0(byteArrayOutputStream4, pkVar5);
                            byte[] byteArray4 = byteArrayOutputStream4.toByteArray();
                            byteArrayOutputStream4.close();
                            t0(byteArrayOutputStream3, i13);
                            int length4 = byteArray3.length + 2 + byteArray4.length;
                            int i15 = i14 + 6;
                            ArrayList arrayList4 = arrayList3;
                            s0(byteArrayOutputStream3, length4, 4);
                            t0(byteArrayOutputStream3, iIntValue);
                            byteArrayOutputStream3.write(byteArray3);
                            byteArrayOutputStream3.write(byteArray4);
                            i14 = i15 + length4;
                            i13++;
                            arrayList3 = arrayList4;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            }
            ArrayList arrayList5 = arrayList3;
            byte[] byteArray5 = byteArrayOutputStream3.toByteArray();
            if (i14 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i14 + ", does not match actual size " + byteArray5.length);
            }
            kv0 kv0Var3 = new kv0(4, byteArray5, true);
            byteArrayOutputStream3.close();
            arrayList2.add(kv0Var3);
            long j3 = 4;
            long size2 = j3 + j3 + 4 + ((long) (arrayList2.size() * 16));
            s0(byteArrayOutputStream, arrayList2.size(), 4);
            int i16 = 0;
            while (i16 < arrayList2.size()) {
                kv0 kv0Var4 = (kv0) arrayList2.get(i16);
                int i17 = kv0Var4.a;
                if (i17 == 1) {
                    j2 = 0;
                } else if (i17 == 2) {
                    j2 = 1;
                } else if (i17 == 3) {
                    j2 = 2;
                } else if (i17 == 4) {
                    j2 = 3;
                } else {
                    if (i17 != 5) {
                        throw null;
                    }
                    j2 = 4;
                }
                s0(byteArrayOutputStream, j2, 4);
                s0(byteArrayOutputStream, size2, 4);
                byte[] bArr7 = kv0Var4.b;
                if (kv0Var4.c) {
                    long length5 = bArr7.length;
                    byte[] bArrF3 = f(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(bArrF3);
                    s0(byteArrayOutputStream, bArrF3.length, 4);
                    s0(byteArrayOutputStream, length5, 4);
                    length = bArrF3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    s0(byteArrayOutputStream, bArr7.length, 4);
                    s0(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += (long) length;
                i16++;
                arrayList5 = arrayList;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i18 = 0; i18 < arrayList6.size(); i18++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i18));
            }
            return true;
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream2.close();
                throw th3;
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
                throw th3;
            }
        }
    }

    public static int m(String str, int i2, int i3, boolean z) {
        while (i2 < i3) {
            char cCharAt = str.charAt(i2);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || ('0' <= cCharAt && cCharAt < ':') || (('a' <= cCharAt && cCharAt < '{') || (('A' <= cCharAt && cCharAt < '[') || cCharAt == ':'))) == (!z)) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    public static pb0[] n(pb0[] pb0VarArr) {
        pb0[] pb0VarArr2 = new pb0[pb0VarArr.length];
        for (int i2 = 0; i2 < pb0VarArr.length; i2++) {
            pb0VarArr2[i2] = new pb0(pb0VarArr[i2]);
        }
        return pb0VarArr2;
    }

    public static final boolean o(char c2, char c3, boolean z) {
        if (c2 == c3) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c2);
        char upperCase2 = Character.toUpperCase(c3);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static void o0(ByteArrayOutputStream byteArrayOutputStream, pk pkVar) throws IOException {
        r0(byteArrayOutputStream, pkVar);
        int[] iArr = pkVar.h;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = iArr[i2];
            t0(byteArrayOutputStream, i4 - i3);
            i2++;
            i3 = i4;
        }
        int i5 = pkVar.g;
        byte[] bArr = new byte[(((i5 * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : pkVar.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            if ((iIntValue2 & 2) != 0) {
                int i6 = iIntValue / 8;
                bArr[i6] = (byte) (bArr[i6] | (1 << (iIntValue % 8)));
            }
            if ((iIntValue2 & 4) != 0) {
                int i7 = iIntValue + i5;
                int i8 = i7 / 8;
                bArr[i8] = (byte) ((1 << (i7 % 8)) | bArr[i8]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static String p(String str, String str2, byte[] bArr) {
        byte[] bArr2 = nv0.n;
        boolean zEquals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = nv0.m;
        String str3 = (zEquals || Arrays.equals(bArr, bArr3)) ? ":" : "!";
        if (str.length() <= 0) {
            if ("!".equals(str3)) {
                return str2.replace(":", "!");
            }
            if (":".equals(str3)) {
                return str2.replace("!", ":");
            }
        } else {
            if (str2.equals("classes.dex")) {
                return str;
            }
            if (str2.contains("!") || str2.contains(":")) {
                if ("!".equals(str3)) {
                    return str2.replace(":", "!");
                }
                if (":".equals(str3)) {
                    return str2.replace("!", ":");
                }
            } else if (!str2.endsWith(".apk")) {
                return hk.k(hk.l(str), (Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!", str2);
            }
        }
        return str2;
    }

    public static void p0(ByteArrayOutputStream byteArrayOutputStream, pk pkVar, String str) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        t0(byteArrayOutputStream, str.getBytes(charset).length);
        t0(byteArrayOutputStream, pkVar.e);
        s0(byteArrayOutputStream, pkVar.f, 4);
        s0(byteArrayOutputStream, pkVar.c, 4);
        s0(byteArrayOutputStream, pkVar.g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static c60 q(String str) {
        oy.j(str, "<this>");
        Matcher matcher = c60.c.matcher(str);
        if (!matcher.lookingAt()) {
            throw new IllegalArgumentException(("No subtype found for: \"" + str + '\"').toString());
        }
        String strGroup = matcher.group(1);
        oy.i(strGroup, "typeSubtype.group(1)");
        Locale locale = Locale.US;
        oy.i(locale, "US");
        oy.i(strGroup.toLowerCase(locale), "this as java.lang.String).toLowerCase(locale)");
        String strGroup2 = matcher.group(2);
        oy.i(strGroup2, "typeSubtype.group(2)");
        oy.i(strGroup2.toLowerCase(locale), "this as java.lang.String).toLowerCase(locale)");
        ArrayList arrayList = new ArrayList();
        Matcher matcher2 = c60.d.matcher(str);
        int iEnd = matcher.end();
        while (iEnd < str.length()) {
            matcher2.region(iEnd, str.length());
            if (!matcher2.lookingAt()) {
                StringBuilder sb = new StringBuilder("Parameter is not formatted correctly: \"");
                String strSubstring = str.substring(iEnd);
                oy.i(strSubstring, "this as java.lang.String).substring(startIndex)");
                sb.append(strSubstring);
                sb.append("\" for: \"");
                sb.append(str);
                sb.append('\"');
                throw new IllegalArgumentException(sb.toString().toString());
            }
            String strGroup3 = matcher2.group(1);
            if (strGroup3 == null) {
                iEnd = matcher2.end();
            } else {
                String strGroup4 = matcher2.group(2);
                if (strGroup4 == null) {
                    strGroup4 = matcher2.group(3);
                } else if (dl0.j0(strGroup4, "'", false) && dl0.e0(strGroup4, "'") && strGroup4.length() > 2) {
                    strGroup4 = strGroup4.substring(1, strGroup4.length() - 1);
                    oy.i(strGroup4, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                arrayList.add(strGroup3);
                arrayList.add(strGroup4);
                iEnd = matcher2.end();
            }
        }
        return new c60(str, (String[]) arrayList.toArray(new String[0]));
    }

    public static void q0(ByteArrayOutputStream byteArrayOutputStream, int i2, pk pkVar) throws IOException {
        int iBitCount = Integer.bitCount(i2 & (-2));
        int i3 = pkVar.g;
        byte[] bArr = new byte[(((iBitCount * i3) + 7) & (-8)) / 8];
        for (Map.Entry entry : pkVar.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            int i4 = 0;
            for (int i5 = 1; i5 <= 4; i5 <<= 1) {
                if (i5 != 1 && (i5 & i2) != 0) {
                    if ((i5 & iIntValue2) == i5) {
                        int i6 = (i4 * i3) + iIntValue;
                        int i7 = i6 / 8;
                        bArr[i7] = (byte) ((1 << (i6 % 8)) | bArr[i7]);
                    }
                    i4++;
                }
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static final int r(Cursor cursor, String str) {
        oy.j(cursor, "c");
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        int columnIndex2 = cursor.getColumnIndex("`" + str + '`');
        if (columnIndex2 >= 0) {
            return columnIndex2;
        }
        if (Build.VERSION.SDK_INT > 25 || str.length() == 0) {
            return -1;
        }
        String[] columnNames = cursor.getColumnNames();
        oy.i(columnNames, "columnNames");
        String strConcat = ".".concat(str);
        String str2 = "." + str + '`';
        int length = columnNames.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            String str3 = columnNames[i2];
            int i4 = i3 + 1;
            if (str3.length() >= str.length() + 2 && (dl0.e0(str3, strConcat) || (str3.charAt(0) == '`' && dl0.e0(str3, str2)))) {
                return i3;
            }
            i2++;
            i3 = i4;
        }
        return -1;
    }

    public static void r0(ByteArrayOutputStream byteArrayOutputStream, pk pkVar) throws IOException {
        int i2 = 0;
        for (Map.Entry entry : pkVar.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                t0(byteArrayOutputStream, iIntValue - i2);
                t0(byteArrayOutputStream, 0);
                i2 = iIntValue;
            }
        }
    }

    public static final int s(Cursor cursor, String str) {
        String string;
        oy.j(cursor, "c");
        int iR = r(cursor, str);
        if (iR >= 0) {
            return iR;
        }
        try {
            String[] columnNames = cursor.getColumnNames();
            oy.i(columnNames, "c.columnNames");
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) ExtensionRequestData.EMPTY_VALUE);
            int i2 = 0;
            for (String str2 : columnNames) {
                i2++;
                if (i2 > 1) {
                    sb.append((CharSequence) ", ");
                }
                rp0.c(sb, str2, null);
            }
            sb.append((CharSequence) ExtensionRequestData.EMPTY_VALUE);
            string = sb.toString();
            oy.i(string, "toString(...)");
        } catch (Exception e2) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", e2);
            string = "unknown";
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist. Available columns: " + string);
    }

    public static void s0(ByteArrayOutputStream byteArrayOutputStream, long j2, int i2) throws IOException {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((j2 >> (i3 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void t0(ByteArrayOutputStream byteArrayOutputStream, int i2) throws IOException {
        s0(byteArrayOutputStream, i2, 2);
    }

    public static w2 v(Context context, o2 o2Var) throws PackageManager.NameNotFoundException {
        Cursor cursorQuery;
        PackageManager packageManager = context.getPackageManager();
        Resources resources = context.getResources();
        String str = (String) o2Var.b;
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(str, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException(hk.n("No package found for authority: ", str));
        }
        String str2 = providerInfoResolveContentProvider.packageName;
        String str3 = (String) o2Var.c;
        if (!str2.equals(str3)) {
            throw new PackageManager.NameNotFoundException("Found content provider " + str + ", but package was not " + str3);
        }
        Signature[] signatureArr = packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures;
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        os osVar = d;
        Collections.sort(arrayList, osVar);
        List listR = (List) o2Var.e;
        if (listR == null) {
            listR = rp0.R(resources, 0);
        }
        int i2 = 0;
        loop1: while (true) {
            cursorQuery = null;
            if (i2 >= listR.size()) {
                providerInfoResolveContentProvider = null;
                break;
            }
            ArrayList arrayList2 = new ArrayList((Collection) listR.get(i2));
            Collections.sort(arrayList2, osVar);
            if (arrayList.size() == arrayList2.size()) {
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    if (!Arrays.equals((byte[]) arrayList.get(i3), (byte[]) arrayList2.get(i3))) {
                        break;
                    }
                }
                break loop1;
            }
            i2++;
        }
        if (providerInfoResolveContentProvider == null) {
            return new w2(1, (at[]) null);
        }
        String str4 = providerInfoResolveContentProvider.authority;
        ArrayList arrayList3 = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str4).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str4).appendPath("file").build();
        ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(uriBuild);
        try {
            String[] strArr = {"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"};
            String[] strArr2 = {(String) o2Var.d};
            if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                try {
                    cursorQuery = contentProviderClientAcquireUnstableContentProviderClient.query(uriBuild, strArr, "query = ?", strArr2, null, null);
                } catch (RemoteException e2) {
                    Log.w("FontsProvider", "Unable to query the content provider", e2);
                }
            }
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex("result_code");
                arrayList3 = new ArrayList();
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                int columnIndex3 = cursorQuery.getColumnIndex("file_id");
                int columnIndex4 = cursorQuery.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                while (cursorQuery.moveToNext()) {
                    arrayList3.add(new at(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0));
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                contentProviderClientAcquireUnstableContentProviderClient.close();
            }
            return new w2(0, (at[]) arrayList3.toArray(new at[0]));
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                contentProviderClientAcquireUnstableContentProviderClient.close();
            }
            throw th;
        }
    }

    public static final Class x(tz tzVar) {
        Class clsA = ((id) tzVar).a();
        oy.h(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return clsA;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class y(tz tzVar) {
        oy.j(tzVar, "<this>");
        Class clsA = ((id) tzVar).a();
        if (clsA.isPrimitive()) {
            String name = clsA.getName();
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.class;
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return Integer.class;
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        return Byte.class;
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return Character.class;
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        return Long.class;
                    }
                    break;
                case 3625364:
                    if (name.equals("void")) {
                        return Void.class;
                    }
                    break;
                case 64711720:
                    if (name.equals("boolean")) {
                        return Boolean.class;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.class;
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return Short.class;
                    }
                    break;
            }
        }
        return clsA;
    }

    public abstract int A();

    public abstract int B(View view);

    public abstract int C(CoordinatorLayout coordinatorLayout);

    public abstract int D();

    public float F(View view) {
        if (r) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                r = false;
            }
        }
        return view.getAlpha();
    }

    public abstract boolean G(float f2);

    public abstract boolean H(View view);

    public abstract boolean I(float f2, float f3);

    public abstract int a(ViewGroup.MarginLayoutParams marginLayoutParams);

    public abstract float b(int i2);

    public void e0(View view, float f2) {
        if (r) {
            try {
                view.setTransitionAlpha(f2);
                return;
            } catch (NoSuchMethodError unused) {
                r = false;
            }
        }
        view.setAlpha(f2);
    }

    public void f0(View view, int i2) {
        if (!q) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                p = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsBase", "fetchViewFlagsField: ");
            }
            q = true;
        }
        Field field = p;
        if (field != null) {
            try {
                p.setInt(view, i2 | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    public abstract boolean g0(View view, float f2);

    public abstract void m0(ViewGroup.MarginLayoutParams marginLayoutParams, int i2);

    public abstract void n0(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3);

    public abstract int t(ViewGroup.MarginLayoutParams marginLayoutParams);

    public abstract int u();

    public abstract int w();

    public abstract int z();
}
