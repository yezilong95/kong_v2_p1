package defpackage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class di0 implements Externalizable {
    private static final long serialVersionUID = 0;
    public Map b;

    private final Object readResolve() {
        return this.b;
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) throws IOException {
        oy.j(objectInput, "input");
        byte b = objectInput.readByte();
        if (b != 0) {
            throw new InvalidObjectException(hk.g(b, "Unsupported flags value: "));
        }
        int i = objectInput.readInt();
        if (i < 0) {
            throw new InvalidObjectException("Illegal size value: " + i + '.');
        }
        MapBuilder mapBuilder = new MapBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            mapBuilder.put(objectInput.readObject(), objectInput.readObject());
        }
        this.b = mapBuilder.build();
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) throws IOException {
        oy.j(objectOutput, "output");
        objectOutput.writeByte(0);
        objectOutput.writeInt(this.b.size());
        for (Map.Entry entry : this.b.entrySet()) {
            objectOutput.writeObject(entry.getKey());
            objectOutput.writeObject(entry.getValue());
        }
    }
}
