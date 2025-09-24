package org.example.service;

import org.example.dto.response.AssetResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AssetService {
    List<AssetResponseDTO> getAllAssets();
}
