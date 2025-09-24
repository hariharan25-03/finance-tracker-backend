package org.example.service.impl;


import org.example.dto.response.InvestmentDTO;
import org.example.dto.req.InvestmentRequestDTO;
import org.example.entity.Asset;
import org.example.entity.Investment;
import org.example.entity.Portfolio;
import org.example.mapper.AssetMapper;
import org.example.mapper.InvestmentMapper;
import org.example.mapper.PortfolioMapper;
import org.example.repository.AssetRepository;
import org.example.repository.InvestmentRepository;
import org.example.repository.PortfolioRepository;
import org.example.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired private InvestmentMapper investmentMapper;

    @Autowired private PortfolioMapper portfolioMapper;
    @Autowired private AssetMapper assetMapper;

    @Autowired private InvestmentRepository investmentRepository;

    @Autowired PortfolioRepository portfolioRepository;

    @Autowired private AssetRepository assetRepository;



    @Override
    public List<InvestmentDTO> getInvestmentByPortfolioId(UUID portfolioId) {
        var investments = investmentRepository.findByPortfolioId(portfolioId);
        return investments.stream().map(x -> {
           var invest = investmentMapper.toDto(x);
           invest.setAsset(assetMapper.toDto(x.getAsset()));
           invest.setPortfolio(portfolioMapper.toDto(x.getPortfolio()));

           return invest;
        }).collect(Collectors.toList());
    }

    @Override
    public InvestmentDTO createInvestment(InvestmentRequestDTO investmentRequestDTO) {
        var investement = investmentMapper.toEntity(investmentRequestDTO);
        Portfolio portfolio = portfolioRepository.findById(investmentRequestDTO.getPortfolioId())
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));
        Asset asset = assetRepository.findById(investmentRequestDTO.getAssetId())
                .orElseThrow(() -> new RuntimeException("Asset not found"));


        BigDecimal amountInvested = investmentRequestDTO.getBuyPrice().multiply(BigDecimal.valueOf(investmentRequestDTO.getUnits()));

        BigDecimal totalAmountInvested = asset.getAmountInvested().add(amountInvested);
        BigDecimal totalUnitsEarned = asset.getUnits().add(BigDecimal.valueOf(investmentRequestDTO.getUnits()));
        asset.setAmountInvested(totalAmountInvested);
        asset.setUnits(totalUnitsEarned);
        investement.setAsset(asset);
        investement.setPortfolio(portfolio);
        investement.setAmountInvested(amountInvested);
        var savedinvestement = investmentRepository.save(investement);
        var responsedto =  investmentMapper.toDto(savedinvestement);

        responsedto.setPortfolio(portfolioMapper.toDto(portfolio));
        responsedto.setAsset(assetMapper.toDto(asset));
      return  responsedto;
    }
}
