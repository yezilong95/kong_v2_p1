package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class o8 {
    public static final byte[] e = new byte[1792];
    public final CharSequence a;
    public final int b;
    public int c;
    public char d;

    static {
        for (int i = 0; i < 1792; i++) {
            e[i] = Character.getDirectionality(i);
        }
    }

    public o8(CharSequence charSequence) {
        this.a = charSequence;
        this.b = charSequence.length();
    }

    public final byte a() {
        int i = this.c - 1;
        CharSequence charSequence = this.a;
        char cCharAt = charSequence.charAt(i);
        this.d = cCharAt;
        if (Character.isLowSurrogate(cCharAt)) {
            int iCodePointBefore = Character.codePointBefore(charSequence, this.c);
            this.c -= Character.charCount(iCodePointBefore);
            return Character.getDirectionality(iCodePointBefore);
        }
        this.c--;
        char c = this.d;
        return c < 1792 ? e[c] : Character.getDirectionality(c);
    }
}
