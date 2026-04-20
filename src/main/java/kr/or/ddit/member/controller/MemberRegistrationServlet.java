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
import kr.or.ddit.member.dto.MemberDTO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/member/register")
public class MemberRegistrationServlet extends HttpServlet {
    private final ViewResolver viewResolver = new ViewResolverComposite();
    private MemberService service = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lvn = "member/registrationForm";
        viewResolver.resolveViewName(lvn, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("요ㅕ청바등ㅁ ");

        Map<String, String[]> parameterMap = req.getParameterMap();

        MemberDTO memberDTO = PopulateUtils.populate(parameterMap, MemberDTO.class);
        Map<String, List<String>> errors = ValidateUtils.validate(memberDTO, InsertGroup.class);
        req.setAttribute("errors", errors);

        String lvn = null;
        if (errors.isEmpty()) { // 검증 통과
            service.registerMember(memberDTO);
            lvn = "redirect:/";
        } else {
            lvn = "member/registrationForm";
            req.setAttribute("member", memberDTO);
        }
        viewResolver.resolveViewName(lvn, req, resp);
    }

}
