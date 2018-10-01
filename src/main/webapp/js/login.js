/**
 * 
 */
$(function() { // anonymous function short for doucment ready or document load

	function showPassword() {

		var key_attr = $('#pass').attr('type');

		if (key_attr != 'text') {

			$('.checkbox').addClass('show');
			$('#pass').attr('type', 'text');

		} else {

			$('.checkbox').removeClass('show');
			$('#pass').attr('type', 'password');

		}

	}
});