$("#phone").mask("+375 (99) 999-99-99");
$('#datepicker').mask("99.99.9999");

$('#address').on('keyup', function() {
	var address = $('#address').val();
	if(address.length != 0) {
		$('#addressinput').attr('class', 'form-group has-feedback');
		$('#purchase').prop('disabled', false);
	} else {
		$('#addressinput').attr('class', 'form-group has-feedback has-error');
		$('#purchase').prop('disabled', true);
	}
});

function isValidDate(s) {
	var bits = s.split('.');
	var d = new Date(bits[2], bits[1] - 1, bits[0]);
	return d && (d.getMonth() + 1) == bits[1];
}