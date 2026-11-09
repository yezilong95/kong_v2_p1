package io.nats.client.support;

import io.nats.client.ConsumeOptions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class SerializableConsumeOptions implements Serializable {
    private static final long serialVersionUID = 1;
    private transient ConsumeOptions co;

    public SerializableConsumeOptions() {
        setConsumeOptions(ConsumeOptions.DEFAULT_CONSUME_OPTIONS);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.co = ConsumeOptions.builder().json(objectInputStream.readUTF()).build();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.co.toJson());
    }

    public ConsumeOptions getConsumeOptions() {
        return this.co;
    }

    public void setConsumeOptions(ConsumeOptions consumeOptions) {
        this.co = consumeOptions;
    }

    public SerializableConsumeOptions(ConsumeOptions consumeOptions) {
        setConsumeOptions(consumeOptions);
    }

    public SerializableConsumeOptions(ConsumeOptions.Builder builder) {
        setConsumeOptions(builder.build());
    }
}
