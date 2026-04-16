package kr.or.ddit.member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.member.dto.MemberDTO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/member/mypage")
public class MyPageServlet extends HttpServlet {
    private final MemberService memberService = new MemberServiceImpl();
    private final ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String memId = req.getUserPrincipal().getName();
        MemberDTO memberDTO = memberService.readMember(memId);
        req.setAttribute("member", memberDTO);
        String lvn = "member/mypage";
        viewResolver.resolveViewName(lvn, req, resp);
    }
}
