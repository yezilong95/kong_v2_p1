package io.nats.client.support;

import io.nats.client.FetchConsumeOptions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class SerializableFetchConsumeOptions implements Serializable {
    private static final long serialVersionUID = 1;
    private transient FetchConsumeOptions fo;

    public SerializableFetchConsumeOptions() {
        setFetchConsumeOptions(FetchConsumeOptions.DEFAULT_FETCH_OPTIONS);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.fo = FetchConsumeOptions.builder().json(objectInputStream.readUTF()).build();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.fo.toJson());
    }

    public FetchConsumeOptions getFetchConsumeOptions() {
        return this.fo;
    }

    public void setFetchConsumeOptions(FetchConsumeOptions fetchConsumeOptions) {
        this.fo = fetchConsumeOptions;
    }

    public SerializableFetchConsumeOptions(FetchConsumeOptions fetchConsumeOptions) {
        setFetchConsumeOptions(fetchConsumeOptions);
    }

    public SerializableFetchConsumeOptions(FetchConsumeOptions.Builder builder) {
        setFetchConsumeOptions(builder.build());
    }
}
