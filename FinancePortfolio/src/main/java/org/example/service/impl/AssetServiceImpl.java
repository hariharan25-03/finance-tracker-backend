package org.example.service.impl;

import org.example.dto.response.AssetResponseDTO;
import org.example.mapper.AssetMapper;
import org.example.repository.AssetRepository;
import org.example.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService {

       @Autowired AssetRepository assetRepository;

       @Autowired AssetMapper assetMapper;
    @Override
    public List<AssetResponseDTO> getAllAssets() {
        return assetRepository.findAll().stream().map(x->{
           return assetMapper.toDto(x);
        }).toList();
    }
}
