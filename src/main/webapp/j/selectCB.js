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
