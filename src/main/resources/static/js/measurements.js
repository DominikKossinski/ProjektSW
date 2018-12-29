setTimeout(function () {
    location.reload();
}, 30000);

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

function start() {
    
    setTimeout(waitForLogout(), 2000);
    getAllMeasurements();
	getLastMeasurements();
}

function draw(measurements) {
	
    var CHART = document.getElementById("myCanvas");
	console.log(CHART);
	var lineChart = new Chart(CHART,{
		type: 'line',
		data: {
			labels: ["A","B","C","D","E","F","G"],
			datasets: [ 
				{
					label: "Temperatura w pomieszczeniu",
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
					data: [65, 59, 80, 81, 56, 55, 40],
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
                    }
                }],
                xAxes: [{
                    ticks: {
                        fontColor: "white",
                    }
                }]
            }
        }
	});

	
	x.map(function (measurement) {
			var h = measurement.temperature;
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
			draw(data.measurements);
            return data.measurements;
        }

    });
}