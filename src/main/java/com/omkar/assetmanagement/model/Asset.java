package com.omkar.assetmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="asset_id")
	private long assetId;
	
	@Column(name="asset_name")
	private String assetName;
	
	@Column(name="asset_model")
	private String assetModel;
}
