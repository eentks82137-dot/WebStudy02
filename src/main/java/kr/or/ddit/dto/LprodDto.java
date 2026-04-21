package kr.or.ddit.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "lprodGu")
public class LprodDto {
    Integer lprodId;
    String lprodGu;
    String lprodName;


}
