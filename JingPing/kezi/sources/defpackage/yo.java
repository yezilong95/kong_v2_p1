package defpackage;

import android.animation.ValueAnimator;
import android.util.Log;
import androidx.fragment.app.c;
import androidx.work.impl.WorkDatabaseVersions;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.uuid.Uuid;
import okhttp3.Protocol;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.java_websocket.extensions.ExtensionRequestData;
import org.slf4j.Marker;
import org.slf4j.helpers.NOPLogger;

/* JADX INFO: loaded from: classes.dex */
public class yo implements sl0, jx, ic0, ga, i90 {
    public final /* synthetic */ int b;

    public /* synthetic */ yo(int i) {
        this.b = i;
    }

    public static final String g(byte[] bArr, byte[][] bArr2, int i) {
        int i2;
        boolean z;
        int i3;
        int i4;
        int i5 = -1;
        byte[] bArr3 = PublicSuffixDatabase.e;
        int length = bArr.length;
        int i6 = 0;
        while (i6 < length) {
            int i7 = (i6 + length) / 2;
            while (i7 > i5 && bArr[i7] != 10) {
                i7 += i5;
            }
            int i8 = i7 + 1;
            int i9 = 1;
            while (true) {
                i2 = i8 + i9;
                if (bArr[i2] == 10) {
                    break;
                }
                i9++;
            }
            int i10 = i2 - i8;
            int i11 = i;
            boolean z2 = false;
            int i12 = 0;
            int i13 = 0;
            while (true) {
                if (z2) {
                    i3 = 46;
                    z = false;
                } else {
                    byte b = bArr2[i11][i12];
                    byte[] bArr4 = kq0.a;
                    int i14 = b & 255;
                    z = z2;
                    i3 = i14;
                }
                byte b2 = bArr[i8 + i13];
                byte[] bArr5 = kq0.a;
                i4 = i3 - (b2 & 255);
                if (i4 != 0) {
                    break;
                }
                i13++;
                i12++;
                if (i13 == i10) {
                    break;
                }
                if (bArr2[i11].length != i12) {
                    z2 = z;
                } else {
                    if (i11 == bArr2.length - 1) {
                        break;
                    }
                    i11++;
                    z2 = true;
                    i12 = -1;
                }
            }
            if (i4 >= 0) {
                if (i4 <= 0) {
                    int i15 = i10 - i13;
                    int length2 = bArr2[i11].length - i12;
                    int length3 = bArr2.length;
                    for (int i16 = i11 + 1; i16 < length3; i16++) {
                        length2 += bArr2[i16].length;
                    }
                    if (length2 >= i15) {
                        if (length2 <= i15) {
                            Charset charset = StandardCharsets.UTF_8;
                            oy.i(charset, "UTF_8");
                            return new String(bArr, i8, i10, charset);
                        }
                    }
                    length = i7;
                }
                i6 = i2 + 1;
            } else {
                length = i7;
            }
            i5 = -1;
        }
        return null;
    }

    public static ArrayList h(List list) {
        oy.j(list, "protocols");
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((Protocol) obj) != Protocol.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(ce.Z(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((Protocol) it.next()).toString());
        }
        return arrayList2;
    }

    public static String j(String str, int i, int i2, String str2, int i3) {
        int i4 = (i3 & 1) != 0 ? 0 : i;
        int length = (i3 & 2) != 0 ? str.length() : i2;
        boolean z = (i3 & 8) == 0;
        boolean z2 = (i3 & 16) == 0;
        boolean z3 = (i3 & 32) == 0;
        boolean z4 = (i3 & 64) == 0;
        oy.j(str, "<this>");
        int iCharCount = i4;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            int i5 = 32;
            int i6 = Uuid.SIZE_BITS;
            if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= 128 && !z4) || vk0.k0(str2, (char) iCodePointAt) || ((iCodePointAt == 37 && (!z || (z2 && !m(str, iCharCount, length)))) || (iCodePointAt == 43 && z3)))) {
                p9 p9Var = new p9();
                p9Var.S(str, i4, iCharCount);
                p9 p9Var2 = null;
                while (iCharCount < length) {
                    int iCodePointAt2 = str.codePointAt(iCharCount);
                    if (!z || (iCodePointAt2 != 9 && iCodePointAt2 != 10 && iCodePointAt2 != 12 && iCodePointAt2 != 13)) {
                        if (iCodePointAt2 == 43 && z3) {
                            p9Var.R(z ? Marker.ANY_NON_NULL_MARKER : "%2B");
                        } else if (iCodePointAt2 < i5 || iCodePointAt2 == 127 || ((iCodePointAt2 >= i6 && !z4) || vk0.k0(str2, (char) iCodePointAt2) || (iCodePointAt2 == 37 && (!z || (z2 && !m(str, iCharCount, length)))))) {
                            if (p9Var2 == null) {
                                p9Var2 = new p9();
                            }
                            p9Var2.T(iCodePointAt2);
                            while (!p9Var2.a()) {
                                byte bC = p9Var2.C();
                                p9Var.O(37);
                                char[] cArr = fx.j;
                                p9Var.O(cArr[((bC & 255) >> 4) & 15]);
                                p9Var.O(cArr[bC & 15]);
                            }
                        } else {
                            p9Var.T(iCodePointAt2);
                        }
                    }
                    iCharCount += Character.charCount(iCodePointAt2);
                    i5 = 32;
                    i6 = Uuid.SIZE_BITS;
                }
                return p9Var.H(p9Var.c, zb.a);
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        String strSubstring = str.substring(i4, length);
        oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static byte[] k(List list) {
        oy.j(list, "protocols");
        p9 p9Var = new p9();
        for (String str : h(list)) {
            p9Var.O(str.length());
            p9Var.R(str);
        }
        return p9Var.D(p9Var.c);
    }

    public static boolean l() {
        return "Dalvik".equals(System.getProperty("java.vm.name"));
    }

    public static boolean m(String str, int i, int i2) {
        int i3 = i + 2;
        return i3 < i2 && str.charAt(i) == '%' && kq0.r(str.charAt(i + 1)) != -1 && kq0.r(str.charAt(i3)) != -1;
    }

    public static String q(String str, int i, int i2, int i3) {
        int i4;
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        boolean z = (i3 & 4) == 0;
        oy.j(str, "<this>");
        int iCharCount = i;
        while (iCharCount < i2) {
            char cCharAt = str.charAt(iCharCount);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                p9 p9Var = new p9();
                p9Var.S(str, i, iCharCount);
                while (iCharCount < i2) {
                    int iCodePointAt = str.codePointAt(iCharCount);
                    if (iCodePointAt == 37 && (i4 = iCharCount + 2) < i2) {
                        int iR = kq0.r(str.charAt(iCharCount + 1));
                        int iR2 = kq0.r(str.charAt(i4));
                        if (iR == -1 || iR2 == -1) {
                            p9Var.T(iCodePointAt);
                            iCharCount += Character.charCount(iCodePointAt);
                        } else {
                            p9Var.O((iR << 4) + iR2);
                            iCharCount = Character.charCount(iCodePointAt) + i4;
                        }
                    } else if (iCodePointAt == 43 && z) {
                        p9Var.O(32);
                        iCharCount++;
                    } else {
                        p9Var.T(iCodePointAt);
                        iCharCount += Character.charCount(iCodePointAt);
                    }
                }
                return p9Var.H(p9Var.c, zb.a);
            }
            iCharCount++;
        }
        String strSubstring = str.substring(i, i2);
        oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static ArrayList r(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int iO0 = vk0.o0(str, '&', i, 4);
            if (iO0 == -1) {
                iO0 = str.length();
            }
            int iO02 = vk0.o0(str, '=', i, 4);
            if (iO02 == -1 || iO02 > iO0) {
                String strSubstring = str.substring(i, iO0);
                oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(strSubstring);
                arrayList.add(null);
            } else {
                String strSubstring2 = str.substring(i, iO02);
                oy.i(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(strSubstring2);
                String strSubstring3 = str.substring(iO02 + 1, iO0);
                oy.i(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(strSubstring3);
            }
            i = iO0 + 1;
        }
        return arrayList;
    }

    @Override // defpackage.sl0
    public tl0 a(rl0 rl0Var) {
        return new ku(rl0Var.a, rl0Var.b, rl0Var.c, rl0Var.d, rl0Var.e);
    }

    @Override // defpackage.ic0
    public void b(int i, Serializable serializable) {
        String str;
        switch (this.b) {
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                break;
            default:
                switch (i) {
                    case 1:
                        str = "RESULT_INSTALL_SUCCESS";
                        break;
                    case 2:
                        str = "RESULT_ALREADY_INSTALLED";
                        break;
                    case 3:
                        str = "RESULT_UNSUPPORTED_ART_VERSION";
                        break;
                    case 4:
                        str = "RESULT_NOT_WRITABLE";
                        break;
                    case 5:
                        str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                        break;
                    case 6:
                        str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                        break;
                    case 7:
                        str = "RESULT_IO_EXCEPTION";
                        break;
                    case 8:
                        str = "RESULT_PARSE_EXCEPTION";
                        break;
                    case WorkDatabaseVersions.VERSION_9 /* 9 */:
                    default:
                        str = ExtensionRequestData.EMPTY_VALUE;
                        break;
                    case WorkDatabaseVersions.VERSION_10 /* 10 */:
                        str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                        break;
                    case WorkDatabaseVersions.VERSION_11 /* 11 */:
                        str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                        break;
                }
                if (i == 6 || i == 7 || i == 8) {
                    Log.e("ProfileInstaller", str, (Throwable) serializable);
                } else {
                    Log.d("ProfileInstaller", str);
                }
                break;
        }
    }

    @Override // defpackage.jx
    public n30 c(String str) {
        return NOPLogger.NOP_LOGGER;
    }

    @Override // defpackage.ic0
    public void d() {
        switch (this.b) {
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                break;
            default:
                Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
                break;
        }
    }

    @Override // defpackage.ga
    public void e(IOException iOException) {
        iOException.printStackTrace();
    }

    @Override // defpackage.ga
    public void f(gf0 gf0Var) {
        Log.d("TrackScreen", "onResponse callback!");
    }

    public float i(float f, float f2) {
        return 1.0f;
    }

    public boolean n(CharSequence charSequence) {
        return false;
    }

    public yo(int[] iArr, ValueAnimator valueAnimator) {
        this.b = 22;
    }

    public yo(c cVar) {
        this.b = 2;
    }

    private final void o() {
    }

    private final void p(int i, Serializable serializable) {
    }
}
