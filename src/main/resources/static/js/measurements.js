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
	
	var n = "100,200,300,400,500";
	var values = n.split(',');
	
	var canvas = document.getElementById("myCanvas");
	var ctx = canvas.getContext("2d");
	var X = 50;
	var width = 40;
	
	
	ctx.fillStyle = "#2ecc71";
	
	for(var i = 0; i < values.length; i++){
		var h = values[i];
		ctx.fillRect(X,canvas.height - h ,width,h);	
		X += width + 15;
		
	}
}