function startCheckingLoginAdmin() {
    setTimeout("waitForLogout()", 2000);
}


function waitForLogout() {
    fetch("/api/getLoggedUser").then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data);
        if (data.logInStatus === "ok") {
            setTimeout("waitForLogout()", 2000);
        } else {
            logout();
        }
    });
}


function logout() {
    fetch("/api/logout").then(function (response) {
        return response.text();
    }).then(function (data) {
        if (data === "true") {
            //TODO info o wylogowaniu
            window.location.assign("/home");
        } else {
            setTimeout("waitForLogout()", 2000);
        }
    })
}

function deleteUsr(){
	
	var mystring = document.getElementById('deleteUsername').value; 
    if(!mystring.match(/\S/)) {
        alert ('Empty value is not allowed');
        return false;
    } else {
        alert("correct input");
        return true;
    }
}

function tryAlert(){
	alert ('Wywołano próbny alarm!');
}

function calc(){
	
	if (document.getElementById('readCheckBox').checked) 
	{
      console.log('read checked');
	} else {
      console.log(' read unchecked');
	}
	
	
}

function calcAdmin(){
	
	if (document.getElementById('adminCheckBox').checked) 
	{
      console.log('admin checked');
	} else {
      console.log(' admin unchecked');
	}
}
