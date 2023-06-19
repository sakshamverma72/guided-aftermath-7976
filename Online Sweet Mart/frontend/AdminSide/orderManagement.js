const orderBtn = document.getElementById('orderBtn');
orderBtn.addEventListener('click', handleOrderManagement);
function handleOrderManagement() {
    console.log('Order Management button clicked');
    // Add your code to handle the order management action here
    content.innerHTML="";


    content.innerHTML=`
    <div class="productContainer">
    
    <div class="submenu" id="productSubMenu">
    <button id="viewSingleOrderBtn">View Customer Orders</button>
    </div>
    <div id="productContent"><h1 class="bouncing-text">Orders Menu</h1></div>
    
    </div>
    `;
    // <button id="viewAllOrdersBtn">View All Orders</button>
    // <button id="removeOrderByIdBtn">Remove Order</button>

// var viewAllOrdersBtn=document.getElementById("viewAllOrdersBtn");
var viewSingleOrderBtn=document.getElementById("viewSingleOrderBtn");
// var removeOrderByIdBtn=document.getElementById("removeOrderByIdBtn");
var productContainer=document.querySelector(".productContainer");

//view All orders Handler
// viewAllOrdersBtn.addEventListener("click",()=>{
//     productContent.innerHTML="View All Clicked";
// })



//view single order handler here
viewSingleOrderBtn.addEventListener("click",()=>{
    productContent.innerHTML="";
    productContent.innerHTML=` 
    <div class="container">
    <h1>Get User Orders</h1>

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
let getSingleCustomer='http://localhost:8080/admin/customer';
  const url = `${getSingleCustomer}/${username}/orders`;                                //replace username here

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
    <h2>Orders Found</h2>
    <table id="productTable">
      <tr style="position:sticky; top:0%; background-color:#111011;">
       <th>Order Id</th>
        <th>Customer Name</th>
        <th>Total Bill</th>
      </tr>
    </table>
  </div>`;
  var table = document.getElementById('productTable');
    var product = data[0];
 console.log(data[0]);
            var row = table.insertRow();
            var customerId=row.insertCell();
            customerId.innerText=product.cart.orders[0].orderId;
           var nameCell = row.insertCell();
           nameCell.innerText = product.name;
 
           var mailCell = row.insertCell();
           
           mailCell.innerText = product.cart.orders[0].products[0].price;
  }
})



//remove order handler
// removeOrderByIdBtn.addEventListener("click",()=>{
//     productContent.innerHTML="remove order  Clicked";
// })





}