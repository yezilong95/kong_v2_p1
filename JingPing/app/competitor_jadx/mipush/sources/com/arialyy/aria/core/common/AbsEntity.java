package com.arialyy.aria.core.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.work.impl.Scheduler;
import defpackage.km;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsEntity extends km implements Parcelable, Serializable {
    public long F;
    public String G;
    public int H;
    public int I;
    public String J;
    public long K;
    public String L;
    public int M;
    public long N;
    public long O;
    public int P;
    public boolean Q;
    public long R;
    public int S;

    public AbsEntity() {
        this.F = 0L;
        this.H = 0;
        this.I = Integer.MAX_VALUE;
        this.K = 0L;
        this.M = 3;
        this.N = 0L;
        this.Q = false;
        this.R = 0L;
        this.S = Scheduler.MAX_GREEDY_SCHEDULER_LIMIT;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getCompleteTime() {
        return this.O;
    }

    public String getConvertFileSize() {
        return this.L;
    }

    public String getConvertSpeed() {
        return this.G;
    }

    public long getCurrentProgress() {
        return this.N;
    }

    public int getFailNum() {
        return this.H;
    }

    public long getFileSize() {
        return this.K;
    }

    public long getId() {
        return this.rowID;
    }

    public abstract String getKey();

    public int getNetCode() {
        return this.S;
    }

    public int getPercent() {
        return this.P;
    }

    public long getSpeed() {
        return this.F;
    }

    public int getState() {
        return this.M;
    }

    public long getStopTime() {
        return this.R;
    }

    public String getStr() {
        return this.J;
    }

    public abstract int getTaskType();

    public int getTimeLeft() {
        return this.I;
    }

    public boolean isComplete() {
        return this.Q;
    }

    public void setComplete(boolean z) {
        this.Q = z;
    }

    public void setCompleteTime(long j) {
        this.O = j;
    }

    public void setConvertFileSize(String str) {
        this.L = str;
    }

    public void setConvertSpeed(String str) {
        this.G = str;
    }

    public void setCurrentProgress(long j) {
        this.N = j;
    }

    public void setFailNum(int i) {
        this.H = i;
    }

    public void setFileSize(long j) {
        this.K = j;
    }

    public void setNetCode(int i) {
        this.S = i;
    }

    public void setPercent(int i) {
        this.P = i;
    }

    public void setSpeed(long j) {
        this.F = j;
    }

    public void setState(int i) {
        this.M = i;
    }

    public void setStopTime(long j) {
        this.R = j;
    }

    public void setStr(String str) {
        this.J = str;
    }

    public void setTimeLeft(int i) {
        this.I = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.F);
        parcel.writeString(this.G);
        parcel.writeInt(this.H);
        parcel.writeString(this.J);
        parcel.writeLong(this.K);
        parcel.writeString(this.L);
        parcel.writeInt(this.M);
        parcel.writeLong(this.N);
        parcel.writeLong(this.O);
        parcel.writeInt(this.P);
        parcel.writeByte(this.Q ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.R);
    }

    public AbsEntity(Parcel parcel) {
        this.F = 0L;
        this.H = 0;
        this.I = Integer.MAX_VALUE;
        this.K = 0L;
        this.M = 3;
        this.N = 0L;
        this.Q = false;
        this.R = 0L;
        this.S = Scheduler.MAX_GREEDY_SCHEDULER_LIMIT;
        this.F = parcel.readLong();
        this.G = parcel.readString();
        this.H = parcel.readInt();
        this.J = parcel.readString();
        this.K = parcel.readLong();
        this.L = parcel.readString();
        this.M = parcel.readInt();
        this.N = parcel.readLong();
        this.O = parcel.readLong();
        this.P = parcel.readInt();
        this.Q = parcel.readByte() != 0;
        this.R = parcel.readLong();
    }
}
