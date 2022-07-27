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
    var categoryName = document.getElementById('categoryName').value;
    var categoryDescription = document.getElementById('categoryDescription').value;
    var category = {};
    category.id = categoryId;
    category.name = categoryName;
    category.description = categoryDescription;
    var categoryJSON = JSON.stringify(category);
    console.log(categoryJSON);
    const baseURL = 'http://localhost:8080/';
    const requestURL = baseURL + 'categories/' + categoryId;
    const xhr = new XMLHttpRequest();
    xhr.open('PUT', requestURL);
    xhr.send(categoryJSON);
}