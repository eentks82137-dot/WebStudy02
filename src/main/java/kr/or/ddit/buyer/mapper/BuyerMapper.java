package kr.or.ddit.buyer.mapper;

import java.util.List;
import kr.or.ddit.dto.BuyerDto;

/**
 * 제조사 관리 Persistence Layer
 */
public interface BuyerMapper {
    /**
     * 제조사 등록
     * 
     * @param buyerDto 등록할 제조사 정보가 담긴 DTO 객체
     * @return 등록 성공 시 1, 실패 시 0
     */
    int insertBuyer(BuyerDto buyerDto);



    // selectBuyer
    List<BuyerDto> selectBuyerList();
    // updateBuyer
    // deleteBuyer
}
