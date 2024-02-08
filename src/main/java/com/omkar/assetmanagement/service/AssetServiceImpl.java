package com.omkar.assetmanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omkar.assetmanagement.dto.AssetDto;
import com.omkar.assetmanagement.exception.AssetNotFoundException;
import com.omkar.assetmanagement.model.Asset;
import com.omkar.assetmanagement.repository.AssetRepository;

@Service
public class AssetServiceImpl implements AssetService {

	private AssetRepository assetRepository;

	public AssetServiceImpl(AssetRepository assetRepo) {
		assetRepository = assetRepo;
	}

	@Override
	public boolean deleteAssetById(Long assetId) {
		if(assetRepository.existsById(assetId)) {	
			assetRepository.deleteById(assetId);
		}
		else {
			throw new AssetNotFoundException("Asset Not Found With Id: "+assetId);
		}
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
		Asset asset = assetRepository.findById(assetId).orElseThrow(()->new AssetNotFoundException("Asset Not Found With Id: "+assetId));
		AssetDto assetDto = new AssetDto();
		assetDto.setAssetId(asset.getAssetId());
		assetDto.setAssetModel(asset.getAssetModel());
		assetDto.setAssetName(asset.getAssetName());
		return assetDto;
	}

	private static final String UPLOAD_DIR = "D:\\Assets-Uploads";

	public ResponseEntity<Object> downloadFile(String fileName) {
		ResponseEntity<Object> response = null;
		try {

			Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
			File file = filePath.toFile();

			if (file.exists()) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment", fileName);

				response = ResponseEntity.ok().headers(headers).contentLength(file.length())
						.body(new FileSystemResource(file));
			} else {
				response = ResponseEntity.notFound().build();
			}
		} catch (Exception ex) {
			response = ResponseEntity.status(500).body("Failed to download the file: " + ex.getMessage());
		}

		return response;
	}

	@Override
	public String uploadFile(MultipartFile file) {
		String fileName;
		try {
			 
			File directory = new File(UPLOAD_DIR);
			if (!directory.exists()) {
				directory.mkdirs();
			}
 
			if (file.getContentType().equals("text/plain")) {
				fileName = "info.txt";
				Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
				if (!filePath.toFile().exists()) {
					filePath.toFile().createNewFile();
				}
				Files.write(filePath, file.getBytes(), StandardOpenOption.APPEND);
			} else {
 
				fileName = file.getOriginalFilename();
				Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
				Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);
			}
 
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/asset/download/")
					.path(fileName).toUriString();
 
			System.out.println(file.getContentType());
 
			return ("File uploaded successfully. Download URL: " + fileDownloadUri);
		} catch (IOException e) {
			return e.getMessage();
		}
	}
}
