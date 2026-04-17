package kr.or.ddit.auth;

public class SecurityContextHolder {
    private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<>();

    public static SecurityContext getContext() {
        SecurityContext context = contextHolder.get();
        if (context == null) {
            context = new SecurityContext();
            contextHolder.set(context);
        }
        return context;
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}
