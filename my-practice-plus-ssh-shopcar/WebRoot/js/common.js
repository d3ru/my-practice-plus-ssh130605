function showPage(url, page) {
	var urlAddress = url;
	urlAddress += url.indexOf("?") == -1 ? "?page=" + page : "&page=" + page;
	window.location.replace(urlAddress);
}

function isDeleteOrder(url) {
	question = confirm("��ȷ��Ҫɾ�����������?");
	if (question != "0") {
		window.location.replace(url);
	}
}

function productSigned(url, tid) {
	question = confirm("��ȷ���յ���Ʒ����?");
	if (question != "0") {
		var request = null;
		try {
			request = new XMLHttpRequest();
		} catch (e) {
			try {
				request = new ActiveXObject("MSXML2.XMLHTTP");
			} catch (e1) {
				try {
					request = new ActiveXObject("MSXML.XMLHTTP");
				} catch (e2) {
					return null;
				}
			}
		}
		request.open("get", url);

		request.onreadystatechange = function() {
			var state = request.readyState;
			if (state == 4) {
				var sta = request.status;
				if (sta == 200) {
					var obj = eval("isSigned" + tid);
					var data = request.responseText;
					obj.innerHTML = data;
				}
			}
		}
		request.send(null);
	}
}