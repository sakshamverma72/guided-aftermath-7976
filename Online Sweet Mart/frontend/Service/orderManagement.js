const orderBtn = document.getElementById('orderBtn');
orderBtn.addEventListener('click', handleOrderManagement);
function handleOrderManagement() {
    console.log('Order Management button clicked');
    // Add your code to handle the order management action here
    content.innerHTML="";


    content.innerHTML=`
    <div class="productContainer">
    
    <div class="submenu" id="productSubMenu">
    <button id="viewAllCustomersBtn">View Customers</button>
    <button id="editRole">Edit Role</button>
    <button id="deactivateCustomerBtn">Deactivate Customer</button>
    <button id="searchByMailBtn">Search Customer</button>
</div>
<div id="productContent"><h1 class="bouncing-text">Orders Menu</h1></div>

</div>
`;
}