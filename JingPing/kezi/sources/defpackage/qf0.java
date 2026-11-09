package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class qf0 {
    public final int version;

    public qf0(int i) {
        this.version = i;
    }

    public abstract void createAllTables(ql0 ql0Var);

    public abstract void dropAllTables(ql0 ql0Var);

    public abstract void onCreate(ql0 ql0Var);

    public abstract void onOpen(ql0 ql0Var);

    public abstract void onPostMigrate(ql0 ql0Var);

    public abstract void onPreMigrate(ql0 ql0Var);

    public abstract rf0 onValidateSchema(ql0 ql0Var);

    public void validateMigration(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        throw new UnsupportedOperationException("validateMigration is deprecated");
    }
}
