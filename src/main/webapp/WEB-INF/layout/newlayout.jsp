<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title"/></title>
<style type="text/css">
body {
	padding-top: 125px;
	padding-bottom: 125px;
	repeat: none;
	background-image: url("https://dl.dropboxusercontent.com/u/106456165/url.png");
}

.button {
	-moz-box-shadow: inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow: inset 0px 1px 0px 0px #ffffff;
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ededed
		), color-stop(1, #99CCFF));
	background: -moz-linear-gradient(center top, #ededed 5%, #dfdfdf 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#B8DBFF',
		endColorstr='#99CCFF');
	background-color: #ededed;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	border: 1px solid #99CCFF;
	display: inline-block;
	color: #6699FF;
	font-family: arial;
	font-size: 15px;
	font-weight: bold;
	padding: 6px 24px;
	text-decoration: none;
	text-shadow: 2px 2px 0px #ffffff;
}

.button:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #dfdfdf
		), color-stop(1, #ededed));
	background: -moz-linear-gradient(center top, #dfdfdf 5%, #ededed 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#dfdfdf',
		endColorstr='#ededed');
	background-color: #dfdfdf;
}

.button:active {
	position: relative;
	top: 1px;
}

.newUser {
	font-family: 'calibri';
	font-weight: bold;
	font-size: 20px;
}

a {
	text-decoration: none;
	color: black;
}

a:HOVER {
	color: #527ACC;
}

.main {
	padding-top: 100px;
	text-align: center;
}

input.rounded {
	border: 1px solid #ccc;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
	-moz-box-shadow: 2px 2px 3px #666;
	-webkit-box-shadow: 2px 2px 3px #666;
	box-shadow: 2px 2px 3px #666;
	font-size: 20px;
	padding: 4px 7px;
	outline: 0;
	-webkit-appearance: none;
	font-family: 'prosto';
}

input.rounded:focus {
	border-color: #527ACC;
	font-family: 'prosto';
}
</style>
</head>
<body>
<table>
<tr>
<td width="30%">
<tiles:insertAttribute name="menu"/>
</td>
<td width="70%">
<tiles:insertAttribute name="body"/>
</td>
<td>
<tiles:insertAttribute name="right"/>
</td>
</tr>
</table>
</body>
</html>