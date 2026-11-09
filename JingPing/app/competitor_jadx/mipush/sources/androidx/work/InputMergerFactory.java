package androidx.work;

/* JADX INFO: loaded from: classes.dex */
public abstract class InputMergerFactory {
    public static InputMergerFactory getDefaultInputMergerFactory() {
        return new InputMergerFactory() { // from class: androidx.work.InputMergerFactory.1
            @Override // androidx.work.InputMergerFactory
            public InputMerger createInputMerger(String str) {
                return null;
            }
        };
    }

    public abstract InputMerger createInputMerger(String str);

    public final InputMerger createInputMergerWithDefaultFallback(String str) {
        InputMerger inputMergerCreateInputMerger = createInputMerger(str);
        return inputMergerCreateInputMerger == null ? InputMerger.fromClassName(str) : inputMergerCreateInputMerger;
    }
}
