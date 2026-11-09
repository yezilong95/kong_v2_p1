package com.arialyy.aria.core.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.work.impl.Scheduler;
import defpackage.qi;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsEntity extends qi implements Parcelable, Serializable {
    public long c;
    public String d;
    public int e;
    public int f;
    public String g;
    public long h;
    public String i;
    public int j;
    public long k;
    public long l;
    public int m;
    public boolean n;
    public long o;
    public int p;

    public AbsEntity() {
        this.c = 0L;
        this.e = 0;
        this.f = Integer.MAX_VALUE;
        this.h = 0L;
        this.j = 3;
        this.k = 0L;
        this.n = false;
        this.o = 0L;
        this.p = Scheduler.MAX_GREEDY_SCHEDULER_LIMIT;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getCompleteTime() {
        return this.l;
    }

    public String getConvertFileSize() {
        return this.i;
    }

    public String getConvertSpeed() {
        return this.d;
    }

    public long getCurrentProgress() {
        return this.k;
    }

    public int getFailNum() {
        return this.e;
    }

    public long getFileSize() {
        return this.h;
    }

    public long getId() {
        return this.rowID;
    }

    public abstract String getKey();

    public int getNetCode() {
        return this.p;
    }

    public int getPercent() {
        return this.m;
    }

    public long getSpeed() {
        return this.c;
    }

    public int getState() {
        return this.j;
    }

    public long getStopTime() {
        return this.o;
    }

    public String getStr() {
        return this.g;
    }

    public abstract int getTaskType();

    public int getTimeLeft() {
        return this.f;
    }

    public boolean isComplete() {
        return this.n;
    }

    public void setComplete(boolean z) {
        this.n = z;
    }

    public void setCompleteTime(long j) {
        this.l = j;
    }

    public void setConvertFileSize(String str) {
        this.i = str;
    }

    public void setConvertSpeed(String str) {
        this.d = str;
    }

    public void setCurrentProgress(long j) {
        this.k = j;
    }

    public void setFailNum(int i) {
        this.e = i;
    }

    public void setFileSize(long j) {
        this.h = j;
    }

    public void setNetCode(int i) {
        this.p = i;
    }

    public void setPercent(int i) {
        this.m = i;
    }

    public void setSpeed(long j) {
        this.c = j;
    }

    public void setState(int i) {
        this.j = i;
    }

    public void setStopTime(long j) {
        this.o = j;
    }

    public void setStr(String str) {
        this.g = str;
    }

    public void setTimeLeft(int i) {
        this.f = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.g);
        parcel.writeLong(this.h);
        parcel.writeString(this.i);
        parcel.writeInt(this.j);
        parcel.writeLong(this.k);
        parcel.writeLong(this.l);
        parcel.writeInt(this.m);
        parcel.writeByte(this.n ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.o);
    }

    public AbsEntity(Parcel parcel) {
        this.c = 0L;
        this.e = 0;
        this.f = Integer.MAX_VALUE;
        this.h = 0L;
        this.j = 3;
        this.k = 0L;
        this.n = false;
        this.o = 0L;
        this.p = Scheduler.MAX_GREEDY_SCHEDULER_LIMIT;
        this.c = parcel.readLong();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readLong();
        this.i = parcel.readString();
        this.j = parcel.readInt();
        this.k = parcel.readLong();
        this.l = parcel.readLong();
        this.m = parcel.readInt();
        this.n = parcel.readByte() != 0;
        this.o = parcel.readLong();
    }
}
