package defpackage;

import android.content.Intent;
import android.content.IntentSender;
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
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class e2 implements Parcelable.Creator {
    public final /* synthetic */ int a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.a) {
            case 0:
                return new ActivityResult(parcel);
            case 1:
                BadgeState$State badgeState$State = new BadgeState$State();
                badgeState$State.j = 255;
                badgeState$State.l = -2;
                badgeState$State.m = -2;
                badgeState$State.n = -2;
                badgeState$State.u = Boolean.TRUE;
                badgeState$State.b = parcel.readInt();
                badgeState$State.c = (Integer) parcel.readSerializable();
                badgeState$State.d = (Integer) parcel.readSerializable();
                badgeState$State.e = (Integer) parcel.readSerializable();
                badgeState$State.f = (Integer) parcel.readSerializable();
                badgeState$State.g = (Integer) parcel.readSerializable();
                badgeState$State.h = (Integer) parcel.readSerializable();
                badgeState$State.i = (Integer) parcel.readSerializable();
                badgeState$State.j = parcel.readInt();
                badgeState$State.k = parcel.readString();
                badgeState$State.l = parcel.readInt();
                badgeState$State.m = parcel.readInt();
                badgeState$State.n = parcel.readInt();
                badgeState$State.p = parcel.readString();
                badgeState$State.q = parcel.readString();
                badgeState$State.r = parcel.readInt();
                badgeState$State.t = (Integer) parcel.readSerializable();
                badgeState$State.v = (Integer) parcel.readSerializable();
                badgeState$State.w = (Integer) parcel.readSerializable();
                badgeState$State.x = (Integer) parcel.readSerializable();
                badgeState$State.y = (Integer) parcel.readSerializable();
                badgeState$State.z = (Integer) parcel.readSerializable();
                badgeState$State.A = (Integer) parcel.readSerializable();
                badgeState$State.D = (Integer) parcel.readSerializable();
                badgeState$State.B = (Integer) parcel.readSerializable();
                badgeState$State.C = (Integer) parcel.readSerializable();
                badgeState$State.u = (Boolean) parcel.readSerializable();
                badgeState$State.o = (Locale) parcel.readSerializable();
                badgeState$State.E = (Boolean) parcel.readSerializable();
                return badgeState$State;
            case 2:
                return new DateValidatorPointBackward(parcel.readLong());
            case 3:
                return new DateValidatorPointForward(parcel.readLong());
            case 4:
                DownloadEntity downloadEntity = new DownloadEntity(parcel);
                downloadEntity.v = parcel.readString();
                downloadEntity.w = parcel.readString();
                downloadEntity.x = parcel.readString();
                downloadEntity.y = parcel.readString();
                downloadEntity.z = parcel.readString();
                downloadEntity.A = (M3U8Entity) parcel.readParcelable(M3U8Entity.class.getClassLoader());
                return downloadEntity;
            case 5:
                DownloadGroupEntity downloadGroupEntity = new DownloadGroupEntity(parcel);
                downloadGroupEntity.t = new ArrayList();
                downloadGroupEntity.q = parcel.readString();
                downloadGroupEntity.r = parcel.readString();
                downloadGroupEntity.u = parcel.createTypedArrayList(DownloadEntity.CREATOR);
                return downloadGroupEntity;
            case 6:
                oy.j(parcel, "inParcel");
                Parcelable parcelable = parcel.readParcelable(IntentSender.class.getClassLoader());
                oy.g(parcelable);
                return new IntentSenderRequest((IntentSender) parcelable, (Intent) parcel.readParcelable(Intent.class.getClassLoader()), parcel.readInt(), parcel.readInt());
            case 7:
                M3U8Entity m3U8Entity = new M3U8Entity();
                m3U8Entity.c = parcel.readString();
                m3U8Entity.d = parcel.readInt();
                m3U8Entity.e = parcel.readInt();
                m3U8Entity.f = parcel.readByte() != 0;
                m3U8Entity.g = parcel.readString();
                m3U8Entity.h = parcel.readString();
                m3U8Entity.i = parcel.readString();
                m3U8Entity.j = parcel.readString();
                m3U8Entity.k = parcel.readString();
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
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
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
                rangeDateSelector.c = null;
                rangeDateSelector.d = null;
                rangeDateSelector.e = null;
                rangeDateSelector.f = null;
                rangeDateSelector.c = (Long) parcel.readValue(Long.class.getClassLoader());
                rangeDateSelector.d = (Long) parcel.readValue(Long.class.getClassLoader());
                return rangeDateSelector;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                SingleDateSelector singleDateSelector = new SingleDateSelector();
                singleDateSelector.b = (Long) parcel.readValue(Long.class.getClassLoader());
                return singleDateSelector;
            default:
                UploadEntity uploadEntity = new UploadEntity(parcel);
                uploadEntity.w = ExtensionRequestData.EMPTY_VALUE;
                uploadEntity.v = parcel.readString();
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
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
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
