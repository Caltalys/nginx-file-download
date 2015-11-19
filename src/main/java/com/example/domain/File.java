package com.example.domain;

public class File extends Base {

	private Long id;
	private String dir;
	private String name;
	private Long size;
	private Long productionId;
	private Long sdCardPriceFen;
	private String md;
	private Long fileServiceGroupId;
	
	public void resetBy(File another) {
		super.resetBy(another);
		setId(another.getId());
		setDir(another.getDir());
		setName(another.getName());
		setSize(another.getSize());
		setProductionId(another.getProductionId());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		if (dir == null) {
			this.dir = EMPTY_STRING;
		}
		dir = dir.trim();
		this.dir = dir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getProductionId() {
		return productionId;
	}

	public void setProductionId(Long productionId) {
		this.productionId = productionId;
	}

	public Long getSdCardPriceFen() {
		return sdCardPriceFen;
	}

	public void setSdCardPriceFen(Long sdCardPriceFen) {
		this.sdCardPriceFen = sdCardPriceFen;
	}

	public String getMd() {
		return md;
	}

	public void setMd(String md) {
		this.md = md;
	}

	public Long getFileServiceGroupId() {
		return fileServiceGroupId;
	}

	public void setFileServiceGroupId(Long fileServiceGroupId) {
		this.fileServiceGroupId = fileServiceGroupId;
	}

}
