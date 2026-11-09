package org.bouncycastle.crypto.util;

import defpackage.e0;
import defpackage.j0;
import defpackage.l4;
import defpackage.la0;
import defpackage.tk;
import defpackage.tt;
import defpackage.u;
import defpackage.wk;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.SecureRandom;
import org.bouncycastle.crypto.a;

/* JADX INFO: loaded from: classes.dex */
public class JournaledAlgorithm implements tt, Serializable {
    public transient JournalingSecureRandom E;
    public transient l4 F;

    public JournaledAlgorithm(l4 l4Var, JournalingSecureRandom journalingSecureRandom) {
        if (l4Var == null) {
            throw new NullPointerException("AlgorithmIdentifier passed to JournaledAlgorithm is null");
        }
        if (journalingSecureRandom == null) {
            throw new NullPointerException("JournalingSecureRandom passed to JournaledAlgorithm is null");
        }
        this.E = journalingSecureRandom;
        this.F = l4Var;
    }

    public static JournaledAlgorithm getState(File file, SecureRandom secureRandom) throws IOException, ClassNotFoundException {
        if (file == null) {
            throw new NullPointerException("File for loading is null in JournaledAlgorithm");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            return new JournaledAlgorithm(la0.Z(bufferedInputStream), secureRandom);
        } finally {
            bufferedInputStream.close();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        a((byte[]) objectInputStream.readObject(), a.b());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public final void a(byte[] bArr, SecureRandom secureRandom) {
        j0 j0VarQ = j0.q(bArr);
        this.F = l4.g(j0VarQ.r(0));
        this.E = new JournalingSecureRandom(e0.o(j0VarQ.r(1)).E, secureRandom);
    }

    public l4 getAlgorithmIdentifier() {
        return this.F;
    }

    @Override // defpackage.tt
    public byte[] getEncoded() throws IOException {
        u uVar = new u();
        uVar.c(this.F);
        uVar.c(new tk(this.E.getFullTranscript()));
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar.getEncoded();
    }

    public JournalingSecureRandom getJournalingSecureRandom() {
        return this.E;
    }

    public void storeState(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("file for storage is null in JournaledAlgorithm");
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            storeState(fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    public JournaledAlgorithm(byte[] bArr) {
        this(bArr, a.b());
    }

    public static JournaledAlgorithm getState(InputStream inputStream, SecureRandom secureRandom) throws IOException, ClassNotFoundException {
        if (inputStream == null) {
            throw new NullPointerException("stream for loading is null in JournaledAlgorithm");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        try {
            return new JournaledAlgorithm(la0.Z(bufferedInputStream), secureRandom);
        } finally {
            bufferedInputStream.close();
        }
    }

    public void storeState(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new NullPointerException("output stream for storage is null in JournaledAlgorithm");
        }
        outputStream.write(getEncoded());
    }

    public JournaledAlgorithm(byte[] bArr, SecureRandom secureRandom) {
        if (bArr == null) {
            throw new NullPointerException("encoding passed to JournaledAlgorithm is null");
        }
        if (secureRandom == null) {
            throw new NullPointerException("random passed to JournaledAlgorithm is null");
        }
        a(bArr, secureRandom);
    }
}
