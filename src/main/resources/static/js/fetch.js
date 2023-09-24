async function changeDispo(id, value){
    await fetch("http://localhost:8080/api/epreuve/jour/"+id+"/"+value);
}

async function changePonctuel(id, value){
    await fetch("http://localhost:8080/api/etablissement/ponctuel/"+id+"/"+value);
}

async function changeNbrJuries(jourId, etabEpreuveId, nbrJury){
    await fetch("http://localhost:8080/api/epreuve/nbr-juries/"+jourId+"/"+etabEpreuveId+"/"+nbrJury);
}