$(document).ready(function() {
    loadCustomers();
});

function loadCustomers() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/computers",
        success: function(data) {
            let content = '<table id="display-list" border="1"><tr>' +
                '<th>Name</th>' +
                '<th>Code</th>' +
                '<th>Type</th>' +
                '<th>Update</th>' +
                '<th>Delete</th>' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += getProduct(data[i]);
            }
            content += "</table>";
            $('#fuckingList').html(content).show();
            $('#add-customers').hide();
            $('#display-create').show();
            $('#edit-customers').show();
            $('#title').show();
        }
    });
}

function getProduct(customer) {
    return `<tr>
        <td>${customer.name}</td>
        <td>${customer.code}</td>
        <td>${customer.type}</td>
        <td><button class="updateSmartphone" onclick="formEdit(${customer.id})">Update</button></td>
        <td><button class="deleteSmartphone" onclick="deleteCustomer(${customer.id})">Delete</button></td>
    </tr>`;
}

function addNewCustomer(event) {
    event.preventDefault();
    let name = $('#name').val();
    let code = $('#code').val();
    let type = $('#type').val();
    let newCustomer = { name: name, code: code, type: type };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/api/computers",
        data: JSON.stringify(newCustomer),
        success: successHandler
    });
}

function successHandler() {
    loadCustomers();
}

function displayFormCreate() {
    $('#title').show();
    $('#fuckingList').hide();
    $('#add-customers').show();
    $('#display-create').hide();
    $('#edit-customers').hide();
}

function deleteCustomer(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/computers/${id}`,
        success: successHandler
    });
}

function updateCustomer(event, id) {
    event.preventDefault();
    let name = $('#name-edit').val();
    let code = $('#code-edit').val();
    let type = $('#type-edit').val();
    let updatedCustomer = { name: name, code: code, type: type };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: `http://localhost:8080/api/computers/${id}`,
        data: JSON.stringify(updatedCustomer),
        success: successHandler
    });
}

function getCustomerById(customer) {
    return `<tr>
            <td><label for="name-edit">Name:</label></td>
            <td><input type="text" id="name-edit" value="${customer.name}"></td>
        </tr>
        <tr>
            <td><label for="code-edit">Code:</label></td>
            <td><input type="text" id="code-edit" value="${customer.code}"></td>
        </tr>
        <tr>
            <td><label for="type-edit">Type:</label></td>
            <td><input type="text" id="type-edit" value="${customer.type}"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Edit"></td>
        </tr>`;
}

function formEdit(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/computers/${id}`,
        success: function(data) {
            let contentData = '<form id="edit-customer" onsubmit="updateCustomer(event, ' + id + ')">' +
                '<h1>Form edit</h1>' +
                '<table>';
            contentData += getCustomerById(data);
            contentData += '</table></form>';
            $('#edit-customer-1').html(contentData);
            $('#fuckingList').hide();
            $('#add-customers').hide();
            $('#display-create').hide();
            $('#title').hide();
            $('#edit-customers').show();
        }
    });
}
