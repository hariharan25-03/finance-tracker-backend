package org.example.service.impl;

import org.example.dto.response.PortFolioDTO;
import org.example.dto.req.PortFolioRequestDTO;
import org.example.mapper.PortfolioMapper;
import org.example.repository.PortfolioRepository;
import org.example.repository.UserRepository;
import org.example.service.PortFolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class PortfolioServiceImpl implements PortFolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioMapper portfolioMapper;

    @Override
    public PortFolioDTO getPortfolioById (UUID portfolioId) {
        var portfolio = portfolioRepository.findById(portfolioId);
        return portfolioMapper.toDto(portfolio.get());
    }

    @Override
    public List<PortFolioDTO> getPortfolioByUserId (UUID userId) {
        var portfolios = portfolioRepository.findByUserId(userId);
        return portfolios.stream().map(x->portfolioMapper.toDto(x)).collect(Collectors.toList());
    }
    @Override
    public PortFolioDTO createPortFolio(PortFolioRequestDTO portFolioRequestDTO) {
        var portfolio = portfolioMapper.toEntity(portFolioRequestDTO);
        var user =  userRepository.findById(portFolioRequestDTO.getUserId());
        portfolio.setUser(user.get());
        return  portfolioMapper.toDto(portfolioRepository.save(portfolio));
    }

    @Override
    public void deletePortFolio(UUID portFolioId) {
        if(portfolioRepository.findById(portFolioId).isPresent())
        {
            portfolioRepository.deleteById(portFolioId);
        }
        else {
            throw new RuntimeException("Provided Entity Details are not founded to Delete");
        }
    }
}
