package defpackage;

import java.util.Comparator;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cz implements Comparator {
    public final /* synthetic */ int a;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (bArr.length != bArr2.length) {
                    return bArr.length - bArr2.length;
                }
                for (int i = 0; i < bArr.length; i++) {
                    byte b = bArr[i];
                    byte b2 = bArr2[i];
                    if (b != b2) {
                        return b - b2;
                    }
                }
                return 0;
            default:
                Uuid uuid = (Uuid) obj;
                Uuid uuid2 = (Uuid) obj2;
                long j = uuid.E;
                long j2 = uuid2.E;
                return j != j2 ? Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) : Long.compare(uuid.F ^ Long.MIN_VALUE, uuid2.F ^ Long.MIN_VALUE);
        }
    }
}
