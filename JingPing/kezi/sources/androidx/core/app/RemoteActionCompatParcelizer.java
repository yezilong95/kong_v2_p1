package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.ar0;
import defpackage.br0;
import defpackage.cr0;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(ar0 ar0Var) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        cr0 cr0VarG = remoteActionCompat.a;
        boolean z = true;
        if (ar0Var.e(1)) {
            cr0VarG = ar0Var.g();
        }
        remoteActionCompat.a = (IconCompat) cr0VarG;
        CharSequence charSequence = remoteActionCompat.b;
        if (ar0Var.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((br0) ar0Var).e);
        }
        remoteActionCompat.b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.c;
        if (ar0Var.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((br0) ar0Var).e);
        }
        remoteActionCompat.c = charSequence2;
        remoteActionCompat.d = (PendingIntent) ar0Var.f(remoteActionCompat.d, 4);
        boolean z2 = remoteActionCompat.e;
        if (ar0Var.e(5)) {
            z2 = ((br0) ar0Var).e.readInt() != 0;
        }
        remoteActionCompat.e = z2;
        boolean z3 = remoteActionCompat.f;
        if (!ar0Var.e(6)) {
            z = z3;
        } else if (((br0) ar0Var).e.readInt() == 0) {
            z = false;
        }
        remoteActionCompat.f = z;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, ar0 ar0Var) {
        ar0Var.getClass();
        IconCompat iconCompat = remoteActionCompat.a;
        ar0Var.h(1);
        ar0Var.i(iconCompat);
        CharSequence charSequence = remoteActionCompat.b;
        ar0Var.h(2);
        Parcel parcel = ((br0) ar0Var).e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.c;
        ar0Var.h(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.d;
        ar0Var.h(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z = remoteActionCompat.e;
        ar0Var.h(5);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = remoteActionCompat.f;
        ar0Var.h(6);
        parcel.writeInt(z2 ? 1 : 0);
    }
}
