<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>文件上传</title>
</head>

<body>
	<form action="${pageContext.request.contextPath}/OtaFileUploadServlet" enctype="multipart/form-data" method="post">
		版本号：<input type="text" name="version" value="${newVersion}"><br /> <br /> 上传文件：<input type="file" name="file"><br /> <br /> <input type="submit" value="提交">
	</form>
</body>
</html>