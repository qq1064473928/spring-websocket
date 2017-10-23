<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>

<!doctype html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>测试</title>
    <!--移动端版本兼容 -->
	<script type="text/javascript">
		var jsVer = 22;
		var phoneWidth = parseInt(window.screen.width);
		var phoneScale = phoneWidth/640;
		var ua = navigator.userAgent;
		if (/Android (\d+\.\d+)/.test(ua)){
			var version = parseFloat(RegExp.$1);
			if(version > 2.3){ // andriod 2.3
				document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
			}else{ // andriod 2.3以上
				document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
			}
		}  else { // 其他系统
			document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
		}
	</script>
	<!--移动端版本兼容 end -->
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<legend>当前时间</legend>
  	<form>
	  	<input type="text" id="time" value="" />
	    <input type="button" value="查询数据库时间" onclick="getCurrentTimeFn()" />
  	</form>
    <script src="${path }/js/jquery-1.10.2.min.js"></script>
    <script>
    	function getCurrentTimeFn(){
    		$.ajax({
    			type : "post",
    			url : "${path }/yundao/getCurrentTime.action",
    			data : null,
    			dataType : "json",
    			success : function (data){
    				$("#time").val(data.time);
    			}
    		});    		
    	}
    </script>
  </body>
</html>
