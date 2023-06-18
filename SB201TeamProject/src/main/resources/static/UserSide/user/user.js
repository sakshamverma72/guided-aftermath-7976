const productBtn = document.getElementById('productBtn');
const customerBtn = document.getElementById('customerBtn');
const orderBtn = document.getElementById('orderBtn');
const categoryBtn = document.getElementById('categoryBtn');
const logoutBtn = document.getElementById('logoutBtn');
const content=document.getElementById("content");
const customerId = 3;
const customerApi='https://648c85bd8620b8bae7ed09b3.mockapi.io/customers/3';
// Add event listeners to the buttons
productBtn.addEventListener('click',  displayProducts);
customerBtn.addEventListener('click',handledeactivateCustomerBtn);
orderBtn.addEventListener('click', handleviewAllCustomersBtn);
//categoryBtn.addEventListener('click',displayProductsall );
//logoutBtn.addEventListener('click', handleLogout);

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
      { id: 1, name: 'User 1' },
      { id: 2, name: 'User 2'},
      { id: 3, name: 'User 3'},
      { id: 4, name: 'User 4'},
      { id: 5, name: 'User 5' }
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
      { name: 'User 1',},
      { name: 'User 2',  },
      { name: 'User 3',  },
      { name: 'User 4',  },
      { name: 'User 5',  }
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
  
  function handleviewAllCustomersBtn() {
    console.log('View All Customers Clicked');
    // Add your code to handle the Add Product action here
    content.innerHTML="";
    content.innerHTML=` <div class="viewAllProducts">
    <h2>User Details</h2>
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
     fetch(customerApi)
       .then(function(response) {
       
        return response.json();
       })
       .then(function(data) {
        console.log(data)
         var table = document.getElementById('productTable');
         // table.innerHTML = '';
 
        
           
           var product = data;
 
            var row = table.insertRow();
            var customerId=row.insertCell();
            customerId.innerText=product.customerId;
           var nameCell = row.insertCell();
           nameCell.innerText = product.name;
 
           var mailCell = row.insertCell();
           
           mailCell.innerText = product.mailOrUsername;
     
 
           var acitveCell = row.insertCell();
           acitveCell.innerText = product.active;
 
           var roleCell = row.insertCell();
           roleCell.innerText = product.role;
 
           
         
       })
       .catch(function(error) {
         console.error('Error:', error);
       });
 
 }  
}


function handledeactivateCustomerBtn() {
  console.log('handledeactivateCustomerBtn button clicked');
  // Add your code to handle the Edit Product action here

  content.innerHTML="";
  content.innerHTML=`
  <div class="container">
  <h1>Delete User</h1>

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

const url = `${customerApi}/${customerId}`;

fetch(url, {
  method: 'PATCH',
  headers: {
    'Content-Type': 'application/json'
    // Add any other headers required by your API
  },
  body: JSON.stringify({ active: false }) 
})
  .then(response => {
    if (response.ok) {
      console.log('Customer deactivated successfully.');
      alert('User deleted successfully.');
    } else {
      console.log('Failed to deactivate customer.');
      alert('Failed to delete  customer.');
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

  content.innerHTML="";
  content.innerHTML=` 
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

const url = `${customerApi}/${1}`;                                //replace username here

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


  content.innerHTML=` <div class="viewAllProducts">
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
         
         mailCell.innerText = product.mailOrUsername;
   

         var acitveCell = row.insertCell();
         acitveCell.innerText = product.active;

         var roleCell = row.insertCell();
         roleCell.innerText = product.role;
}



}