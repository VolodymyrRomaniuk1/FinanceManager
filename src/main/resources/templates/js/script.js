//Get json array from server on this url
const requestURL = 'http://localhost:8080/categoriesList';
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
    var lengthObjects = arrObjects.length;
    var rowsOnPage = 40;//How many rows must to be on one pages
    var count = 0;
    var countPages = 0;//How many pages we will have in the end, that need for build carousel
    do {
        count += rowsOnPage;
        countPages++;
    } while (lengthObjects > count);

    var finish = listPagesOfCarousel(arrObjects,countPages,rowsOnPage);
    document.getElementById("dataTableAndCarousel").innerHTML = finish;
}
xhr.send();

function listPagesOfCarousel (arr, howManyCycles, rowsOnPage) {
    var result = "";
    var count = 0;
    var tempVariable = "";
    var tempHowManyCycles = howManyCycles;
    while (tempHowManyCycles !=0) {

        //Use when starting first cycle, because first carousel have different class
        if (tempHowManyCycles == howManyCycles) {
            //Add top carousel
            tempVariable = headFirstPageCarouselAndTable();
            //Add data in that carousel
            for ( let i=count; i<count+rowsOnPage; i++) {
                tempVariable += rowInTable(arr,i);
            }
            //Add bottom carousel
            tempVariable += futCarouselAndTable();
            count += rowsOnPage;
            //Add that carousel to result,
            //that result we return on page with all carousels and data in that carousel
            result += tempVariable;
        }
        //Use in another cycles without the last
        else if (tempHowManyCycles < howManyCycles && count+rowsOnPage < arr.length) {
            for ( let j=count; j<count+rowsOnPage; j++) {
                tempVariable += rowInTable(arr,j);
            }
            tempVariable += futCarouselAndTable();
            count += rowsOnPage;
            result += tempVariable;
        }
        //Use in last cycle
        else {
            tempVariable = headCarouselAndTable();
            for ( let k=count; k<arr.length; k++) {
                tempVariable += rowInTable(arr,k);
            }
            tempVariable += futCarouselAndTable();
            count += rowsOnPage;
            result += tempVariable;
        }
        tempHowManyCycles--;
    }
    return result;
}
function rowInTable(arr, index) {
    return "<tr>" +
        "<td>" + arr[index].dateAndTime + "</td>" +
        "<td>" + arr[index].judges + "</td>" +
        "<td>" + arr[index].caseNumber + "</td>" +
        "<td>" + arr[index].proceedingsNumber + "</td>" +
        "<td>" + arr[index].claimantDefendant + "</td>" +
        "<td>" + arr[index].gistClaim + "</td>" +
        "<td>" + arr[index].courtroom + "</td>" +
        "</tr>";
}
function headFirstPageCarouselAndTable() {
    return "<div class=\"carousel-item active\" data-bs-interval=\"10000\" style=\"height: 100%\">\n" +
        "                <table class=\"test\">\n" +
        "                    <tr>\n" +
        "                        <th class=\"fieldDate\">Дата/Час</th>\n" +
        "                        <th class=\"fieldComposition\">Склад суду</th>\n" +
        "                        <th class=\"fieldNumber\">Номер справи</th>\n" +
        "                        <th class=\"fieldProceedingsNumber\">Номер провадження</th>\n" +
        "                        <th class=\"fieldParties\">Сторони по справі</th>\n" +
        "                        <th class=\"fieldGist\">Суть позову</th>\n" +
        "                        <th class=\"fieldBox\">Зал судових засідань</th>\n" +
        "                    </tr>";
}
function headCarouselAndTable() {
    return "<div class=\"carousel-item\" data-bs-interval=\"10000\" style=\"height: 100%\">\n" +
        "                <table class=\"test\">\n" +
        "                    <tr>\n" +
        "                        <th class=\"fieldDate\">Дата/Час</th>\n" +
        "                        <th class=\"fieldComposition\">Склад суду</th>\n" +
        "                        <th class=\"fieldNumber\">Номер справи</th>\n" +
        "                        <th class=\"fieldProceedingsNumber\">Номер провадження</th>\n" +
        "                        <th class=\"fieldParties\">Сторони по справі</th>\n" +
        "                        <th class=\"fieldGist\">Суть позову</th>\n" +
        "                        <th class=\"fieldBox\">Зал судових засідань</th>\n" +
        "                    </tr>";
}
function futCarouselAndTable() {
    return "</table>\n" +
        "            </div>";
}

// const createClock = setInterval(f,1000)
// function f(){
//     console.log("set int");
// }
