package org.example.controller;

import org.example.dto.ApiResponse;
import org.example.dto.response.AssetResponseDTO;
import org.example.dto.response.PortfolioInsightDTO;
import org.example.mapper.AssetMapper;
import org.example.repository.InvestmentRepository;
import org.example.repository.PortfolioRepository;
import org.example.service.PortfolioInsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/portfolio")
public class PortfolioInsightController {
    @Autowired
    private PortfolioInsightService insightService;
    @Autowired
    private  AssetMapper assetMapper;
    @Autowired
    private  PortfolioRepository portfolioRepository;
    @Autowired
    private InvestmentRepository investmentRepository;

    @GetMapping("/insight/{portfolioId}")
    public ResponseEntity<ApiResponse> getPortfolioInsight(@PathVariable UUID portfolioId) {
        var portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        List<AssetResponseDTO> assetDTOs = investmentRepository.findByPortfolioId(portfolioId).stream()
                .map(inv -> assetMapper.toDto(inv.getAsset()))
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(
                "Provided Intelligence report",
                HttpStatus.OK,
                insightService.generateInsight(portfolioId, portfolio.getName(), assetDTOs)
        ));
    }
}
