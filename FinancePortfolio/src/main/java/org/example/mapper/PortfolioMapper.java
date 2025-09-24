package org.example.mapper;


import org.example.dto.response.PortFolioDTO;
import org.example.dto.response.InvestmentDTO;
import org.example.dto.req.InvestmentRequestDTO;
import org.example.dto.req.PortFolioRequestDTO;
import org.example.entity.Portfolio;
import org.example.entity.Investment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {

    PortFolioDTO toDto(Portfolio portfolio);

    @Mapping(target = "portfolioId", ignore = true)
    Portfolio toEntity(PortFolioRequestDTO dto);

}
