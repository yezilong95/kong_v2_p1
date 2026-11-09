package defpackage;

import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import io.nats.client.BaseConsumeOptions;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class l extends t91 {
    public final /* synthetic */ int c;

    public /* synthetic */ l(int i) {
        this.c = i;
    }

    private final xq e() {
        int i;
        BigInteger bigInteger = new BigInteger("9ba48cba5ebcb9b6bd33b92830b2a2e0e192f10a", 16);
        new BigInteger("c39c6c3b3a36d7701b9c71a1f5804ae5d0003f4", 16);
        BigInteger[] bigIntegerArr = {new BigInteger("9162fbe73984472a0a9e", 16), new BigInteger("-96341f1138933bc2f505", 16)};
        BigInteger[] bigIntegerArr2 = {new BigInteger("127971af8721782ecffa3", 16), new BigInteger("9162fbe73984472a0a9e", 16)};
        new BigInteger("9162fbe73984472a0a9d0590", 16);
        new BigInteger("96341f1138933bc2f503fd44", 16);
        ma0.i(bigIntegerArr, "v1");
        ma0.i(bigIntegerArr2, "v2");
        xq ms0Var = new ms0();
        synchronized (ms0Var) {
            i = ms0Var.f;
        }
        ms0Var.h(bigInteger);
        if (!ms0Var.p(i)) {
            throw new IllegalStateException("unsupported coordinate system");
        }
        xq xqVarA = ms0Var.a();
        if (xqVarA == ms0Var) {
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
                return new wq(new BigInteger(1, c30.b("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C03")), new BigInteger(1, c30.b("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C00")), new BigInteger(1, c30.b("EE353FCA5428A9300D4ABA754A44C00FDFEC0C9AE4B1A1803075ED967B7BB73F")), new BigInteger(1, c30.b("F1FD178C0B3AD58F10126DE8CE42435B53DC67E140D2BF941FFDD459C6D655E1")), BigInteger.valueOf(1L), true);
            case 1:
                BigInteger bigInteger = new BigInteger("7ae96a2b657c07106e64479eac3434e99cf0497512f58995c1396c28719501ee", 16);
                new BigInteger("5363ad4cc05c30e0a5261c028812645a122e22ea20816678df02967c1b23bd72", 16);
                BigInteger[] bigIntegerArr = {new BigInteger("3086d221a7d46bcde86c90e49284eb15", 16), new BigInteger("-e4437ed6010e88286f547fa90abfe4c3", 16)};
                BigInteger[] bigIntegerArr2 = {new BigInteger("114ca50f7a8e2f3f657c1108d9d44cfd8", 16), new BigInteger("3086d221a7d46bcde86c90e49284eb15", 16)};
                new BigInteger("3086d221a7d46bcde86c90e49284eb153dab", 16);
                new BigInteger("e4437ed6010e88286f547fa90abfe4c42212", 16);
                ma0.i(bigIntegerArr, "v1");
                ma0.i(bigIntegerArr2, "v2");
                xq zs0Var = new zs0();
                synchronized (zs0Var) {
                    i = zs0Var.f;
                }
                zs0Var.h(bigInteger);
                if (!zs0Var.p(i)) {
                    throw new IllegalStateException("unsupported coordinate system");
                }
                xq xqVarA = zs0Var.a();
                if (xqVarA == zs0Var) {
                    throw new IllegalStateException("implementation returned current curve");
                }
                synchronized (xqVarA) {
                    xqVarA.f = i;
                    break;
                }
                return xqVarA;
            case 2:
                return new bt0();
            case 3:
                return new dt0();
            case 4:
                return new ft0();
            case 5:
                return new it0();
            case 6:
                return new jt0();
            case 7:
                return new lt0();
            case 8:
                return new mt0();
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return new ot0();
            case 10:
                return new pt0();
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                return new ak();
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                return new qt0();
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                return new st0();
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return new tt0();
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                return new vt0();
            case 16:
                return new wt0();
            case 17:
                return new yt0();
            case 18:
                return new au0();
            case 19:
                return new bu0();
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                return new du0();
            case 21:
                return new eu0();
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                return new ks0();
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                return new hu0();
            case 24:
                return new iu0();
            case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                return new mq0();
            case 26:
                return e();
            case 27:
                return new ns0();
            case 28:
                return new ps0();
            default:
                BigInteger bigInteger2 = new BigInteger("bb85691939b869c1d087f601554b96b80cb4f55b35f433c2", 16);
                new BigInteger("3d84f26c12238d7b4f3d516613c1759033b1a5800175d0b1", 16);
                BigInteger[] bigIntegerArr3 = {new BigInteger("71169be7330b3038edb025f1", 16), new BigInteger("-b3fb3400dec5c4adceb8655c", 16)};
                BigInteger[] bigIntegerArr4 = {new BigInteger("12511cfe811d0f4e6bc688b4d", 16), new BigInteger("71169be7330b3038edb025f1", 16)};
                new BigInteger("71169be7330b3038edb025f1d0f9", 16);
                new BigInteger("b3fb3400dec5c4adceb8655d4c94", 16);
                ma0.i(bigIntegerArr3, "v1");
                ma0.i(bigIntegerArr4, "v2");
                xq rs0Var = new rs0();
                synchronized (rs0Var) {
                    i2 = rs0Var.f;
                }
                rs0Var.h(bigInteger2);
                if (!rs0Var.p(i2)) {
                    throw new IllegalStateException("unsupported coordinate system");
                }
                xq xqVarA2 = rs0Var.a();
                if (xqVarA2 == rs0Var) {
                    throw new IllegalStateException("implementation returned current curve");
                }
                synchronized (xqVarA2) {
                    xqVarA2.f = i2;
                    break;
                }
                return xqVarA2;
        }
    }

    @Override // defpackage.t91
    public final s91 b() {
        switch (this.c) {
            case 0:
                xq xqVarA = a();
                u91 u91Var = new u91(xqVarA, c30.b("04B6B3D4C356C139EB31183D4749D423958C27D2DCAF98B70164C97A2DD98F5CFF6142E0F7C8B204911F9271F0F3ECEF8C2701C307E8E4C9E183115A1554062CFB"));
                qm.j(u91Var.g());
                return new s91(xqVarA, u91Var, xqVarA.d, xqVarA.e, null);
            case 1:
                xq xqVarC = c();
                return new s91(xqVarC, gk.a(xqVarC, "0479BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8"), xqVarC.d, xqVarC.e, null);
            case 2:
                byte[] bArrB = c30.b("C49D360886E704936A6678E1139D26B7819F7E90");
                xq xqVarC2 = c();
                return new s91(xqVarC2, gk.a(xqVarC2, "046B17D1F2E12C4247F8BCE6E563A440F277037D812DEB33A0F4A13945D898C2964FE342E2FE1A7F9B8EE7EB4A7C0F9E162BCE33576B315ECECBB6406837BF51F5"), xqVarC2.d, xqVarC2.e, bArrB);
            case 3:
                byte[] bArrB2 = c30.b("A335926AA319A27A1D00896A6773A4827ACDAC73");
                xq xqVarC3 = c();
                return new s91(xqVarC3, gk.a(xqVarC3, "04AA87CA22BE8B05378EB1C71EF320AD746E1D3B628BA79B9859F741E082542A385502F25DBF55296C3A545E3872760AB73617DE4A96262C6F5D9E98BF9292DC29F8F41DBD289A147CE9DA3113B5F0B8C00A60B1CE1D7E819D7A431D7C90EA0E5F"), xqVarC3.d, xqVarC3.e, bArrB2);
            case 4:
                byte[] bArrB3 = c30.b("D09E8800291CB85396CC6717393284AAA0DA64BA");
                xq xqVarC4 = c();
                return new s91(xqVarC4, gk.a(xqVarC4, "0400C6858E06B70404E9CD9E3ECB662395B4429C648139053FB521F828AF606B4D3DBAA14B5E77EFE75928FE1DC127A2FFA8DE3348B3C1856A429BF97E7E31C2E5BD66011839296A789A3BC0045C8A5FB42C7D1BD998F54449579B446817AFBD17273E662C97EE72995EF42640C550B9013FAD0761353C7086A272C24088BE94769FD16650"), xqVarC4.d, xqVarC4.e, bArrB3);
            case 5:
                byte[] bArrB4 = c30.b("10E723AB14D696E6768756151756FEBF8FCB49A9");
                xq xqVarC5 = c();
                return new s91(xqVarC5, gk.a(xqVarC5, "04009D73616F35F4AB1407D73562C10F00A52830277958EE84D1315ED31886"), xqVarC5.d, xqVarC5.e, bArrB4);
            case 6:
                byte[] bArrB5 = c30.b("10C0FB15760860DEF1EEF4D696E676875615175D");
                xq xqVarC6 = c();
                return new s91(xqVarC6, gk.a(xqVarC6, "0401A57A6A7B26CA5EF52FCDB816479700B3ADC94ED1FE674C06E695BABA1D"), xqVarC6.d, xqVarC6.e, bArrB5);
            case 7:
                byte[] bArrB6 = c30.b("4D696E676875615175985BD3ADBADA21B43A97E2");
                xq xqVarC7 = c();
                return new s91(xqVarC7, gk.a(xqVarC7, "040081BAF91FDF9833C40F9C181343638399078C6E7EA38C001F73C8134B1B4EF9E150"), xqVarC7.d, xqVarC7.e, bArrB6);
            case 8:
                byte[] bArrB7 = c30.b("985BD3ADBAD4D696E676875615175A21B43A97E3");
                xq xqVarC8 = c();
                return new s91(xqVarC8, gk.a(xqVarC8, "040356DCD8F2F95031AD652D23951BB366A80648F06D867940A5366D9E265DE9EB240F"), xqVarC8.d, xqVarC8.e, bArrB7);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                xq xqVarC9 = c();
                return new s91(xqVarC9, gk.a(xqVarC9, "0402FE13C0537BBC11ACAA07D793DE4E6D5E5C94EEE80289070FB05D38FF58321F2E800536D538CCDAA3D9"), xqVarC9.d, xqVarC9.e, null);
            case 10:
                byte[] bArrB8 = c30.b("24B7B137C8A14D696E6768756151756FD0DA2E5C");
                xq xqVarC10 = c();
                return new s91(xqVarC10, gk.a(xqVarC10, "040369979697AB43897789566789567F787A7876A65400435EDB42EFAFB2989D51FEFCE3C80988F41FF883"), xqVarC10.d, xqVarC10.e, bArrB8);
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                xq xqVarC11 = c();
                return new s91(xqVarC11, gk.a(xqVarC11, "042AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD245A20AE19A1B8A086B4E01EDD2C7748D14C923D4D7E6D7C61B229E9C5A27ECED3D9"), xqVarC11.d, xqVarC11.e, null);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                byte[] bArrB9 = c30.b("85E25BFE5C86226CDB12016F7553F9D0E693A268");
                xq xqVarC12 = c();
                return new s91(xqVarC12, gk.a(xqVarC12, "0403F0EBA16286A2D57EA0991168D4994637E8343E3600D51FBC6C71A0094FA2CDD545B11C5C0C797324F1"), xqVarC12.d, xqVarC12.e, bArrB9);
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                byte[] bArrB10 = c30.b("103FAEC74D696E676875615175777FC5B191EF30");
                xq xqVarC13 = c();
                return new s91(xqVarC13, gk.a(xqVarC13, "0401F481BC5F0FF84A74AD6CDF6FDEF4BF6179625372D8C0C5E10025E399F2903712CCF3EA9E3A1AD17FB0B3201B6AF7CE1B05"), xqVarC13.d, xqVarC13.e, bArrB10);
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                byte[] bArrB11 = c30.b("10B7B4D696E676875615175137C8A16FD0DA2211");
                xq xqVarC14 = c();
                return new s91(xqVarC14, gk.a(xqVarC14, "0400D9B67D192E0367C803F39E1A7E82CA14A651350AAE617E8F01CE94335607C304AC29E7DEFBD9CA01F596F927224CDECF6C"), xqVarC14.d, xqVarC14.e, bArrB11);
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                xq xqVarC15 = c();
                return new s91(xqVarC15, gk.a(xqVarC15, "04017232BA853A7E731AF129F22FF4149563A419C26BF50A4C9D6EEFAD612601DB537DECE819B7F70F555A67C427A8CD9BF18AEB9B56E0C11056FAE6A3"), xqVarC15.d, xqVarC15.e, null);
            case 16:
                byte[] bArrB12 = c30.b("74D59FF07F6B413D0EA14B344B20A2DB049B50C3");
                xq xqVarC16 = c();
                return new s91(xqVarC16, gk.a(xqVarC16, "0400FAC9DFCBAC8313BB2139F1BB755FEF65BC391F8B36F8F8EB7371FD558B01006A08A41903350678E58528BEBF8A0BEFF867A7CA36716F7E01F81052"), xqVarC16.d, xqVarC16.e, bArrB12);
            case 17:
                xq xqVarC17 = c();
                return new s91(xqVarC17, gk.a(xqVarC17, "0429A0B6A887A983E9730988A68727A8B2D126C44CC2CC7B2A6555193035DC76310804F12E549BDB011C103089E73510ACB275FC312A5DC6B76553F0CA"), xqVarC17.d, xqVarC17.e, null);
            case 18:
                xq xqVarC18 = c();
                return new s91(xqVarC18, gk.a(xqVarC18, "040503213F78CA44883F1A3B8162F188E553CD265F23C1567A16876913B0C2AC245849283601CCDA380F1C9E318D90F95D07E5426FE87E45C0E8184698E45962364E34116177DD2259"), xqVarC18.d, xqVarC18.e, null);
            case 19:
                byte[] bArrB13 = c30.b("77E2B07370EB0F832A6DD5B62DFC88CD06BB84BE");
                xq xqVarC19 = c();
                return new s91(xqVarC19, gk.a(xqVarC19, "0405F939258DB7DD90E1934F8C70B0DFEC2EED25B8557EAC9C80E2E198F8CDBECD86B1205303676854FE24141CB98FE6D4B20D02B4516FF702350EDDB0826779C813F0DF45BE8112F4"), xqVarC19.d, xqVarC19.e, bArrB13);
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                xq xqVarC20 = c();
                return new s91(xqVarC20, gk.a(xqVarC20, "040060F05F658F49C1AD3AB1890F7184210EFD0987E307C84C27ACCFB8F9F67CC2C460189EB5AAAA62EE222EB1B35540CFE902374601E369050B7C4E42ACBA1DACBF04299C3460782F918EA427E6325165E9EA10E3DA5F6C42E9C55215AA9CA27A5863EC48D8E0286B"), xqVarC20.d, xqVarC20.e, null);
            case 21:
                byte[] bArrB14 = c30.b("4099B5A457F9D69F79213D094C4BCD4D4262210B");
                xq xqVarC21 = c();
                return new s91(xqVarC21, gk.a(xqVarC21, "04015D4860D088DDB3496B0C6064756260441CDE4AF1771D4DB01FFE5B34E59703DC255A868A1180515603AEAB60794E54BB7996A70061B1CFAB6BE5F32BBFA78324ED106A7636B9C5A7BD198D0158AA4F5488D08F38514F1FDF4B4F40D2181B3681C364BA0273C706"), xqVarC21.d, xqVarC21.e, bArrB14);
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                byte[] bArrB15 = c30.b("000E0D4D696E6768756151750CC03A4473D03679");
                xq xqVarC22 = c();
                return new s91(xqVarC22, gk.a(xqVarC22, "04161FF7528B899B2D0C28607CA52C5B86CF5AC8395BAFEB13C02DA292DDED7A83"), xqVarC22.d, xqVarC22.e, bArrB15);
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                xq xqVarC23 = c();
                return new s91(xqVarC23, gk.a(xqVarC23, "04026EB7A859923FBC82189631F8103FE4AC9CA2970012D5D46024804801841CA44370958493B205E647DA304DB4CEB08CBBD1BA39494776FB988B47174DCA88C7E2945283A01C89720349DC807F4FBF374F4AEADE3BCA95314DD58CEC9F307A54FFC61EFC006D8A2C9D4979C0AC44AEA74FBEBBB9F772AEDCB620B01A7BA7AF1B320430C8591984F601CD4C143EF1C7A3"), xqVarC23.d, xqVarC23.e, null);
            case 24:
                byte[] bArrB16 = c30.b("2AA058F73A0E33AB486B0F610410C53A7F132310");
                xq xqVarC24 = c();
                return new s91(xqVarC24, gk.a(xqVarC24, "040303001D34B856296C16C0D40D3CD7750A93D1D2955FA80AA5F40FC8DB7B2ABDBDE53950F4C0D293CDD711A35B67FB1499AE60038614F1394ABFA3B4C850D927E1E7769C8EEC2D19037BF27342DA639B6DCCFFFEB73D69D78C6C27A6009CBBCA1980F8533921E8A684423E43BAB08A576291AF8F461BB2A8B3531D2F0485C19B16E2F1516E23DD3C1A4827AF1B8AC15B"), xqVarC24.d, xqVarC24.e, bArrB16);
            case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                xq xqVarC25 = c();
                return new s91(xqVarC25, gk.a(xqVarC25, "0432C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0"), xqVarC25.d, xqVarC25.e, null);
            case 26:
                xq xqVarC26 = c();
                return new s91(xqVarC26, gk.a(xqVarC26, "043B4C382CE37AA192A4019E763036F4F5DD4D7EBB938CF935318FDCED6BC28286531733C3F03C4FEE"), xqVarC26.d, xqVarC26.e, null);
            case 27:
                byte[] bArrB17 = c30.b("1053CDE42C14D696E67687561517533BF3F83345");
                xq xqVarC27 = c();
                return new s91(xqVarC27, gk.a(xqVarC27, "044A96B5688EF573284664698968C38BB913CBFC8223A628553168947D59DCC912042351377AC5FB32"), xqVarC27.d, xqVarC27.e, bArrB17);
            case 28:
                byte[] bArrB18 = c30.b("B99B99B099B323E02709A4D696E6768756151751");
                xq xqVarC28 = c();
                return new s91(xqVarC28, gk.a(xqVarC28, "0452DCB034293A117E1F4FF11B30F7199D3144CE6DFEAFFEF2E331F296E071FA0DF9982CFEA7D43F2E"), xqVarC28.d, xqVarC28.e, bArrB18);
            default:
                xq xqVarC29 = c();
                return new s91(xqVarC29, gk.a(xqVarC29, "04DB4FF10EC057E9AE26B07D0280B7F4341DA5D1B1EAE06C7D9B2F2F6D9C5628A7844163D015BE86344082AA88D95E2F9D"), xqVarC29.d, xqVarC29.e, null);
        }
    }
}
