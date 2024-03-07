<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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

	<div align="left" style="width: 100%; margin-top: 30px;">
		<div class="mb-3" style="width: 70%; margin: 0 auto;">

			<form>
				<label for="exampleFormControlInput1" class="form-label">제목</label>
				<br>
				<textarea readonly class="form-control" id="exampleFormControlTextarea1"
					name="title" rows="1">${board.title}</textarea>
							<br>	
					<label for="exampleFormControlInput1" class="form-label">작성자</label>
				<br>
				<textarea readonly class="form-control" id="exampleFormControlTextarea1"
					name="name" rows="1">${board.name}</textarea>
						<br>
					<label for="exampleFormControlInput1" class="form-label">작성일</label>
				<br>
				<textarea readonly class="form-control" id="exampleFormControlTextarea1"
					name="regtime" rows="1">${board.regtime}</textarea>
						<br>
				<label for="exampleFormControlInput1" class="form-label">조회수</label>
				<br>
				<textarea readonly class="form-control" id="exampleFormControlTextarea1"
					name="hits" rows="1">${board.hits}</textarea>
						<br>
				<label for="exampleFormControlInput1" class="form-label">내용</label>
				<br>
				<textarea readonly class="form-control" id="exampleFormControlTextarea1"
					name="content" rows="10">${board.content}</textarea>
				<br> <br>
				<div style="text-align: right;">
					<input type="button" class="btn btn-primary" value="목록보기"
						onclick="location.href='boardList.do'"> <input
						type="button" class="btn btn-success" value="수정"
						onclick="location.href='boardWrite.do?num=${board.num}'">
					<input type="button" class="btn btn-dark" value="삭제" onclick="myFunction1(${board.num})">
				</div>
			</form>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script>
function myFunction1(num) {
	if (confirm("삭제하시겠습니까?")){
	location.href = "boardDelete.do?num=" + num;
	}
}

</script>
</body>
</html>
