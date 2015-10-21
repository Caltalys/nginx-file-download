package com.example.web;

public final class RouteDefine {

	private RouteDefine() {
	}

	public static final String ROOT = "/";

	public static final String ADMIN = "admin";

	public static final String API = ROOT + "api";

	public static final String ADMIN_API = API + "/" + ADMIN;
	public static final String ADMIN_API_DOWNLOAD_TASKS = ADMIN_API
			+ "/dld-tasks";
	public static final String ADMIN_API_SD_CARD_ORDERS = ADMIN_API
			+ "/sd-card-orders";

	public static final String API_FILES = API + "/files";

	public static final String ASSETS = ROOT + "assets";
	public static final String ASSETS_IMAGES = ASSETS + "/images";

	public static final String SYSTEM = ROOT + "system";
	public static final String SYSTEM_UUID = SYSTEM + "/uuid";
	public static final String SYSTEM_VERSION = SYSTEM + "/version";

}
