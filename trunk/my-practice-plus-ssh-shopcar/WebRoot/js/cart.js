// JavaScript Documentvar cart;
// 获取某个Cookie
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
// 把用户选购的商品设置到客户端的Cookie

function setCookie(sName, sValue, oExpires) {

	// 应该判定该Cookie是否已经存在，存在数量累加１。
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
// 第一个参数是商品ＩＤ，第二个参数是数量。
// 原则上服务器没有相应，客户端不能再添加。
function ShopCarts(id, num) {
	shopsWindow.style.display = "block";
	if (isOver) {
		isOver = false;
		var sName = "pro_" + id;
		/*
		 * 把信息放置在本地Cookie中， 通过异步请求对象向服务器发送请求， 在服务器通过Request得到Cookie中选购商品信息。
		 * 然后在数据库中得到商品价格，总计出总价. 并通过异步对象返回到客户端。
		 */
		setCookie(sName, num);// 这里没有设置过期时间。
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
					// 获取数据后强制显示
					shopsWindow.style.display = "block";
					isOver = true;
				} else
					alert("添加商品到购物车失败，请重新选购![" + sta + "]");
			}
		}
		cart.send(null);
	}
}
var cart_h;
// 利用AJAX从服务器获取选购的商品信息
function ShopCartsShow() {
	// 初始化异步请求对象。
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
	// 发起请求。加日期是保证ＩＥ刷新请求。
	cart_h.open("get", "shopcart.jsp?da=" + new Date());
	cart_h.onreadystatechange = function() {
		var state = cart_h.readyState;
		if (state == 4) {
			var sta = cart_h.status;
			if (sta == 200) {
				// 请求的数据，加入到购物车窗体的div中.
				var data = cart_h.responseText;
				shops_carts.innerHTML = data;
				// 请求完毕后，强制显示一下。
				// showShopWindow();
				shopsWindow.style.display = "block";
			} else
				alert("选购商品信息访问失败，请重新刷新![" + sta + "]");
		}
	}
	cart_h.send(null);
}
var cart_c;
// 清空购物车。
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
				// 清空选购商品后，回显数据。
				var data = cart_c.responseText;
				shops_carts.innerHTML = data;
				// 请求完毕后，强制显示一下。
				// showShopWindow();
				shopsWindow.style.display = "block";
			} else
				alert("选购商品清空失败，请重新刷新下![" + sta + "]");
		}
	}
	cart_c.send(null);
}
// id商品id
// num商品数量
// n商品顺序
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
// 显示购物车内部窗体。
function showShopWindow() {
	if (shopsWindow.style.display = "none") {
		shopsWindow.style.display = "block";
		ShopCartsShow();// 获取来自服务器的选购商品。
	}
}

function closeShopWindow() {
	if (shopsWindow.style.display = "block")
		shopsWindow.style.display = "none";
}