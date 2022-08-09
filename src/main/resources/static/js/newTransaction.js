const baseURL = 'http://localhost:8080/';
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
    document.getElementById("category").innerHTML = finish;
}
xhrCategories.send();

function generateSelectOption(obj){
    return "<option value = '" + obj.id + "'>" + obj.name + "</option>"
}