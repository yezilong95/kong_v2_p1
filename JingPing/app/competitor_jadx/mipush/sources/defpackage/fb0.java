package defpackage;

import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.NatsObjectStoreUtil;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class fb0 {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final Object g;
    public final Object h;

    public fb0(int i, int i2) {
        int i3;
        switch (i2) {
            case 1:
                this.a = i;
                if (i == 2) {
                    this.c = 3;
                } else {
                    if (i != 3 && i != 4) {
                        throw new IllegalArgumentException(zn.k(i, "K: ", " is not supported for Crystals Kyber"));
                    }
                    this.c = 2;
                }
                int i4 = i * 384;
                this.b = i4;
                int i5 = i4 + 32;
                this.d = i5;
                this.e = i4;
                this.f = i5;
                this.h = new tb(5);
                this.g = new kb0(this);
                return;
            default:
                this.g = new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
                if (i == 2) {
                    this.b = 4;
                    this.c = 4;
                    this.d = 2;
                    this.e = NatsObjectStoreUtil.DEFAULT_CHUNK_SIZE;
                } else {
                    if (i == 3) {
                        this.b = 6;
                        this.c = 5;
                        this.d = 4;
                        this.e = 524288;
                        this.a = Uuid.SIZE_BITS;
                        this.h = new i6(29);
                        this.f = (this.b * 320) + 32;
                        i3 = this.e;
                        if (i3 != 131072 && i3 != 524288) {
                            throw new RuntimeException("Wrong Dilithium Gamma1!");
                        }
                        return;
                    }
                    if (i != 5) {
                        throw new IllegalArgumentException(zn.k(i, "The mode ", "is not supported by Crystals Dilithium!"));
                    }
                    this.b = 8;
                    this.c = 7;
                    this.d = 2;
                    this.e = 524288;
                }
                this.a = 96;
                this.h = new i6(29);
                this.f = (this.b * 320) + 32;
                i3 = this.e;
                if (i3 != 131072) {
                    throw new RuntimeException("Wrong Dilithium Gamma1!");
                }
                return;
        }
    }
}
