package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

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
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

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
        MemberDTO memberDTO = new MemberDTO();
        // if (!req.getContentType().contains("json")) {
        // resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        // } else {
        // ObjectMapper objectMapper = new ObjectMapper();
        // memberDTO = objectMapper.readValue(req.getInputStream(), MemberDTO.class);
        // log.info("Received member data: {}", memberDTO);
        // }

        Map<String, String[]> parameterMap = req.getParameterMap();
        try {
            BeanUtils.populate(memberDTO, parameterMap);
            log.info("Populated memberDTO: {}", memberDTO);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // 검증
        Map<String, List<String>> errors = new LinkedHashMap<>();
        validate(memberDTO, errors);
        String lvn = null;
        if (errors.isEmpty()) { // 검증 통과
            service.registerMember(memberDTO);
            lvn = "redirect:/";
        } else {
            lvn = "member/registrationForm";
            req.setAttribute("member", memberDTO);
            req.setAttribute("errors", errors);
        }
        viewResolver.resolveViewName(lvn, req, resp);
    }

    private void validate(MemberDTO memberDTO, Map<String, List<String>> errors) {
        if (StringUtils.isBlank(memberDTO.getMemId())) {
            errors.put("memId", List.of("회원 아이디는 필수입니다."));
        }
        if (StringUtils.isBlank(memberDTO.getMemPass())) {
            errors.put("memPass", List.of("비밀번호는 필수입니다."));

        }
        if (StringUtils.isBlank(memberDTO.getMemName())) {
            errors.put("memName", List.of("회원 이름은 필수입니다."));

        }
        if (StringUtils.isBlank(memberDTO.getMemZip())) {
            errors.put("memZip", List.of("우편번호는 필수입니다."));
        }
        if (StringUtils.isBlank(memberDTO.getMemAdd1())) {
            errors.put("memAdd1", List.of("주소는 필수입니다."));

        }
        if (StringUtils.isBlank(memberDTO.getMemAdd2())) {
            errors.put("memAdd2", List.of("상세 주소는 필수입니다."));

        }
        if (StringUtils.isBlank(memberDTO.getMemMail())) {
            errors.put("memMail", List.of("이메일은 필수입니다."));

        }
    }
}
