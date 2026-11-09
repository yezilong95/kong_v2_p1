package io.nats.client;

import defpackage.as;
import defpackage.bs;
import defpackage.l21;
import defpackage.tj;
import defpackage.z8;
import defpackage.zr;
import io.nats.client.support.Encoding;
import io.nats.client.support.RandomUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import org.bouncycastle.crypto.a;

/* JADX INFO: loaded from: classes.dex */
public class NKey {
    private static final int ED25519_PRIVATE_KEYSIZE = 64;
    private static final int ED25519_PUBLIC_KEYSIZE = 32;
    private static final int ED25519_SEED_SIZE = 32;
    static final int PREFIX_BYTE_ACCOUNT = 0;
    static final int PREFIX_BYTE_CLUSTER = 16;
    static final int PREFIX_BYTE_OPERATOR = 112;
    static final int PREFIX_BYTE_PRIVATE = 120;
    private static final int PREFIX_BYTE_SEED = 144;
    static final int PREFIX_BYTE_SERVER = 104;
    static final int PREFIX_BYTE_USER = 160;
    private static final int[] crc16table = {0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935, 4657, 528, 12915, 8786, 21173, 17044, 29431, 25302, 37689, 33560, 45947, 41818, 54205, 50076, 62463, 58334, 9314, 13379, 1056, 5121, 25830, 29895, 17572, 21637, 42346, 46411, 34088, 38153, 58862, 62927, 50604, 54669, 13907, 9842, 5649, 1584, 30423, 26358, 22165, 18100, 46939, 42874, 38681, 34616, 63455, 59390, 55197, 51132, 18628, 22757, 26758, 30887, 2112, 6241, 10242, 14371, 51660, 55789, 59790, 63919, 35144, 39273, 43274, 47403, 23285, 19156, 31415, 27286, 6769, 2640, 14899, 10770, 56317, 52188, 64447, 60318, 39801, 35672, 47931, 43802, 27814, 31879, 19684, 23749, 11298, 15363, 3168, 7233, 60846, 64911, 52716, 56781, 44330, 48395, 36200, 40265, 32407, 28342, 24277, 20212, 15891, 11826, 7761, 3696, 65439, 61374, 57309, 53244, 48923, 44858, 40793, 36728, 37256, 33193, 45514, 41451, 53516, 49453, 61774, 57711, 4224, 161, 12482, 8419, 20484, 16421, 28742, 24679, 33721, 37784, 41979, 46042, 49981, 54044, 58239, 62302, 689, 4752, 8947, 13010, 16949, 21012, 25207, 29270, 46570, 42443, 38312, 34185, 62830, 58703, 54572, 50445, 13538, 9411, 5280, 1153, 29798, 25671, 21540, 17413, 42971, 47098, 34713, 38840, 59231, 63358, 50973, 55100, 9939, 14066, 1681, 5808, 26199, 30326, 17941, 22068, 55628, 51565, 63758, 59695, 39368, 35305, 47498, 43435, 22596, 18533, 30726, 26663, 6336, 2273, 14466, 10403, 52093, 56156, 60223, 64286, 35833, 39896, 43963, 48026, 19061, 23124, 27191, 31254, 2801, 6864, 10931, 14994, 64814, 60687, 56684, 52557, 48554, 44427, 40424, 36297, 31782, 27655, 23652, 19525, 15522, 11395, 7392, 3265, 61215, 65342, 53085, 57212, 44955, 49082, 36825, 40952, 28183, 32310, 20053, 24180, 11923, 16050, 3793, 7920};
    private final char[] privateKeyAsSeed;
    private final char[] publicKey;
    private final Type type;

    public enum Type {
        USER(NKey.PREFIX_BYTE_USER),
        ACCOUNT(0),
        SERVER(NKey.PREFIX_BYTE_SERVER),
        OPERATOR(NKey.PREFIX_BYTE_OPERATOR),
        CLUSTER(16),
        PRIVATE(NKey.PREFIX_BYTE_PRIVATE);

        private final int prefix;

        Type(int i) {
            this.prefix = i;
        }

        public static Type fromPrefix(int i) {
            if (i == 0) {
                return ACCOUNT;
            }
            if (i == NKey.PREFIX_BYTE_SERVER) {
                return SERVER;
            }
            if (i == NKey.PREFIX_BYTE_USER) {
                return USER;
            }
            if (i == 16) {
                return CLUSTER;
            }
            if (i == NKey.PREFIX_BYTE_PRIVATE) {
                return ACCOUNT;
            }
            if (i == NKey.PREFIX_BYTE_OPERATOR) {
                return OPERATOR;
            }
            throw new IllegalArgumentException("Unknown prefix");
        }
    }

    private NKey(Type type, char[] cArr, char[] cArr2) {
        this.type = type;
        this.privateKeyAsSeed = cArr2;
        this.publicKey = cArr;
    }

    public static int crc16(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i = crc16table[((i >> 8) ^ (b & 255)) & 255] ^ ((i << 8) & 65535);
        }
        return i;
    }

    public static NKey createAccount(SecureRandom secureRandom) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        return createPair(Type.ACCOUNT, secureRandom);
    }

    public static NKey createCluster(SecureRandom secureRandom) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        return createPair(Type.CLUSTER, secureRandom);
    }

    public static NKey createOperator(SecureRandom secureRandom) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        return createPair(Type.OPERATOR, secureRandom);
    }

    private static NKey createPair(Type type, byte[] bArr) throws IOException {
        z8 z8VarG;
        if (bArr.length != 32) {
            throw new IllegalArgumentException("'buf' must have length 32");
        }
        byte[] bArr2 = new byte[32];
        System.arraycopy(bArr, 0, bArr2, 0, 32);
        synchronized (bArr2) {
            z8VarG = l21.G(bArr2);
        }
        byte[] bArr3 = new byte[32];
        l21.D(z8VarG, bArr3, 0);
        byte[] bArr4 = new byte[bArr.length + 32];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length, 32);
        return new NKey(type, null, encodeSeed(type, bArr4));
    }

    public static NKey createServer(SecureRandom secureRandom) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        return createPair(Type.SERVER, secureRandom);
    }

    public static NKey createUser(SecureRandom secureRandom) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        return createPair(Type.USER, secureRandom);
    }

    public static byte[] decode(char[] cArr) {
        byte[] bArrBase32Decode = Encoding.base32Decode(cArr);
        if (bArrBase32Decode.length < 4) {
            throw new IllegalArgumentException("Invalid encoding for source string");
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArrBase32Decode, bArrBase32Decode.length - 2, bArrBase32Decode.length);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArrBase32Decode, 0, bArrBase32Decode.length - 2);
        if (crc16(bArrCopyOfRange2) == (ByteBuffer.wrap(bArrCopyOfRange).order(ByteOrder.LITTLE_ENDIAN).getShort() & 65535)) {
            return bArrCopyOfRange2;
        }
        throw new IllegalArgumentException("CRC is invalid");
    }

    public static DecodedSeed decodeSeed(char[] cArr) {
        byte[] bArrDecode = decode(cArr);
        byte b = bArrDecode[0];
        int i = b & 248;
        int i2 = ((b & 7) << 5) | ((bArrDecode[1] & 248) >> 3);
        if (i != PREFIX_BYTE_SEED) {
            throw new IllegalArgumentException("Invalid encoding");
        }
        if (notValidPublicPrefixByte(i2)) {
            throw new IllegalArgumentException("Invalid encoded prefix byte");
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArrDecode, 2, bArrDecode.length);
        DecodedSeed decodedSeed = new DecodedSeed();
        decodedSeed.prefix = i2;
        decodedSeed.bytes = bArrCopyOfRange;
        return decodedSeed;
    }

    public static char[] encode(Type type, byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(type.prefix);
        byteArrayOutputStream.write(bArr);
        byteArrayOutputStream.write(ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort((short) crc16(byteArrayOutputStream.toByteArray())).array());
        return removePaddingAndClear(Encoding.base32Encode(byteArrayOutputStream.toByteArray()));
    }

    public static char[] encodeSeed(Type type, byte[] bArr) throws IOException {
        if (bArr.length != 64 && bArr.length != 32) {
            throw new IllegalArgumentException("Source is not the correct size for an ED25519 seed");
        }
        int i = (type.prefix >> 5) | PREFIX_BYTE_SEED;
        int i2 = (type.prefix & 31) << 3;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(i);
        byteArrayOutputStream.write(i2);
        byteArrayOutputStream.write(bArr);
        byteArrayOutputStream.write(ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort((short) crc16(byteArrayOutputStream.toByteArray())).array());
        return removePaddingAndClear(Encoding.base32Encode(byteArrayOutputStream.toByteArray()));
    }

    public static NKey fromPublicKey(char[] cArr) {
        int i = decode(cArr)[0] & 255;
        if (notValidPublicPrefixByte(i)) {
            throw new IllegalArgumentException("Not a valid public NKey");
        }
        return new NKey(Type.fromPrefix(i), cArr, null);
    }

    public static NKey fromSeed(char[] cArr) {
        DecodedSeed decodedSeedDecodeSeed = decodeSeed(cArr);
        if (decodedSeedDecodeSeed.bytes.length == 64) {
            return new NKey(Type.fromPrefix(decodedSeedDecodeSeed.prefix), null, cArr);
        }
        try {
            return createPair(Type.fromPrefix(decodedSeedDecodeSeed.prefix), decodedSeedDecodeSeed.bytes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad seed value", e);
        }
    }

    public static boolean isValidPublicAccountKey(char[] cArr) {
        return decode(Type.ACCOUNT, cArr, true) != null;
    }

    public static boolean isValidPublicClusterKey(char[] cArr) {
        return decode(Type.CLUSTER, cArr, true) != null;
    }

    public static boolean isValidPublicOperatorKey(char[] cArr) {
        return decode(Type.OPERATOR, cArr, true) != null;
    }

    public static boolean isValidPublicServerKey(char[] cArr) {
        return decode(Type.SERVER, cArr, true) != null;
    }

    public static boolean isValidPublicUserKey(char[] cArr) {
        return decode(Type.USER, cArr, true) != null;
    }

    private static boolean notValidPublicPrefixByte(int i) {
        return (i == 0 || i == 16 || i == PREFIX_BYTE_SERVER || i == PREFIX_BYTE_OPERATOR || i == PREFIX_BYTE_USER) ? false : true;
    }

    public static char[] removePaddingAndClear(char[] cArr) {
        int length = cArr.length - 1;
        while (length >= 0 && cArr[length] == '=') {
            length--;
        }
        int i = length + 1;
        char[] cArr2 = new char[i];
        System.arraycopy(cArr, 0, cArr2, 0, i);
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = 0;
        }
        return cArr2;
    }

    public void clear() {
        if (this.privateKeyAsSeed != null) {
            int i = 0;
            while (true) {
                char[] cArr = this.privateKeyAsSeed;
                if (i >= cArr.length) {
                    break;
                }
                cArr[i] = 0;
                i++;
            }
        }
        if (this.publicKey == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            char[] cArr2 = this.publicKey;
            if (i2 >= cArr2.length) {
                return;
            }
            cArr2[i2] = 0;
            i2++;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NKey)) {
            return false;
        }
        NKey nKey = (NKey) obj;
        if (this.type != nKey.type) {
            return false;
        }
        char[] cArr = this.privateKeyAsSeed;
        return cArr == null ? Arrays.equals(this.publicKey, nKey.publicKey) : Arrays.equals(cArr, nKey.privateKeyAsSeed);
    }

    public KeyPair getKeyPair() throws GeneralSecurityException, IOException {
        char[] cArr = this.privateKeyAsSeed;
        if (cArr == null) {
            throw new IllegalStateException("Public-only NKey");
        }
        DecodedSeed decodedSeedDecodeSeed = decodeSeed(cArr);
        byte[] bArr = new byte[32];
        byte[] bArr2 = new byte[32];
        System.arraycopy(decodedSeedDecodeSeed.bytes, 0, bArr, 0, 32);
        System.arraycopy(decodedSeedDecodeSeed.bytes, 32, bArr2, 0, 32);
        return new KeyPair(new PublicKeyWrapper(new as(bArr2)), new PrivateKeyWrapper(new zr(bArr)));
    }

    public char[] getPrivateKey() throws GeneralSecurityException, IOException {
        char[] cArr = this.privateKeyAsSeed;
        if (cArr == null) {
            throw new IllegalStateException("Public-only NKey");
        }
        return encode(Type.PRIVATE, decodeSeed(cArr).bytes);
    }

    public char[] getPublicKey() throws GeneralSecurityException, IOException {
        char[] cArr = this.publicKey;
        return cArr != null ? cArr : encode(this.type, getKeyPair().getPublic().getEncoded());
    }

    public char[] getSeed() {
        char[] cArr = this.privateKeyAsSeed;
        if (cArr == null) {
            throw new IllegalStateException("Public-only NKey");
        }
        DecodedSeed decodedSeedDecodeSeed = decodeSeed(cArr);
        byte[] bArr = new byte[32];
        System.arraycopy(decodedSeedDecodeSeed.bytes, 0, bArr, 0, 32);
        try {
            return encodeSeed(Type.fromPrefix(decodedSeedDecodeSeed.prefix), bArr);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create seed.", e);
        }
    }

    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        int i;
        int iHashCode;
        int i2 = this.type.prefix + 527;
        char[] cArr = this.privateKeyAsSeed;
        if (cArr == null) {
            i = i2 * 31;
            iHashCode = Arrays.hashCode(this.publicKey);
        } else {
            i = i2 * 31;
            iHashCode = Arrays.hashCode(cArr);
        }
        return iHashCode + i;
    }

    public byte[] sign(byte[] bArr) throws GeneralSecurityException, IOException {
        zr zrVar = new zr(getKeyPair().getPrivate().getEncoded());
        bs bsVar = new bs(0);
        ((tj) a.c.get()).getClass();
        bsVar.reset();
        bsVar.write(bArr, 0, bArr.length);
        return bsVar.l(zrVar);
    }

    public boolean verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException, IOException {
        as asVar;
        if (this.privateKeyAsSeed != null) {
            asVar = new as(getKeyPair().getPublic().getEncoded());
        } else {
            asVar = new as(decode(this.type, getPublicKey(), false));
        }
        bs bsVar = new bs(0);
        ((tj) a.c.get()).getClass();
        bsVar.reset();
        bsVar.write(bArr, 0, bArr.length);
        return bsVar.w(asVar, bArr2);
    }

    public static byte[] decode(Type type, char[] cArr, boolean z) {
        byte[] bArrDecode = decode(cArr);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArrDecode, 1, bArrDecode.length);
        if (Type.fromPrefix(bArrDecode[0] & 255) == type) {
            return bArrCopyOfRange;
        }
        if (z) {
            return null;
        }
        throw new IllegalArgumentException("Unexpected type");
    }

    private static NKey createPair(Type type, SecureRandom secureRandom) throws IOException {
        byte[] bArr = new byte[32];
        if (secureRandom == null) {
            RandomUtils.SRAND.nextBytes(bArr);
        } else {
            secureRandom.nextBytes(bArr);
        }
        return createPair(type, bArr);
    }
}
