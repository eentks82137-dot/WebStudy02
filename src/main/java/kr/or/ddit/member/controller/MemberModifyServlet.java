package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.auth.Authentication;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.MemberValidator;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;

@WebServlet("/member/modify")
public class MemberModifyServlet extends HttpServlet {
    private final MemberService memberService = new MemberServiceImpl();
    private final ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 아이디 가져와서 dto 만들고 req에 담고 보내주기

        Authentication authentication = (Authentication) req.getUserPrincipal();
        String username = authentication.getName();
        MemberDTO authMember = authentication.getPrincipal();

        String memId = req.getUserPrincipal().getName();
        MemberDTO memberDTO = memberService.readMember(memId);
        req.setAttribute("member", memberDTO);
        String lvn = "member/modifyForm";
        viewResolver.resolveViewName(lvn, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 받은 정보에 principal에서 memId 꺼내서 설정하고 validate 후 업데이트
        Map<String, String[]> parameterMap = req.getParameterMap();
        String lvn = null;

        MemberDTO memberDTO = null;
        try {
            memberDTO = PopulateUtils.populate(parameterMap, MemberDTO.class);
        } catch (Exception e) {
            lvn = "member/modifyForm";
            viewResolver.resolveViewName(lvn, req, resp);
            return;
        }
        Map<String, List<String>> errors = ValidateUtils.validate(memberDTO, UpdateGroup.class);
        req.setAttribute("errors", errors);

        // 검증
        if (errors.isEmpty()) { // 검증 통과
            try {
                memberService.modifyMember(memberDTO);
                lvn = "redirect:/member/mypage";
            } catch (RuntimeException e) {
                errors.put("", List.of("인증 실패, 비밀번호를 확인하세요"));
                lvn = "member/modifyForm";
                req.setAttribute("member", memberDTO);
            }
        } else {
            lvn = "member/modifyForm";
            req.setAttribute("member", memberDTO);
        }
        viewResolver.resolveViewName(lvn, req, resp);
    }

}
