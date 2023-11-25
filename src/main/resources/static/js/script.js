//calls API
async function changeNbrCandidats(EtabEpreuveid, nbrCandidats) {
    const jwtToken = sessionStorage.getItem("jwt");
    if (jwtToken) {
        const requestOptions = {
            headers: {
                'Authorization': `Bearer ${jwtToken}`,
                'Set-Cookie': ''
            },
            credentials: 'omit'
        }
        await fetch('/api/epreuve/nbr-candidats/' + EtabEpreuveid + '/' + nbrCandidats, requestOptions)
    } else {
        console.error("Pas de jwt");
    }
}
async function changeDispo(id, input){
    let resultatConfirm = false;
    if (!input.checked) {
        resultatConfirm = confirm(input.getAttribute('data-confirm-delete'))
    }
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
            for (const input of inputs) {
                let idEtab = parseInt(input.parentNode.parentNode.className)
                await changeNbrJuries(id, idEtab, 0)
                input.value = 0
                calcTotalEtab(input)
                calcTotalJour(input)
                changeColor(input)
                input.setAttribute('disabled', '')
            }
            calcTotal()
        } else {
            console.error("Pas de jwt");
        }
    } else {
        input.checked = true
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
            let classe = 'totalNbr' + id
            let inputs = document.querySelectorAll('input[type=number].'+classe);
            inputs.forEach((input) => {
                input.removeAttribute("disabled")
            })
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

//variables
let inputs = document.querySelectorAll("input.nbrJury")
let inputsCand = document.querySelectorAll("input.nbrCand")
let inputsJury = document.querySelectorAll("input.jurysConv")

//déclarations fonctions
function calcTotalEtab(input) {
    let tr = input.parentNode.parentNode
    let resultat = 0
    for (const element of tr.querySelectorAll("input.nbrJury")) {
        resultat += parseInt(element.value)
    }
    tr.lastElementChild.innerText = resultat
    //décrémenter jurys à convoquer
    //TODO
    //tr.querySelector("input.jurysConv").value--
}
function calcTotalJour(input) {
    //récupérer le nom de la classe de l'input ciblé commençant par 'totalNbr'
    let classe = ""
    let classes = input.classList
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
function calcJuryConv(input) {
    let tr = input.parentNode.parentNode
    let resultat = 0
    resultat += Math.ceil(parseInt((input.value))/5)
    tr.querySelector("input.jurysConv").value = resultat
}
function changeColor(input) {
    let tr = input.parentNode.parentNode
    let inputJ = tr.querySelector("input.jurysConv")
    if (inputJ.value > parseInt(tr.lastElementChild.innerText)) {
        tr.lastElementChild.style.color = "orange"
    }
    if (inputJ.value == parseInt(tr.lastElementChild.innerText)) {
        tr.lastElementChild.style.color = "green"
    }
    if (inputJ.value < parseInt(tr.lastElementChild.innerText)) {
        tr.lastElementChild.style.color = "red"
    }
}

//appels
for (const input of inputs) {
    input.addEventListener("change", () => {
        calcTotalEtab(input)
        calcTotalJour(input)
        calcTotal()
        changeColor(input)
    })
}
for (const input of inputsCand) {
    input.addEventListener("change", () => {
        calcJuryConv(input)
        changeColor(input)
    })
}
for (const input of inputsJury) {
    input.addEventListener("change", () => {
        changeColor(input)
    })
}
window.addEventListener("load", () => {
    for (const input of inputs) {
        calcTotalEtab(input)
        calcTotalJour(input)
    }
    calcTotal()
    for (const input of inputsCand) {
        calcJuryConv(input)
        changeColor(input)
    }
    for (const input of inputsJury) {
        changeColor(input)
    }
});