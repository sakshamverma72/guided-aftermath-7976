const customerBtn = document.getElementById('customerBtn');
customerBtn.addEventListener('click', handleCustomerManagement);
const customerApi='http://localhost:8080/admin';
// const content=document.getElementById("content");
function handleCustomerManagement() {
    console.log('Customer Management button clicked');
    content.innerHTML="";


    content.innerHTML=`
    <div class="productContainer">
    
    <div class="submenu" id="productSubMenu">
    <button id="viewAllCustomersBtn">View Customers</button>
    <button id="editRole">Edit Role</button>
    <button id="deactivateCustomerBtn">Deactivate Customer</button>
    <button id="searchByMailBtn">Search Customer</button>
</div>
<div id="productContent"><h1 class="bouncing-text">Customer Menu</h1></div>

</div>
`;

const viewAllCustomersBtn = document.getElementById('viewAllCustomersBtn');
const editRole = document.getElementById('editRole');
const deactivateCustomerBtn = document.getElementById('deactivateCustomerBtn');
const searchByMailBtn = document.getElementById('searchByMailBtn');

viewAllCustomersBtn.addEventListener('click', handleviewAllCustomersBtn);
editRole.addEventListener('click', handleeditRole);
deactivateCustomerBtn.addEventListener('click', handledeactivateCustomerBtn);
searchByMailBtn.addEventListener('click', handleSearchByMailBtn);

function handleviewAllCustomersBtn() {
    console.log('View All Customers Clicked');
    // Add your code to handle the Add Product action here
    productContent.innerHTML="";
    productContent.innerHTML=` <div class="viewAllProducts">
    <h2>Product List</h2>
    <table id="productTable">
      <tr style="position:sticky; top:0%; background-color:#111011;">
       <th>Id</th>
        <th>Name</th>
        <th>UserName</th>
        <th>Status</th>
        <th>Role</th>
      </tr>
    </table>
  </div>`;
  fetchProducts();
  function fetchProducts() {
    let getAllCustomersApi=customerApi+'/customers';
     fetch(getAllCustomersApi)
       .then(function(response) {
         return response.json();
       })
       .then(function(data) {
         var table = document.getElementById('productTable');
         // table.innerHTML = '';
 
         for (var i = 0; i < data.length; i++) {
           
           var product = data[i];
 
            var row = table.insertRow();
            var customerId=row.insertCell();
            customerId.innerText=product.customerId;
           var nameCell = row.insertCell();
           nameCell.innerText = product.name;
 
           var mailCell = row.insertCell();
           
           mailCell.innerText = product.email;
     
 
           var acitveCell = row.insertCell();
           if(product.active==true)
           acitveCell.innerText = "Active";
           else
           acitveCell.innerText = "Deactivated";
 
           var roleCell = row.insertCell();
           roleCell.innerText = product.role;
 
           
         }
       })
       .catch(function(error) {
         console.error('Error:', error);
       });
 
 }  
}

function handleeditRole() {
    console.log('editRole button clicked');
    productContent.innerHTML="";
productContent.innerHTML=`
<div class="container">
<h1>Update Customer Role</h1>
<form id="roleForm">
  <label for="customerId">Customer ID:</label>
  <input type="text" id="customerId" name="customerId" required>

  <label for="role">New Role:</label>
  <input type="text" id="role" name="role" required>

  <button type="submit">Update Role</button>
</form></div>`;



const form = document.querySelector('#roleForm');

form.addEventListener('submit', (event) => {
  event.preventDefault();

  const customerId = document.querySelector('#customerId').value;
  console.log(customerId)
  const newRole = document.querySelector('#role').value;
  let updateRoleApi='http://localhost:8080/admin/updaterole/customer';
  const url = `${updateRoleApi}/${customerId}`;
  
  console.log(url)
  const data = {
    role: "ROLE_"+newRole
  };
console.log(data);
  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
    .then(response => {
      if (response.ok) {
        console.log('Role updated successfully.');
        alert('Role updated successfully.');
      } else {
        console.log('Failed to update role.');
        alert('Failed to update role.');
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
});
}

function handledeactivateCustomerBtn() {
    console.log('handledeactivateCustomerBtn button clicked');
    // Add your code to handle the Edit Product action here

    productContent.innerHTML="";
    productContent.innerHTML=`
    <div class="container">
    <h1>Deactivate Customer</h1>

    <form id="deactivateForm">
      <label for="customerId">Customer ID:</label>
      <input type="text" id="customerId" name="customerId" required>
  
      <button type="submit">Deactivate</button>
    </form>
    </div>`;



    const form = document.querySelector('#deactivateForm');

form.addEventListener('submit', (event) => {
  event.preventDefault();

  const customerId = document.querySelector('#customerId').value;
 let customerRemoveApi=`http://localhost:8080/admin/delete/customer`;
  const url = `${customerRemoveApi}/${customerId}`;

  fetch(url, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    },
    // body: JSON.stringify({ active: false }) 
  })
    .then(response => {
      if (response.ok) {
        console.log('Customer deactivated successfully.');
        alert('Customer deactivated successfully.');
      } else {
        console.log('Failed to deactivate customer.');
        alert('Failed to deactivate customer.');
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
});

}

function handleSearchByMailBtn() {
    console.log('handleSearchByMailBtn button clicked');
    // Add your code to handle the Delete Product action here

    productContent.innerHTML="";
    productContent.innerHTML=` 
    <div class="container">
    <h1>Find User</h1>

    <form id="lookupForm">
      <label for="username">Username:</label>
      <input type="text" id="username" name="username" required>
  
      <button type="submit">Search</button>
    </form></div>
    <div id="result"></div>`

    const form = document.querySelector('#lookupForm');
const resultDiv = document.querySelector('#result');

form.addEventListener('submit', (event) => {
  event.preventDefault();

  const username = document.querySelector('#username').value;
let getSingleCustomer='http://localhost:8080/admin/customer'
  const url = `${getSingleCustomer}/${username}`;                                //replace username here

  fetch(url)
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Failed to fetch user.');
      }
    })
    .then(data => {
      
    //   const user = data.user;

    //   resultDiv.textContent = `User found: ${user.name}`;
    displaySingleUser(data);
    })
    .catch(error => {
      console.error('Error:', error);
      resultDiv.textContent = 'Failed to fetch user.';
      alert('Failed to fetch user.');
    });
});


function displaySingleUser(data){


    productContent.innerHTML=` <div class="viewAllProducts">
    <h2>User Found</h2>
    <table id="productTable">
      <tr style="position:sticky; top:0%; background-color:#111011;">
       <th>Id</th>
        <th>Name</th>
        <th>UserName</th>
        <th>Status</th>
        <th>Role</th>
      </tr>
    </table>
  </div>`;
  var table = document.getElementById('productTable');
    var product = data;
 
            var row = table.insertRow();
            var customerId=row.insertCell();
            customerId.innerText=product.customerId;
           var nameCell = row.insertCell();
           nameCell.innerText = product.name;
 
           var mailCell = row.insertCell();
           
           mailCell.innerText = product.email;
     
 
           var acitveCell = row.insertCell();
           if(product.active==true)
           acitveCell.innerText = "Active";
           else
           acitveCell.innerText = "Deactivated";
 
           var roleCell = row.insertCell();
           roleCell.innerText = product.role;
  }
}}