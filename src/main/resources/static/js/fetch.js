async function changeDispo(id,value){
    await fetch("http://localhost:8080/api/epreuve/jour/"+id+"/"+value, {method: "GET"});
}

function changePonctuel(id,value){
    fetch("http://localhost:8080/api/etablissement/ponctuel/"+id+"/"+value, {method: "GET"});
}

function changeNbrJurys(id,value){
    fetch("http://localhost:8080/api/nbrJurys/nbr/"+id+"/"+value, {method: "GET"});
}