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
    <button id="viewAllCategory">View Category</button>
    <button id="removeCategoryBtn">Remove Category</button>
</div>
<div id="productContent"><h1 class="bouncing-text">Category Menu</h1></div>
</div>
`;

var addNewCategoryBtn=document.getElementById("addNewCategoryBtn");
var removeCategoryBtn=document.getElementById("removeCategoryBtn");
var vieAllCategoryHandler=document.getElementById("viewAllCategory");




addNewCategoryBtn.addEventListener("click",()=>{
    productContent.innerHTML="";
    productContent.innerHTML=`
    <div class="container">
    <h1>Add Category</h1>
    <form id="categoryForm">
      <label for="categoryName">Category Name:</label>
      <input type="text" id="categoryName" name="categoryName" required>
  
      <button type="submit">Add Category</button>
    </form>
  </div>
  `;

  const form = document.querySelector('#categoryForm');

  form.addEventListener('submit', (event) => {
    event.preventDefault();
  
    const categoryName = document.querySelector('#categoryName').value;
  
    const url = 'http://localhost:8080/admin/add/categories';
  
    const data = {
      name: categoryName
    };
  
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
      .then(response =>{ 
        if(response.ok)
        alert("Category Added SuccessFully")
         return response.json()})
      .then(data => {
        console.log('Category added successfully:', data);
        // Perform any further actions with the response data
      })
      .catch(error => {
        console.error('Error:', error);
      });
  });
  
})


removeCategoryBtn.addEventListener("click",()=>{
    productContent.innerHTML="";
    productContent.innerHTML=`
    <div class="container">
  <h1>Remove Category</h1>
  <form id="removeCategoryForm">
    <label for="categoryId">Category ID:</label>
    <input type="text" id="categoryId" name="categoryId" required>

    <button type="submit">Remove Category</button>
  </form>
</div>

  `;

  const form = document.querySelector('#removeCategoryForm');

  form.addEventListener('submit', (event) => {
    event.preventDefault();
  
    const categoryId = document.querySelector('#categoryId').value;
  
    const url = `http://localhost:8080/admin/delete/category/${categoryId}`;
  
    fetch(url, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        if (response.ok) {
          console.log('Category removed successfully.');
          alert('Category removed successfully.');
        } else {
          console.log('Failed to remove category.');
          alert('Failed to remove category.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });
  });
  
  
})






vieAllCategoryHandler.addEventListener("click",()=> {
    console.log('View Products button clicked');
   productContent.innerHTML="";
   productContent.innerHTML=` <div class="viewAllProducts">
   <h2>Product List</h2>
   <table id="productTable">
     <tr style="position:sticky; top:0%; background-color:#111011;">
     <th>Id</th>
       <th> Category Name</th>
       <th>Status</th>
       </tr>
       </table>
       </div>`;
       fetchProducts();
 function fetchProducts() {
  let viewAllProd='http://localhost:8080/admin/category';
    fetch(viewAllProd)
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
           prodId.innerText=product.categoryId;
          var nameCell = row.insertCell();
          nameCell.innerText = product.name;

         
    
          var availableCell = row.insertCell();
          availableCell.innerText = product.active ? 'Yes' : 'No';


          
        }
      })
      .catch(function(error) {
        console.error('Error:', error);
      });

    }})
}

