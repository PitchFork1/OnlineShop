$(document).ready(function(){

    $("#registration_first_name_error").hide();
    $("#registration_last_name_error").hide();
    $("#registration_login_error").hide();
	$("#registration_email_error").hide(); 
	$("#registration_password_error").hide();
	$("#registration_tryPassword_error").hide();

    var error_first_name = false;
    var error_last_name = false;
    var error_login = false;
	var error_email = false;
	var error_password = false;
	var error_tryPassword = false;
});

$("#registration_form").focusout(function() {
    checkFirstName();
    checkLastName();
    checkLogin();
    checkEmail();
    checkPassword();
    checkTryPassword();
});

function checkFirstName() {
    var first_name = $("#registration_first_name").val();
    var first_name_validation_regex = /\w{2,}/;

    if(!first_name_validation_regex.test(first_name)) {
        $("#registration_first_name_error").show();
        error_first_name = true;
    } else {
        $("#registration_first_name_error").hide();
    }
}

function checkLastName() {
    var last_name = $("#registration_last_name").val();
    var last_name_validation_regex = /\w{2,}/;

    if(!last_name_validation_regex.test(last_name)) {
        $("#registration_last_name_error").show();
        error_last_name = true;
    } else {
        $("#registration_last_name_error").hide();
    }
}

function checkLogin() {
	var login = $("#registration_login").val();
	var login_validation_regex = /\S{6,20}/;	
		
	if(!login_validation_regex.test(login)) {
		$("#registration_login_error").show();
		error_login = true;
	} else {
		$("#registration_login_error").hide();
	}
}
	
function checkEmail() { 
	var email = $("#registration_email").val(); 
	var email_validation_regex = /\w{1,15}@[a-zA-Z]{1,10}.[a-zA-Z]{2,4}/;

	if(!email_validation_regex.test(email)) { 
		$("#registration_email_error").show();
		error_email = true; 
	} else {
		$("#registration_email_error").hide();
	} 
}

function checkPassword() { 
	var password = $("#registration_password").val(); 
	var password_validation_regex = /\S{6,20}/;
	
	if(!password_validation_regex.test(password)) { 
		$("#registration_password_error").show(); 
		error_password = true; 
	} else {
		$("#registration_password_error").hide();
	}  
}

function checkTryPassword() {
	var password = $("#registration_password").val();
	var repeated_password = $("#registration_tryPassword").val(); 

	if(repeated_password != password) { 
		$("#registration_tryPassword_error").show(); 
		error_tryPassword = true; 
	} else {
		$("#registration_tryPassword_error").hide();
	}  
}


$("#registration_form").submit(function() {
    error_first_name = false;
    error_last_name = false;
	error_login = false;
	error_email = false;
	error_password = false;
	error_tryPassword = false;

    checkFirstName();
    checkLastName();
    checkLogin();
	checkEmail();
	checkPassword();
	checkTryPassword();
	
	if(error_first_name == false && error_last_name == false && error_login == false && error_email == false && error_password == false && error_tryPassword == false) {
		return true;
	} else {
		return false;
	}
});