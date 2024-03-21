<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
<title>top page</title>
<style>
h1 {
	font-size: 18pt;
	font-weight: bold;
	color: gray;
}

body {
	font-size: 13pt;
	color: gray;
	margin: 5px 25px;
}

tr {
	margin: 5px;
}

th {
	padding: 5px;
	color: white;
	background: darkgray;
}

td {
	padding: 5px;
	color: black;
	background: #e0e0ff;
}

.err {
	color: red;
}
</style>
</head>
<body>
	<h1><a href="/">Hello page</a></h1>
	<p></p>



	<c:choose>
		<c:when test="${SelectedMember.id != null}">
			<form method="post" action="/mUpdateButton">
				<table>
					<tr>
						<td><label for="name">이름</label></td>
						<td><input type="text" name="name"
							value="${SelectedMember.name}" /></td>
					</tr>
					<tr>
						<td><label for="age">연령</label></td>
						<td><input type="text" name="age"
							value="${SelectedMember.age}" /></td>
							
					</tr>
					<tr>
						<td><label for="mail">메일</label></td>
						<td><input type="text" readonly name="email"
							value="${SelectedMember.email}" /></td>
					</tr>
					<tr>
						<td><label for="memo">메모</label></td>
						<td><textarea name="memo" cols="20" rows="5">${SelectedMember.memo}</textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="수정"/></td>
					</tr>
				</table>
			</form>
		</c:when>

		<c:otherwise>
			<form method="post" action="/mInsertButton">
				<table>
					<tr>
						<td><label for="name">이름</label></td>
						<td><input type="text" name="name" /></td>

					</tr>
					<tr>
						<td><label for="age">연령</label></td>
						<td><input type="text" name="age"></td>
					</tr>
					<tr>
						<td><label for="mail">메일</label></td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td><label for="memo">메모</label></td>
						<td><textarea name="memo" cols="20" rows="5"></textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="등록" /></td>
					</tr>
				</table>
			</form>
		</c:otherwise>
	</c:choose>

	<hr />
	<table>
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>나이</th>
			<th>이메일</th>
			<th>메모</th>
			<th>편집</th>
		</tr>
		<c:forEach var="member" items="${MemberList}">
			<tr>
				<td>${member.id}</td>
				<td>${member.name}</td>
				<td>${member.age}</td>
				<td>${member.email}</td>
				<td>${member.memo}</td>
				<td>
				<a href="mEditButton?id=${member.id}" class="btn btn-warning">수정</a>
				<a href="mDeleteButton?id=${member.id}" class="btn btn-warning">삭제</a> 
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
