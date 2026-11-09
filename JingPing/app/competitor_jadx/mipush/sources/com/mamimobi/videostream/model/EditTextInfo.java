package com.mamimobi.videostream.model;

import android.graphics.Rect;
import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public class EditTextInfo {
    public final Rect bounds;
    public final int eventType;
    public final boolean hasFocus;
    public final boolean isActive;
    public final boolean isPasswd;
    public final String packageName;
    public final String resourceName;
    public String text;
    public long timestamp = System.currentTimeMillis();

    public EditTextInfo(int i, String str, Rect rect, String str2, boolean z, String str3, boolean z2, boolean z3) {
        this.eventType = i;
        this.text = str;
        this.bounds = rect;
        this.packageName = str2;
        this.hasFocus = z;
        this.resourceName = str3;
        this.isPasswd = z2;
        this.isActive = z3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            EditTextInfo editTextInfo = (EditTextInfo) obj;
            if (this.timestamp == editTextInfo.timestamp && this.hasFocus == editTextInfo.hasFocus && this.text.equals(editTextInfo.text) && this.bounds.equals(editTextInfo.bounds) && this.packageName.equals(editTextInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.packageName.hashCode() + this.bounds.hashCode() + this.text.hashCode();
    }

    public boolean isDifferentFrom(EditTextInfo editTextInfo) {
        return (editTextInfo != null && this.text.equals(editTextInfo.text) && this.bounds.equals(editTextInfo.bounds) && this.hasFocus == editTextInfo.hasFocus) ? false : true;
    }

    public boolean isEmpty() {
        return this.text.isEmpty() && this.bounds.isEmpty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("EditTextInfo{text='");
        sb.append(this.text);
        sb.append("', bounds=");
        sb.append(this.bounds);
        sb.append(", timestamp=");
        sb.append(this.timestamp);
        sb.append(", hasFocus=");
        sb.append(this.hasFocus);
        sb.append(", packageName='");
        return zn.o(sb, this.packageName, "'}");
    }
}
