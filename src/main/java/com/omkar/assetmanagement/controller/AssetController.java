package com.omkar.assetmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omkar.assetmanagement.dto.AssetDto;
import com.omkar.assetmanagement.service.AssetService;

@RestController
@RequestMapping("/api/asset")
public class AssetController {
	
	private AssetService assetService;
	public AssetController(AssetService assetService) {
		this.assetService=assetService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AssetDto>> getAllAsset(){
		return ResponseEntity.ok(assetService.getallAsset());
	}
	
	@PostMapping({"/add","/update"})
	public ResponseEntity<String>  addAsset(@RequestBody AssetDto assetDto){
		assetService.addAsset(assetDto); 
		return ResponseEntity.ok("Success");
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAsset(@RequestParam Long assetId){
		assetService.deleteAssetById(assetId);
		return ResponseEntity.ok("Delete Success");
	}
	
	@GetMapping("/get")
	public ResponseEntity<AssetDto> getAssetById(@RequestParam Long assetId){
		return ResponseEntity.ok(assetService.getAssetById(assetId));
		
	}
	
//	@PostMapping("/upload")
//	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file){
//		
//		return ResponseEntity.ok(assetService.uploadFile(file));
//	}
//	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file){
		
		return ResponseEntity.ok(assetService.uploadFile(file));
	}
	
	@GetMapping("/download/{name}")
	public ResponseEntity<Object> downloadFile(@PathVariable String name){
		return assetService.downloadFile(name);
	}
	
}
