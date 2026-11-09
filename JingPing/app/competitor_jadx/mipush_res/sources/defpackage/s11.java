package defpackage;

import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public interface s11 {
    default void a(Transition transition) {
        g(transition);
    }

    void b();

    void c(Transition transition);

    void d(Transition transition);

    void e();

    default void f(Transition transition) {
        c(transition);
    }

    void g(Transition transition);
}
