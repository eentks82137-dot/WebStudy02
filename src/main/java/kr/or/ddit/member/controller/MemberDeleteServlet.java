package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.auth.exception.AuthenticationException;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import lombok.extern.slf4j.Slf4j;

@WebServlet("/member/leave-out")
@Slf4j
public class MemberDeleteServlet extends HttpServlet {
    MemberService memberService = new MemberServiceImpl();
    ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String memId = req.getUserPrincipal().getName();
        String memPass = req.getParameter("memPass");
        String lvn = null;

        if (StringUtils.isBlank(memPass)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
            return;
        }

        MemberDTO memberDTO = MemberDTO.builder().memId(memId).memPass(memPass).build();
        Map<String, List<String>> errors = ValidateUtils.validate(memberDTO, DeleteGroup.class);
        if (!errors.isEmpty()) {
            req.getSession().setAttribute("errors", errors);
            lvn = "redirect:/member/mypageBS";
        } else {
            try {
                memberService.removeMember(memId, memPass);
                lvn = "redirect:/logout";
            } catch (AuthenticationException e) {
                log.error("회원 탈퇴 실패", e);
                req.getSession().setAttribute("message", "회원 탈퇴 실패: " + e.getMessage());
                lvn = "redirect:/member/mypageBS";
            }
        }
        viewResolver.resolveViewName(lvn, req, resp);
    }
}
