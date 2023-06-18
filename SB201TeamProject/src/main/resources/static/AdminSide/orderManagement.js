const orderBtn = document.getElementById('orderBtn');
orderBtn.addEventListener('click', handleOrderManagement);
function handleOrderManagement() {
    console.log('Order Management button clicked');
    // Add your code to handle the order management action here
    content.innerHTML="";


    content.innerHTML=`
    <div class="productContainer">
    
    <div class="submenu" id="productSubMenu">
    <button id="viewAllOrdersBtn">View All Orders</button>
    <button id="viewSingleOrderBtn">View Customer Orders</button>
    <button id="removeOrderByIdBtn">Remove Order</button>
</div>
<div id="productContent"><h1 class="bouncing-text">Orders Menu</h1></div>

</div>
`;

var viewAllOrdersBtn=document.getElementById("viewAllOrdersBtn");
var viewSingleOrderBtn=document.getElementById("viewSingleOrderBtn");
var removeOrderByIdBtn=document.getElementById("removeOrderByIdBtn");
var productContainer=document.querySelector(".productContainer");

//view All orders Handler
viewAllOrdersBtn.addEventListener("click",()=>{
    productContent.innerHTML="";
    productContent.innerHTML=` <div class="viewAllProducts">
    <h2>Orders List</h2>
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
})



//view single order handler here
viewSingleOrderBtn.addEventListener("click",()=>{
    productContent.innerHTML="View single order  Clicked";
})



//remove order handler
removeOrderByIdBtn.addEventListener("click",()=>{
    productContent.innerHTML="remove order  Clicked";
})





}