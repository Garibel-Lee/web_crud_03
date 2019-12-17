<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title></title>
</head>
<body>
	<a href="/student/edit">添加学生</a>
	<table border="1" width="50%" cellpadding="0" cellspacing="0">
		<tr style="background-color: orange;">
			<th>编号</th>	
			<th>姓名</th>
			<th>年龄</th>
			<td>操作</td>
		</tr>
		<c:forEach items="${students}" var="s" varStatus="vs">
			
	
			<tr style="background-color:${vs.count % 2 == 0 ? "gray":""};">
				<td>${s.id}</td>
				<td>${s.name}</td>
				<td>${s.age}</td>
				<td>
					<a href="/student/delete?id=${s.id}">删除</a>    |
					<a href="/student/edit?id=${s.id}">编辑</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>