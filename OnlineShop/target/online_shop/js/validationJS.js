function validateFirstName(firstName){
    var reg=/[a-zA-Z][2,]/;
    var errorMassage = document.getElementById("registration_first_name_error");
    return handler(reg.test(firstName.value), errorMassage);
}

function validateLastName(lastName){
    var reg=/[a-zA-Z][2,]/;
    var errorMassage = document.getElementById("registration_last_name_error");
    return handler(reg.test(lastName.value), errorMassage);
}

function validateLogin(login){
	var reg=/\S{6,20}/;
	var errorMassage = document.getElementById("registration_login_error");
	return handler(reg.test(login.value), errorMassage);
}

function validateEmail(email){
	var reg=/\w{1,15}@[a-zA-Z]{1,10}.[a-zA-Z]{2,4}/;
	var errorMassage = document.getElementById("registration_email_error");
	return handler(reg.test(email.value), errorMassage);
}

function validatePassword(password){
	var reg=/\S{6,20}/;
	var errorMassage = document.getElementById("registration_password_error");
	return handler(reg.test(password.value), errorMassage);
} 

function validateTryPassword(password, tryPassword){
	var isValid = password.value === tryPassword.value;
	var errorMassage = document.getElementById("registration_tryPassword_error");
	return handler(isValid, errorMassage);
}

function handler(isValid, errorBlock){
	if(!isValid) {
		errorBlock.style.display="block";
		return false;
	} else {
		errorBlock.style.display="none";
		return true;
	}
}

document.getElementById("registration_form").onsubmit = function(){
	var firstName=document.getElementById("registration_first_name");
    var lastName=document.getElementById("registration_last_name");
    var login=document.getElementById("registration_login");
	var email=document.getElementById("registration_email");
	var password=document.getElementById("registration_password");
	var tryPassword=document.getElementById("registration_tryPassword");
	if(validateFirstName(firstName) & validateLastName(lastName) & validateLogin(login) & validateEmail(email) & validatePassword(password) & validateTryPassword(password, tryPassword)){
		return true;
	}
	return false;
}