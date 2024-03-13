<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Todo List</h1>
	<input id="todoInput" type="text" placeholder="할 일을 넣으세요" />
	<button id="addButton">Add</button>
	<ul id="todoList">
		<!-- 할 일 목록이 여기에 추가됩니다. -->
	</ul>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
		$(function() {
			$.ajax({
				url : 'GetTodos',
				method : 'GET',
				success : function(data) {
					addItem(data);
					console.log(data);
				}
			});
		});

		function addItem(data) {
			const list = $('#todoList');
			const item = $('<li></li>').text(data);
			const removeButton = $('<button></button>').text('삭');
			removeButton.click(function() {
				$.ajax({//removeButton을 클릭하면 일어나는 동작을 정의
					url : 'RemoveTodo',
					method : 'POST',
					data : { // JS의 객체 A : B 의 형식으로 표현됨 
						text : data // text라는 이름으로 data를 보냄
					},
					success : function() {
						item.remove(); // jquery 함수, const item을 지우는 기능을 수행
					}
				});
			});
			list.append(item);
			item.append(removeButton);
		}
	</script>
</body>
</html>



