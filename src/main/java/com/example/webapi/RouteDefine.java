package com.example.webapi;

public final class RouteDefine {

	private RouteDefine() {
	}

	public static final String ROOT = "/";

	public static final String ADMIN = ROOT + "admin";

	public static final String API = ROOT + "api";

	// -------------------------------------------------------------------------
	public static final String BASE_ASSETS = ROOT + "assets";
	public static final String ASSETS_VERSION = BASE_ASSETS + "/{version}";
	public static final String ASSETS_VERSION_IMAGES = ASSETS_VERSION
			+ "/images";
	public static final String ASSETS_VERSION_CSS = ASSETS_VERSION + "/css";
	public static final String ASSETS_VERSION_JAVASCRIPT = ASSETS_VERSION
			+ "/js";

	// -------------------------------------------------------------------------
	public static final String API_ADMIN = API + ADMIN;
	public static final String API_ADMIN_DOWNLOAD_TASKS = API_ADMIN
			+ "/dld-tasks";
	public static final String API_ADMIN_SD_CARD_ORDERS = API_ADMIN
			+ "/sd-card-orders";
	public static final String API_ADMIN_PRODUCTIONS = API_ADMIN
			+ "/productions";

	// -------------------------------------------------------------------------
	public static final String API_FILES = API + "/files";

	// -------------------------------------------------------------------------
	public static final String API_I = API + "/i";
	public static final String API_I_DOWNLOAD_TASKS = API_I + "/dld-tasks";
	public static final String API_I_SD_CARD_ORDERS = API_I + "/sd-card-orders";
	
	// -------------------------------------------------------------------------
	public static final String ADMIN_DOWNLOAD_TASKS = ADMIN + "/dld-tasks";
	public static final String ADMIN_SD_CARD_ORDERS = ADMIN + "/sd-card-orders";
	public static final String ADMIN_PRODUCTIONS = ADMIN + "/productions";

	// -------------------------------------------------------------------------
	public static final String DOWNLOAD = "/download";

	public static final String FILES = ROOT + "files";

	public static final String I = ROOT + "i";
	public static final String I_DOWNLOAD_TASKS = I + "/dld-tasks";
	public static final String I_SD_CARD_ORDERS = I + "/sd-card-orders";
	public static final String I_SD_CARD_ORDERS_NEW = I_SD_CARD_ORDERS + "/new";

	public static final String LOGIN = ROOT + "login";
	public static final String LOGOUT = ROOT + "logout";

	public static final String SYSTEM = ROOT + "system";
	public static final String SYSTEM_UUID = SYSTEM + "/uuid";
	public static final String SYSTEM_VERSION = SYSTEM + "/version";

}
