package com.omkar.assetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.omkar.assetmanagement.model.Asset;

@Component
public interface AssetRepository extends JpaRepository<Asset, Long>{
	
}
