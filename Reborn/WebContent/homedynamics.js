
if (document.location.search.match(/type=embed/gi)) {
    window.parent.postMessage("resize", "*");
}

window.console = window.console || function(t) {
};

//Apparition/Désapparition bande synopsis 

var boites = document.getElementsByClassName("boite_pochette");



var toggleBande = function() {
    document.querySelector(".bande_synopsis").classList.toggle("bande_synopsis--ouverte");
};

function changerTexte(e) {
    var texte = document.getElementById("texte");
    texte.firstChild.nodeValue = jsListeSynopsis[e.target.firstElementChild.firstElementChild.getNodeValue];
};

for (var i = 0; i < boites.length; i++) {
    boites[i].addEventListener('mouseenter', toggleBande, false);
    boites[i].addEventListener('mouseenter', changerTexte, false);
    boites[i].addEventListener('mouseleave', toggleBande, false);
}