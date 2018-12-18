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
