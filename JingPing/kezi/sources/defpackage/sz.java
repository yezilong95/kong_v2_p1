package defpackage;

import java.util.List;
import java.util.Map;
import kotlin.reflect.KVisibility;

/* JADX INFO: loaded from: classes.dex */
public interface sz extends rz {
    Object call(Object... objArr);

    Object callBy(Map map);

    List getParameters();

    n00 getReturnType();

    List getTypeParameters();

    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();

    boolean isSuspend();
}
