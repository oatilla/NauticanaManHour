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
