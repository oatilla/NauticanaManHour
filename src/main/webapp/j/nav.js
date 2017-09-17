function showMenu() {
  if (  document.getElementById("accordion").style.display == "block") {
    document.getElementById("accordion").style.display = "none";
    document.getElementById("content").style.left = "0";
  } else {
    document.getElementById("accordion").style.display = "block";
    document.getElementById("content").style.left = "200px";
  }
}

function doAjaxGet(target) {
  $.ajax({
     type: "GET",
     url : target,
     success : function( response ) {
        $("#content").html( response );
     }
  });
}
function doAjaxPost(x) {

    $.ajax({
        type: "POST",
        url: x,
        data: $("#f").serialize(),
        success: function(response) {
            $("#content").html( response );
        }
    });
}
