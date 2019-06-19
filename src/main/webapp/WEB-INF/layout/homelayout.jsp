<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
</script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><tiles:insertAttribute name="title"/></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/default.css"
	media="screen" />
</head>
<body>
<!-- start header -->
<div id="header">
	<div id="logo">
		<h1><a href="#">iHealthcare Solution </a></h1>
	</div>
	<div id="menu">
		<tiles:insertAttribute name="menu"/>
	</div>
</div>
<hr />
<!-- end header -->
<!-- start page -->
<div id="wrapper">
	<div id="page">
		<!-- start content -->
		<div id="content">
		<tiles:insertAttribute name="body" />
 		</div> 
		<!-- end content -->
		<!-- start sidebar -->
		<div id="sidebar">
			<ul>
				<br/><br/>
				<li id="calendar">
					<h2>Current News</h2>
					<iframe src="http://www.google.com/uds/modules/elements/newsshow/iframe.html?format=300x250"
        			frameborder="0" width="300" height="250"
        			marginwidth="0" marginheight="0">
				</li>
			</ul>
		</div>
		<!-- end sidebar -->
		<br style="clear: both;" />
	</div>
</div>
<!-- end page -->
<!-- start footer -->
<div id="footer">
<p id="legal"> &copy;2013 iHealthcare . All Rights Reserved.
	&nbsp;&nbsp;&bull;&nbsp;&nbsp;
</p>
<!-- end footer -->
</div>
</body>
</html>
