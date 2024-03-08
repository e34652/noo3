const divEl = document.createElement('div'); //divEl = div 태그의 분신
const pEl = document.createElement('p');//pEl = p 태그의 분신
divEl.appendChild(pEl); // pEl을 divEl의 자식태그로 추가
document.body.appendChild(divEl); // pEl이 자식태그로 있는 divEl을 body의 자식태그로 추가 <<body에 삽입
pEl.textContent = 'Hello World'; // pEl의 텍스트를 Hello World로 변경
pEl.style.color = 'red'; //style태그의 color속성을 red로 변경

const btnEl = document.createElement('button'); //button 태그의 분신
btnEl.textContent = 'Click Me'; //버튼의 텍스트를 Click Me로 변경
divEl.appendChild(btnEl); // divEl의 자식 태그로 추가

// document.body.removeChild(divEl);  << divEl의 내용 지우기
// const bodyEl = document.querySelector('body'); << body의 전체 내용 지우기
// bodyEl.textContent = null;

console.log('script.js');
const title1 = document.getElementById('title'); // 하나의 아이디(ById)
console.log(title1);

const title2 = document.querySelector('#title'); // 하나의 아이디(#)
console.log(title2);

const con1 = document.getElementsByClassName('container'); // 모든 container Class
console.log(con1); 

const con2 = document.querySelectorAll('.container'); // 모든 container Class(.)
console.log(con2);