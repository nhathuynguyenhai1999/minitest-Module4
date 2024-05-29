$.ajax({
    type: "GET",
    //tên API
    url: "http://localhost:8080/api/computers",
    //xử lý khi thành công
    success: function (data) {
        // hiển thị danh sách ở đây
        let content = '    <table id="display-list"  border="1"><tr>\n' +
            '        <th>Name</td>\n' +
            '        <th>Code</td>\n' +
            '        <th>Type</td>\n' +
            '        <th>Update</td>\n' +
            '        <th>Delete</td>\n' +
            '    </tr>';
        for (let i = 0; i < data.length; i++) {
            content += getProduct(data[i]);
        }
        content += "</table>"
        document.getElementById('fuckingList').innerHTML = content;
        document.getElementById('fuckingList').style.display = "block";
        document.getElementById('add-customers').style.display = "none";
        document.getElementById('edit-customer-1').style.display= "none";
        document.getElementById('display-create').style.display = "block";
        document.getElementById('title').style.display = "block";
    }
});

function getProduct(customer) {
    return `<tr><td >${customer.id}</td><td >${customer.name}</td><td >${customer.code}</td><td >${customer.type}</td>` +
        `<td class="btn"><button class="deleteSmartphone" onclick="deleteCustomers(${customer.id})">Delete</button></td>
        <td class="btn"><button class="editSmartphone" onclick="formEditCustomers(${customer.id})">Edit</button></td></tr>`;
}

function addNewCustomers() {
    //lấy dữ liệu từ form html
    let name = $('#name').val();
    let code = $('#code').val();
    let type = $('#type').val();
    let newSmartphone = {
        name: name,
        code: code,
        type: type
    };
    // gọi phương thức ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newSmartphone),
        //tên API
        url: "http://localhost:8080/api/computers",
        //xử lý khi thành công
        success: successHandler

    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}
function successHandler() {
    // Gọi lại API để lấy danh sách khách hàng mới nhất
    $.ajax({
        type: "GET",
        // Tên API
        url: "http://localhost:8080/api/computers",
        // Xử lý khi thành công
        success: function (data) {
            // Hiển thị danh sách ở đây
            let content = '<table id="display-list" border="1"><tr>' +
                '    <th>Name</th>' +
                '    <th>Code</th>' +
                '    <th>Type</th>' +
                '    <th>Update</th>' +
                '    <th>Delete</th>' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += getProduct(data[i]);
            }
            content += "</table>";
            document.getElementById('fuckingList').innerHTML = content;
            document.getElementById('fuckingList').style.display = "block";
            document.getElementById('add-customers').style.display = "none";
            document.getElementById('display-create').style.display = "block";
            document.getElementById('title').style.display = "block";
        }
    });
}

function displayFormCreate(){
    document.getElementById('title').style.display = "block";
    document.getElementById("fuckingList").style.display = "none";
    document.getElementById('add-customers').style.display = "block";
    document.getElementById('display-create').style.display = "none";
}
function deleteCustomers(buttonElement){
    var id = $(buttonElement).data("id");
    $.ajax({
        type: "DELETE",
        //tên API
        url: "http://localhost:8080/api/computers/${id}",
        //xử lý khi thành công
        success: successHandler
    });
}
function backtoListCustomers(){
    window.location.href = 'Ajax.html';
}
function formEditCustomers(){
    $.ajax({type: "GET",
        url: "http://localhost:8080/api/computers/${id}",
        success: function (data) {
        let content = '<table id="display-list" border="1"><tr>' +
            '    <h1>Form edit</h1>\n' +
            '    <table>\n';
        contentData += getComputerCustomerById(data);
        contentData+='</table>\n' +
            '</form>'
        document.getElementById('editCustomer-1').innerHTML = content;
        document.getElementById('fuckingList').innerHTML = "none";
        document.getElementById('add-customers').style.display = "block";
        document.getElementById('display-create').style.display = "none";
    }
    })
}
function getComputerCustomerById(){
    return`<tr>
            <td><label for="name">Name:</label></td>
            <td><input type="text" id="name-edit" value="${customers.name}"></td>
        </tr>
        <tr>
            <td><label for="model">Code:</label></td>
            <td><input type="text" id="code-edit" value="${customers.code}"></td>
        </tr>
        <tr>
            <td><label for="type">Type:</label></td>
            <td><input type="text" id="type-edit" value="${customers.type}"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Edit" onclick="editCustomers(${customers.id})"></td>
        </tr>`
}
function editCustomers(){
    event.preventDefault();
    let name = document.getElementById("name-edit").value;
    let code = document.getElementById("code-edit").value;
    let type = document.getElementById("type-edit").value;
    let newCustomerForProduct = {
            "name" : name,
            "code" : code,
            "type" : type
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content - type' : 'application/json'
        },
        type : 'PUT',
        url : `http://localhost:8080/api/computers/${id}`,
        success: successHandler
    })
}
