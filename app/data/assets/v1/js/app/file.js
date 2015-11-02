$(function() {
	$.get("/api/files", function(result) {
		initTable(result['content']);
	});
});

function initTable(result) {
	for (var i = 0, len = result.length; i < len; ++i) {
		var elem = result[i];
		var table = $('#table');
		var buffer = [];
		if (i % 2 == 0) {
			buffer.push('<tr class="active">');
		} else {
			buffer.push('<tr>');
		}
		var id = elem['id'];
		var sizeInMB = parseInt(elem['size'] / 1024 / 1024);
		buffer.push('<td>', id, '</td>');
		buffer.push('<td>', elem['name'], '</td>');
		buffer.push('<td>', elem['createdAt'], '</td>');
		buffer.push('<td style="text-align: right;">', sizeInMB, '</td>');
		buffer.push('<td><a href="/download/' + elem['name'] + '?fileId=' + id
				+ '&uuid=', UUID.generate(),
				'" class="btn btn-primary btn-xs">下载</a></td>');
		buffer.push('<td><a href="/i/sd-card-orders/new?fileId=' + id,
				'" class="btn btn-primary btn-xs">下单</a></td>');
		buffer.push('/<tr>');
		var newRow = buffer.join('');
		$('#table tr:last').after(newRow);
	}
}
