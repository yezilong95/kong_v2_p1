package kotlin.collections.builders;

import defpackage.qu0;
import defpackage.t40;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class SerializedCollection implements Externalizable {
    public static final qu0 Companion = new qu0();
    private static final long serialVersionUID = 0;
    public static final int tagList = 0;
    public static final int tagSet = 1;
    public Collection E;
    public final int F;

    public SerializedCollection() {
        this(EmptyList.INSTANCE, 0);
    }

    private final Object readResolve() {
        return this.E;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException {
        Collection collectionBuild;
        t40.n(objectInput, "input");
        byte b = objectInput.readByte();
        int i = b & 1;
        if ((b & (-2)) != 0) {
            throw new InvalidObjectException("Unsupported flags value: " + ((int) b) + '.');
        }
        int i2 = objectInput.readInt();
        if (i2 < 0) {
            throw new InvalidObjectException("Illegal size value: " + i2 + '.');
        }
        int i3 = 0;
        if (i == 0) {
            ListBuilder listBuilder = new ListBuilder(i2);
            while (i3 < i2) {
                listBuilder.add(objectInput.readObject());
                i3++;
            }
            collectionBuild = listBuilder.build();
        } else {
            if (i != 1) {
                throw new InvalidObjectException("Unsupported collection type tag: " + i + '.');
            }
            SetBuilder setBuilder = new SetBuilder(i2);
            while (i3 < i2) {
                setBuilder.add(objectInput.readObject());
                i3++;
            }
            collectionBuild = setBuilder.build();
        }
        this.E = collectionBuild;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        t40.n(objectOutput, "output");
        objectOutput.writeByte(this.F);
        objectOutput.writeInt(this.E.size());
        Iterator it = this.E.iterator();
        while (it.hasNext()) {
            objectOutput.writeObject(it.next());
        }
    }

    public SerializedCollection(Collection<?> collection, int i) {
        t40.n(collection, "collection");
        this.E = collection;
        this.F = i;
    }
}
