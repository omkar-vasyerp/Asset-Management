package com.omkar.assetmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkar.assetmanagement.dto.AssetDto;
import com.omkar.assetmanagement.model.Asset;
import com.omkar.assetmanagement.repository.AssetRepository;


@Service
public class AssetServiceImpl implements AssetService {

	
	private AssetRepository assetRepository;
	
	public AssetServiceImpl(AssetRepository assetRepo) {
		assetRepository=assetRepo;		
	}

	@Override
	public boolean deleteAssetById(Long assetId) {
		assetRepository.deleteById(assetId);
		return true;
	}

	public boolean addAsset(AssetDto assetDto) {
		Asset asset = new Asset();
		asset.setAssetId(assetDto.getAssetId());
		asset.setAssetName(assetDto.getAssetName());
		asset.setAssetModel(assetDto.getAssetModel());
		assetRepository.save(asset);
		return true;
	}

	@Override
	public List<AssetDto> getallAsset() {
		List<Asset> assets = assetRepository.findAll();
		List<AssetDto> assetDtos = new ArrayList<AssetDto>();
		for (Asset asset : assets) {
			AssetDto assetDto = new AssetDto();
			assetDto.setAssetId(asset.getAssetId());
			assetDto.setAssetName(asset.getAssetName());
			assetDto.setAssetModel(asset.getAssetModel());
			assetDtos.add(assetDto);
		}
		return assetDtos;
	}

	@Override
	public AssetDto getAssetById(Long assetId) {
		Asset asset = assetRepository.findById(assetId).get();
		AssetDto assetDto = new AssetDto();
		assetDto.setAssetId(asset.getAssetId());
		assetDto.setAssetModel(asset.getAssetModel());
		assetDto.setAssetName(asset.getAssetName());
		return assetDto;
	}

}
