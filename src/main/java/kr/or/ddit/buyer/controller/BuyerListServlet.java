package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.ViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/buyer/list")
public class BuyerListServlet extends HttpServlet {
    BuyerService buyerService = new BuyerServiceImpl();
    ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<BuyerDto> bList = buyerService.readBuyerList();
        req.setAttribute("buyerList", bList);
        String lvn = "/buyer/buyerList";
        viewResolver.resolveViewName(lvn, req, resp);

    }
}
