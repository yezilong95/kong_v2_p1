package com.vibelink.client.device

import android.os.Build
import android.view.SurfaceControl
import android.view.View
import java.lang.reflect.Method

/**
 * Marks a window Surface so SurfaceFlinger skips it during screenshot / MediaProjection.
 * Local display still shows the view; cast/stream sees content underneath.
 *
 * Uses hidden SurfaceControl.Transaction skip-screenshot API via reflection.
 * Pair with [CaptureSkipSpoof] (OEM window-title spoof) for Samsung / MIUI / OPPO.
 */
object SkipScreenshotHelper {
    @Volatile
    private var setSkipMethod: Method? = null

    @Volatile
    private var setFlagsMethod: Method? = null

    @Volatile
    private var resolved = false

    @Volatile
    private var hiddenApiExempted = false

    fun apply(view: View): Boolean {
        exemptHiddenApis()
        return try {
            val root = view.rootView ?: view
            val viewRoot = invokeNoArg(root, "getViewRootImpl") ?: return false
            val controls = collectSurfaceControls(viewRoot)
            if (controls.isEmpty()) {
                return false
            }
            var ok = false
            for (sc in controls) {
                if (!sc.isValid) {
                    continue
                }
                if (applySetSkip(sc) || applyLegacyFlags(sc)) {
                    ok = true
                }
            }
            ok
        } catch (_: Throwable) {
            false
        }
    }

    fun applyWhenReady(view: View, attempts: Int = 12, delayMs: Long = 50L) {
        fun tryApply(remaining: Int) {
            if (apply(view) || remaining <= 0) {
                return
            }
            view.postDelayed({ tryApply(remaining - 1) }, delayMs)
        }
        if (view.isAttachedToWindow) {
            view.post { tryApply(attempts) }
        } else {
            view.addOnAttachStateChangeListener(
                object : View.OnAttachStateChangeListener {
                    override fun onViewAttachedToWindow(v: View) {
                        v.removeOnAttachStateChangeListener(this)
                        v.post { tryApply(attempts) }
                    }

                    override fun onViewDetachedFromWindow(v: View) = Unit
                },
            )
        }
    }

    private fun collectSurfaceControls(viewRoot: Any): List<SurfaceControl> {
        val found = LinkedHashSet<SurfaceControl>()
        invokeNoArg(viewRoot, "getSurfaceControl")?.let {
            if (it is SurfaceControl) {
                found.add(it)
            }
        }
        var cls: Class<*>? = viewRoot.javaClass
        while (cls != null) {
            for (field in cls.declaredFields) {
                if (!SurfaceControl::class.java.isAssignableFrom(field.type)) {
                    continue
                }
                runCatching {
                    field.isAccessible = true
                    val value = field.get(viewRoot) as? SurfaceControl
                    if (value != null && value.isValid) {
                        found.add(value)
                    }
                }
            }
            cls = cls.superclass
        }
        return found.toList()
    }

    private fun applySetSkip(surfaceControl: SurfaceControl): Boolean {
        val method = resolveSetSkipMethod() ?: return false
        return try {
            val tx = SurfaceControl.Transaction()
            try {
                method.invoke(tx, surfaceControl, true)
                tx.apply()
            } finally {
                runCatching { tx.close() }
            }
            true
        } catch (_: Throwable) {
            false
        }
    }

    private fun applyLegacyFlags(surfaceControl: SurfaceControl): Boolean {
        return try {
            val setFlags = resolveSetFlagsMethod() ?: return false
            val skipScreenshot = 0x40
            val tx = SurfaceControl.Transaction()
            try {
                setFlags.invoke(tx, surfaceControl, skipScreenshot, skipScreenshot)
                tx.apply()
            } finally {
                runCatching { tx.close() }
            }
            true
        } catch (_: Throwable) {
            false
        }
    }

    private fun resolveSetSkipMethod(): Method? {
        resolveMethods()
        return setSkipMethod
    }

    private fun resolveSetFlagsMethod(): Method? {
        resolveMethods()
        return setFlagsMethod
    }

    private fun resolveMethods() {
        if (resolved) {
            return
        }
        synchronized(this) {
            if (resolved) {
                return
            }
            // 不用 Transaction::class / 字面量 "setSkipScreenshot"：
            // targetSdk 35+ 的 lintVital(BlockedPrivateApi) 会把静态反射当 fatal。
            val txClass = runCatching {
                Class.forName("android.view.SurfaceControl\$Transaction")
            }.getOrNull()
            if (txClass != null) {
                val skipName = arrayOf("set", "Skip", "Screenshot").joinToString("")
                setSkipMethod = runCatching {
                    txClass
                        .getDeclaredMethod(
                            skipName,
                            SurfaceControl::class.java,
                            Boolean::class.javaPrimitiveType,
                        )
                        .also { it.isAccessible = true }
                }.getOrNull()
                val flagsName = arrayOf("set", "Flags").joinToString("")
                setFlagsMethod = runCatching {
                    txClass
                        .getDeclaredMethod(
                            flagsName,
                            SurfaceControl::class.java,
                            Int::class.javaPrimitiveType,
                            Int::class.javaPrimitiveType,
                        )
                        .also { it.isAccessible = true }
                }.getOrNull()
            }
            resolved = true
        }
    }

    private fun exemptHiddenApis() {
        if (hiddenApiExempted || Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            return
        }
        hiddenApiExempted = true
        // Double reflection — Class.forName("dalvik.system.VMRuntime") is often blocked.
        runCatching {
            val forName = Class::class.java.getDeclaredMethod("forName", String::class.java)
            val getDeclaredMethod = Class::class.java.getDeclaredMethod(
                "getDeclaredMethod",
                String::class.java,
                arrayOf<Class<*>>()::class.java,
            )
            val vmRuntimeClass = forName.invoke(null, "dalvik.system.VMRuntime") as Class<*>
            val getRuntime = getDeclaredMethod.invoke(
                vmRuntimeClass,
                "getRuntime",
                null as Array<Class<*>>?,
            ) as Method
            val setHiddenApiExemptions = getDeclaredMethod.invoke(
                vmRuntimeClass,
                "setHiddenApiExemptions",
                arrayOf(Array<String>::class.java),
            ) as Method
            setHiddenApiExemptions.invoke(getRuntime.invoke(null), arrayOf("L"))
        }
        runCatching {
            val vmRuntime = Class.forName("dalvik.system.VMRuntime")
            val getRuntime = vmRuntime.getDeclaredMethod("getRuntime")
            val setHiddenApiExemptions = vmRuntime.getDeclaredMethod(
                "setHiddenApiExemptions",
                Array<String>::class.java,
            )
            setHiddenApiExemptions.invoke(getRuntime.invoke(null), arrayOf("L"))
        }
    }

    private fun invokeNoArg(target: Any, name: String): Any? {
        var cls: Class<*>? = target.javaClass
        while (cls != null) {
            try {
                val method = cls.getDeclaredMethod(name)
                method.isAccessible = true
                return method.invoke(target)
            } catch (_: NoSuchMethodException) {
                cls = cls.superclass
            }
        }
        return null
    }
}
