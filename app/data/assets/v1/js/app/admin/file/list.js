$(function() {
	init();
});

function init() {
	doGetAll(function(result) {
		initTable(extractContent(result));
	});
}

function initTable(result) {
	$('#tbody').empty();
	var len = result.length;
	for (var i = 0; i < len; ++i) {
		var elem = result[i];
		var buffer = [];
		if (i % 2 == 0) {
			buffer.push('<tr class="active">');
		} else {
			buffer.push('<tr>');
		}

		var id = elem['id'];
		buffer.push('<td>', id, '</td>');
		var enabled = elem['enabled'];
		buffer.push('<td>', enabled, '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['createdAt']), '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['updatedAt']), '</td>');
		buffer.push('<td>', elem['dir'], '</td>');
		buffer.push('<td>', elem['name'], '</td>');
		buffer.push('<td style="text-align: right;">',
				elem['sdCardPriceFen'] / 100, '</td>');
		buffer.push('<td>', elem['productionId'], '</td>');
		var sizeInMB = parseInt(elem['size'] / 1024 / 1024);
		buffer.push('<td style="text-align: right;">', sizeInMB, '</td>');
		buffer.push('<td>', elem['md'], '</td>');
		buffer.push('<td>', elem['fileServiceGroupId'], '</td>');
		// ----------
		buffer.push('<td>');
		buffer.push('<a href="/admin/files/edit?id=' + id,
				'" class="btn btn-primary btn-xs">编辑</a>');
		buffer.push('&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;');
		if (enabled == true) {
			buffer.push('<a href="#" onclick="javascript:disable(' + id,
					');" class="btn btn-warning btn-xs">停用</a>');
		} else {
			buffer.push('<a href=#" onclick="javascript:enable(' + id,
					');" class="btn btn-success btn-xs">启用</a>');
		}
		buffer.push('</td>');
		// ----------
		buffer.push('&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;');
		buffer.push('<a href="/download/' + elem['name'] + '?fileId=' + id
				+ '&uuid=', UUID.generate(),
				'" class="btn btn-info btn-xs">下载</a>');
		buffer.push('</td>');

		// ----------
		buffer.push('</tr>');
		var newRow = buffer.join('');
		$('#tbody').append(newRow);
	}
}

function disable(id) {
	doDisable(id, function(data) {
		showAppModelForOk();
		init();
	});
}

function enable(id) {
	doEnable(id, function(data) {
		showAppModelForOk();
		init();
	});
}
