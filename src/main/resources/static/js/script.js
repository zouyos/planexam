async function changeDispo(id, value){
    await fetch("http://localhost:8080/api/epreuve/jour/"+id+"/"+value);
}

async function changePonctuel(id, value){
    await fetch("http://localhost:8080/api/etablissement/ponctuel/"+id+"/"+value);
}

async function changeNbrJuries(jourId, etabEpreuveId, nbrJury){
    await fetch("http://localhost:8080/api/epreuve/nbr-juries/"+jourId+"/"+etabEpreuveId+"/"+nbrJury);
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

        //récupérer le nom de la classe de l'input ciblé commençant par 'totalNbr'
        let classes = e.target.classList
        let classe = ""
        for (const classeElement of classes) {
            if(classeElement.startsWith('totalNbr')) {
                classe = classeElement
            }
        }
        console.log(classe)
        //récupérer les inputs qui ont la même classe
        let inputsCol = document.querySelectorAll('input.'+classe)
        //sommer la valeur des inputs ayant la même classe
        let resultat2 = 0
        for (const inputCol of inputsCol) {
            resultat2 += parseInt(inputCol.value)
        }
        //cibler la cellule 'total' du jour en question (lui donner un id dynamique) et y afficher le résultat
        document.querySelector('#'+classe).innerText = resultat2
    })
}