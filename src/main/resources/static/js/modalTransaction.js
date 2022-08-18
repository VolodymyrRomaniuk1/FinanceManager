const modal = document.getElementById('editModal')
modal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const button = event.relatedTarget
    // Extract info from data-bs-* attributes
    const transactionInfo = button.getAttribute('data-bs-transaction-info')
    const transaction = JSON.parse(transactionInfo);
    // If necessary, you could initiate an AJAX request here
    // and then do the updating in a callback.
    //
    // Update the modal's content.
    const modalTitle = modal.querySelector('.modal-title')
    const modalBodyId = modal.querySelector('.modal-body #transactionId')
    const modalBodyCategory = modal.querySelector('.modal-body #transactionCategory')
    const modalBodyOperationType = modal.querySelector('.modal-body #transactionOperationType')
    const modalBodySum = modal.querySelector('.modal-body #transactionSum')
    const modalBodyDate = modal.querySelector('.modal-body #transactionDate')
    const modalBodyDescription = modal.querySelector('.modal-body #transactionDescription')

    modalTitle.textContent = `Editing transaction, id: ${transaction.id}`;
    modalBodyId.value = transaction.id;
    for (var i = 0; i < modalBodyCategory.options.length; i++){
        modalBodyCategory.options[i].removeAttribute("selected");
        if(JSON.parse(transactionInfo).category.id === JSON.parse(modalBodyCategory.options[i].value).id){
            modalBodyCategory.options[i].setAttribute("selected", "selected");
        }
    }
    modalBodyOperationType.value = transaction.operationType;
    modalBodySum.value = transaction.sum;
    modalBodyDate.value = transaction.date;
    modalBodyDescription.value = transaction.description;
})

function updateTransaction() {
    var transaction = {};
    transaction.id = Number(document.getElementById('transactionId').value);
    transaction.category = JSON.parse(document.getElementById('transactionCategory').value);
    transaction.operationType = document.getElementById('transactionOperationType').value;
    transaction.sum = document.getElementById('transactionSum').value;
    transaction.date = document.getElementById('transactionDate').value;
    transaction.description = document.getElementById('transactionDescription').value;
    var transactionJSON = JSON.stringify(transaction);
    console.log(transaction);
    const baseURL = 'http://localhost:8080/';
    const requestURL = baseURL + 'transactions/' + transaction.id;
    const xhr = new XMLHttpRequest();
    xhr.open('PUT', requestURL);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(transactionJSON);
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) {
            return;
        }
        localStorage.setItem("statusMessage", xhr.responseText)
        window.location.reload();
    }
}

function deleteTransaction(){
    var transactionId = Number(document.getElementById('transactionId').value);
    const baseURL = 'http://localhost:8080/';
    const requestURL = baseURL + 'transactions/' + transactionId;
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