const productBtn = document.getElementById('productBtn');
const customerBtn = document.getElementById('customerBtn');
const orderBtn = document.getElementById('orderBtn');
const categoryBtn = document.getElementById('categoryBtn');
const logoutBtn = document.getElementById('logoutBtn');
const content=document.getElementById("content");
// Add event listeners to the buttons
productBtn.addEventListener('click',  displayProducts);
//customerBtn.addEventListener('click', handleCustomerManagement);
orderBtn.addEventListener('click', removeProducts);
categoryBtn.addEventListener('click',displayProductsall );
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
  
