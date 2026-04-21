package kr.or.ddit.buyer.service;

import java.util.List;
import kr.or.ddit.buyer.mapper.BuyerMapper;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mybatis.MapperProxyGenerator;

public class BuyerServiceImpl implements BuyerService {
    private BuyerMapper buyerMapper = MapperProxyGenerator.generateMapperProxy(BuyerMapper.class);

    @Override
    public List<BuyerDto> readBuyerList() {
        return buyerMapper.selectBuyerList();
    }

}
