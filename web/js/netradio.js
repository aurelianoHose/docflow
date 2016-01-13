function play(src, title, img) {
    var p = document.getElementById('audio-player');
    p.setAttribute('src', src);
    p.play();

    var t = document.getElementById('p_title');
    t.innerHTML = title;

    var im = document.getElementById('p_img');
    im.setAttribute('src',img);
}
