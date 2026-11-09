package io.nats.client.impl;

import io.nats.client.AuthHandler;
import io.nats.client.NKey;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/* JADX INFO: loaded from: classes.dex */
class FileAuthHandler implements AuthHandler {
    private String credsFile;
    private String jwtFile;
    private String nkeyFile;

    public FileAuthHandler(String str) {
        this.credsFile = str;
    }

    private char[] extract(CharBuffer charBuffer, int i) {
        CharBuffer charBufferAllocate = CharBuffer.allocate(charBuffer.length());
        boolean z = false;
        int i2 = 0;
        loop0: while (true) {
            int i3 = -1;
            while (charBuffer.length() > 0) {
                char c = charBuffer.get();
                i3++;
                if (c == '\n' || c == '\r') {
                    if (charBufferAllocate.position() > 0) {
                        break;
                    }
                    z = false;
                } else if (!z && !Character.isWhitespace(c)) {
                    if (c == '-' && i3 == 0) {
                        i2++;
                        z = true;
                    } else if (!z && i2 == i) {
                        charBufferAllocate.put(c);
                    }
                }
            }
            break loop0;
        }
        if (charBufferAllocate.position() == 0 && i == 1) {
            charBuffer.position(0);
            while (charBuffer.length() > 0) {
                char c2 = charBuffer.get();
                if (c2 == '\n' || c2 == '\r' || Character.isWhitespace(c2)) {
                    if (charBufferAllocate.position() > 0) {
                        break;
                    }
                } else {
                    charBufferAllocate.put(c2);
                }
            }
            charBufferAllocate.flip();
        } else {
            charBufferAllocate.flip();
        }
        char[] cArr = new char[charBufferAllocate.length()];
        charBufferAllocate.get(cArr);
        charBufferAllocate.clear();
        for (int i4 = 0; i4 < charBufferAllocate.capacity(); i4++) {
            charBufferAllocate.put((char) 0);
        }
        return cArr;
    }

    private char[] readKeyChars() throws IOException {
        String str = this.credsFile;
        if (str != null) {
            byte[] allBytes = Files.readAllBytes(Paths.get(str, new String[0]));
            CharBuffer charBufferDecode = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(allBytes));
            char[] cArrExtract = extract(charBufferDecode, 3);
            charBufferDecode.clear();
            for (int i = 0; i < charBufferDecode.capacity(); i++) {
                charBufferDecode.put((char) 0);
            }
            for (int i2 = 0; i2 < allBytes.length; i2++) {
                allBytes[i2] = 0;
            }
            return cArrExtract;
        }
        byte[] allBytes2 = Files.readAllBytes(Paths.get(this.nkeyFile, new String[0]));
        CharBuffer charBufferDecode2 = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(allBytes2));
        char[] cArrExtract2 = extract(charBufferDecode2, 1);
        charBufferDecode2.clear();
        for (int i3 = 0; i3 < charBufferDecode2.capacity(); i3++) {
            charBufferDecode2.put((char) 0);
        }
        for (int i4 = 0; i4 < allBytes2.length; i4++) {
            allBytes2[i4] = 0;
        }
        return cArrExtract2;
    }

    @Override // io.nats.client.AuthHandler
    public char[] getID() {
        try {
            NKey nKeyFromSeed = NKey.fromSeed(readKeyChars());
            char[] publicKey = nKeyFromSeed.getPublicKey();
            nKeyFromSeed.clear();
            return publicKey;
        } catch (Exception e) {
            throw new IllegalStateException("problem getting public key", e);
        }
    }

    @Override // io.nats.client.AuthHandler
    public char[] getJWT() {
        try {
            String str = this.jwtFile;
            String str2 = this.credsFile;
            if (str2 != null) {
                str = str2;
            }
            if (str == null) {
                return null;
            }
            byte[] allBytes = Files.readAllBytes(Paths.get(str, new String[0]));
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(allBytes);
            CharBuffer charBufferDecode = StandardCharsets.UTF_8.decode(byteBufferWrap);
            char[] cArrExtract = extract(charBufferDecode, 1);
            charBufferDecode.clear();
            for (int i = 0; i < charBufferDecode.capacity(); i++) {
                charBufferDecode.put((char) 0);
            }
            byteBufferWrap.clear();
            for (int i2 = 0; i2 < allBytes.length; i2++) {
                allBytes[i2] = 0;
            }
            return cArrExtract;
        } catch (Exception e) {
            throw new IllegalStateException("problem reading jwt", e);
        }
    }

    @Override // io.nats.client.AuthHandler
    public byte[] sign(byte[] bArr) {
        try {
            NKey nKeyFromSeed = NKey.fromSeed(readKeyChars());
            byte[] bArrSign = nKeyFromSeed.sign(bArr);
            nKeyFromSeed.clear();
            return bArrSign;
        } catch (Exception e) {
            throw new IllegalStateException("problem signing nonce", e);
        }
    }

    public FileAuthHandler(String str, String str2) {
        this.jwtFile = str;
        this.nkeyFile = str2;
    }
}
