// 创建request对象
function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();//大多数浏览器
	} catch (e) {
		try {
			return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
		} catch (e) {
			try {
				return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
			} catch (e) {
				alert("您的浏览器不支持!!");
				throw e;
			}
		}
	}
}
/*
 * option对象有如下属性
 */
 		/*请求方式*///method, 
		/*请求的url*/// url, 
		/*是否异步*///asyn, 
		/*请求体*///params, 
		/*回调方法*///callback,
		/*服务器响应数据转换成什么类型*///type

function ajax(option) {
	/*
	 * 1. 得到xmlHttp
	 */
	var xmlHttp = createXMLHttpRequest();
	/*
	 * 2. 打开连接
	 */
	if(!option.method) {//默认为GET请求
		option.method = "GET";
	}
	if(option.asyn == undefined) {//默认为异步处理
		option.asyn = true;
	}
	xmlHttp.open(option.method, option.url, option.asyn);
	/*
	 * 3. 判断是否为POST
	 */
	if("POST" == option.method) {
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	}
	/*
	 * 4. 发送请求
	 */
	xmlHttp.send(option.params);
	
	/*
	 * 5. 注册监听
	 */
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {//双重判断
			var data;
			// 获取服务器的响应数据，进行转换！
			if(!option.type) {//如果type没有赋值，那么默认为文本
				data = xmlHttp.responseText;
			} else if(option.type == "xml") {
				data = xmlHttp.responseXML;
			} else if(option.type == "text") {
				data = xmlHttp.responseText;
			} else if(option.type == "json") {
				var text = xmlHttp.responseText;
				data = eval("(" + text + ")");
			}
			
			// 调用回调方法
			option.callback(data);
		}
	};
};



