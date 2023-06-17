const productBtn = document.getElementById('productBtn');
productBtn.addEventListener('click', handleProductManagement);
const apiUrl = 'https://648c85bd8620b8bae7ed09b3.mockapi.io/products'; //replace api
function handleProductManagement() {
    console.log('Product Management button clicked');
    content.innerHTML="";


    content.innerHTML=`
    <div class="productContainer">
    
    <div class="submenu" id="productSubMenu">
    <div class="loading" id="loadingAnimation"></div>
    <button id="addProductBtn">Add Product</button>
    <button id="viewProductsBtn">View Products</button>
    <button id="editProductBtn">Update Product</button>
    <button id="deleteProductBtn">Delete Product</button>
</div>
<div id="productContent"><h1 class="bouncing-text">Product Menu</h1></div>

</div>
`;

const addProductBtn = document.getElementById('addProductBtn');
const viewProductsBtn = document.getElementById('viewProductsBtn');
const editProductBtn = document.getElementById('editProductBtn');
const deleteProductBtn = document.getElementById('deleteProductBtn');

addProductBtn.addEventListener('click', handleAddProduct);
viewProductsBtn.addEventListener('click', handleViewProducts);
editProductBtn.addEventListener('click', handleEditProduct);
deleteProductBtn.addEventListener('click', handleDeleteProduct);

function handleAddProduct() {
    console.log('Add Product button clicked');
    productContent.innerHTML="";
    productContent.innerHTML=
    ` <div class="container">
    <h2 style="margin-left:12vw">Add New Product</h2>
    <form id="productForm">
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
      </div>
      <div class="form-group">
        <label for="image">Image URL:</label>
        <input type="text" id="image" name="image" required>
      </div>
      <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" required>
      </div>
      <div class="form-group">
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>
      </div>
      <div class="form-group">
        <label for="available">Available:</label>
        <select id="available" name="available" required>
          <option value="true">Yes</option>
          <option value="false">No</option>
        </select>
      </div>
      <div class="form-group">
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" required>
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>`;

  function postData(url, data) {
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
     
        console.log('API response:', result);
        alert("Product Added");
       
    })
    .catch(error => {
       
        console.error('Error:', error);
    });
}

const productForm = document.getElementById('productForm');
productForm.addEventListener('submit', function (event) {
    event.preventDefault(); 

    const name = document.getElementById('name').value;
    const image = document.getElementById('image').value;
    const price = document.getElementById('price').value;
    const description = document.getElementById('description').value;
    const available = document.getElementById('available').value === 'true';
    const category = document.getElementById('category').value;

    const productData = {
        name: name,
        image: image,
        price: price,
        description: description,
        available: available,
        category: category
    };
console.log(productData);
   

    postData(apiUrl, productData);
});



}

function handleViewProducts() {
    console.log('View Products button clicked');
   productContent.innerHTML="";
   productContent.innerHTML=` <div class="viewAllProducts">
   <h2>Product List</h2>
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

function handleEditProduct() {
    console.log('Edit Product button clicked');
    // Add your code to handle the Edit Product action here
    productContent.innerHTML="";
    productContent.innerHTML=

    `<div class="container"><form id="checkProductAvailability">
<label for="idInput">Enter Product ID:</label>
<input type="number" id="idInput" name="idInput" required>
<input type="submit" value="Check Availability">
</form></div>`;
var checkProductAvailability=document.getElementById("checkProductAvailability");
checkProductAvailability.addEventListener('submit',(e)=>{
  e.preventDefault();
  fetch(apiUrl)
  .then(function(response) {
    return response.json();
  })
  .then(function(data) {
mainUpdateHandler(data);
      })
      .catch(function(error) {
        console.error('Error:', error);
      });

}
)
function notAvailableForm(id){
  alert("No Product Found With the given Id "+id);
 }


function mainUpdateHandler(data){
console.log(data)
    var table = document.getElementById('productTable');
    // table.innerHTML = '';
    
    var checkProductId=document.getElementById("idInput").value;
        for (var i = 0; i < data.length; i++) {
          var id=parseInt(data[i].id);



if(id==checkProductId){
  createForm(data[i]);
  return;
} }
        notAvailableForm(checkProductId,data);
        return;

}
}

function createForm(obj){
  console.log(obj.description)
  productContent.innerHTML=
  ` <div class="container">
  <h2 style="margin-left:12vw">Update Product : ( Id : ${obj.id} )</h2>
  <form id="productForm">
    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" required value=${obj.name}>
    </div>
    <div class="form-group">
      <label for="image">Image URL:</label>
      <input type="text" id="image" name="image" required value=${obj.image}>
    </div>
    <div class="form-group">
      <label for="price">Price:</label>
      <input type="number" id="price" name="price" required value=${obj.price}>
    </div>
    <div class="form-group">
      <label for="description">Description:</label>
      <input type="text" id="description" name="description" required value=${obj.description}>
    </div>
    <div class="form-group">
      <label for="available">Available:</label>
      <select id="available" name="available" required value=${obj.available}>
        <option value="true">Yes</option>
        <option value="false">No</option>
      </select>
    </div>
    <div class="form-group">
      <label for="category">Category:</label>
      <input type="text" id="category" name="category" required value=${obj.category}>
    </div>
    <button type="submit">Submit</button>
  </form>
</div>`;
var updateProductBtn=document.getElementById("productForm");

updateProductBtn.addEventListener("submit",(e)=>{
  e.preventDefault();
  try{
    const name = document.getElementById('name').value;
    const image = document.getElementById('image').value;
    const price = document.getElementById('price').value;
    const description = document.getElementById('description').value;
    const available = document.getElementById('available').value;
    const category = document.getElementById('category').value;
    var productData = {
      name: name,
      image: image,
      price: price,
      description: description,
      available: available,
      category: category
  };
     let postProductval= fetch(`${apiUrl}/${obj.id}`,{
          method:"PUT",
          headers:{
              'Content-Type':'application/json'
          },
          body:JSON.stringify(productData)
      })
          alert("Product Updated") 
  }catch(err){
      console.log("Some issue");
  }
})

}


function handleDeleteProduct() {
    console.log('Delete Product button clicked');
    productContent.innerHTML=`
    <div class="container">
    <h2>Delete Product</h2>
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
    });})}}