const form=document.getElementById('form');
const fname=document.getElementById('fname');
const lname=document.getElementById('lname');
const password=document.getElementById('pass');
const cPassword=document.getElementById('cpass');
const email=document.getElementById('email');
const dob=document.getElementById('bdate');
const phone=document.getElementById('phone');

function check(){
    validateInputs()
}

function validateInputs(){
    if(password.value!=cPassword.value){
        alert("Enter right password!")
    }
}