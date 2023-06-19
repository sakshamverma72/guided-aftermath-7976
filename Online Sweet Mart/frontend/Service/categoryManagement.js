const categoryBtn = document.getElementById('categoryBtn');
categoryBtn.addEventListener('click', handleCategoryManagement);

function handleCategoryManagement() {
    console.log('Category Management button clicked');
    // Add your code to handle the category management action here
    content.innerHTML="";
    content.innerHTML=`
    <div class="productContainer">
    
    <div class="submenu" id="productSubMenu">
    <button id="addNewCategoryBtn">Add Category</button>
    <button id="removeCategoryBtn">Remove Category</button>
</div>
<div id="productContent"><h1 class="bouncing-text">Category Menu</h1></div>

</div>
`;
    
}