function sendForm() {
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
                document.getElementById("reportTable")
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

function addTotalSum(totalSum){
    return "<tr class='totalSum'>" +
        "<td colspan='2'>"+
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