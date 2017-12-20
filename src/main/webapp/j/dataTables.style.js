$(document).ready(function () { $('.report').DataTable({
 	'paging':false, 
 	'scrollY':'50vh',
	'scrollX': true,
  	'lengthChange':false, 
 	'searching':false, 
 	'ordering':false, 
 	'info':true, 
 	'autoWidth': true ,
 	'responsive': true,
 	'scrollCollapse': false,
 	dom: 'Bfrtip',
    buttons: [
        'copyHtml5',
        'excelHtml5',
        'csvHtml5',
        {
            "extend": "pdfHtml5",
            "orientation": "landscape",
            "text" : "PDF"		                    
        },
        'print'
    ]
	
}).columns.adjust().draw();


$('.wide-list').DataTable({
 	'paging':false, 
 	'scrollY':'50vh',
	'scrollX': true,
  	'lengthChange':false, 
 	'searching':false, 
 	'ordering':false, 
 	'info':true, 
 	'autoWidth': true ,
 	'responsive': true,
 	'scrollCollapse': false
}).columns.adjust().draw();

$('.thin-list').DataTable({
	'paging':false, 
	'scrollY': '50vh', 
	'lengthChange':false, 
	'searching':true, 
	'ordering':true, 
	'info':true, 
	'autoWidth': true, 
	'responsive': true, 
	'scrollCollapse': true 
}).columns.adjust().draw();

}) 