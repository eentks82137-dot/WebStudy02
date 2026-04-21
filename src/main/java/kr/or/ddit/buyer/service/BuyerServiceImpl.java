package kr.or.ddit.buyer.service;

import java.util.List;
import kr.or.ddit.buyer.mapper.BuyerMapper;
import kr.or.ddit.common.exception.EntityNotFoundException;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mybatis.MapperProxyGenerator;

public class BuyerServiceImpl implements BuyerService {
    private BuyerMapper buyerMapper = MapperProxyGenerator.generateMapperProxy(BuyerMapper.class);

    @Override
    public BuyerDto readBuyer(String buyerId) {
        BuyerDto buyerDto = buyerMapper.selectBuyer(buyerId);
        if (buyerDto == null) {
            throw new EntityNotFoundException("존재하지 않는 id 입니다. id: %s".formatted(buyerId));
        }
        return buyerDto;
    }

    @Override
    public List<BuyerDto> readBuyerList() {
        return buyerMapper.selectBuyerList();
    }

}
