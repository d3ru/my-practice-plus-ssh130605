// JavaScript Documentvar cart;
// ��ȡĳ��Cookie
var isOver = true;
function getCookieValue(cookieName) {
	var cookieValue = document.cookie;
	var cookieStartsAt = cookieValue.indexOf(" " + cookieName + "=");
	if (cookieStartsAt == -1) {
		cookieStartsAt = cookieValue.indexOf(cookieName + "=");
	}
	if (cookieStartsAt == -1) {
		cookieValue = null;
	} else {
		cookieStartsAt = cookieValue.indexOf("=", cookieStartsAt) + 1;
		var cookieEndsAt = cookieValue.indexOf(";", cookieStartsAt);
		if (cookieEndsAt == -1) {
			cookieEndsAt = cookieValue.length;
		}
		cookieValue = unescape(cookieValue.substring(cookieStartsAt,
				cookieEndsAt));
	}
	return cookieValue;
}
// ���û�ѡ������Ʒ���õ��ͻ��˵�Cookie

function setCookie(sName, sValue, oExpires) {

	// Ӧ���ж���Cookie�Ƿ��Ѿ����ڣ����������ۼӣ���
	var reValue = getCookieValue(sName);
	var num = 0;
	if (reValue) {
		num = eval(reValue);
		num += eval(sValue);
	} else {
		num = eval(sValue);
	}
	var sCookie = sName + "=" + encodeURIComponent(num);
	if (oExpires) {
		sCookie += ";expires=" + oExpires.toGMTString();
	}
	document.cookie = sCookie;
}
function getCookie(sName, sValue) {
	var sRE = "(?:;)?" + sName + "=([^;]*);?";
	var oRE = new RegExp(sRE);
	if (oRE.test(document.cookie)) {
		return decodeURIComponent(RegExp["$1"]);
	} else {
		return null;
	}

}
function deleteCookie(sName) {
	setCookie(sName, "", new Date(0));
}
function deleteCookieCart(sName) {
	setCookie(sName, "", new Date(0));
	ShopCartsShow();
}

var cart;
// ��һ����������Ʒ�ɣģ��ڶ���������������
// ԭ���Ϸ�����û����Ӧ���ͻ��˲�������ӡ�
function ShopCarts(id, num) {
	shopsWindow.style.display = "block";
	if (isOver) {
		isOver = false;
		var sName = "pro_" + id;
		/*
		 * ����Ϣ�����ڱ���Cookie�У� ͨ���첽���������������������� �ڷ�����ͨ��Request�õ�Cookie��ѡ����Ʒ��Ϣ��
		 * Ȼ�������ݿ��еõ���Ʒ�۸��ܼƳ��ܼ�. ��ͨ���첽���󷵻ص��ͻ��ˡ�
		 */
		setCookie(sName, num);// ����û�����ù���ʱ�䡣
		// showShopWindow();
		try {
			cart = new XMLHttpRequest();
		} catch (er) {
			try {
				cart = new ActiveXObject("MSXML2.XMLHTTP");
			} catch (ex) {
				try {
					cart = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (ep) {
					cart = null;
				}
			}
		}
		cart.open("get", "shopcart.jsp?dt=" + new Date());
		cart.onreadystatechange = function() {
			var state = cart.readyState;
			if (state == 4) {
				var sta = cart.status;
				if (sta == 200) {
					var data = cart.responseText;
					shops_carts.innerHTML = data;
					// showShopWindow();
					// ��ȡ���ݺ�ǿ����ʾ
					shopsWindow.style.display = "block";
					isOver = true;
				} else
					alert("�����Ʒ�����ﳵʧ�ܣ�������ѡ��![" + sta + "]");
			}
		}
		cart.send(null);
	}
}
var cart_h;
// ����AJAX�ӷ�������ȡѡ������Ʒ��Ϣ
function ShopCartsShow() {
	// ��ʼ���첽�������
	try {
		cart_h = new XMLHttpRequest();
	} catch (er) {
		try {
			cart_h = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (ex) {
			try {
				cart_h = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (ep) {
				cart_h = null;
			}
		}
	}
	// �������󡣼������Ǳ�֤�ɣ�ˢ������
	cart_h.open("get", "shopcart.jsp?da=" + new Date());
	cart_h.onreadystatechange = function() {
		var state = cart_h.readyState;
		if (state == 4) {
			var sta = cart_h.status;
			if (sta == 200) {
				// ��������ݣ����뵽���ﳵ�����div��.
				var data = cart_h.responseText;
				shops_carts.innerHTML = data;
				// ������Ϻ�ǿ����ʾһ�¡�
				// showShopWindow();
				shopsWindow.style.display = "block";
			} else
				alert("ѡ����Ʒ��Ϣ����ʧ�ܣ�������ˢ��![" + sta + "]");
		}
	}
	cart_h.send(null);
}
var cart_c;
// ��չ��ﳵ��
function ShopCartsClear() {
	try {
		cart_c = new XMLHttpRequest();
	} catch (er) {
		try {
			cart_c = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (ex) {
			try {
				cart_c = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (ep) {
				cart_c = null;
			}
		}
	}
	cart_c.open("get", "clearsc.jsp?da=" + new Date());
	cart_c.onreadystatechange = function() {
		var state = cart_c.readyState;
		if (state == 4) {
			var sta = cart_c.status;
			if (sta == 200) {
				// ���ѡ����Ʒ�󣬻������ݡ�
				var data = cart_c.responseText;
				shops_carts.innerHTML = data;
				// ������Ϻ�ǿ����ʾһ�¡�
				// showShopWindow();
				shopsWindow.style.display = "block";
			} else
				alert("ѡ����Ʒ���ʧ�ܣ�������ˢ����![" + sta + "]");
		}
	}
	cart_c.send(null);
}
// id��Ʒid
// num��Ʒ����
// n��Ʒ˳��
var cartupdate;
function CheckNum(id, num, n) {
	try {
		cartupdate = new XMLHttpRequest();
	} catch (er) {
		try {
			cartupdate = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (ex) {
			try {
				cartupdate = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (ep) {
				cartupdate = null;
			}
		}
	}

	var vpp = eval("num" + n);
	var url = "?id=" + id;
	url += "&num=" + num;
	url += "&method=add";
	url += "&modify=true";
	url += "&date=" + new Date();
	cartupdate.open("get", "shopcart.jsp" + url);
	cartupdate.onreadystatechange = function() {
		var state = cartupdate.readyState;
		if (state == 4) {
			var sta = cartupdate.status;
			if (sta == 200) {
				var data = cartupdate.responseText;
				vpp.innerHTML = data;
				if (data.length == 38) {
					var typenum = (shops_carts.all.length - 3) / 14;
					var price = eval("price" + id);
					var money = eval("money" + n);
					money.innerHTML = price.innerHTML * num;
					var re = 0.0;
					for (var i = 0; i < typenum; i++) {
						var pmoney = eval("money" + i);
						re = re + pmoney.innerHTML * 1;
					}
					allmoney.innerHTML = re;
				}

			} else
				alert("please reflush this page![" + sta + "]");
		}
	}
	cartupdate.send(null);
}
/*
 * function accountSubmit(){ var typenum=(shops_carts.all.length-3)/14; for(var
 * i=0;i<typenum;i++){ var vpp=eval("num"+i); var len=vpp.innerHTML.length;
 * if(len==85){ alert("\u6570\u91cf\u6709\u8bef,\u8bf7\u68c0\u67e5!"); return; } }
 * alert("ok"); }
 */
function accountSubmit(proName) {
	location.replace(proName + "/userinfo/order_manager.jsp?method=add");
}
// ��ʾ���ﳵ�ڲ����塣
function showShopWindow() {
	if (shopsWindow.style.display = "none") {
		shopsWindow.style.display = "block";
		ShopCartsShow();// ��ȡ���Է�������ѡ����Ʒ��
	}
}

function closeShopWindow() {
	if (shopsWindow.style.display = "block")
		shopsWindow.style.display = "none";
}