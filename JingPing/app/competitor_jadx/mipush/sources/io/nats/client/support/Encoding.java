package io.nats.client.support;

import androidx.work.impl.WorkDatabaseVersions;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public abstract class Encoding {
    private static final String BASE32_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
    private static final int[] BASE32_LOOKUP;
    private static final int MASK = 31;
    private static final int SHIFT = 5;

    static {
        int[] iArr = new int[NatsJetStreamConstants.MAX_PULL_SIZE];
        BASE32_LOOKUP = iArr;
        Arrays.fill(iArr, 255);
        for (int i = 0; i < 32; i++) {
            BASE32_LOOKUP[BASE32_CHARS.charAt(i) - '0'] = i;
        }
    }

    private Encoding() {
    }

    private static void appendChar(StringBuilder sb, char c) {
        if (c == '\f') {
            sb.append("\\f");
            return;
        }
        if (c == '\r') {
            sb.append("\\r");
            return;
        }
        if (c == '\"') {
            sb.append("\\\"");
            return;
        }
        if (c == '/') {
            sb.append("\\/");
            return;
        }
        if (c == '\\') {
            sb.append("\\\\");
            return;
        }
        switch (c) {
            case '\b':
                sb.append("\\b");
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                sb.append("\\t");
                break;
            case '\n':
                sb.append("\\n");
                break;
            default:
                if (c >= ' ') {
                    sb.append(c);
                } else {
                    sb.append(String.format("\\u%04x", Integer.valueOf(c)));
                }
                break;
        }
    }

    public static byte[] base32Decode(char[] cArr) {
        byte[] bArr = new byte[(cArr.length * 5) / 8];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (char c : cArr) {
            int i4 = c - '0';
            if (i4 >= 0) {
                int[] iArr = BASE32_LOOKUP;
                if (i4 < iArr.length) {
                    i = (i << 5) | (iArr[i4] & MASK);
                    int i5 = i2 + 5;
                    if (i5 >= 8) {
                        bArr[i3] = (byte) (i >> (i2 - 3));
                        i2 -= 3;
                        i3++;
                    } else {
                        i2 = i5;
                    }
                }
            }
        }
        return bArr;
    }

    public static char[] base32Encode(byte[] bArr) {
        int length = bArr.length;
        int i = 8;
        int i2 = ((length + 7) * 8) / 5;
        char[] cArr = new char[i2];
        int i3 = bArr[0];
        int i4 = 0;
        int i5 = 1;
        while (true) {
            if (i <= 0 && i5 >= length) {
                break;
            }
            if (i < 5) {
                if (i5 < length) {
                    i3 = (i3 << 8) | (bArr[i5] & 255);
                    i += 8;
                    i5++;
                } else {
                    int i6 = 5 - i;
                    i3 <<= i6;
                    i += i6;
                }
            }
            int i7 = (i3 >> (i - 5)) & MASK;
            i -= 5;
            cArr[i4] = BASE32_CHARS.charAt(i7);
            i4++;
        }
        int i8 = i2 - 1;
        while (i8 >= 0 && cArr[i8] == 0) {
            i8--;
        }
        int i9 = i8 + 1;
        char[] cArr2 = new char[i9];
        System.arraycopy(cArr, 0, cArr2, 0, i9);
        Arrays.fill(cArr, (char) 0);
        return cArr2;
    }

    public static byte[] base64BasicDecode(byte[] bArr) {
        return Base64.getDecoder().decode(bArr);
    }

    public static String base64BasicDecodeToString(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    public static byte[] base64BasicEncode(byte[] bArr) {
        return Base64.getEncoder().encode(bArr);
    }

    public static String base64BasicEncodeToString(byte[] bArr) {
        return Base64.getEncoder().encodeToString(bArr);
    }

    @Deprecated
    public static byte[] base64Encode(byte[] bArr) {
        return base64UrlEncode(bArr);
    }

    public static byte[] base64UrlDecode(byte[] bArr) {
        return Base64.getUrlDecoder().decode(bArr);
    }

    public static String base64UrlDecodeToString(String str) {
        return new String(Base64.getUrlDecoder().decode(str));
    }

    public static byte[] base64UrlEncode(byte[] bArr) {
        return Base64.getUrlEncoder().withoutPadding().encode(bArr);
    }

    public static String base64UrlEncodeToString(byte[] bArr) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bArr);
    }

    @Deprecated
    public static String fromBase64Url(String str) {
        return base64UrlDecodeToString(str);
    }

    public static String jsonDecode(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt != '\\') {
                sb.append(cCharAt);
            } else {
                char cCharAt2 = i == length + (-1) ? '\\' : str.charAt(i + 1);
                if (cCharAt2 != '\\') {
                    if (cCharAt2 == 'b') {
                        cCharAt = '\b';
                    } else if (cCharAt2 == 'f') {
                        cCharAt = '\f';
                    } else if (cCharAt2 == 'n') {
                        cCharAt = '\n';
                    } else if (cCharAt2 == 'r') {
                        cCharAt = '\r';
                    } else if (cCharAt2 != 't') {
                        cCharAt = 'u';
                        if (cCharAt2 != 'u') {
                            cCharAt = cCharAt2;
                        } else if (i < length - 5) {
                            StringBuilder sb2 = new StringBuilder("");
                            sb2.append(str.charAt(i + 2));
                            sb2.append(str.charAt(i + 3));
                            sb2.append(str.charAt(i + 4));
                            i += 5;
                            sb2.append(str.charAt(i));
                            sb.append(Character.toChars(Integer.parseInt(sb2.toString(), 16)));
                        }
                    } else {
                        cCharAt = '\t';
                    }
                }
                i++;
                sb.append(cCharAt);
            }
            i++;
        }
        return sb.toString();
    }

    public static String jsonEncode(String str) {
        return jsonEncode(new StringBuilder(), str).toString();
    }

    @Deprecated
    public static String toBase64Url(byte[] bArr) {
        return base64UrlEncodeToString(bArr);
    }

    public static String uriDecode(String str) {
        try {
            return URLDecoder.decode(str.replace(Marker.ANY_NON_NULL_MARKER, "%2B"), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static byte[] base64BasicDecode(String str) {
        return Base64.getDecoder().decode(str);
    }

    public static String base64BasicEncodeToString(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] base64UrlDecode(String str) {
        return Base64.getUrlDecoder().decode(str);
    }

    public static String base64UrlEncodeToString(String str) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    public static StringBuilder jsonEncode(StringBuilder sb, String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            appendChar(sb, str.charAt(i));
        }
        return sb;
    }

    @Deprecated
    public static String toBase64Url(String str) {
        return base64UrlEncodeToString(str);
    }

    public static String jsonEncode(char[] cArr) {
        return jsonEncode(new StringBuilder(), cArr).toString();
    }

    public static StringBuilder jsonEncode(StringBuilder sb, char[] cArr) {
        for (char c : cArr) {
            appendChar(sb, c);
        }
        return sb;
    }
}
