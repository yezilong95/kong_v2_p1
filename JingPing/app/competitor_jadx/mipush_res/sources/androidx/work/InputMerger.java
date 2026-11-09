package androidx.work;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class InputMerger {
    private static final String TAG = Logger.tagWithPrefix("InputMerger");

    public static InputMerger fromClassName(String str) {
        try {
            return (InputMerger) Class.forName(str).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            Logger.get().error(TAG, "Trouble instantiating + " + str, e);
            return null;
        }
    }

    public abstract Data merge(List<Data> list);
}
