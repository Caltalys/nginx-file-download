$(function() {
	$.get("/api/admin/dld-tasks", function(result) {
		initTable(result['content']);
	});
});

function initTable(result) {
	var len = result.length;
	for (var i = 0; i < len; ++i) {
		var elem = result[i];
		var buffer = [];
		if (i % 2 == 0) {
			buffer.push('<tr class="active">');
		} else {
			buffer.push('<tr>');
		}

		buffer.push('<td>', elem['id'], '</td>');
		buffer.push('<td>');
		buffer.push('<a href="/files/' + elem['fileId'] + '" target="_blank">',
				elem['fileId'], '</a>');
		buffer.push('</td>');
		buffer.push('<td>', elem['userId'], '</td>');
		buffer.push('<td>', elem['clientIp'], '</td>');
		buffer.push('<td>', iso8601ToHuman(elem['createdAt']), '</td>');
		var timeCostSeconds = parseInt(elem['timeCostMillis'] / 1000);
		var hours = parseInt(timeCostSeconds / 3600);
		var minutes = parseInt((timeCostSeconds - 3600 * hours) / 60);
		if (minutes < 10) {
			minutes = '0' + minutes;
		}
		var seconds = parseInt(timeCostSeconds - 3600 * hours - 60 * minutes);
		if (seconds < 10) {
			seconds = '0' + seconds;
		}
		buffer.push('<td style="text-align: right;">', hours, 'h ', minutes,
				'm ', seconds, 's', '</td>');

		buffer.push('</tr>');
		var newRow = buffer.join('');
		$('#tbody').append(newRow);
	}
}
