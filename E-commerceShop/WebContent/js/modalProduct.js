$("#pTable").on('click', '#updateProduct', function() {
	var currentRow=$(this).closest("tr");
	var pid = currentRow.find('td:eq(0)').text();
	var pTitle = currentRow.find('td:eq(2)').text();
	var pCategory = $(this).data("cat");
	var pPrice = currentRow.find('td:eq(4)').text();
	var pDesc = currentRow.find('td:eq(5)').text();
	var pAmount = currentRow.find('td:eq(6)').text();
	$("#productInfo #product_id").val( pid );
    $("#productInfo #title").val( pTitle );
    $("#productInfo #category").val( pCategory );
    $("#productInfo #price").val( pPrice );
    $("#productInfo #description").val( pDesc );
    $("#productInfo #amount").val( pAmount ); 
});

$("#addProduct").click(function() {
	$("#productInfo #title").val("");
    $("#productInfo #category").val("");
    $("#productInfo #price").val("");
    $("#productInfo #description").val("");
    $("#productInfo #amount").val(""); 
});