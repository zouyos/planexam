async function changeNbrCandidats(EtabEpreuveid, nbrCandidats) {
    await fetch('/api/epreuve/nbr-candidats/' + EtabEpreuveid + '/' + nbrCandidats)
}

async function changeDispo(id, input){
    let resultatConfirm = false;
    if (!input.checked) {
        resultatConfirm = confirm(input.getAttribute('data-confirm-delete'))
    }
    if (resultatConfirm == true || input.checked) {
        // Récupère le JWT depuis la session de stockage du navigateur
        const jwtToken = sessionStorage.getItem("jwt");

        // Vérifie si un JWT existe dans la session de stockage
        if (jwtToken) {
            // Si un JWT est trouvé, configure les options de la requête
            const requestOptions = {
                headers: {
                    'Authorization': `Bearer ${jwtToken}`, // Ajoute le JWT dans l'en-tête d'autorisation
                    'Set-Cookie': ''
                },
                credentials: 'omit'
            }
            await fetch("/api/epreuve/jour/" + id + "/" + input.checked, requestOptions);
        } else {
            // Si aucun JWT n'est trouvé, affiche un message d'erreur dans la console
            console.error("Pas de jwt");
        }
    } else if(resultatConfirm == false) {
        input.checked = true
    }
}

async function changeNbrJuries(jourId, etabEpreuveId, nbrJury){
    const jwtToken = sessionStorage.getItem("jwt");
    if (jwtToken) {
        const requestOptions = {
            headers: {
                'Authorization': `Bearer ${jwtToken}`,
                'Set-Cookie': ''
            },
            credentials: 'omit'
        }
        await fetch("/api/epreuve/nbr-juries/"+jourId+"/"+etabEpreuveId+"/"+nbrJury, requestOptions);
    } else {
        console.error("Pas de jwt");
    }
}

let inputs = document.querySelectorAll("input.nbrJury")
for (const input of inputs) {
    input.addEventListener("change", (e) => {
        let tr = e.target.parentNode.parentNode
        let resultat = 0
        for (const element of tr.querySelectorAll("input.nbrJury")) {
            resultat += parseInt(element.value)
        }
        tr.lastElementChild.innerText = resultat
        tr.querySelector("input.jurysConv").value--

        //pour les colonnes :

        //récupérer le nom de la classe de l'input ciblé commençant par 'totalNbr'
        let classe = ""
        let classes = e.target.classList
        for (const classeElement of classes) {
            if(classeElement.startsWith('totalNbr')) {
                classe = classeElement
            }
        }
        //récupérer les inputs qui ont la même classe
        let inputsCol = document.querySelectorAll('input[type=number].'+classe)
        //sommer la valeur des inputs ayant la même classe
        let resultat2 = 0
        for (const inputCol of inputsCol) {
            resultat2 += parseInt(inputCol.value)
        }
        //cibler la cellule 'total' du jour en question (lui donner un id dynamique) et y afficher le résultat
        document.querySelector('#'+classe).innerText = resultat2

        let resultat3 = 0
        for (const input of inputs) {
            resultat3 += parseInt(input.value)
        }
        document.querySelector("#totalJury").innerText = resultat3
    })
}

//pour réinitialiser les jurys jours

let inputsJours = document.querySelectorAll('input[type=checkbox]')
for (const inputJour of inputsJours) {
    inputJour.addEventListener("change", (e) => {
        let classe = ""
        let classes = e.target.classList
        for (const classeElement of classes) {
            if (classeElement.startsWith('totalNbr')) {
                classe = classeElement
            }
        }
        let inputsCol = document.querySelectorAll('input[type=number].'+classe)
        console.log(inputsCol)
    })
}

//pour calc jury by nbrCand

let inputsCand = document.querySelectorAll("input.nbrCand")
for (const input of inputsCand) {
    input.addEventListener("change", (e) => {
        let tr = e.target.parentNode.parentNode
        let resultat = 0
        resultat += Math.ceil(parseInt((e.target.value))/5)
        tr.querySelector("input.jurysConv").value = resultat
    })
}

window.addEventListener("load", (e) => {
    for (const input of inputsCand) {
        let tr = input.parentNode.parentNode
        let resultat = 0
        resultat += Math.ceil(parseInt((input.value))/5)
        tr.querySelector("input.jurysConv").value = resultat
    }
});