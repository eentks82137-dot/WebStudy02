package kr.or.ddit.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import kr.or.ddit.dto.MemberDTO;

public class MemberValidator {

    /**
     * 회원 정보 검증
     *
     * <p>
     * 리스트를 받아서 여러 오류 메세지들을 저장할 수 있도록 함
     * 
     * @param memberDTO 검증할 회원 정보
     * @param errors    검증 오류를 저장할 맵 (키: 필드명, 값: 오류 메시지 리스트)
     */
    public static void validate(MemberDTO memberDTO, Map<String, List<String>> errors) {
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
