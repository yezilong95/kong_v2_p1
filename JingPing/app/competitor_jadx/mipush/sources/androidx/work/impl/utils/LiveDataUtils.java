package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import defpackage.ea0;
import defpackage.j10;
import defpackage.ti0;
import defpackage.ud0;

/* JADX INFO: loaded from: classes.dex */
public class LiveDataUtils {

    /* JADX INFO: renamed from: androidx.work.impl.utils.LiveDataUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements ti0 {
        Out mCurrentOutput = null;
        final /* synthetic */ Object val$lock;
        final /* synthetic */ j10 val$mappingMethod;
        final /* synthetic */ ud0 val$outputLiveData;
        final /* synthetic */ TaskExecutor val$workTaskExecutor;

        public AnonymousClass1(TaskExecutor taskExecutor, Object obj, j10 j10Var, ud0 ud0Var) {
            this.val$workTaskExecutor = taskExecutor;
            this.val$lock = obj;
            this.val$mappingMethod = j10Var;
            this.val$outputLiveData = ud0Var;
        }

        @Override // defpackage.ti0
        public void onChanged(final In in) {
            this.val$workTaskExecutor.executeOnTaskThread(new Runnable() { // from class: androidx.work.impl.utils.LiveDataUtils.1.1
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r1v3, types: [Out, java.lang.Object] */
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
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (AnonymousClass1.this.val$lock) {
                        try {
                            ?? Apply = AnonymousClass1.this.val$mappingMethod.apply(in);
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            Out out = anonymousClass1.mCurrentOutput;
                            if (out == 0 && Apply != 0) {
                                anonymousClass1.mCurrentOutput = Apply;
                                anonymousClass1.val$outputLiveData.f(Apply);
                            } else if (out != 0 && !out.equals(Apply)) {
                                AnonymousClass1 anonymousClass12 = AnonymousClass1.this;
                                anonymousClass12.mCurrentOutput = Apply;
                                anonymousClass12.val$outputLiveData.f(Apply);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            });
        }
    }

    private LiveDataUtils() {
    }

    @SuppressLint({"LambdaLast"})
    public static <In, Out> ea0 dedupedMappedLiveDataFor(ea0 ea0Var, j10 j10Var, TaskExecutor taskExecutor) {
        Object obj = new Object();
        ud0 ud0Var = new ud0();
        ud0Var.h(ea0Var, new AnonymousClass1(taskExecutor, obj, j10Var, ud0Var));
        return ud0Var;
    }
}
