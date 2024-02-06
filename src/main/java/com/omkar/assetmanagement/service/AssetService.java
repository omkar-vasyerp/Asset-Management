package com.omkar.assetmanagement.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omkar.assetmanagement.dto.AssetDto;

@Component
public interface AssetService {
	
	List<AssetDto> getallAsset();

	boolean deleteAssetById(Long assetId);

	AssetDto getAssetById(Long assetId);

	boolean addAsset(AssetDto assetDto);
}
