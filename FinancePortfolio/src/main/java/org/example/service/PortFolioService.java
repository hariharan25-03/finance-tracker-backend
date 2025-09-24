package org.example.service;

import org.example.dto.response.PortFolioDTO;
import org.example.dto.req.PortFolioRequestDTO;


import java.util.List;
import java.util.UUID;


public interface PortFolioService {

        PortFolioDTO getPortfolioById(UUID portfolioId);

        List<PortFolioDTO> getPortfolioByUserId (UUID userId);
        PortFolioDTO  createPortFolio(PortFolioRequestDTO portFolioRequestDTO);
        void deletePortFolio(UUID portFolioId);
}
