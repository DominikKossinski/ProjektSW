function startCheckingLogin() {
    setTimeout("waitForLogout()", 2000);
    getMeasurements("measurements-ul");
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

function getMeasurements(parentElementId) {
    console.log("Ok");
    var parentElement = document.getElementById(parentElementId);
    fetch("/api/getMeasurements").then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data);
        if (data.responseStatus === "ok") {
            var measurements = data.measurements;
            measurements.map(function (measurement) {
                //TODO dodać jakieś ładniejsze wyświetlanie
                var li = document.createElement("li");
                li.innerText = measurement.date + " Temp: " + measurement.temperature + " Hum: " + measurement.humidity;
                parentElement.appendChild(li);
            })
        }
    })
}