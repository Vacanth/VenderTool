// Set current page in top-nav
(function() {
	var topNav = $('.hdr .topnav'),
		location = document.location.href, 
		link
	;
	
	if (location.indexOf('accounthub') > 0) {
		link = topNav.find('.accounthub');
		if (link) {
			link.addClass('curr');
		}
	}
})();

