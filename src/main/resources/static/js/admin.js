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
            window.location.assign("/home");
        } else {
            setTimeout("waitForLogout()", 2000);
        }
    })
}

function deleteUsr(){
	
	var mystring = document.getElementById('deleteUsername').value;
    if (mystring === "") {
        //TODO język polski :P
        alert ('Empty value is not allowed');
    } else {
        var data = JSON.stringify({"name": mystring});
        var http = new XMLHttpRequest();
        var url = "/api/deleteUser";
        http.open("Delete", url, true);
        http.setRequestHeader("Content-Type", "application/json");
        http.send(data);
        http.onreadystatechange = function (e) {
            if (http.readyState === 4) {
                var header = http.responseText;
                if (header === "deleted") {
                    alert("Usunięto użytkownika")
                    //TODO odaj tu chowanie tego formularza
                } else if (header === "ERROR") {
                    alert("Error");
                } else {
                    alert("Użytkownik o podanej nazwie nie istnieje")
                }
            }
        };
        console.log(data);
        return true;
    }
}

function tryAlert(){
    fetch("/api/testAlert").then(function (value) {
        return value.text();
    }).then(function (data) {
        if (data === "true") {
            alert('Wywołano próbny alarm!');
        }
    });

}

function startReadingCart() {
    fetch("/api/readCart").then(function (value) {
        return value.json();
    }).then(function (data) {
        console.log(data);
        var cartIdInput = document.getElementById("cardID");
        if (data.rfid !== "") {
            cartIdInput.value = data.rfid;
        }
        if (document.getElementById('readCheckBox').checked) {
            setTimeout("startReadingCart()", 500);
        }
    })
}

function calc(){
	
	if (document.getElementById('readCheckBox').checked) 
	{
        fetch("/api/startTestingCart").then(
            function (value) {
                return value.text();
            }
        ).then(function (data) {
            if (data === "true") {
                setTimeout("startReadingCart()", 500);
                console.log('read checked');
            }
        });

	} else {

        fetch("/api/stopTestingCart").then(function (value) {
            return value.text();
        }).then(function (data) {
            console.log(data);
            if (data === "true") {
                console.log(' read unchecked');
            }
        })
	}
	
	
}


function addUser() {
    var rfid = document.getElementById("cardID").value;
    var name = document.getElementById("username").value;

    if (rfid !== "" && name !== "") {
        if (document.getElementById('adminCheckBox').checked) {
            var data = JSON.stringify({"name": name, "rfid": rfid, "role": "ADMIN"});
            var http = new XMLHttpRequest();
            var url = "/api/createUser";
            http.open("Put", url, true);
            http.setRequestHeader("Content-Type", "application/json");
            http.send(data);
            http.onreadystatechange = function (e) {
                if (http.readyState === 4) {
                    var header = http.responseText;
                    if (header === "added") {
                        alert("Dodano użytkownika")
                        //TODO odaj tu chowanie tego formularza
                    } else if (header === "ERROR") {
                        alert("Error");
                    } else {
                        alert("Użytkownik o podanej nazwie bądź numerze karty już istnieje")
                    }
                }
            };
            console.log(data);

        } else {
            var data = JSON.stringify({"name": name, "rfid": rfid, "role": "USER"});
            var http = new XMLHttpRequest();
            var url = "/api/createUser";
            http.open("Put", url, true);
            http.setRequestHeader("Content-Type", "application/json");
            http.send(data);
            http.onreadystatechange = function (e) {
                if (http.readyState === 4) {
                    var header = http.responseText;
                    if (header === "added") {
                        alert("Dodano użytkownika")
                    } else if (header === "ERROR") {
                        alert("Error");
                    } else {
                        alert("Użytkownik o podanej nazwie bądź numerze karty już istnieje")
                    }
                }
            };
            console.log(data);
        }
    } else {
        alert("Nazwa użytkownika i numer karty muszą być podane");
    }
}
