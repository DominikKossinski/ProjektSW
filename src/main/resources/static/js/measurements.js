function startCheckingLogin() {
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
function start(){
	draw();
	waitForLogout();
}

function draw(){
	var canvas = document.getElementById("myCanvas");
	var ctx = canvas.getContext("2d");
	var X = 0;
	var Y = 0;
	var width = 200;
	var height = 100;
	ctx.fillStyle = "#2ecc71";
	ctx.fillRect(X,Y,width,height);
}