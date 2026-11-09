package io.nats.client.support;

import io.nats.client.api.ConsumerConfiguration;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class SerializableConsumerConfiguration implements Serializable {
    private static final long serialVersionUID = 1;
    private transient ConsumerConfiguration cc;

    public SerializableConsumerConfiguration() {
        setConsumerConfiguration(ConsumerConfiguration.builder().build());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.cc = ConsumerConfiguration.builder().json(objectInputStream.readUTF()).build();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.cc.toJson());
    }

    public ConsumerConfiguration getConsumerConfiguration() {
        return this.cc;
    }

    public void setConsumerConfiguration(ConsumerConfiguration consumerConfiguration) {
        this.cc = consumerConfiguration;
    }

    public SerializableConsumerConfiguration(ConsumerConfiguration consumerConfiguration) {
        setConsumerConfiguration(consumerConfiguration);
    }

    public SerializableConsumerConfiguration(ConsumerConfiguration.Builder builder) {
        setConsumerConfiguration(builder.build());
    }
}
