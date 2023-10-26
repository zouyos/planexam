async function changeDispo(id, value){
    // Récupère le JWT depuis la session de stockage du navigateur
    const jwtToken = sessionStorage.getItem("jwt");

    // Vérifie si un JWT existe dans la session de stockage
    if (jwtToken) {
        // Si un JWT est trouvé, configure les options de la requête
        const requestOptions = {
            method: 'POST', // Méthode HTTP
            headers: {
                'Authorization': `Bearer ${jwtToken}`, // Ajoute le JWT dans l'en-tête d'autorisation
                'Set-Cookie': ''
            },
            credentials: 'omit'
        }
        await fetch("/api/epreuve/jour/" + id + "/" + value, requestOptions);
    } else {
        // Si aucun JWT n'est trouvé, affiche un message d'erreur dans la console
        console.error("Pas de jwt");
    }
}

async function changeNbrJuries(jourId, etabEpreuveId, nbrJury){
    // Récupère le JWT depuis la session de stockage du navigateur
    const jwtToken = sessionStorage.getItem("jwt");

    // Vérifie si un JWT existe dans la session de stockage
    if (jwtToken) {
        // Si un JWT est trouvé, configure les options de la requête
        const requestOptions = {
            method: 'POST', // Méthode HTTP
            headers: {
                'Authorization': `Bearer ${jwtToken}`, // Ajoute le JWT dans l'en-tête d'autorisation
                'Set-Cookie': ''
            },
            credentials: 'omit'
        }
        await fetch("/api/epreuve/nbr-juries/"+jourId+"/"+etabEpreuveId+"/"+nbrJury, requestOptions);
    } else {
        // Si aucun JWT n'est trouvé, affiche un message d'erreur dans la console
        console.error("Pas de jwt");
    }
}

async function changePonctuel(id, value){
    // Récupère le JWT depuis la session de stockage du navigateur
    const jwtToken = sessionStorage.getItem("jwt");

    // Vérifie si un JWT existe dans la session de stockage
    if (jwtToken) {
        // Si un JWT est trouvé, configure les options de la requête
        const requestOptions = {
            method: 'POST', // Méthode HTTP
            headers: {
                'Authorization': `Bearer ${jwtToken}`, // Ajoute le JWT dans l'en-tête d'autorisation
                'Set-Cookie': ''
            },
            credentials: 'omit'
        }
        await fetch("/api/etablissement/ponctuel/"+id+"/"+value, requestOptions);
    } else {
        // Si aucun JWT n'est trouvé, affiche un message d'erreur dans la console
        console.error("Pas de jwt");
    }
}

let inputs = document.querySelectorAll("tbody tr input")
for (const input of inputs) {
    input.addEventListener("change", (e) => {
        let tr = e.target.parentNode.parentNode
        let resultat = 0
        for (const element of tr.querySelectorAll("input")) {
            resultat += parseInt(element.value)
        }
        tr.lastElementChild.innerText = resultat

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
        let inputsCol = document.querySelectorAll('.'+classe)
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