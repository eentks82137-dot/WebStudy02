package kr.or.ddit.buyer.service;

import java.util.List;
import kr.or.ddit.dto.BuyerDto;

public interface BuyerService {
    // createBuyer
    BuyerDto readBuyer(String buyerId);

    List<BuyerDto> readBuyerList();
    // modifyBuyer
    // removeBuyer
}
