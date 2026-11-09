package com.mamimobi.videostream.model;

import android.graphics.Rect;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ScreenPoints {
    public String action;
    public CharSequence contentDesc;
    public String key;
    public Rect rect;
    public String resId;
    public CharSequence text;
    public float x;
    public float y;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScreenPoints)) {
            return false;
        }
        ScreenPoints screenPoints = (ScreenPoints) obj;
        return Objects.equals(this.action, screenPoints.action) && Float.compare(this.x, screenPoints.x) == 0 && Float.compare(this.y, screenPoints.y) == 0;
    }

    public int hashCode() {
        String str = this.action;
        return Float.hashCode(this.y) + ((Float.hashCode(this.x) + ((str != null ? str.hashCode() : 0) * 31)) * 31);
    }
}
