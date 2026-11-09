package defpackage;

import android.widget.AbsListView;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public abstract class fq {
    public static final Field a;

    static {
        Field declaredField = null;
        try {
            declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        a = declaredField;
    }
}
