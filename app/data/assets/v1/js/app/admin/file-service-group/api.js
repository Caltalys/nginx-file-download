const ROUTE = '/api/admin/fsgroups/';

function doGetAll(func) {
	$.get(ROUTE, func);
}

function doDisable(id, func) {
	$.post(ROUTE + id + '/disable', func);
}

function doEnable(id, func) {
	$.post(ROUTE + id + '/enable', func);
}
