package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.e2;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class IntentSenderRequest implements Parcelable {
    public static final Parcelable.Creator<IntentSenderRequest> CREATOR = new e2(6);
    public final IntentSender b;
    public final Intent c;
    public final int d;
    public final int e;

    public IntentSenderRequest(IntentSender intentSender, Intent intent, int i, int i2) {
        this.b = intentSender;
        this.c = intent;
        this.d = i;
        this.e = i2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        oy.j(parcel, "dest");
        parcel.writeParcelable(this.b, i);
        parcel.writeParcelable(this.c, i);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
    }
}
