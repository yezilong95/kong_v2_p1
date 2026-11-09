package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.f41;
import defpackage.g41;
import defpackage.h41;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(f41 f41Var) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        h41 h41VarG = remoteActionCompat.a;
        boolean z = true;
        if (f41Var.e(1)) {
            h41VarG = f41Var.g();
        }
        remoteActionCompat.a = (IconCompat) h41VarG;
        CharSequence charSequence = remoteActionCompat.b;
        if (f41Var.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((g41) f41Var).e);
        }
        remoteActionCompat.b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.c;
        if (f41Var.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((g41) f41Var).e);
        }
        remoteActionCompat.c = charSequence2;
        remoteActionCompat.d = (PendingIntent) f41Var.f(remoteActionCompat.d, 4);
        boolean z2 = remoteActionCompat.e;
        if (f41Var.e(5)) {
            z2 = ((g41) f41Var).e.readInt() != 0;
        }
        remoteActionCompat.e = z2;
        boolean z3 = remoteActionCompat.f;
        if (!f41Var.e(6)) {
            z = z3;
        } else if (((g41) f41Var).e.readInt() == 0) {
            z = false;
        }
        remoteActionCompat.f = z;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, f41 f41Var) {
        f41Var.getClass();
        IconCompat iconCompat = remoteActionCompat.a;
        f41Var.h(1);
        f41Var.i(iconCompat);
        CharSequence charSequence = remoteActionCompat.b;
        f41Var.h(2);
        Parcel parcel = ((g41) f41Var).e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.c;
        f41Var.h(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.d;
        f41Var.h(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z = remoteActionCompat.e;
        f41Var.h(5);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = remoteActionCompat.f;
        f41Var.h(6);
        parcel.writeInt(z2 ? 1 : 0);
    }
}
