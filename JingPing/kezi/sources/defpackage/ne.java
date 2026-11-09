package defpackage;

import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
public final class ne extends SuspendLambda implements ru {
    public rb f;
    public byte[] g;
    public int h;
    public int i;
    public int j;
    public /* synthetic */ Object k;
    public final /* synthetic */ oq[] l;
    public final /* synthetic */ xb m;
    public final /* synthetic */ SuspendLambda n;
    public final /* synthetic */ qq o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ne(oq[] oqVarArr, xb xbVar, su suVar, qq qqVar, fh fhVar) {
        super(2, fhVar);
        this.l = oqVarArr;
        this.m = xbVar;
        this.n = (SuspendLambda) suVar;
        this.o = qqVar;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [kotlin.coroutines.jvm.internal.SuspendLambda, su] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final fh create(Object obj, fh fhVar) {
        ne neVar = new ne(this.l, this.m, this.n, this.o, fhVar);
        neVar.k = obj;
        return neVar;
    }

    @Override // defpackage.ru
    public final Object invoke(Object obj, Object obj2) {
        return ((ne) create((xh) obj, (fh) obj2)).invokeSuspend(bq0.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x024f, code lost:
    
        r3.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0269, code lost:
    
        throw new java.lang.IllegalStateException("unexpected");
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x028a, code lost:
    
        if (r3.invoke(r23.o, r10, r23) == r1) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0226, code lost:
    
        if ((r14 instanceof defpackage.rt0) == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0228, code lost:
    
        r14 = (defpackage.rt0) r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x022b, code lost:
    
        r14 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x022c, code lost:
    
        if (r14 == null) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x022e, code lost:
    
        r14.a(r3, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0231, code lost:
    
        r11.A(r15);
        r3.h();
        r6 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x024d, code lost:
    
        if (r6 == defpackage.u9.n) goto L118;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0224 A[EDGE_INSN: B:121:0x0224->B:85:0x0224 BREAK  A[LOOP:1: B:73:0x01d5->B:81:0x0214], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x017e A[LOOP:0: B:60:0x017e->B:108:0x025e, LOOP_START, PHI: r3 r7 r11
      0x017e: PHI (r3v3 nm) = (r3v2 nm), (r3v14 nm) binds: [B:58:0x017b, B:108:0x025e] A[DONT_GENERATE, DONT_INLINE]
      0x017e: PHI (r7v4 int) = (r7v3 int), (r7v5 int) binds: [B:58:0x017b, B:108:0x025e] A[DONT_GENERATE, DONT_INLINE]
      0x017e: PHI (r11v8 wx) = (r11v7 wx), (r11v13 wx) binds: [B:58:0x017b, B:108:0x025e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x023a  */
    /* JADX WARN: Type inference failed for: r3v5, types: [kotlin.coroutines.jvm.internal.SuspendLambda, su] */
    /* JADX WARN: Type inference failed for: r4v8, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v3, types: [T, java.lang.Object, kotlin.coroutines.EmptyCoroutineContext, nh] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:116:0x028d -> B:50:0x0157). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 659
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ne.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
