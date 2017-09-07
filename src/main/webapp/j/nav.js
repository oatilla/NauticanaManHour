function showMenu() {
  document.getElementById("showMenuBtn").style.display = "none";
  document.getElementById("shutMenuBtn").style.display = "block";
  document.getElementById("accordion").style.display = "block";
  document.getElementById("content").style.left = "200px";
}

function shutMenu() {
  document.getElementById("shutMenuBtn").style.display = "none";
  document.getElementById("showMenuBtn").style.display = "block";
  document.getElementById("accordion").style.display = "none";
  document.getElementById("content").style.left = "0";
}