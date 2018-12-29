function startWaiting() {
    setTimeout("waitForLogin()", 2000);
}

function waitForLogin() {
    fetch("/api/getLoggedUser").then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data);
        if (data.logInStatus !== "ok") {
            //TODO  wyswietlenie ladnej informaji o nie zalogowaniu
            setTimeout("waitForLogin()", 5000);
        } else {
            fetch("/api/login?userName=" + data.user.name).then(function (resp) {
                return resp.text();
            }).then(function (data) {
                if (data === "true") {
                    //TODO wyswietlenie info o zalogowaniu
                    window.location.assign("/measurements");
                } else {
                    //TODO  wyswietlenie ładnej informaji o nie zalogowaniu
                    setTimeout("waitForLogin()", 2000);
                }
            })
        }
    });


}