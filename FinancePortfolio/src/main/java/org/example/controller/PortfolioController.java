package org.example.controller;

import org.example.dto.ApiResponse;
import org.example.dto.response.AssetResponseDTO;
import org.example.dto.response.InvestmentDTO;
import org.example.dto.response.PortFolioDTO;
import org.example.dto.req.InvestmentRequestDTO;
import org.example.dto.req.PortFolioRequestDTO;
import org.example.service.AssetService;
import org.example.service.InvestmentService;
import org.example.service.PortFolioService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "https://finance-tracker-coral-three.vercel.app")
@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    private PortFolioService portFolioService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private AssetService assetService;

    @GetMapping("/view/{portfolioId}")
    public ResponseEntity<ApiResponse> viewPortfolio(@PathVariable UUID portfolioId) {
        PortFolioDTO portfolio = portFolioService.getPortfolioById(portfolioId);
        return ResponseEntity.ok(new ApiResponse<>(
                "Portfolio fetched successfully",
                HttpStatus.OK,
                portfolio
        ));
    }

    @GetMapping("/view-assets")
    public ResponseEntity<ApiResponse> getAssets() {
        List<AssetResponseDTO> assets = assetService.getAllAssets() ;
        return ResponseEntity.ok(new ApiResponse<>(
                "Portfolio fetched successfully",
                HttpStatus.OK,
                assets
        ));
    }

    @GetMapping("/view-user/{userId}")
    public ResponseEntity<ApiResponse> userPortfolios(@PathVariable UUID userId) {
        List<PortFolioDTO> userportFolios = portFolioService.getPortfolioByUserId(userId);
        return ResponseEntity.ok(new ApiResponse<>(
                "User Portfolio fetched successfully",
                HttpStatus.OK,
                userportFolios
        ));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPortFolio(@RequestBody PortFolioRequestDTO portFolioRequestDTO) {

        return ResponseEntity.ok(new ApiResponse<>(
                "User Portfolio fetched successfully",
                HttpStatus.OK,
                portFolioService.createPortFolio(portFolioRequestDTO)
        ));

    }

    @GetMapping("/view-investments/{portfolioId}")
    public ResponseEntity<ApiResponse> portfolioInvestments(@PathVariable UUID portfolioId) {
        List<InvestmentDTO> investmentDTOS = investmentService.getInvestmentByPortfolioId(portfolioId);
        return ResponseEntity.ok(new ApiResponse<>(
                "Portfolio's Investment fetched successfully",
                HttpStatus.OK,
                investmentDTOS
        ));
    }

    @PostMapping("/create-investment/{userId}")
    public ResponseEntity<ApiResponse> createInvestment(@RequestBody InvestmentRequestDTO investmentRequestDTO, @PathVariable UUID userId) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Investement Created Successfully",
                HttpStatus.OK,
                investmentService.createInvestment(investmentRequestDTO)
        ));
    }
    }

