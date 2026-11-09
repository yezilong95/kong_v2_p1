package defpackage;

import android.view.MenuItem;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class ay0 implements MenuItem.OnMenuItemClickListener {
    public static final Class[] d = {MenuItem.class};
    public final /* synthetic */ int a = 0;
    public Object b;
    public Object c;

    public /* synthetic */ ay0() {
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        switch (this.a) {
            case 0:
                Method method = (Method) this.c;
                try {
                    Class<?> returnType = method.getReturnType();
                    Class<?> cls = Boolean.TYPE;
                    Object obj = this.b;
                    if (returnType == cls) {
                        return ((Boolean) method.invoke(obj, menuItem)).booleanValue();
                    }
                    method.invoke(obj, menuItem);
                    return true;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            default:
                return ((MenuItem.OnMenuItemClickListener) this.b).onMenuItemClick(((he0) this.c).i(menuItem));
        }
    }

    public ay0(he0 he0Var, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.c = he0Var;
        this.b = onMenuItemClickListener;
    }
}
