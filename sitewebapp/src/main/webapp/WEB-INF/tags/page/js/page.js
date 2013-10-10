var PageUtil = {};

PageUtil.showSpinner = function() {
	$('#pageSpinner').show();
};

PageUtil.hideSpinner = function() {
	$('#pageSpinner').fadeOut();
};

PageUtil.scrollTop = function() {
	$('html, body').animate({ scrollTop: 0 }, 'fast');
};

