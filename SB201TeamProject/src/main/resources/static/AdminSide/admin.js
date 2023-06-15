// Get the menu buttons




const logoutBtn = document.getElementById('logoutBtn');
const content=document.getElementById("content");
// Add event listeners to the buttons




logoutBtn.addEventListener('click', handleLogout);




  //clock code
  function updateClock() {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');
    const timeString = `${hours}:${minutes}:${seconds}`;
    document.getElementById('clock').textContent = timeString;
}







// Button click event handlers


// Initial call to update the clock immediately
updateClock();

// Update the clock every second
setInterval(updateClock, 1000);









function handleLogout() {
    console.log('Logout button clicked');
  window.location="www.goole.com";
}
