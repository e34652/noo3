const onClick = function (){ // 아래의 함수선언과 같음
    const textEl = document.getElementById("add-text"); // textEl를 add-text와 연동
    const text = textEl.value; // text에 textEl.value값 저장
    textEl.value = ""; // 값 저장 후 ""(공백)으로 초기화

const li = document.createElement("li"); // li태그 생성

const div = document.createElement("div"); // div태그 생성

const p = document.createElement("p"); // p 태그 생성
p.textContent = text; // p태그의 텍스트 값을 text로 설정

const button = document.createElement("button"); // button 태그 생성
button.textContent = "삭제"; // button의 text를 삭제로 설정

//삭제버튼 클릭시 발생하는 이벤트 설정
button.addEventListener("click",() => { 

    const deleteTarget = button.closest("li"); // 삭제 대상(target) 지정, closest() = 부모 요소와 일치하는 문자열을 찾는 메서드

    document.getElementById("memo-list").removeChild(deleteTarget); // 삭제대상을 memo-list로부터 삭제
});

div.appendChild(p); //div의 자식 태그로 p태그 추가

div.appendChild(button) // button의 위치를 div의 자식 태그로 설정

li.appendChild(div); //li의 자식 태그로 div태그 추가

document.getElementById("memo-list").appendChild(li);
}

//추가 버튼 클릭시 EventListener가 클릭을 감지하면 맨 위에 onClick 함수 실행
document.getElementById('add-button').addEventListener('click', onClick);

