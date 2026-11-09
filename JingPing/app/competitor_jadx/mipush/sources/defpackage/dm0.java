package defpackage;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.util.Property;

/* JADX INFO: loaded from: classes.dex */
public abstract class dm0 {
    public static <V> PropertyValuesHolder a(Property<?, V> property, Path path) {
        return PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
    }
}
