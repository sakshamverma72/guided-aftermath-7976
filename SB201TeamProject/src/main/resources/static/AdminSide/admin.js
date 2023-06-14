// Get the menu buttons
const productBtn = document.getElementById('productBtn');
const customerBtn = document.getElementById('customerBtn');
const orderBtn = document.getElementById('orderBtn');
const categoryBtn = document.getElementById('categoryBtn');
const logoutBtn = document.getElementById('logoutBtn');
const content=document.getElementById("content");
// Add event listeners to the buttons
productBtn.addEventListener('click', handleProductManagement);
customerBtn.addEventListener('click', handleCustomerManagement);
orderBtn.addEventListener('click', handleOrderManagement);
categoryBtn.addEventListener('click', handleCategoryManagement);
logoutBtn.addEventListener('click', handleLogout);




  //clock code
  function updateClock() {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');
    const timeString = `${hours}:${minutes}:${seconds}`;
    document.getElementById('clock').textContent = timeString;
}







// Button click event handlers


// Initial call to update the clock immediately
updateClock();

// Update the clock every second
setInterval(updateClock, 1000);



function handleProductManagement() {
    console.log('Product Management button clicked');
    content.innerHTML="";


    content.innerHTML=`
    <div class="productContainer">
    
    <div class="submenu" id="productSubMenu">
    <div class="loading" id="loadingAnimation"></div>
    <button id="addProductBtn">Add Product</button>
    <button id="viewProductsBtn">View Products</button>
    <button id="editProductBtn">Update Product</button>
    <button id="deleteProductBtn">Delete Product</button>
</div>
<div id="productContent"><h1 class="bouncing-text">Product Menu</h1></div>

</div>
`;

const addProductBtn = document.getElementById('addProductBtn');
const viewProductsBtn = document.getElementById('viewProductsBtn');
const editProductBtn = document.getElementById('editProductBtn');
const deleteProductBtn = document.getElementById('deleteProductBtn');

addProductBtn.addEventListener('click', handleAddProduct);
viewProductsBtn.addEventListener('click', handleViewProducts);
editProductBtn.addEventListener('click', handleEditProduct);
deleteProductBtn.addEventListener('click', handleDeleteProduct);

function handleAddProduct() {
    console.log('Add Product button clicked');
    productContent.innerHTML="";
    productContent.innerHTML=
    ` <div class="container">
    <h2 style="margin-left:12vw">Add New Product</h2>
    <form id="productForm">
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
      </div>
      <div class="form-group">
        <label for="image">Image URL:</label>
        <input type="text" id="image" name="image" required>
      </div>
      <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" required>
      </div>
      <div class="form-group">
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>
      </div>
      <div class="form-group">
        <label for="available">Available:</label>
        <select id="available" name="available" required>
          <option value="true">Yes</option>
          <option value="false">No</option>
        </select>
      </div>
      <div class="form-group">
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" required>
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>`;

  function postData(url, data) {
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
     
        console.log('API response:', result);
       
    })
    .catch(error => {
       
        console.error('Error:', error);
    });
}

const productForm = document.getElementById('productForm');
productForm.addEventListener('submit', function (event) {
    event.preventDefault(); 

    const name = document.getElementById('name').value;
    const image = document.getElementById('image').value;
    const price = document.getElementById('price').value;
    const description = document.getElementById('description').value;
    const available = document.getElementById('available').value === 'true';
    const category = document.getElementById('category').value;

    const productData = {
        name: name,
        image: image,
        price: price,
        description: description,
        available: available,
        category: category
    };
console.log(productData);
    const apiUrl = 'https://localhost:8080/products'; //replace api

    postData(apiUrl, productData);
});



}

function handleViewProducts() {
    console.log('View Products button clicked');
   productContent.innerHTML="";
   productContent.innerHTML=` <div class="viewAllProducts">
   <h2>Product List</h2>
   <table id="productTable">
     <tr>
       <th>Name</th>
       <th>Image</th>
       <th>Price</th>
       <th>Description</th>
       <th>Available</th>
       <th>Category</th>
     </tr>
   </table>
 </div>`;

 function fetchProducts() {
    fetch(apiUrl)
      .then(function(response) {
        return response.json();
      })
      .then(function(data) {
        var table = document.getElementById('productTable');
        table.innerHTML = '';

        for (var i = 0; i < data.length; i++) {
          var product = data[i];

          var row = table.insertRow();

          var nameCell = row.insertCell();
          nameCell.innerText = product.name;

          var imageCell = row.insertCell();
          imageCell.innerHTML = '<img src="' + product.image + '" alt="' + product.name + '">';

          var priceCell = row.insertCell();
          priceCell.innerText = product.price;

          var descriptionCell = row.insertCell();
          descriptionCell.innerText = product.description;

          var availableCell = row.insertCell();
          availableCell.innerText = product.available ? 'Yes' : 'No';

          var categoryCell = row.insertCell();
          categoryCell.innerText = product.category;

          
        }
      })
      .catch(function(error) {
        console.error('Error:', error);
      });

}
}

function handleEditProduct() {
    console.log('Edit Product button clicked');
    // Add your code to handle the Edit Product action here
}

function handleDeleteProduct() {
    console.log('Delete Product button clicked');
    // Add your code to handle the Delete Product action here
}
    


}

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
}

function handleeditRole() {
    console.log('editRole button clicked');
    // Add your code to handle the View Products action here
}

function handledeactivateCustomerBtn() {
    console.log('handledeactivateCustomerBtn button clicked');
    // Add your code to handle the Edit Product action here
}

function handleSearchByMailBtn() {
    console.log('handleSearchByMailBtn button clicked');
    // Add your code to handle the Delete Product action here
}






}

function handleOrderManagement() {
    console.log('Order Management button clicked');
    // Add your code to handle the order management action here
}

function handleCategoryManagement() {
    console.log('Category Management button clicked');
    // Add your code to handle the category management action here
}

function handleLogout() {
    console.log('Logout button clicked');
  window.location="www.goole.com";
}
