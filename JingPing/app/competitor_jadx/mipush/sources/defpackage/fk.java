package defpackage;

import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import io.nats.client.BaseConsumeOptions;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class fk extends t91 {
    public final /* synthetic */ int c;

    private final xq e() {
        int i;
        BigInteger bigIntegerA = zp0.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F");
        BigInteger bigInteger = qq.a;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(7L);
        BigInteger bigIntegerA2 = zp0.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141");
        BigInteger bigIntegerValueOf2 = BigInteger.valueOf(1L);
        BigInteger bigInteger2 = new BigInteger("7ae96a2b657c07106e64479eac3434e99cf0497512f58995c1396c28719501ee", 16);
        new BigInteger("5363ad4cc05c30e0a5261c028812645a122e22ea20816678df02967c1b23bd72", 16);
        BigInteger[] bigIntegerArr = {new BigInteger("3086d221a7d46bcde86c90e49284eb15", 16), new BigInteger("-e4437ed6010e88286f547fa90abfe4c3", 16)};
        BigInteger[] bigIntegerArr2 = {new BigInteger("114ca50f7a8e2f3f657c1108d9d44cfd8", 16), new BigInteger("3086d221a7d46bcde86c90e49284eb15", 16)};
        new BigInteger("3086d221a7d46bcde86c90e49284eb153dab", 16);
        new BigInteger("e4437ed6010e88286f547fa90abfe4c42212", 16);
        ma0.i(bigIntegerArr, "v1");
        ma0.i(bigIntegerArr2, "v2");
        xq wqVar = new wq(bigIntegerA, bigInteger, bigIntegerValueOf, bigIntegerA2, bigIntegerValueOf2, true);
        synchronized (wqVar) {
            i = wqVar.f;
        }
        wqVar.h(bigInteger2);
        if (!wqVar.p(i)) {
            throw new IllegalStateException("unsupported coordinate system");
        }
        xq xqVarA = wqVar.a();
        if (xqVarA == wqVar) {
            throw new IllegalStateException("implementation returned current curve");
        }
        synchronized (xqVarA) {
            xqVarA.f = i;
        }
        return xqVarA;
    }

    @Override // defpackage.t91
    public final xq a() {
        int i;
        int i2;
        switch (this.c) {
            case 0:
                return new ts0();
            case 1:
                BigInteger bigInteger = new BigInteger("fe0e87005b4e83761908c5131d552a850b3f58b749c37cf5b84d6768", 16);
                new BigInteger("60dcd2104c4cbc0be6eeefc2bdd610739ec34e317f9b33046c9e4788", 16);
                BigInteger[] bigIntegerArr = {new BigInteger("6b8cf07d4ca75c88957d9d670591", 16), new BigInteger("-b8adf1378a6eb73409fa6c9c637d", 16)};
                BigInteger[] bigIntegerArr2 = {new BigInteger("1243ae1b4d71613bc9f780a03690e", 16), new BigInteger("6b8cf07d4ca75c88957d9d670591", 16)};
                new BigInteger("6b8cf07d4ca75c88957d9d67059037a4", 16);
                new BigInteger("b8adf1378a6eb73409fa6c9c637ba7f5", 16);
                ma0.i(bigIntegerArr, "v1");
                ma0.i(bigIntegerArr2, "v2");
                xq vs0Var = new vs0();
                synchronized (vs0Var) {
                    i = vs0Var.f;
                }
                vs0Var.h(bigInteger);
                if (!vs0Var.p(i)) {
                    throw new IllegalStateException("unsupported coordinate system");
                }
                xq xqVarA = vs0Var.a();
                if (xqVarA == vs0Var) {
                    throw new IllegalStateException("implementation returned current curve");
                }
                synchronized (xqVarA) {
                    xqVarA.f = i;
                    break;
                }
                return xqVarA;
            case 2:
                return new xs0();
            case 3:
                return new wq(dr.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD97"), dr.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD94"), dr.a("A6"), dr.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF6C611070995AD10045841B09B761B893"), qq.b, true);
            case 4:
                return new wq(dr.a("8000000000000000000000000000000000000000000000000000000000000C99"), dr.a("8000000000000000000000000000000000000000000000000000000000000C96"), dr.a("3E1AF419A269A5F866A7D3C25C3DF80AE979259373FF2B182F49D4CE7E1BBC8B"), dr.a("800000000000000000000000000000015F700CFFF1A624E5E497161BCC8A198F"), qq.b, true);
            case 5:
                return new wq(dr.a("9B9F605F5A858107AB1EC85E6B41C8AACF846E86789051D37998F7B9022D759B"), dr.a("9B9F605F5A858107AB1EC85E6B41C8AACF846E86789051D37998F7B9022D7598"), dr.a("805A"), dr.a("9B9F605F5A858107AB1EC85E6B41C8AA582CA3511EDDFB74F02F3A6598980BB9"), qq.b, true);
            case 6:
                return new wq(dr.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFD97"), dr.a("C2173F1513981673AF4892C23035A27CE25E2013BF95AA33B22C656F277E7335"), dr.a("295F9BAE7428ED9CCC20E7C359A9D41A22FCCD9108E17BF7BA9337A6F8AE9513"), dr.a("400000000000000000000000000000000FD8CDDFC87B6635C115AF556C360C67"), qq.e, true);
            case 7:
                return new wq(dr.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDC7"), dr.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDC4"), dr.a("E8C2505DEDFC86DDC1BD0B2B6667F1DA34B82574761CB0E879BD081CFD0B6265EE3CB090F30D27614CB4574010DA90DD862EF9D4EBEE4761503190785A71C760"), dr.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF27E69532F48D89116FF22B8D4E0560609B4B38ABFAD2B85DCACDB1411F10B275"), qq.b, true);
            case 8:
                return new wq(dr.a("8000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006F"), dr.a("8000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006C"), dr.a("687D1B459DC841457E3E06CF6F5E2517B97C7D614AF138BCBF85DC806C4B289F3E965D2DB1416D217F8B276FAD1AB69C50F78BEE1FA3106EFB8CCBC7C5140116"), dr.a("800000000000000000000000000000000000000000000000000000000000000149A1EC142565A545ACFDB77BD9D40CFA8B996712101BEA0EC6346C54374F25BD"), qq.b, true);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return new wq(dr.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDC7"), dr.a("DC9203E514A721875485A529D2C722FB187BC8980EB866644DE41C68E143064546E861C0E2C9EDD92ADE71F46FCF50FF2AD97F951FDA9F2A2EB6546F39689BD3"), dr.a("B4C4EE28CEBC6C2C8AC12952CF37F16AC7EFB6A9F69F4B57FFDA2E4F0DE5ADE038CBC2FFF719D2C18DE0284B8BFEF3B52B8CC7A5F5BF0A3C8D2319A5312557E1"), dr.a("3FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC98CDBA46506AB004C33A9FF5147502CC8EDA9E7A769A12694623CEF47F023ED"), qq.e, true);
            case 10:
                return new wq(m10.a("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF"), m10.a("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC"), m10.a("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93"), m10.a("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123"), BigInteger.valueOf(1L), true);
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                return new wq(m10.a("BDB6F4FE3E8B1D9E0DA8C0D46F4C318CEFE4AFE3B6B8551F"), m10.a("BB8E5E8FBC115E139FE6A814FE48AAA6F0ADA1AA5DF91985"), m10.a("1854BEBDC31B21B7AEFC80AB0ECD10D5B1B3308E6DBF11C1"), m10.a("BDB6F4FE3E8B1D9E0DA8C0D40FC962195DFAE76F56564677"), BigInteger.valueOf(1L), true);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                BigInteger bigIntegerA = zp0.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFE56D");
                BigInteger bigInteger2 = qq.a;
                BigInteger bigIntegerValueOf = BigInteger.valueOf(5L);
                BigInteger bigIntegerA2 = zp0.a("010000000000000000000000000001DCE8D2EC6184CAF0A971769FB1F7");
                BigInteger bigIntegerValueOf2 = BigInteger.valueOf(1L);
                BigInteger bigInteger3 = new BigInteger("fe0e87005b4e83761908c5131d552a850b3f58b749c37cf5b84d6768", 16);
                new BigInteger("60dcd2104c4cbc0be6eeefc2bdd610739ec34e317f9b33046c9e4788", 16);
                BigInteger[] bigIntegerArr3 = {new BigInteger("6b8cf07d4ca75c88957d9d670591", 16), new BigInteger("-b8adf1378a6eb73409fa6c9c637d", 16)};
                BigInteger[] bigIntegerArr4 = {new BigInteger("1243ae1b4d71613bc9f780a03690e", 16), new BigInteger("6b8cf07d4ca75c88957d9d670591", 16)};
                new BigInteger("6b8cf07d4ca75c88957d9d67059037a4", 16);
                new BigInteger("b8adf1378a6eb73409fa6c9c637ba7f5", 16);
                ma0.i(bigIntegerArr3, "v1");
                ma0.i(bigIntegerArr4, "v2");
                xq wqVar = new wq(bigIntegerA, bigInteger2, bigIntegerValueOf, bigIntegerA2, bigIntegerValueOf2, true);
                synchronized (wqVar) {
                    i2 = wqVar.f;
                }
                wqVar.h(bigInteger3);
                if (!wqVar.p(i2)) {
                    throw new IllegalStateException("unsupported coordinate system");
                }
                xq xqVarA2 = wqVar.a();
                if (xqVarA2 == wqVar) {
                    throw new IllegalStateException("implementation returned current curve");
                }
                synchronized (xqVarA2) {
                    xqVarA2.f = i2;
                    break;
                }
                return xqVarA2;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                return new wq(zp0.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000001"), zp0.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFE"), zp0.a("B4050A850C04B3ABF54132565044B0B7D7BFD8BA270B39432355FFB4"), zp0.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFF16A2E0B8F03E13DD29455C5C2A3D"), BigInteger.valueOf(1L), true);
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return e();
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                return new wq(zp0.a("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFF"), zp0.a("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFC"), zp0.a("5AC635D8AA3A93E7B3EBBD55769886BC651D06B0CC53B0F63BCE3C3E27D2604B"), zp0.a("FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632551"), BigInteger.valueOf(1L), true);
            case 16:
                return new wq(zp0.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFF"), zp0.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFC"), zp0.a("B3312FA7E23EE7E4988E056BE3F82D19181D9C6EFE8141120314088F5013875AC656398D8A2ED19D2A85C8EDD3EC2AEF"), zp0.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC7634D81F4372DDF581A0DB248B0A77AECEC196ACCC52973"), BigInteger.valueOf(1L), true);
            case 17:
                return new wq(zp0.a("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"), zp0.a("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC"), zp0.a("0051953EB9618E1C9A1F929A21A0B68540EEA2DA725B99B315F3B8B489918EF109E156193951EC7E937B1652C0BD3BB1BF073573DF883D2C34F1EF451FD46B503F00"), zp0.a("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA51868783BF2F966B7FCC0148F709A5D03BB5C9B8899C47AEBB6FB71E91386409"), BigInteger.valueOf(1L), true);
            case 18:
                return new vq(113, 9, zp0.a("003088250CA6E7C7FE649CE85820F7"), zp0.a("00E8BEE4D3E2260744188BE0E9C723"), zp0.a("0100000000000000D9CCEC8A39E56F"), BigInteger.valueOf(2L));
            case 19:
                return new vq(113, 9, zp0.a("00689918DBEC7E5A0DD6DFC0AA55C7"), zp0.a("0095E9A9EC9B297BD4BF36E059184F"), zp0.a("010000000000000108789B2496AF93"), BigInteger.valueOf(2L));
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                return new vq(131, 2, 3, 8, zp0.a("07A11B09A76B562144418FF3FF8C2570B8"), zp0.a("0217C05610884B63B9C6C7291678F9D341"), zp0.a("0400000000000000023123953A9464B54D"), BigInteger.valueOf(2L));
            case 21:
                return new vq(131, 2, 3, 8, zp0.a("03E5A88919D7CAFCBF415F07C2176573B2"), zp0.a("04B8266A46C55657AC734CE38F018F2192"), zp0.a("0400000000000000016954A233049BA98F"), BigInteger.valueOf(2L));
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                return new wq(zp0.a("DB7C2ABF62E35E668076BEAD208B"), zp0.a("DB7C2ABF62E35E668076BEAD2088"), zp0.a("659EF8BA043916EEDE8911702B22"), zp0.a("DB7C2ABF62E35E7628DFAC6561C5"), BigInteger.valueOf(1L), true);
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                return new vq(163, 3, 6, 7, BigInteger.valueOf(1L), BigInteger.valueOf(1L), zp0.a("04000000000000000000020108A2E0CC0D99F8A5EF"), BigInteger.valueOf(2L));
            case 24:
                return new vq(163, 3, 6, 7, zp0.a("07B6882CAAEFA84F9554FF8428BD88E246D2782AE2"), zp0.a("0713612DCDDCB40AAB946BDA29CA91F73AF958AFD9"), zp0.a("03FFFFFFFFFFFFFFFFFFFF48AAB689C29CA710279B"), BigInteger.valueOf(2L));
            case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                return new vq(163, 3, 6, 7, BigInteger.valueOf(1L), zp0.a("020A601907B8C953CA1481EB10512F78744A3205FD"), zp0.a("040000000000000000000292FE77E70C12A4234C33"), BigInteger.valueOf(2L));
            case 26:
                return new vq(193, 15, zp0.a("0017858FEB7A98975169E171F77B4087DE098AC8A911DF7B01"), zp0.a("00FDFB49BFE6C3A89FACADAA7A1E5BBC7CC1C2E5D831478814"), zp0.a("01000000000000000000000000C7F34A778F443ACC920EBA49"), BigInteger.valueOf(2L));
            case 27:
                return new vq(193, 15, zp0.a("0163F35A5137C2CE3EA6ED8667190B0BC43ECD69977702709B"), zp0.a("00C9BB9E8927D4D64C377E2AB2856A5B16E3EFB7F61D4316AE"), zp0.a("010000000000000000000000015AAB561B005413CCD4EE99D5"), BigInteger.valueOf(2L));
            case 28:
                return new vq(233, 74, qq.a, BigInteger.valueOf(1L), zp0.a("8000000000000000000000000000069D5BB915BCD46EFB1AD5F173ABDF"), BigInteger.valueOf(4L));
            default:
                return new vq(233, 74, BigInteger.valueOf(1L), zp0.a("0066647EDE6C332C7F8C0923BB58213B333B20E9CE4281FE115F7D8F90AD"), zp0.a("01000000000000000000000000000013E974E72F8A6922031D2603CFE0D7"), BigInteger.valueOf(2L));
        }
    }

    @Override // defpackage.t91
    public final s91 b() {
        switch (this.c) {
            case 0:
                byte[] bArrB = c30.b("3045AE6FC8422F64ED579528D38120EAE12196D5");
                xq xqVarC = c();
                return new s91(xqVarC, gk.a(xqVarC, "04188DA80EB03090F67CBF20EB43A18800F4FF0AFD82FF101207192B95FFC8DA78631011ED6B24CDD573F977A11E794811"), xqVarC.d, xqVarC.e, bArrB);
            case 1:
                xq xqVarC2 = c();
                return new s91(xqVarC2, gk.a(xqVarC2, "04A1455B334DF099DF30FC28A169A467E9E47075A90F7E650EB6B7A45C7E089FED7FBA344282CAFBD6F7E319F7C0B0BD59E2CA4BDB556D61A5"), xqVarC2.d, xqVarC2.e, null);
            case 2:
                byte[] bArrB2 = c30.b("BD71344799D5C7FCDC45B59FA3B9AB8F6A948BC5");
                xq xqVarC3 = c();
                return new s91(xqVarC3, gk.a(xqVarC3, "04B70E0CBD6BB4BF7F321390B94A03C1D356C21122343280D6115C1D21BD376388B5F723FB4C22DFE6CD4375A05A07476444D5819985007E34"), xqVarC3.d, xqVarC3.e, bArrB2);
            case 3:
                xq xqVarC4 = c();
                return new s91(xqVarC4, dr.b(xqVarC4, qq.b, dr.a("8D91E471E0989CDA27DF505A453F2B7635294F2DDF23E3B122ACC99C9E9F1E14")), xqVarC4.d, xqVarC4.e, null);
            case 4:
                xq xqVarC5 = c();
                return new s91(xqVarC5, dr.b(xqVarC5, qq.b, dr.a("3FA8124359F96680B83D1C3EB2C070E5C545C9858D03ECFB744BF8D717717EFC")), xqVarC5.d, xqVarC5.e, null);
            case 5:
                xq xqVarC6 = c();
                return new s91(xqVarC6, dr.b(xqVarC6, qq.a, dr.a("41ECE55743711A8C3CBF3783CD08C0EE4D4DC440D4641A8F366E550DFDB3BB67")), xqVarC6.d, xqVarC6.e, null);
            case 6:
                xq xqVarC7 = c();
                return new s91(xqVarC7, dr.b(xqVarC7, dr.a("91E38443A5E82C0D880923425712B2BB658B9196932E02C78B2582FE742DAA28"), dr.a("32879423AB1A0375895786C4BB46E9565FDE0B5344766740AF268ADB32322E5C")), xqVarC7.d, xqVarC7.e, null);
            case 7:
                xq xqVarC8 = c();
                return new s91(xqVarC8, dr.b(xqVarC8, qq.d, dr.a("7503CFE87A836AE3A61B8816E25450E6CE5E1C93ACF1ABC1778064FDCBEFA921DF1626BE4FD036E93D75E6A50E3A41E98028FE5FC235F5B889A589CB5215F2A4")), xqVarC8.d, xqVarC8.e, null);
            case 8:
                xq xqVarC9 = c();
                return new s91(xqVarC9, dr.b(xqVarC9, qq.c, dr.a("1A8F7EDA389B094C2C071E3647A8940F3C123B697578C213BE6DD9E6C8EC7335DCB228FD1EDF4A39152CBCAAF8C0398828041055F94CEEEC7E21340780FE41BD")), xqVarC9.d, xqVarC9.e, null);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                xq xqVarC10 = c();
                return new s91(xqVarC10, dr.b(xqVarC10, dr.a("E2E31EDFC23DE7BDEBE241CE593EF5DE2295B7A9CBAEF021D385F7074CEA043AA27272A7AE602BF2A7B9033DB9ED3610C6FB85487EAE97AAC5BC7928C1950148"), dr.a("F5CE40D95B5EB899ABBCCFF5911CB8577939804D6527378B8C108C3D2090FF9BE18E2D33E3021ED2EF32D85822423B6304F726AA854BAE07D0396E9A9ADDC40F")), xqVarC10.d, xqVarC10.e, null);
            case 10:
                xq xqVarC11 = c();
                u91 u91Var = new u91(xqVarC11, c30.b("0432C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0"));
                qm.j(u91Var.g());
                return new s91(xqVarC11, u91Var, xqVarC11.d, xqVarC11.e, null);
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                xq xqVarC12 = c();
                u91 u91Var2 = new u91(xqVarC12, c30.b("044AD5F7048DE709AD51236DE65E4D4B482C836DC6E410664002BB3A02D4AAADACAE24817A4CA3A1B014B5270432DB27D2"));
                qm.j(u91Var2.g());
                return new s91(xqVarC12, u91Var2, xqVarC12.d, xqVarC12.e, null);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                xq xqVarC13 = c();
                return new s91(xqVarC13, zp0.b(xqVarC13, "04A1455B334DF099DF30FC28A169A467E9E47075A90F7E650EB6B7A45C7E089FED7FBA344282CAFBD6F7E319F7C0B0BD59E2CA4BDB556D61A5"), xqVarC13.d, xqVarC13.e, null);
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                byte[] bArrB3 = c30.b("BD71344799D5C7FCDC45B59FA3B9AB8F6A948BC5");
                xq xqVarC14 = c();
                return new s91(xqVarC14, zp0.b(xqVarC14, "04B70E0CBD6BB4BF7F321390B94A03C1D356C21122343280D6115C1D21BD376388B5F723FB4C22DFE6CD4375A05A07476444D5819985007E34"), xqVarC14.d, xqVarC14.e, bArrB3);
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                xq xqVarC15 = c();
                return new s91(xqVarC15, zp0.b(xqVarC15, "0479BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8"), xqVarC15.d, xqVarC15.e, null);
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                byte[] bArrB4 = c30.b("C49D360886E704936A6678E1139D26B7819F7E90");
                xq xqVarC16 = c();
                return new s91(xqVarC16, zp0.b(xqVarC16, "046B17D1F2E12C4247F8BCE6E563A440F277037D812DEB33A0F4A13945D898C2964FE342E2FE1A7F9B8EE7EB4A7C0F9E162BCE33576B315ECECBB6406837BF51F5"), xqVarC16.d, xqVarC16.e, bArrB4);
            case 16:
                byte[] bArrB5 = c30.b("A335926AA319A27A1D00896A6773A4827ACDAC73");
                xq xqVarC17 = c();
                return new s91(xqVarC17, zp0.b(xqVarC17, "04AA87CA22BE8B05378EB1C71EF320AD746E1D3B628BA79B9859F741E082542A385502F25DBF55296C3A545E3872760AB73617DE4A96262C6F5D9E98BF9292DC29F8F41DBD289A147CE9DA3113B5F0B8C00A60B1CE1D7E819D7A431D7C90EA0E5F"), xqVarC17.d, xqVarC17.e, bArrB5);
            case 17:
                byte[] bArrB6 = c30.b("D09E8800291CB85396CC6717393284AAA0DA64BA");
                xq xqVarC18 = c();
                return new s91(xqVarC18, zp0.b(xqVarC18, "0400C6858E06B70404E9CD9E3ECB662395B4429C648139053FB521F828AF606B4D3DBAA14B5E77EFE75928FE1DC127A2FFA8DE3348B3C1856A429BF97E7E31C2E5BD66011839296A789A3BC0045C8A5FB42C7D1BD998F54449579B446817AFBD17273E662C97EE72995EF42640C550B9013FAD0761353C7086A272C24088BE94769FD16650"), xqVarC18.d, xqVarC18.e, bArrB6);
            case 18:
                byte[] bArrB7 = c30.b("10E723AB14D696E6768756151756FEBF8FCB49A9");
                xq xqVarC19 = c();
                return new s91(xqVarC19, zp0.b(xqVarC19, "04009D73616F35F4AB1407D73562C10F00A52830277958EE84D1315ED31886"), xqVarC19.d, xqVarC19.e, bArrB7);
            case 19:
                byte[] bArrB8 = c30.b("10C0FB15760860DEF1EEF4D696E676875615175D");
                xq xqVarC20 = c();
                return new s91(xqVarC20, zp0.b(xqVarC20, "0401A57A6A7B26CA5EF52FCDB816479700B3ADC94ED1FE674C06E695BABA1D"), xqVarC20.d, xqVarC20.e, bArrB8);
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                byte[] bArrB9 = c30.b("4D696E676875615175985BD3ADBADA21B43A97E2");
                xq xqVarC21 = c();
                return new s91(xqVarC21, zp0.b(xqVarC21, "040081BAF91FDF9833C40F9C181343638399078C6E7EA38C001F73C8134B1B4EF9E150"), xqVarC21.d, xqVarC21.e, bArrB9);
            case 21:
                byte[] bArrB10 = c30.b("985BD3ADBAD4D696E676875615175A21B43A97E3");
                xq xqVarC22 = c();
                return new s91(xqVarC22, zp0.b(xqVarC22, "040356DCD8F2F95031AD652D23951BB366A80648F06D867940A5366D9E265DE9EB240F"), xqVarC22.d, xqVarC22.e, bArrB10);
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                byte[] bArrB11 = c30.b("00F50B028E4D696E676875615175290472783FB1");
                xq xqVarC23 = c();
                return new s91(xqVarC23, zp0.b(xqVarC23, "0409487239995A5EE76B55F9C2F098A89CE5AF8724C0A23E0E0FF77500"), xqVarC23.d, xqVarC23.e, bArrB11);
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                xq xqVarC24 = c();
                return new s91(xqVarC24, zp0.b(xqVarC24, "0402FE13C0537BBC11ACAA07D793DE4E6D5E5C94EEE80289070FB05D38FF58321F2E800536D538CCDAA3D9"), xqVarC24.d, xqVarC24.e, null);
            case 24:
                byte[] bArrB12 = c30.b("24B7B137C8A14D696E6768756151756FD0DA2E5C");
                xq xqVarC25 = c();
                return new s91(xqVarC25, zp0.b(xqVarC25, "040369979697AB43897789566789567F787A7876A65400435EDB42EFAFB2989D51FEFCE3C80988F41FF883"), xqVarC25.d, xqVarC25.e, bArrB12);
            case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                byte[] bArrB13 = c30.b("85E25BFE5C86226CDB12016F7553F9D0E693A268");
                xq xqVarC26 = c();
                return new s91(xqVarC26, zp0.b(xqVarC26, "0403F0EBA16286A2D57EA0991168D4994637E8343E3600D51FBC6C71A0094FA2CDD545B11C5C0C797324F1"), xqVarC26.d, xqVarC26.e, bArrB13);
            case 26:
                byte[] bArrB14 = c30.b("103FAEC74D696E676875615175777FC5B191EF30");
                xq xqVarC27 = c();
                return new s91(xqVarC27, zp0.b(xqVarC27, "0401F481BC5F0FF84A74AD6CDF6FDEF4BF6179625372D8C0C5E10025E399F2903712CCF3EA9E3A1AD17FB0B3201B6AF7CE1B05"), xqVarC27.d, xqVarC27.e, bArrB14);
            case 27:
                byte[] bArrB15 = c30.b("10B7B4D696E676875615175137C8A16FD0DA2211");
                xq xqVarC28 = c();
                return new s91(xqVarC28, zp0.b(xqVarC28, "0400D9B67D192E0367C803F39E1A7E82CA14A651350AAE617E8F01CE94335607C304AC29E7DEFBD9CA01F596F927224CDECF6C"), xqVarC28.d, xqVarC28.e, bArrB15);
            case 28:
                xq xqVarC29 = c();
                return new s91(xqVarC29, zp0.b(xqVarC29, "04017232BA853A7E731AF129F22FF4149563A419C26BF50A4C9D6EEFAD612601DB537DECE819B7F70F555A67C427A8CD9BF18AEB9B56E0C11056FAE6A3"), xqVarC29.d, xqVarC29.e, null);
            default:
                byte[] bArrB16 = c30.b("74D59FF07F6B413D0EA14B344B20A2DB049B50C3");
                xq xqVarC30 = c();
                return new s91(xqVarC30, zp0.b(xqVarC30, "0400FAC9DFCBAC8313BB2139F1BB755FEF65BC391F8B36F8F8EB7371FD558B01006A08A41903350678E58528BEBF8A0BEFF867A7CA36716F7E01F81052"), xqVarC30.d, xqVarC30.e, bArrB16);
        }
    }
}
