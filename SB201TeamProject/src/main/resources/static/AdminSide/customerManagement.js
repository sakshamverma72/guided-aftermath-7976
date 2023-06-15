const customerBtn = document.getElementById('customerBtn');
customerBtn.addEventListener('click', handleCustomerManagement);
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