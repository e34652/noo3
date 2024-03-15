//divEl = 생성된 div 요소를 담는 변수 El = element의 줄임말
const divEl = document.createElement('div'); 

//pEl = 생성된 p 요소를 담는 변수
const pEl = document.createElement('p');

// pEl를 divEl의 자식요소로 설정 
divEl.appendChild(pEl);  

// divEl를 body의 자식태그로 설정
document.body.appendChild(divEl); 

pEl.textContent = 'Hello World'; // pEl의 텍스트를 Hello World로 변경
pEl.style.color = 'red'; //style태그의 color속성을 red로 변경

const btnEl = document.createElement('button'); //생성된 button  태그를 담는 변수
btnEl.textContent = 'Click Me'; //버튼의 텍스트를 Click Me로 변경
divEl.appendChild(btnEl); // divEl의 자식 태그로 추가

// document.body.removeChild(divEl);  << body의 자식태그인 divEl 지우기
// const bodyEl = document.querySelector('body'); << bodyEl을 body 태그 전체에 적용
// bodyEl.textContent = null; << body의 전체 내용 지우기

console.log('script.js');
const title1 = document.getElementById('title'); // 하나의 아이디(ById), title과 연동
console.log(title1);

const title2 = document.querySelector('#title'); // 하나의 아이디(#), title과 연동
console.log(title2);

const con1 = document.getElementsByClassName('container'); // 모든 container Class와 연동
console.log(con1); 

const con2 = document.querySelectorAll('.container'); // 모든 container Class(.)와 연동
console.log(con2);