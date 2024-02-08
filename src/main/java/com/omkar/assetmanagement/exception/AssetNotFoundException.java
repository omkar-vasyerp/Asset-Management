package com.omkar.assetmanagement.exception;

public class AssetNotFoundException extends RuntimeException {

	public AssetNotFoundException(String message) {
		super(message);
	}
	
	public AssetNotFoundException() {
		
	}
}
