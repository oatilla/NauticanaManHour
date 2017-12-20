$(function () {
  $("td").dblclick(function () {
    var OriginalContent = $(this).text();
    $(this).addClass("cellEditing");
    $(this).html("<input type='text' value='" + OriginalContent + "' />");
    $(this).children().first().focus();
    $(this).children().first().keypress(function (e) {
      if (e.which == 13) {
        var newContent = $(this).val();
        $(this).parent().text(newContent);
        $(this).parent().removeClass("cellEditing");
      }
    });
    $(this).children().first().blur(function(){
      $(this).parent().text(OriginalContent);
      $(this).parent().removeClass("cellEditing");
    });
  });
});


function exportTableToCSV(filename) {
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        csv.push(row.join(","));        
    }
    downloadCSV(csv.join("\n"), filename);
}

function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    csvFile = new Blob([csv], {type: "text/csv"});
    downloadLink = document.createElement("a");
    downloadLink.download = filename;
    downloadLink.href = window.URL.createObjectURL(csvFile);
    downloadLink.style.display = "none";
    document.body.appendChild(downloadLink);
    downloadLink.click();
}

function exportExcel(table_id){
    var blob = new Blob([document.getElementById(table_id).innerHTML], {
        type: "text/plain;charset=utf-8;"
    });
    saveAs(blob, "tableExport.xls");
}

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

function doAjaxPost(target) {
	var stop = 0;
	$('input.required').each(function(){
		
		if($(this).val() == ''){
			$(this).css({'border-color':'red'});
			stop=1;
		}else $(this).css({'border-color':''});
		
	});

	if(stop == 0){
		$.ajax({
	     type: "POST",
	     url : target,
	     data: $("#f").serialize(),
	     success : function( response ) {
	        $("#content").html( response );
	     }
		});
	};
}

function selectAll(cname) {
  var checkBoxes = document.getElementsByName(cname);
  for (var i in checkBoxes) {
    checkBoxes[i].checked = true;
  }
}

function deSelectAll(cname) {
  var checkBoxes = document.getElementsByName(cname);
  for (var i in checkBoxes) {
    checkBoxes[i].checked = false;
  }
}

function toggleSelect(cname) {
  var checkBoxes = document.getElementsByName(cname);
  for (var i in checkBoxes) {
    checkBoxes[i].checked = !checkBoxes[i].checked;
  }
}
