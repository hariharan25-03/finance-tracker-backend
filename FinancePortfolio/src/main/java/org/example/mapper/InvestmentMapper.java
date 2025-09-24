package org.example.mapper;

import org.example.dto.response.AssetResponseDTO;
import org.example.dto.response.InvestmentDTO;
import org.example.dto.req.InvestmentRequestDTO;
import org.example.dto.response.PortFolioDTO;
import org.example.entity.Asset;
import org.example.entity.Investment;
import org.example.entity.Portfolio;
import org.example.repository.PortfolioRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {

    @Mapping(target = "portfolio", expression = "java(toPortfolio(dto.getPortfolioId()))")
    @Mapping(target = "asset", expression = "java(toAsset(dto.getAssetId()))")
    Investment toEntity(InvestmentRequestDTO dto);


    @Mapping(source = "asset.name", target = "assetName")
    InvestmentDTO toDto(Investment investment);

    default Portfolio toPortfolio(UUID portfolioId) {
        if (portfolioId == null) return null;
        Portfolio p = new Portfolio();
        p.setPortfolioId(portfolioId);
        return p;
    }

    default Asset toAsset(UUID assetId) {
        if (assetId == null) return null;
        Asset a = new Asset();
        a.setAssetId(assetId);
        return a;
    }


}
