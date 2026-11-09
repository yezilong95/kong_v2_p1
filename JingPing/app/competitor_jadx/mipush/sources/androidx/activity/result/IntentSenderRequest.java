package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.t40;
import defpackage.y3;
import io.nats.client.support.ApiConstants;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class IntentSenderRequest implements Parcelable {
    public static final Parcelable.Creator<IntentSenderRequest> CREATOR = new y3(6);
    public final IntentSender E;
    public final Intent F;
    public final int G;
    public final int H;

    public IntentSenderRequest(Parcel parcel) {
        Parcelable parcelable = parcel.readParcelable(IntentSender.class.getClassLoader());
        t40.k(parcelable);
        Intent intent = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        int i = parcel.readInt();
        int i2 = parcel.readInt();
        this.E = (IntentSender) parcelable;
        this.F = intent;
        this.G = i;
        this.H = i2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        t40.n(parcel, ApiConstants.DEST);
        parcel.writeParcelable(this.E, i);
        parcel.writeParcelable(this.F, i);
        parcel.writeInt(this.G);
        parcel.writeInt(this.H);
    }
}
