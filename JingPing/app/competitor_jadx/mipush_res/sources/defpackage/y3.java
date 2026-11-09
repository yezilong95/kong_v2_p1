package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import androidx.versionedparcelable.ParcelImpl;
import androidx.work.impl.WorkDatabaseVersions;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.DownloadGroupEntity;
import com.arialyy.aria.core.download.M3U8Entity;
import com.arialyy.aria.core.upload.UploadEntity;
import com.google.android.material.badge.BadgeState$State;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.RangeDateSelector;
import com.google.android.material.datepicker.SingleDateSelector;
import com.google.android.material.internal.ParcelableSparseBooleanArray;
import com.google.android.material.internal.ParcelableSparseIntArray;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class y3 implements Parcelable.Creator {
    public final /* synthetic */ int a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.a) {
            case 0:
                return new ActivityResult(parcel);
            case 1:
                BadgeState$State badgeState$State = new BadgeState$State();
                badgeState$State.M = 255;
                badgeState$State.O = -2;
                badgeState$State.P = -2;
                badgeState$State.Q = -2;
                badgeState$State.X = Boolean.TRUE;
                badgeState$State.E = parcel.readInt();
                badgeState$State.F = (Integer) parcel.readSerializable();
                badgeState$State.G = (Integer) parcel.readSerializable();
                badgeState$State.H = (Integer) parcel.readSerializable();
                badgeState$State.I = (Integer) parcel.readSerializable();
                badgeState$State.J = (Integer) parcel.readSerializable();
                badgeState$State.K = (Integer) parcel.readSerializable();
                badgeState$State.L = (Integer) parcel.readSerializable();
                badgeState$State.M = parcel.readInt();
                badgeState$State.N = parcel.readString();
                badgeState$State.O = parcel.readInt();
                badgeState$State.P = parcel.readInt();
                badgeState$State.Q = parcel.readInt();
                badgeState$State.S = parcel.readString();
                badgeState$State.T = parcel.readString();
                badgeState$State.U = parcel.readInt();
                badgeState$State.W = (Integer) parcel.readSerializable();
                badgeState$State.Y = (Integer) parcel.readSerializable();
                badgeState$State.Z = (Integer) parcel.readSerializable();
                badgeState$State.a0 = (Integer) parcel.readSerializable();
                badgeState$State.b0 = (Integer) parcel.readSerializable();
                badgeState$State.c0 = (Integer) parcel.readSerializable();
                badgeState$State.d0 = (Integer) parcel.readSerializable();
                badgeState$State.g0 = (Integer) parcel.readSerializable();
                badgeState$State.e0 = (Integer) parcel.readSerializable();
                badgeState$State.f0 = (Integer) parcel.readSerializable();
                badgeState$State.X = (Boolean) parcel.readSerializable();
                badgeState$State.R = (Locale) parcel.readSerializable();
                badgeState$State.h0 = (Boolean) parcel.readSerializable();
                return badgeState$State;
            case 2:
                return new DateValidatorPointBackward(parcel.readLong());
            case 3:
                return new DateValidatorPointForward(parcel.readLong());
            case 4:
                DownloadEntity downloadEntity = new DownloadEntity(parcel);
                downloadEntity.Y = parcel.readString();
                downloadEntity.Z = parcel.readString();
                downloadEntity.a0 = parcel.readString();
                downloadEntity.b0 = parcel.readString();
                downloadEntity.c0 = parcel.readString();
                downloadEntity.d0 = (M3U8Entity) parcel.readParcelable(M3U8Entity.class.getClassLoader());
                return downloadEntity;
            case 5:
                DownloadGroupEntity downloadGroupEntity = new DownloadGroupEntity(parcel);
                downloadGroupEntity.W = new ArrayList();
                downloadGroupEntity.T = parcel.readString();
                downloadGroupEntity.U = parcel.readString();
                downloadGroupEntity.X = parcel.createTypedArrayList(DownloadEntity.CREATOR);
                return downloadGroupEntity;
            case 6:
                t40.n(parcel, "inParcel");
                return new IntentSenderRequest(parcel);
            case 7:
                M3U8Entity m3U8Entity = new M3U8Entity();
                m3U8Entity.F = parcel.readString();
                m3U8Entity.G = parcel.readInt();
                m3U8Entity.H = parcel.readInt();
                m3U8Entity.I = parcel.readByte() != 0;
                m3U8Entity.J = parcel.readString();
                m3U8Entity.K = parcel.readString();
                m3U8Entity.L = parcel.readString();
                m3U8Entity.M = parcel.readString();
                m3U8Entity.N = parcel.readString();
                return m3U8Entity;
            case 8:
                return new ParcelImpl(parcel);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                int i = parcel.readInt();
                ParcelableSparseBooleanArray parcelableSparseBooleanArray = new ParcelableSparseBooleanArray(i);
                int[] iArr = new int[i];
                boolean[] zArr = new boolean[i];
                parcel.readIntArray(iArr);
                parcel.readBooleanArray(zArr);
                for (int i2 = 0; i2 < i; i2++) {
                    parcelableSparseBooleanArray.put(iArr[i2], zArr[i2]);
                }
                return parcelableSparseBooleanArray;
            case 10:
                int i3 = parcel.readInt();
                ParcelableSparseIntArray parcelableSparseIntArray = new ParcelableSparseIntArray(i3);
                int[] iArr2 = new int[i3];
                int[] iArr3 = new int[i3];
                parcel.readIntArray(iArr2);
                parcel.readIntArray(iArr3);
                for (int i4 = 0; i4 < i3; i4++) {
                    parcelableSparseIntArray.put(iArr2[i4], iArr3[i4]);
                }
                return parcelableSparseIntArray;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                RangeDateSelector rangeDateSelector = new RangeDateSelector();
                rangeDateSelector.F = null;
                rangeDateSelector.G = null;
                rangeDateSelector.H = null;
                rangeDateSelector.I = null;
                rangeDateSelector.F = (Long) parcel.readValue(Long.class.getClassLoader());
                rangeDateSelector.G = (Long) parcel.readValue(Long.class.getClassLoader());
                return rangeDateSelector;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                SingleDateSelector singleDateSelector = new SingleDateSelector();
                singleDateSelector.E = (Long) parcel.readValue(Long.class.getClassLoader());
                return singleDateSelector;
            default:
                UploadEntity uploadEntity = new UploadEntity(parcel);
                uploadEntity.Z = "";
                uploadEntity.Y = parcel.readString();
                return uploadEntity;
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        switch (this.a) {
            case 0:
                return new ActivityResult[i];
            case 1:
                return new BadgeState$State[i];
            case 2:
                return new DateValidatorPointBackward[i];
            case 3:
                return new DateValidatorPointForward[i];
            case 4:
                return new DownloadEntity[i];
            case 5:
                return new DownloadGroupEntity[i];
            case 6:
                return new IntentSenderRequest[i];
            case 7:
                return new M3U8Entity[i];
            case 8:
                return new ParcelImpl[i];
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return new ParcelableSparseBooleanArray[i];
            case 10:
                return new ParcelableSparseIntArray[i];
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                return new RangeDateSelector[i];
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                return new SingleDateSelector[i];
            default:
                return new UploadEntity[i];
        }
    }
}
