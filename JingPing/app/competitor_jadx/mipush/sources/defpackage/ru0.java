package defpackage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class ru0 implements Externalizable {
    private static final long serialVersionUID = 0;
    public Map E;

    private final Object readResolve() {
        return this.E;
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) throws IOException {
        t40.n(objectInput, "input");
        byte b = objectInput.readByte();
        if (b != 0) {
            throw new InvalidObjectException(zn.j(b, "Unsupported flags value: "));
        }
        int i = objectInput.readInt();
        if (i < 0) {
            throw new InvalidObjectException("Illegal size value: " + i + '.');
        }
        MapBuilder mapBuilder = new MapBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            mapBuilder.put(objectInput.readObject(), objectInput.readObject());
        }
        this.E = mapBuilder.build();
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) throws IOException {
        t40.n(objectOutput, "output");
        objectOutput.writeByte(0);
        objectOutput.writeInt(this.E.size());
        for (Map.Entry entry : this.E.entrySet()) {
            objectOutput.writeObject(entry.getKey());
            objectOutput.writeObject(entry.getValue());
        }
    }
}
