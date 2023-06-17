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
    productContent.innerHTML="View All Clicked";
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