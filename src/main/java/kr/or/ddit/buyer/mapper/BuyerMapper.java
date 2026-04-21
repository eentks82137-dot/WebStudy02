package kr.or.ddit.buyer.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
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


    /**
     * 제조사 상세 조회
     * 
     * @param buyerId 조회할 제조사 ID
     * @return BuyerDto
     */
    BuyerDto selectBuyer(@Param("buyerId") String buyerId);

    /**
     * 제조사 목록 조회
     * 
     * @return 제조사 목록
     */
    List<BuyerDto> selectBuyerList();
    // updateBuyer
    // deleteBuyer
}
