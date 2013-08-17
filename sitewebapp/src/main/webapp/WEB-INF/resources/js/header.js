// When language menu is changed, form is submitted to set the language
(function() {
	var _form, _menu = $('#languageMenu');
	if (_menu) {
		_menu.bind('change', function() {
			_form = $(this).closest('form');
			if (_form) {
				_form.submit();
			}
		});
	}
})();

// Account dropdown menu
(function() {
	var _menu = $('#accountMenu');
	
	$('#accountBtn').bind('click', function(evt) {
		if (_menu.is(':visible')) {
			_menu.hide();
		}
		else {
			_menu.show();
		}
		return false;
	});

	$('body').bind('click', function(evt) {
		if (_menu.is(':visible') && evt.target.id!=='accountMenu') {
			_menu.hide();
		}
	});
	
})();