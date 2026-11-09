package defpackage;

import java.util.List;
import java.util.Map;
import kotlin.reflect.KVisibility;

/* JADX INFO: loaded from: classes.dex */
public interface h60 extends g60 {
    Object call(Object... objArr);

    Object callBy(Map map);

    List getParameters();

    a70 getReturnType();

    List getTypeParameters();

    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();

    boolean isSuspend();
}
