<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.do">DCODLAB</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">인기글</a></li>

					<li class="nav-item"><a class="nav-link disabled"
						aria-disabled="true">게시판</a></li>
				</ul>
				
			</div>
		</div>
	</nav>

<div align="center" style="width: 100%; margin-top: 30px;">
    <div class="mb-3" style="width: 70%; margin: 0 auto;">
        <form method="post" action="${action}" style="text-align: left;">
            <label for="exampleFormControlInput1" class="form-label">제목</label><br>
            <input type="text" name="title" maxlength="80" value="${board.title}" class="form-control" id="exampleFormControlInput1" placeholder="제목을 입력해주세요"><br>
            <label for="exampleFormControlTextarea1" class="form-label">내용</label><br>
            <textarea class="form-control" id="exampleFormControlTextarea1" name="content" rows="10">${board.content}</textarea><br>
             <div style="text-align: right;">
            <input type="submit" class="btn btn-primary" value="저장" style="text-align: right">
            <input type="button" class="btn btn-secondary" value="취소" style="text-align: right" onclick="history.back()">
            </div>
        </form>
    </div>
</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

</body>
</html>
