const exampleModal = document.getElementById('editModal')
exampleModal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const button = event.relatedTarget
    // Extract info from data-bs-* attributes
    const categoryInfo = button.getAttribute('data-bs-category-info')
    const category = JSON.parse(categoryInfo);
    // If necessary, you could initiate an AJAX request here
    // and then do the updating in a callback.
    //
    // Update the modal's content.
    const modalTitle = exampleModal.querySelector('.modal-title')
    const modalBodyId = exampleModal.querySelector('.modal-body #categoryId')
    const modalBodyName = exampleModal.querySelector('.modal-body #categoryName')
    const modalBodyDescpription = exampleModal.querySelector('.modal-body #categoryDescription')

    modalTitle.textContent = `Editing: ${category.name}`;
    modalBodyId.value = category.id;
    modalBodyName.value = category.name;
    modalBodyDescpription.value = category.description;
})

function updateCategory() {
    var categoryId = Number(document.getElementById('categoryId').value);
    var category = {};
    category.id = categoryId;
    category.name = document.getElementById('categoryName').value;
    category.description = document.getElementById('categoryDescription').value;
    var categoryJSON = JSON.stringify(category);
    console.log(categoryJSON);
    const baseURL = 'http://localhost:8080/';
    const requestURL = baseURL + 'categories/' + categoryId;
    const xhr = new XMLHttpRequest();
    xhr.open('PUT', requestURL);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(categoryJSON);
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) {
            return;
        }
        localStorage.setItem("statusMessage", xhr.responseText)
        window.location.reload();
    }
}

function deleteCategory(){
    var categoryId = Number(document.getElementById('categoryId').value);
    const baseURL = 'http://localhost:8080/';
    const requestURL = baseURL + 'categories/' + categoryId;
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', requestURL);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4 ) {
            return;
        }
        localStorage.setItem("statusMessage", xhr.responseText)
        window.location.reload();
    }
}