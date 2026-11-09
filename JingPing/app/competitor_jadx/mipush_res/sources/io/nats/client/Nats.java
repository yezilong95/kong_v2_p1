package io.nats.client;

import io.nats.client.Nats;
import io.nats.client.Options;
import io.nats.client.impl.NatsImpl;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class Nats {
    public static final String CLIENT_LANGUAGE = "java";
    public static final String CLIENT_VERSION;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0035, code lost:
    
        r3 = r2.indexOf(34);
        r0 = r2.substring(r3 + 1, r2.lastIndexOf(34)) + ".dev";
     */
    static {
        /*
            java.lang.Class<io.nats.client.Nats> r0 = io.nats.client.Nats.class
            java.lang.Package r0 = r0.getPackage()     // Catch: java.lang.Exception -> Lb
            java.lang.String r0 = r0.getImplementationVersion()     // Catch: java.lang.Exception -> Lb
            goto Lc
        Lb:
            r0 = 0
        Lc:
            if (r0 != 0) goto L56
            java.io.File r1 = new java.io.File     // Catch: java.lang.Exception -> L56
            java.lang.String r2 = "build.gradle"
            r1.<init>(r2)     // Catch: java.lang.Exception -> L56
            java.nio.file.Path r1 = defpackage.c60.e(r1)     // Catch: java.lang.Exception -> L56
            java.util.List r1 = defpackage.c60.l(r1)     // Catch: java.lang.Exception -> L56
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> L56
        L21:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Exception -> L56
            if (r2 == 0) goto L56
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Exception -> L56
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Exception -> L56
            java.lang.String r3 = "def jarVersion"
            boolean r3 = r2.startsWith(r3)     // Catch: java.lang.Exception -> L56
            if (r3 == 0) goto L21
            r1 = 34
            int r3 = r2.indexOf(r1)     // Catch: java.lang.Exception -> L56
            int r1 = r2.lastIndexOf(r1)     // Catch: java.lang.Exception -> L56
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L56
            r4.<init>()     // Catch: java.lang.Exception -> L56
            int r3 = r3 + 1
            java.lang.String r1 = r2.substring(r3, r1)     // Catch: java.lang.Exception -> L56
            r4.append(r1)     // Catch: java.lang.Exception -> L56
            java.lang.String r1 = ".dev"
            r4.append(r1)     // Catch: java.lang.Exception -> L56
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Exception -> L56
        L56:
            if (r0 != 0) goto L5a
            java.lang.String r0 = "development"
        L5a:
            io.nats.client.Nats.CLIENT_VERSION = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.Nats.<clinit>():void");
    }

    private Nats() {
    }

    public static Connection connect() throws InterruptedException, IOException {
        return createConnection(new Options.Builder().server(Options.DEFAULT_URL).build(), false);
    }

    public static void connectAsynchronously(final Options options, final boolean z) throws InterruptedException {
        if (options.getConnectionListener() == null) {
            throw new IllegalArgumentException("Connection Listener required in connectAsynchronously");
        }
        Thread thread = new Thread(new Runnable() { // from class: dg0
            @Override // java.lang.Runnable
            public final void run() {
                Nats.lambda$connectAsynchronously$0(options, z);
            }
        });
        thread.setName("NATS - async connection");
        thread.start();
    }

    public static Connection connectReconnectOnConnect() throws InterruptedException, IOException {
        return createConnection(new Options.Builder().server(Options.DEFAULT_URL).build(), true);
    }

    private static Connection createConnection(Options options, boolean z) throws InterruptedException, IOException {
        return NatsImpl.createConnection(options, z);
    }

    public static AuthHandler credentials(String str) {
        return NatsImpl.credentials(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$connectAsynchronously$0(Options options, boolean z) {
        try {
            NatsImpl.createConnection(options, z);
        } catch (Exception e) {
            options.getErrorListener().exceptionOccurred(null, e);
        }
    }

    public static AuthHandler staticCredentials(byte[] bArr) {
        return NatsImpl.staticCredentials(bArr);
    }

    public static AuthHandler credentials(String str, String str2) {
        return NatsImpl.credentials(str, str2);
    }

    public static AuthHandler staticCredentials(char[] cArr, char[] cArr2) {
        return NatsImpl.staticCredentials(cArr, cArr2);
    }

    public static Connection connect(String str) throws InterruptedException, IOException {
        return createConnection(new Options.Builder().server(str).build(), false);
    }

    public static Connection connectReconnectOnConnect(String str) throws InterruptedException, IOException {
        return createConnection(new Options.Builder().server(str).build(), true);
    }

    public static Connection connect(String str, AuthHandler authHandler) throws InterruptedException, IOException {
        return createConnection(new Options.Builder().server(str).authHandler(authHandler).build(), false);
    }

    public static Connection connectReconnectOnConnect(String str, AuthHandler authHandler) throws InterruptedException, IOException {
        return createConnection(new Options.Builder().server(str).authHandler(authHandler).build(), true);
    }

    public static Connection connect(Options options) throws InterruptedException, IOException {
        return createConnection(options, false);
    }

    public static Connection connectReconnectOnConnect(Options options) throws InterruptedException, IOException {
        return createConnection(options, true);
    }
}
