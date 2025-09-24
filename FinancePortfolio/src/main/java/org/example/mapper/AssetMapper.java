package org.example.mapper;

import org.example.dto.response.AssetResponseDTO;
import org.example.entity.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface AssetMapper {

    AssetResponseDTO toDto(Asset asset);

}
