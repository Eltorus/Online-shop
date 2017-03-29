$(document).ready(function(){
    $("#pTable").on('click', '#updateProduct', function() {
	var currentRow=$(this).closest("tr");
	var pid = currentRow.find('td:eq(0)').text();
	var pTitle = currentRow.find('td:eq(1)').text();
	var pCategory = $(this).data("cat");
	var pPrice = currentRow.find('td:eq(3)').text();
	var pDesc = currentRow.find('td:eq(4)').text();
	var pAmount = currentRow.find('td:eq(5)').text();
	$(".modal-body #product_id").val( pid );
    $(".modal-body #title").val( pTitle );
    $(".modal-body #category").val( pCategory );
    $(".modal-body #price").val( pPrice );
    $(".modal-body #description").val( pDesc );
    $(".modal-body #amount").val( pAmount ); 
});
});

$("#addProduct").click(function() {
	$(".modal-body #title").val("");
    $(".modal-body #category").val("");
    $(".modal-body #price").val("");
    $(".modal-body #description").val("");
    $(".modal-body #amount").val(""); 
});