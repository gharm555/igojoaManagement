document.addEventListener('DOMContentLoaded', function () {
    var tabs = document.querySelectorAll('#myTab .nav-link');
    var firstVisibleTab = null;

    for (var i = 0; i < tabs.length; i++) {
        if (tabs[i].offsetParent !== null) {
            firstVisibleTab = tabs[i];
            break;
        }
    }

    if (firstVisibleTab) {
        firstVisibleTab.classList.add('active');
        document.querySelector(firstVisibleTab.getAttribute('href')).classList.add('show', 'active');
    }
});
