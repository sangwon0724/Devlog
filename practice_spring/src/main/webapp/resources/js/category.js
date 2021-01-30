var iframe = document.getElementById('category_iframe');

window.addEventListener('DOMContentLoaded', function () {
	iframe.addEventListener('load', autoHeight);
});
	
function autoHeight() {
	var frame = iframe;
	var sub = frame.contentDocument ? frame.contentDocument : frame.contentWindow.document;
	iframe.height = sub.body.scrollHeight;
}