package kr.or.ddit.buyer.controller;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
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

@WebServlet("/buyer/detail")
public class BuyerDetailServlet extends HttpServlet {
    private BuyerService buyerService = new BuyerServiceImpl();
    private ViewResolver viewResolver = new ViewResolverComposite();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String buyerId = req.getParameter("id");
        if (StringUtils.isBlank(buyerId)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        BuyerDto buyerDto = buyerService.readBuyer(buyerId);

        req.setAttribute("buyer", buyerDto);
        String lvn = "buyer/buyerDetail";
        viewResolver.resolveViewName(lvn, req, resp);
    }
}
