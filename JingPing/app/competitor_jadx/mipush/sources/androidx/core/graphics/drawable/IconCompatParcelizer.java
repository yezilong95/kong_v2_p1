package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.f41;
import defpackage.g41;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(f41 f41Var) {
        IconCompat iconCompat = new IconCompat();
        int i = iconCompat.a;
        if (f41Var.e(1)) {
            i = ((g41) f41Var).e.readInt();
        }
        iconCompat.a = i;
        byte[] bArr = iconCompat.c;
        if (f41Var.e(2)) {
            Parcel parcel = ((g41) f41Var).e;
            int i2 = parcel.readInt();
            if (i2 < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i2];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.c = bArr;
        iconCompat.d = f41Var.f(iconCompat.d, 3);
        int i3 = iconCompat.e;
        if (f41Var.e(4)) {
            i3 = ((g41) f41Var).e.readInt();
        }
        iconCompat.e = i3;
        int i4 = iconCompat.f;
        if (f41Var.e(5)) {
            i4 = ((g41) f41Var).e.readInt();
        }
        iconCompat.f = i4;
        iconCompat.g = (ColorStateList) f41Var.f(iconCompat.g, 6);
        String string = iconCompat.i;
        if (f41Var.e(7)) {
            string = ((g41) f41Var).e.readString();
        }
        iconCompat.i = string;
        String string2 = iconCompat.j;
        if (f41Var.e(8)) {
            string2 = ((g41) f41Var).e.readString();
        }
        iconCompat.j = string2;
        iconCompat.h = PorterDuff.Mode.valueOf(iconCompat.i);
        switch (iconCompat.a) {
            case -1:
                Parcelable parcelable = iconCompat.d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.d;
                if (parcelable2 != null) {
                    iconCompat.b = parcelable2;
                    return iconCompat;
                }
                byte[] bArr3 = iconCompat.c;
                iconCompat.b = bArr3;
                iconCompat.a = 3;
                iconCompat.e = 0;
                iconCompat.f = bArr3.length;
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str = new String(iconCompat.c, Charset.forName("UTF-16"));
                iconCompat.b = str;
                if (iconCompat.a == 2 && iconCompat.j == null) {
                    iconCompat.j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.b = iconCompat.c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, f41 f41Var) {
        f41Var.getClass();
        iconCompat.i = iconCompat.h.name();
        switch (iconCompat.a) {
            case -1:
                iconCompat.d = (Parcelable) iconCompat.b;
                break;
            case 1:
            case 5:
                iconCompat.d = (Parcelable) iconCompat.b;
                break;
            case 2:
                iconCompat.c = ((String) iconCompat.b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.c = (byte[]) iconCompat.b;
                break;
            case 4:
            case 6:
                iconCompat.c = iconCompat.b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i = iconCompat.a;
        if (-1 != i) {
            f41Var.h(1);
            ((g41) f41Var).e.writeInt(i);
        }
        byte[] bArr = iconCompat.c;
        if (bArr != null) {
            f41Var.h(2);
            int length = bArr.length;
            Parcel parcel = ((g41) f41Var).e;
            parcel.writeInt(length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.d;
        if (parcelable != null) {
            f41Var.h(3);
            ((g41) f41Var).e.writeParcelable(parcelable, 0);
        }
        int i2 = iconCompat.e;
        if (i2 != 0) {
            f41Var.h(4);
            ((g41) f41Var).e.writeInt(i2);
        }
        int i3 = iconCompat.f;
        if (i3 != 0) {
            f41Var.h(5);
            ((g41) f41Var).e.writeInt(i3);
        }
        ColorStateList colorStateList = iconCompat.g;
        if (colorStateList != null) {
            f41Var.h(6);
            ((g41) f41Var).e.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.i;
        if (str != null) {
            f41Var.h(7);
            ((g41) f41Var).e.writeString(str);
        }
        String str2 = iconCompat.j;
        if (str2 != null) {
            f41Var.h(8);
            ((g41) f41Var).e.writeString(str2);
        }
    }
}
