<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<form action="/student?cmd=save"  method="POST">
	<input type="hidden" name="id" value="${student.id}">
		姓名：<input type="text" name="name" value="${student.name}"  required="required"/><br/> 
		年龄：<input type="text" name="age" value="${student.age}"   required="required"/><br/><br/> 
		<input type="submit" value="${student == null ? "保存学生信息":"更新学生信息"}">
	</form>
</body>
</html>