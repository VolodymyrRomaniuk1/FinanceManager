function generatePieDiagramReport() {
    document.getElementById("reportTable").innerHTML = "";
    const baseURL = 'http://localhost:8080/';
    const requestURL = baseURL + 'reports/';
    var form = document.getElementById("sendReportRequest")
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", requestURL);
    xhr.send(formData);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                const response = xhr.responseText;
                const arrObjects = JSON.parse(response);
                const transactionList = arrObjects.transactionList;
                let categories = [];
                for (let i = 0; i < transactionList.length; i++) {
                    if (contains(categories, transactionList[i].category) === false) {
                        categories.push(transactionList[i].category);
                    }
                }

                var finish = tableHead()
                for (let i = 0; i < categories.length; i++) {
                    let sum = 0;
                    for (let j = 0; j < transactionList.length; j++) {
                        if (categories[i].id === transactionList[j].category.id) {
                            sum += transactionList[j].sum
                        }
                    }
                    finish += addObjectToTable(categories[i].name, sum);
                }
                finish += addTotalSum(arrObjects.totalSum);
                document.getElementById("reportTable").innerHTML += finish;
            }
        }
    }
}

function generateDayByDayReport() {
    const baseURL = 'http://localhost:8080/';
    const requestURL = baseURL + 'reports/';
    var form = document.getElementById("sendReportRequest")
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", requestURL);
    xhr.send(formData);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var reportCanvas = document.getElementById("reportCanvas")
                console.log(Chart.getChart(0))
                const response = xhr.responseText;
                const arrObjects = JSON.parse(response);
                var canvasLabel = getDates(new Date(arrObjects.dateStart), new Date(arrObjects.dateEnd))
                const transactionList = arrObjects.transactionList;

                var sumArray = []

                var currentSum = 0;
                for (let i = 0; i < canvasLabel.length; i++) {
                    for(let j = 0; j < transactionList.length; j++){
                        if (canvasLabel[i] === transactionList[j].date) {
                            currentSum += transactionList[j].sum
                        }

                    }
                    sumArray.push(currentSum)
                    currentSum = 0;
                }
                var transactionData = {
                    labels: canvasLabel,
                    datasets: [{
                        label: "Transaction $",
                        data: sumArray,
                    }]
                }

                var chartOptions = {
                    legend: {
                        display: true,
                        position: 'top',
                        labels: {
                            boxWidth: 80,
                            fontColor: 'black'
                        }
                    }
                };

                var lineChart = new Chart(reportCanvas, {
                    type: 'line',
                    data: transactionData,
                    options: chartOptions
                });
            }
        }
    }
}

function generateDayByDayReportSpecific(){
    generateDayByDayReport()
}

function tableHead() {
    return "<thead class='thead-light'>" + "             <tr>\n" +
        "                        <th scope='col' class=\"fieldCategory\">Category</th>\n" +
        "                        <th scope='col' class=\"fieldSum\">Sum</th>\n" +
        "                    </tr>" + "</thead>";
}

function addObjectToTable(name, sum) {
    return "<tr>" +
        "<td>" + name + "</td>" +
        "<td>" + sum + "</td>" +
        "</tr>";
}

function addTotalSum(totalSum) {
    return "<tr class='totalSum'>" +
        "<td colspan='2'>" +
        "Total Sum: " + totalSum +
        "</td>" +
        "</tr>"
}

function contains(arr, elem) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].id == elem.id) {
            return true;
        }
    }
    return false;
}

Date.prototype.addDays = function (days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}

function getDates(startDate, stopDate) {
    var dateArray = new Array();
    var currentDate = startDate;
    while (currentDate <= stopDate) {
        dateArray.push(formatDate(new Date(currentDate)));
        currentDate = currentDate.addDays(1);
    }
    return dateArray;
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}