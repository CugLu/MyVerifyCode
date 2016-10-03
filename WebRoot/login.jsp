<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/login.css'/>">

<script type="text/javascript">
	function _change() {
		var ele = document.getElementById("vCode");
		ele.src = "<c:url value='/UserServlet'/>?method=verifyCode&xxx="
				+ new Date().getTime();
	}
</script>

</head>

<body background="<c:url value='/image/show.jpg'/>">
	<div class="main">
		<div>
			<div class="imageDiv">
				<img class="img" src="<c:url value='/image/cug.jpg'/>" />
			</div>
			<div class="login1">
				<div class="login2">
					<div class="loginTopDiv">
						<span class="loginTop">登录页面</span> <span> <a
							href="<c:url value='/regist.jsp'/>" class="registBtn"><img
								class="imgr" src="<c:url value='/image/regist2.jpg'/>" /> </a> </span>
					</div>
					<div>
						<form action="<c:url value='/UserServlet?method=login'/>"
							method="post" target="_top">
							<table>
								<tr>
									<td width="50"></td>
									<td><label class="error" id="msg">${msg }</label>
									</td>
								</tr>
								<tr>
									<td width="50">用户名：</td>
									<td><input class="input" type="text" name="username"
										value="${form.username }" />
									</td>
								</tr>
								<tr>
									<td height="20">&nbsp;</td>
									<td><label id="loginnameError" class="error">${errors.username
											}</label>
									</td>
								</tr>
								<tr>
									<td>密&nbsp码：</td>
									<td><input class="input" type="password" name="password"
										value="${form.password }" />
									</td>
								</tr>
								<tr>
									<td height="20">&nbsp;</td>
									<td><label id="loginpassError" class="error">${errors.password
											}</label>
									</td>
								</tr>
								<tr>
									<td>验证码：</td>
									<td><input class="input yzm" type="text" name="verifyCode"
										id="verifyCode" /> <img id="vCode"
										src="<c:url value='/UserServlet?method=verifyCode'/>" /> <a
										id="verifyCode" href="javascript:_change()">换张图</a></td>
								</tr>
								<tr>
									<td height="20px">&nbsp;</td>
									<td><label id="verifyCodeError" class="error">${errors.verifyCode
											}</label>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td align="left"><input type="image" id="submit"
										src="<c:url value='/image/login1.jpg'/>" class="loginBtn" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
