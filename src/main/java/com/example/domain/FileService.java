package com.example.domain;

public class FileService extends Base {

	private Long id;
	private String host;
	private FileServiceGroup group;
	private String name;
	
	@Override
	public String toString() {
		return super.toString() + " FileService [id=" + id + ", host=" + host + ", group=" + group + ", name=" + name
				+ "]";
	}

	@Override
	public void reset() {
		super.reset();
		setGroup(new FileServiceGroup());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileServiceGroup getGroup() {
		return group;
	}

	public void setGroup(FileServiceGroup group) {
		this.group = group;
	}

}
