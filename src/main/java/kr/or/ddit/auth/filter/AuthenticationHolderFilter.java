package kr.or.ddit.auth.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.auth.Authentication;
import kr.or.ddit.auth.SecurityContextHolder;
import kr.or.ddit.auth.wrapper.PrincipalRequestWrapper;

public class AuthenticationHolderFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        PrincipalRequestWrapper wrapper = new PrincipalRequestWrapper(req);

        try {
            Authentication authentication = (Authentication) wrapper.getUserPrincipal();
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(wrapper, res);
        } finally {
            // 요청 처리 완료 후 ThreadLocal에 저장된 인증 정보 제거
            SecurityContextHolder.clearContext();
        }
    }
}
