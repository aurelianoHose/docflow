function chngFavorite(element, id, name) {

    $.ajax({
        method : 'POST',
        url : "http://localhost:8080/netradio/ws/streams/" + id + "?status="
                + (element.getAttribute("class") == "fa fa-star-o fa-2x")
                + "&uname=" + name
    });

    if (element.getAttribute("class") == "fa fa-star-o fa-2x") {
        element.setAttribute("class", "fa fa-star fa-2x");
    } else {
        element.setAttribute("class", "fa fa-star-o fa-2x");
    }
}