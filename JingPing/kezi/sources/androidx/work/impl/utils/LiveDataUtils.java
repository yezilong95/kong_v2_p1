package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import defpackage.b30;
import defpackage.ba0;
import defpackage.e60;
import defpackage.xu;

/* JADX INFO: loaded from: classes.dex */
public class LiveDataUtils {

    /* JADX INFO: renamed from: androidx.work.impl.utils.LiveDataUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements ba0 {
        Out mCurrentOutput = null;
        final /* synthetic */ Object val$lock;
        final /* synthetic */ xu val$mappingMethod;
        final /* synthetic */ e60 val$outputLiveData;
        final /* synthetic */ TaskExecutor val$workTaskExecutor;

        public AnonymousClass1(TaskExecutor taskExecutor, Object obj, xu xuVar, e60 e60Var) {
            this.val$workTaskExecutor = taskExecutor;
            this.val$lock = obj;
            this.val$mappingMethod = xuVar;
            this.val$outputLiveData = e60Var;
        }

        @Override // defpackage.ba0
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
                                anonymousClass1.val$outputLiveData.g(Apply);
                            } else if (out != 0 && !out.equals(Apply)) {
                                AnonymousClass1 anonymousClass12 = AnonymousClass1.this;
                                anonymousClass12.mCurrentOutput = Apply;
                                anonymousClass12.val$outputLiveData.g(Apply);
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
    public static <In, Out> b30 dedupedMappedLiveDataFor(b30 b30Var, xu xuVar, TaskExecutor taskExecutor) {
        Object obj = new Object();
        e60 e60Var = new e60();
        e60Var.j(b30Var, new AnonymousClass1(taskExecutor, obj, xuVar, e60Var));
        return e60Var;
    }
}
