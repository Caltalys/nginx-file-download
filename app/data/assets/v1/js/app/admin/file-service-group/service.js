function AdminFileServiceGroupService() {

	var ROUTE = apiRoutePrefixNoSlash() + '/admin/fsgroups/';

	this.getAll = function(func) {
		$.get(ROUTE, func).fail(showAppModelForJqError);
	}

	this.disable = function(id, func) {
		$.post(ROUTE + id + '/disable', func).fail(showAppModelForJqError);
	}

	this.enable = function(id, func) {
		$.post(ROUTE + id + '/enable', func).fail(showAppModelForJqError);
	}
	
	this.remove = function(id, func) {
		$.post(ROUTE + id + '/actions/delete', func).fail(showAppModelForJqError);
	}

}
