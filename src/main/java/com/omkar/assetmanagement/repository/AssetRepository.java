package com.omkar.assetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkar.assetmanagement.model.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long>{
	
}
