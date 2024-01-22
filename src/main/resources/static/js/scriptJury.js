fetch("/api/jury/etabs-epreuve/"+1)
    .then(response => response.json())
    .then(response => alert(JSON.stringify(response)))
    .catch(error => alert("Erreur : " + error));

//WIP
let jourSelectValue = document.getElementById("juryJour").value;

async function updateSecondSelect(jourSelectValue) {
    let etabEpreuveSelect = document.getElementById("juryEtab");

    // Clear previous options
    etabEpreuveSelect.innerHTML = '';

    await fetch("/api/jury/etabs-epreuve/"+jourSelectValue).then((res) => {

    })
    options = []
    for (const option of options) {
        let optionElement = document.createElement("option");
        optionElement.text = option.jour.dateJ;
        optionElement.value = option.jour.id;
        etabEpreuveSelect.appendChild(optionElement);
    }
}
