function startWaiting() {
    setTimeout("waitForLogin()", 2000);
}

function waitForLogin() {
    fetch("/api/getLoggedUser").then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data);
        if (data.logInStatus !== "ok") {
            setTimeout("waitForLogin()", 5000);
        } else {
            fetch("/api/login?userName=" + data.user.name).then(function (resp) {
                return resp.text();
            }).then(function (data) {
                if (data === "true") {
                    window.location.assign("/measurements");
                } else {
                    setTimeout("waitForLogin()", 2000);
                }
            })
        }
    });


}