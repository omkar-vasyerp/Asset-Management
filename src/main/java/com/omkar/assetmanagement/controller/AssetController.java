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
import com.omkar.assetmanagement.dto.ResponseDto;
import com.omkar.assetmanagement.model.Asset;
import com.omkar.assetmanagement.service.AssetService;

@RestController
@RequestMapping("/api/asset")
public class AssetController {
	
	private AssetService assetService;
	public AssetController(AssetService assetService) {
		this.assetService=assetService;
	}
	
	@GetMapping("/all")
	public ResponseDto getAllAsset(){
		List<AssetDto> assetDtos =assetService.getallAsset();
		return new ResponseDto(200, "Success", assetDtos);
	}
	
	@PostMapping({"/add","/update"})
	public ResponseDto  addAsset(@RequestBody AssetDto assetDto){
		assetService.addAsset(assetDto); 
		return new ResponseDto(200, "Success", "Add Successful");
	}
	
	@DeleteMapping("/delete")
	public ResponseDto deleteAsset(@RequestParam Long assetId){
		assetService.deleteAssetById(assetId);
		return new ResponseDto(200, "Success", "Delete Succesful");
	}
	
	@GetMapping("/get")
	public ResponseDto getAssetById(@RequestParam Long assetId){
		AssetDto assetdto =assetService.getAssetById(assetId);
		return new ResponseDto(200, "Success", assetdto);
	}
		
	@PostMapping("/upload")
	public ResponseDto uploadFile(@RequestParam MultipartFile file){
		assetService.uploadFile(file);
		return new ResponseDto(200, "Success", "Upload Successful");
	}
	
	@GetMapping("/download/{name}")
	public ResponseDto downloadFile(@PathVariable String name){
		Object obj= assetService.downloadFile(name);
		return new ResponseDto(200, "Success", obj);
	}
	
}
