package com.google.gwt.core.client;

import com.google.gwt.core.shared.GWTBridge;

/**
 * Main goal is to make the <code>UncaughtExceptionHandler</code> available.
 * 
 * @author sj
 */
public final class GWT {

    public interface UncaughtExceptionHandler {

        void onUncaughtException(Throwable e);
    }

    /**
     * @deprecated Not supported!
     */
    @Deprecated
    public static <T> T create(Class<?> classLiteral) {
        throw new IllegalStateException("Not supported!");
    }

    /**
     * Gets the URL prefix of the hosting page, useful for prepending to relative paths of resources which may be
     * relative to the host page. Typically, you should use {@link #getModuleBaseURL()} unless you have a specific
     * reason to load a resource relative to the host page.
     *
     * @return if non-empty, the base URL is guaranteed to end with a slash
     */
    public static String getHostPageBaseURL() {
        return "";
    }

    /**
     * Gets the URL prefix that should be prepended to URLs that point to static files generated by the GWT compiler,
     * such as files in the module's public path.
     *
     * <p>
     * Normally this will be the same value as {@link #getModuleBaseURL}, but may be different when a GWT app is
     * configured to get its static resources from a different server.
     * </p>
     *
     * @return if non-empty, the base URL is guaranteed to end with a slash
     */
    public static String getModuleBaseForStaticFiles() {
        return "";
    }

    /**
     * Gets the URL prefix that should be prepended to URLs that are intended to be module-relative, such as RPC entry
     * points.
     *
     * <p>
     * If the URL points to an output file of the GWT compiler (such as a file in the public path), use
     * {@link #getModuleBaseForStaticFiles()} instead.</p>
     *
     * @return if non-empty, the base URL is guaranteed to end with a slash
     */
    public static String getModuleBaseURL() {
        return "";
    }

    /**
     * Gets the name of the running module.
     */
    public static String getModuleName() {
        return "";
    }

    /**
     * Returns the permutation's strong name. This can be used to distinguish between different permutations of the same
     * module. In Development Mode, this method will return {@value #HOSTED_MODE_PERMUTATION_STRONG_NAME}.
     */
    public static String getPermutationStrongName() {
        return "";
    }

    /**
     * @deprecated Use {@link Object#getClass()}, {@link Class#getName()}
     */
    @Deprecated
    public static String getTypeName(Object o) {
        throw new IllegalStateException("Not supported!");
    }

    /**
     * Returns the currently active uncaughtExceptionHandler.
     *
     * @return the currently active handler, or null if no handler is active.
     *
     * @see #reportUncaughtException(Throwable)
     */
    public static UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return null;
    }

    /**
     * Reports an exception caught at the "top level" to a handler set via
     * {@link #setUncaughtExceptionHandler(UncaughtExceptionHandler)}. This is used in places where the browser calls
     * into user code such as event callbacks, timers, and RPC.
     * <p>
     * If no {@code UncaughtExceptionHandler} is set, the exception is reported to browser. Browsers usually log these
     * exceptions to the JavaScript console.
     */
    public static void reportUncaughtException(Throwable e) {
    }

    /**
     * @deprecated Not supported!
     */
    @Deprecated
    public static String getUniqueThreadId() {
        throw new IllegalStateException("Not supported!");
    }

    public static String getVersion() {
        return "";
    }

    /**
     * @deprecated Not supported!
     */
    @Deprecated
    public static boolean isClient() {
        throw new IllegalStateException("Not supported!");
    }

    /**
     * @deprecated Not supported!
     */
    @Deprecated
    public static boolean isProdMode() {
        throw new IllegalStateException("Not supported!");
    }

    /**
     * Determines whether or not the running program is script or bytecode.
     */
    public static boolean isScript() {
        return true;
    }

    /**
     * @deprecated Not supported!
     */
    @Deprecated
    public static void log(String message) {
        throw new IllegalStateException("Not supported!");
    }

    /**
     * @deprecated Not supported!
     */
    @Deprecated
    public static void log(String message, Throwable e) {
        throw new IllegalStateException("Not supported!");
    }

    /**
     * Emits a JavaScript "debugger" statement on the line that called this method. If the user has the browser's
     * debugger open, the debugger will stop when the GWT application executes that line. There is no effect in Dev Mode
     * or in server-side code.
     */
    public static void debugger() {
    }

    /**
     * @deprecated Not supported!
     */
    @Deprecated
    @SuppressWarnings("unused") // parameter will be used following replacement
    public static void runAsync(Class<?> name, final RunAsyncCallback callback) {
        throw new IllegalStateException("Not supported!");
    }

    /**
     * @deprecated Not supported!
     */
    @Deprecated
    public static void runAsync(final RunAsyncCallback callback) {
        throw new IllegalStateException("Not supported!");
    }

    /**
     * Sets a custom uncaught exception handler. See {@link #getUncaughtExceptionHandler()} for details.
     *
     * @param handler the handler that should be called when an exception is about to escape to the browser, or
     * <code>null</code> to clear the handler and allow exceptions to escape.
     */
    public static void setUncaughtExceptionHandler(UncaughtExceptionHandler handler) {
    }

    /**
     * @deprecated Not supported!
     */
    @Deprecated
    static void setBridge(GWTBridge bridge) {
        throw new IllegalStateException("Not supported!");
    }
}