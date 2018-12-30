/*setTimeout(function () {
    location.reload();
}, 30000);*/

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

function start(b) {

    setTimeout(waitForLogout(), 2000);
	if(b){
		getLastMeasurements();
	}else{
        var rok = document.getElementById("rok").value;
        var miesiac = document.getElementById("miesiac").value;
        var dzien = document.getElementById("dzien").value;

		getMeasuremeantsByDate(rok+"-"+miesiac+"-"+dzien);
	}
}

function admSelectCheck(nameSelect)
{
    console.log(nameSelect);
    if(nameSelect){
        admOptionValue = document.getElementById("wybranyDzien").value;
        if(admOptionValue == nameSelect.value){
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1; //January is 0!
            var yyyy = today.getFullYear();

            var select_dzien = document.getElementById("dzien");
            for (var i = 0; i < select_dzien.length; i++){
                var option = select_dzien.options[i];
                if(option.text == dd){
                    option.selected = 'selected';
                }
            }

            var select_miesiac = document.getElementById("miesiac");
            for (var i = 0; i < select_miesiac.length; i++){
                var option = select_miesiac.options[i];
                if(option.text == mm){
                    option.selected = 'selected';
                }
            }

            var select_rok = document.getElementById("rok");
            for (var i = 0; i < select_rok.length; i++){
                var option = select_rok.options[i];
                if(option.text == yyyy){
                    option.selected = 'selected';
                }
            }

            document.getElementById("wyborDaty").style.display = "block";
        }
        else{
            document.getElementById("wyborDaty").style.display = "none";
        }
    }
    else{
        document.getElementById("wyborDaty").style.display = "none";
    }
}

function reloadAll(){
	v = document.getElementById("wybranyDzien").value;
	if(v == document.getElementById("val").value){

        var rok = document.getElementById("rok").value;
        var miesiac = document.getElementById("miesiac").value;
        var dzien = document.getElementById("dzien").value;

	    getMeasuremeantsByDate(rok+"-"+miesiac+"-"+dzien);
	    start(false);
	}
	else {
	    getLastMeasurements();
	    start(true);
    }
}

function draw(measurements, bool) {

    var temp = [];
    var humi = [];
    var x_temp = [];
    var x_humi = [];

    measurements.map(function (measurement) {

        if(bool){
            var t = measurement.time.substring(0,5);
        }else{
            var t = measurement.time.substring(0,2);
        }
        x_temp.push(t);
        x_humi = x_temp;
        temp.push(measurement.temperature);
        humi.push(measurement.humidity);
        var h = measurement.temperature;

    });

    drawCharts(temp, x_temp, "myCanvas", "Temperatura w pomieszczeniu");
    drawCharts(humi, x_humi, "myHumCanvas", "Wilgotność w pomieszczeniu");

}

function drawCharts(dat, x_tick, element, label_title) {

    var CHART = document.getElementById(element);
    console.log(CHART);
    var lineChart = new Chart(CHART,{
        type: 'line',
        data: {
            labels: x_tick,
            datasets: [
                {
                    label: label_title,
                    fill: false,
                    lineTension: 0.1,
                    borderColor: "#00ff55",
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    pointBorderColor: "#ffffff",
                    pointBackgroundColor: "fff",
                    pointBorderWidth: 1,
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: "#3399ff",
                    pointHoverBorderColor: "#000000",
                    pointHoverBorderWidth: 2,
                    pointRadius: 6,
                    pointHitRadius: 10,
                    data: dat,
                }
            ]
        },
        options: {
            legend: {
                labels: {
                    // This more specific font property overrides the global property
                    fontColor: '#ffffff'
                }
            },
            scales: {
                yAxes: [{
                    ticks: {
                        fontColor: "white",
                        padding: 30
                    }
                }],
                xAxes: [{
                    ticks: {
                        fontColor: "white"
                    }
                }]
            }
        }
    });
}

function getAllMeasurements() {

    var parentElement = document.getElementById("example-div");
    fetch("/api/getMeasurements").then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data);
        if (data.responseStatus === "ok") {
            var ul = document.createElement("ul");
            var measurements = data.measurements;
            measurements.map(function (measurement) {
                var li = document.createElement("li");
                li.innerText = measurement.date + " Temp: " + measurement.temperature + " Hum: " + measurement.humidity;
                ul.appendChild(li);
            });
            parentElement.appendChild(ul);
            return data.measurements;
        }

    });
}

function getMeasuremeantsByDate(date) {
    fetch("/api/getMeasurements?date=" + date).then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data);
        if (data.responseStatus === "ok") {
			draw(data.measurements, false);
            return data.measurements;
        }

    });
}

function getLastMeasurements() {
    fetch("/api/getLastMeasurements").then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data);
        if (data.responseStatus === "ok") {
            draw(data.measurements, true);
            return data.measurements;
        }

    });
}

function openAdminPanel() {
    console.log("Admin");
    window.location.assign("/admin")
}