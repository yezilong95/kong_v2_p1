package defpackage;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class yy implements InvocationHandler {
    public final ArrayList a;
    public boolean b;
    public String c;

    public yy(ArrayList arrayList) {
        this.a = arrayList;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        oy.j(obj, "proxy");
        oy.j(method, "method");
        if (objArr == null) {
            objArr = new Object[0];
        }
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (oy.c(name, "supports") && oy.c(Boolean.TYPE, returnType)) {
            return Boolean.TRUE;
        }
        if (oy.c(name, "unsupported") && oy.c(Void.TYPE, returnType)) {
            this.b = true;
            return null;
        }
        boolean zC = oy.c(name, "protocols");
        ArrayList arrayList = this.a;
        if (zC && objArr.length == 0) {
            return arrayList;
        }
        if ((oy.c(name, "selectProtocol") || oy.c(name, "select")) && String.class.equals(returnType) && objArr.length == 1) {
            Object obj2 = objArr[0];
            if (obj2 instanceof List) {
                oy.h(obj2, "null cannot be cast to non-null type kotlin.collections.List<*>");
                List list = (List) obj2;
                int size = list.size();
                if (size >= 0) {
                    int i = 0;
                    while (true) {
                        Object obj3 = list.get(i);
                        oy.h(obj3, "null cannot be cast to non-null type kotlin.String");
                        String str = (String) obj3;
                        if (!arrayList.contains(str)) {
                            if (i == size) {
                                break;
                            }
                            i++;
                        } else {
                            this.c = str;
                            return str;
                        }
                    }
                }
                String str2 = (String) arrayList.get(0);
                this.c = str2;
                return str2;
            }
        }
        if ((!oy.c(name, "protocolSelected") && !oy.c(name, "selected")) || objArr.length != 1) {
            return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
        }
        Object obj4 = objArr[0];
        oy.h(obj4, "null cannot be cast to non-null type kotlin.String");
        this.c = (String) obj4;
        return null;
    }
}
