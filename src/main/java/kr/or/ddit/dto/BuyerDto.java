package kr.or.ddit.dto;

import java.io.Serializable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.or.ddit.validate.constraints.PhoneNumber;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 제조사 관리 Domain Layer
 */
@Data
@EqualsAndHashCode(of = "buyerId") // buyerId만 같아도 같은 객체로 판단
public class BuyerDto implements Serializable {

    @NotBlank(groups = UpdateGroup.class)
    private String buyerId;

    @NotBlank
    @Size(min = 4, max = 4)
    private String buyerName;

    @NotBlank
    private String buyerGu;

    private String buyerBank;
    private String buyerBankno;
    private String buyerBankname;
    private String buyerZip;
    private String buyerAdd1;
    private String buyerAdd2;

    @NotBlank
    @PhoneNumber
    private String buyerComtel;

    @NotBlank
    @PhoneNumber
    private String buyerFax;

    @NotBlank
    @Email
    private String buyerMail;

    private String buyerCharger;
    private String buyerTelext;
}
