const baseURL = 'http://localhost:8080/';
const requestURL = baseURL + 'transactions';
const xhr = new XMLHttpRequest();
xhr.open('GET', requestURL);
xhr.onreadystatechange = function() {
    if (xhr.readyState !== 4 || xhr.status !== 200) {
        return;
    }
    //Set this json to constant
    const response = xhr.responseText;

    //After that parse Json and write on site in table
    const arrObjects = JSON.parse(response);
    var lengthObjects = Object.keys(arrObjects).length;

    var rowsOnPage = 4;//How many rows must be on one pages
    var count = 0;
    var countPages = 0;//How many pages we will have in the end, that need for build carousel
    do {
        count += rowsOnPage;
        countPages++;
    } while (lengthObjects > count);

    var finish = headFirstPageCarouselAndTable();
    for(let i = 0; i < arrObjects.length; i++) {
        let obj = arrObjects[i];
        finish += oneObjectInTable(obj);
    }
    finish += futCarouselAndTable();
    document.getElementById("dataTableAndCarousel").innerHTML = finish;
}
xhr.send();

function oneObjectInTable(obj) {
    return "<tr>" +
        "<td>" + obj.id + "</td>" +
        "<td>" + obj.category.name + "</td>" +
        "<td>" + obj.operationType + "</td>" +
        "<td>" + obj.sum + "</td>" +
        "<td>" + obj.date + "</td>" +
        "<td>" + obj.description + "</td>" +
        "<td>" +
        "<button type=\"button\" class=\"btn btn-info editButton\" data-bs-toggle=\"modal\" data-bs-target=\"#editModal\" data-bs-transaction-info='" +
        JSON.stringify(obj) +
        "'" +
        ">Manage</button>" + "</td>" +
        "</tr>";
}

function headFirstPageCarouselAndTable() {
    return "<div class=\"carousel-item active\" data-bs-interval=\"10000\" style=\"height: 100%\">\n" +
        "                <table class=\"test\">\n" +
        "                    <tr>\n" +
        "                        <th class=\"fieldID\">ID</th>\n" +
        "                        <th class=\"fieldCategory\">Category</th>\n" +
        "                        <th class=\"fieldOperationType\">Operation Type</th>\n" +
        "                        <th class=\"fieldSum\">Sum</th>\n" +
        "                        <th class=\"fieldDate\">Date</th>\n" +
        "                        <th class=\"fieldDescription\">Description</th>\n" +
        "                        <th class=\"fieldManage\">Manage</th>\n" +
        "                    </tr>";
}
function futCarouselAndTable() {
    return "</table>\n" +
        "            </div>";
}

$(document).ready(function(){
    //get it if Status key found
    if(localStorage.getItem("statusMessage"))
    {
        document.getElementById("statusMessage").innerHTML = localStorage.getItem("statusMessage");
        localStorage.clear();
    }
});

const requestCategoriesURL = baseURL + 'categories';
const xhrCategories = new XMLHttpRequest();
xhrCategories.open('GET', requestCategoriesURL);
xhrCategories.onreadystatechange = function() {
    if (xhrCategories.readyState !== 4 || xhrCategories.status !== 200) {
        return;
    }
    //Set this json to constant
    const response = xhrCategories.responseText;

    //After that parse Json and write on site in table
    const arrObjects = JSON.parse(response);

    var finish = "";
    for(let i = 0; i < arrObjects.length; i++) {
        let obj = arrObjects[i];
        finish += generateSelectOption(obj);
    }
    document.getElementById("transactionCategory").innerHTML = finish;
}
xhrCategories.send();

function generateSelectOption(obj){
    return "<option value = '" + JSON.stringify(obj) + "'>" + obj.name + "</option>"
}