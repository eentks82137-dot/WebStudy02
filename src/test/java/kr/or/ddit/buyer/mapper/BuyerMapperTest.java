package kr.or.ddit.buyer.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerMapperTest {
    BuyerMapper buyerMapper = MapperProxyGenerator.generateMapperProxy(BuyerMapper.class);

    @Test
    void selectBuyerTest() {
        BuyerDto buyerDto = buyerMapper.selectBuyer("P10101");
        log.info("buyerName: {}, prodList: {}", buyerDto.getBuyerName(),
                buyerDto.getProdList().stream().map(prod -> prod.getProdName()).toList());
        assertNotNull(buyerDto);
    }

    @Test
    void selectBuyerListTest() {
        buyerMapper.selectBuyerList()
                .forEach(buyerDto -> log.info("lprod: {}", buyerDto.getLprod()));
    }

}


