const var1 = 1024;
const var2 = '1024';
console.log(var1 == var2);
console.log(var1 === var2);
console.log(null == undefined);
console.log(null == 0);
console.log(undefined == 0);
console.log(null === undefined);

const id = 'admin';
const res = (id !== null && id !== undefined)?'존재하는 아이디' : '입력 안함';
console.log(res);

const res2 = (id ?? null)?'존재하는 아이디' : '입력 안함';
console.log(res2);