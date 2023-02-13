<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>여러파일 업로드결과</title>
	</head>
	<body>
		<h3>여러파일 업로드결과 </h3>
		C:\springWorkspace\upload폴더에서 확인하세요<br><br><br>
		
		<c:forEach var="file" items="${originalFileNameList }">
		${file }<br>
		</c:forEach>
		
		
		<br><br><br>
		<a href="<c:url value='/'/>">메인 화면으로 이동</a>
 


	</body>
</html>
