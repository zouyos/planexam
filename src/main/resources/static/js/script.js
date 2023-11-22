async function changeNbrCandidats(EtabEpreuveid, nbrCandidats) {
    await fetch('/api/epreuve/nbr-candidats/' + EtabEpreuveid + '/' + nbrCandidats)
}

async function changeDispo(id, input){
    let resultatConfirm = false;
    if (!input.checked) {
        resultatConfirm = confirm(input.getAttribute('data-confirm-delete'))
        if (resultatConfirm == true) {
            const jwtToken = sessionStorage.getItem("jwt");
            if (jwtToken) {
                const requestOptions = {
                    headers: {
                        'Authorization': `Bearer ${jwtToken}`,
                        'Set-Cookie': ''
                    },
                    credentials: 'omit'
                }
                await fetch("/api/epreuve/jour/" + id + "/" + false, requestOptions);
                let classe = 'totalNbr' + id
                let inputs = document.querySelectorAll('input[type=number].'+classe);
                for (let i = 0; i < inputs.length; i++) {
                    await changeNbrJuries(id, i+1, 0)
                }
            } else {
                console.error("Pas de jwt");
            }
        } else {
            input.checked = true
        }
    }
    if (input.checked) {
        const jwtToken = sessionStorage.getItem("jwt");
        if (jwtToken) {
            const requestOptions = {
                headers: {
                    'Authorization': `Bearer ${jwtToken}`,
                    'Set-Cookie': ''
                },
                credentials: 'omit'
            }
            await fetch("/api/epreuve/jour/" + id + "/" + true, requestOptions);
        } else {
            console.error("Pas de jwt");
        }
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

//calculer les totaux
let inputs = document.querySelectorAll("input.nbrJury")
function calcTotalEtab(e) {
    let tr = e.target.parentNode.parentNode
    let resultat = 0
    for (const element of tr.querySelectorAll("input.nbrJury")) {
        resultat += parseInt(element.value)
    }
    tr.lastElementChild.innerText = resultat
    //décrémenter jurys à convoquer
    //TODO
    //tr.querySelector("input.jurysConv").value--
}
function calcTotalJour(e) {
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
    let resultat = 0
    for (const inputCol of inputsCol) {
        resultat += parseInt(inputCol.value)
    }
    //cibler la cellule 'total' du jour en question (lui donner un id dynamique) et y afficher le résultat
    document.querySelector('#'+classe).innerText = resultat
}
function calcTotal() {
    let resultat = 0
    for (const input of inputs) {
        resultat += parseInt(input.value)
    }
    document.querySelector("#totalJury").innerText = resultat
}
for (const input of inputs) {
    input.addEventListener("change", (e) => {
        calcTotalEtab(e)
        calcTotalJour(e)
        calcTotal()
    })
}
window.addEventListener("load", (e) => {
    for (const input of inputs) {
        calcTotalEtab(e)
        calcTotalJour(e)
        calcTotal()
    }
});

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
    })
}

//pour calc jury by nbrCand
function calcJuryConv() {
    let tr = e.target.parentNode.parentNode
    let resultat = 0
    resultat += Math.ceil(parseInt((e.target.value))/5)
    tr.querySelector("input.jurysConv").value = resultat
}
let inputsCand = document.querySelectorAll("input.nbrCand")
for (const input of inputsCand) {
    input.addEventListener("change", (e) => {
        calcJuryConv()
    })
}
window.addEventListener("load", (e) => {
    for (const input of inputsCand) {
        calcJuryConv()
    }
});