package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class lp0 {
    public final int version;

    public lp0(int i) {
        this.version = i;
    }

    public abstract void createAllTables(ey0 ey0Var);

    public abstract void dropAllTables(ey0 ey0Var);

    public abstract void onCreate(ey0 ey0Var);

    public abstract void onOpen(ey0 ey0Var);

    public abstract void onPostMigrate(ey0 ey0Var);

    public abstract void onPreMigrate(ey0 ey0Var);

    public abstract mp0 onValidateSchema(ey0 ey0Var);

    public void validateMigration(ey0 ey0Var) {
        t40.n(ey0Var, "db");
        throw new UnsupportedOperationException("validateMigration is deprecated");
    }
}
