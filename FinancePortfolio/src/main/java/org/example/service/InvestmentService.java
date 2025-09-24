package org.example.service;

import org.example.dto.response.InvestmentDTO;
import org.example.dto.req.InvestmentRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface InvestmentService {


    List<InvestmentDTO> getInvestmentByPortfolioId(UUID portfolioId);
    InvestmentDTO createInvestment(InvestmentRequestDTO investmentRequestDTO);

}
