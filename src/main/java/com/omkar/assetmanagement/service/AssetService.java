package com.omkar.assetmanagement.service;

import java.util.List;

import com.omkar.assetmanagement.dto.AssetDto;

public interface AssetService {
	
	List<AssetDto> getallAsset();

	boolean deleteAssetById(Long assetId);

	AssetDto getAssetById(Long assetId);

	boolean addAsset(AssetDto assetDto);
}
