//Get json array from server on this url
const requestURL = 'http://localhost:8080/categories';
const xhr = new XMLHttpRequest();
xhr.open('GET', requestURL);
xhr.onreadystatechange = function() {
    //TODO rewrite in future, when we well check status response on front end
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
        "<td>" + obj.name + "</td>" +
        "<td>" + obj.description + "</td>" +
        "</tr>";
}
function headFirstPageCarouselAndTable() {
    return "<div class=\"carousel-item active\" data-bs-interval=\"10000\" style=\"height: 100%\">\n" +
        "                <table class=\"test\">\n" +
        "                    <tr>\n" +
        "                        <th class=\"fieldDate\">ID</th>\n" +
        "                        <th class=\"fieldComposition\">Name</th>\n" +
        "                        <th class=\"fieldNumber\">Description</th>\n" +
        "                    </tr>";
}
function futCarouselAndTable() {
    return "</table>\n" +
        "            </div>";
}


// This is old
//var finish = listPagesOfCarousel(arrObjects,countPages,rowsOnPage);
/*function listPagesOfCarousel (arr, howManyCycles, rowsOnPage) {
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
}*/
/*function rowInTable(arr, index) {
    return "<tr>" +
        "<td>" + arr[index].id + "</td>" +
        "<td>" + arr[index].name + "</td>" +
        "<td>" + arr[index].description + "</td>" +
        "</tr>";
}*/

/*function headCarouselAndTable() {
    return "<div class=\"carousel-item\" data-bs-interval=\"10000\" style=\"height: 100%\">\n" +
        "                <table class=\"test\">\n" +
        "                    <tr>\n" +
        "                        <th class=\"fieldDate\">ID</th>\n" +
        "                        <th class=\"fieldComposition\">Name</th>\n" +
        "                        <th class=\"fieldNumber\">Description</th>\n" +
        "                    </tr>";
}*/