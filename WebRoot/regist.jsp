<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>注册页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/regist.css'/>">

</head>

<body background="<c:url value='/image/show.jpg'/>">
	<div id="divMain">
		<div id="divTitle">
			<span id="spanTitle">新用户注册</span>
		</div>
		<div id="divBody">
			<form action="<c:url value='/UserServlet?method=regist'/>"
				method="post" id="registForm">
				<table id="tableForm">
					<tr>
						<td class="tdText">用户名：</td>
						<td class="tdInput"><input class="inputClass" type="text"
							name="username" id="username" value="${form.username }" /></td>
						<td class="tdError"><label id="loginnameError" class="error">${errors.username
								}</label></td>
					</tr>
					<tr>
						<td class="tdText">登录密码：</td>
						<td><input class="inputClass" type="password" name="password"
							id="password" value="${form.password }" /></td>
						<td><label id="loginpassError" class="error">${errors.password
								}</label></td>
					</tr>
					<tr>
						<td class="tdText">确认密码：</td>
						<td><input class="inputClass" type="password"
							name="repassword" id="repassword" value="${form.repassword }" />
						</td>
						<td><label id="reloginpassError" class="error">${errors.repassword
								}</label></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="image"
							src="<c:url value='/image/regist1.jpg'/>" id="submitBtn" /></td>
						<td><label></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
