$(function() {
	$.get("/api/i/sd-card-orders", function(result) {
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
		var createdAt = iso8601ToHuman(elem['createdAt']);
		buffer.push('<td>', elem['id'], '</td>');
		buffer.push('<td>', createdAt, '</td>');
		buffer.push('<td>', elem['status'], '</td>');
		buffer.push('<td><a href="/files/' + elem['fileId'] + '" target="_blank">',
				elem['fileId'], '</a></td>');
		buffer.push('<td style="text-align: right;">', elem['priceFen'] / 100, '</td>');
		buffer.push('<td>', elem['username'], '</td>');
		buffer.push('<td>', elem['userAddr'], '</td>');
		buffer.push('<td>', elem['userZipCode'], '</td>');
		buffer.push('<td>', elem['userMobile'], '</td>');
		buffer.push('<td>', elem['userEmail'], '</td>');
		buffer.push('/<tr>');
		var newRow = buffer.join('');
		$('#table tr:last').after(newRow);
	}
}
