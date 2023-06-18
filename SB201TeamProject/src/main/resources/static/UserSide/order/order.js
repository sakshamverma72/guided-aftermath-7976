const productBtn = document.getElementById('productBtn');
const customerBtn = document.getElementById('customerBtn');
const orderBtn = document.getElementById('orderBtn');
const categoryBtn = document.getElementById('categoryBtn');
const logoutBtn = document.getElementById('logoutBtn');
const content=document.getElementById("content");
// Add event listeners to the buttons
productBtn.addEventListener('click',  handleAddProduct);
//customerBtn.addEventListener('click', handleCustomerManagement);
orderBtn.addEventListener('click', handleDeleteProduct);
categoryBtn.addEventListener('click',handleViewProducts );
//logoutBtn.addEventListener('click', handleLogout);
const apiUrl = 'https://fakestoreapi.com/products'; //replace api

function displayProducts() {
    // Array of products
    const products = [
      { name: 'Product 1', price: 10 },
      { name: 'Product 2', price: 20 },
      { name: 'Product 3', price: 30 },
      { name: 'Product 4', price: 40 },
      { name: 'Product 5', price: 50 }
    ];
  
    // Get the container element
    // content = document.getElementById('productContainer');
  
    // Clear the container
    content.innerHTML = '';
  
    // Loop through the products and create HTML elements
    products.forEach(product => {
      // Create product element
      const productElement = document.createElement('div');
      productElement.innerHTML = `
        <span>${product.name} - $${product.price}</span>
        <button onclick="addToCart('${product.name}', ${product.price})"  style="background-color: red;">Add</button>
      `;
  
      // Append product element to the container
      content.appendChild(productElement);
    });
  }
  

  function removeProducts() {
    // Array of products
    console.log("hello");
    const products = [
      { id: 1, name: 'Product 1', price: 10 },
      { id: 2, name: 'Product 2', price: 20 },
      { id: 3, name: 'Product 3', price: 30 },
      { id: 4, name: 'Product 4', price: 40 },
      { id: 5, name: 'Product 5', price: 50 }
    ];
  
    // Get the container element
    //content = document.getElementById('productContainer');
  
    // Clear the container
    content.innerHTML = '';
  
    // Loop through the products and create HTML elements
    products.forEach(product => {
      // Create product element
      const productElement = document.createElement('div');
      productElement.innerHTML = `
        <span>${product.name} - $${product.price}</span>
        <button onclick="removeFromCart(${product.id})"style="background-color: red;">Remove</button>
      `;
  
      // Append product element to the container
      content.appendChild(productElement);
    });
  }
  
  function removeFromCart(productId) {
    // This is just a placeholder function for demo purposes
    console.log(`Removed product with ID ${productId} from cart.`);
  }

  function displayProductsall() {
    // Array of products
    const products = [
      { name: 'Product 1', price: 10 },
      { name: 'Product 2', price: 20 },
      { name: 'Product 3', price: 30 },
      { name: 'Product 4', price: 40 },
      { name: 'Product 5', price: 50 }
    ];
  
    // Get the container element
    // content = document.getElementById('productContainer');
  
    // Clear the container
    content.innerHTML = '';
  
    // Loop through the products and create HTML elements
    products.forEach(product => {
      // Create product element
      const productElement = document.createElement('div');
      productElement.innerHTML = `
        <span>${product.name} - $${product.price}</span>
      `;
  
      // Append product element to the container
      content.appendChild(productElement);
    });
  }
  
 // viewProductsBtn.addEventListener('click', handleViewProducts);

  function handleViewProducts() {
      console.log('View Products button clicked');
     content.innerHTML="";
     content.innerHTML=` <div class="viewAllProducts">
     <h2>Sweets List</h2>
     <table id="productTable">
       <tr style="position:sticky; top:0%; background-color:#111011;">
       <th>Id</th>
         <th>Name</th>
         <th>Image</th>
         <th>Price</th>
         <th>Description</th>
         <th>Available</th>
         <th>Category</th>
       </tr>
     </table>
   </div>`;
   fetchProducts();
   function fetchProducts() {
      fetch(apiUrl)
        .then(function(response) {
          return response.json();
        })
        .then(function(data) {
          var table = document.getElementById('productTable');
          // table.innerHTML = '';
  
          for (var i = 0; i < data.length; i++) {
            
            var product = data[i];
  
             var row = table.insertRow();
             var prodId=row.insertCell();
             prodId.innerText=product.id;
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


  function handleDeleteProduct() {
    console.log('Delete Product button clicked');
    content.innerHTML=`
    <div class="container">
    <h2>Cancel Sweet</h2>
  <form id="deleteForm">
    <div class="form-group">
      <label for="productId">Product ID:</label>
      <input type="number" id="productId" name="productId" required>
    </div>
    <button type="submit">Delete</button>
  </form></div>`;

  document.getElementById("deleteForm").addEventListener("submit",(e)=>{ 
    e.preventDefault();
    var deleteId=document.getElementById("productId").value;
  fetch(`${apiUrl}/${deleteId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
      // Add any other headers required by your API
    }
  })
    .then(response => {
      if (response.ok) {
        console.log('Item deleted successfully.');
        alert('Item deleted successfully. '+deleteId);
      } else {

        console.log('Failed to delete item.');
        alert('Product Not Found Id -> '+deleteId)
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });})}   


    function handleAddProduct() {
      console.log('Add Product button clicked');
      content.innerHTML=`
      <div class="container">
      <h2>Add Sweet</h2>
    <form id="deleteForm">
      <div class="form-group">
        <label for="productId">Product ID:</label>
        <input type="number" id="productId" name="productId" required>
      </div>
      <button type="submit">Add</button>
    </form></div>`;
  
    document.getElementById("deleteForm").addEventListener("submit",(e)=>{ 
      e.preventDefault();
      var deleteId=document.getElementById("productId").value;
    fetch(`${apiUrl}/${deleteId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
        // Add any other headers required by your API
      }
    })
      .then(response => {
        if (response.ok) {
          console.log('Item added successfully.');
          alert('Item added successfully. '+deleteId);
        } else {
  
          console.log('Failed to add item.');
          alert('Product Not Found Id -> '+deleteId)
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });})}