function showDetails(id) {
    var detailDiv = document.getElementById(id);
    var details = document.querySelectorAll('.details');

    for (var i = 0; i < details.length; i++) {
        details[i].style.display = 'none';
    }

    detailDiv.style.display = 'block';
}