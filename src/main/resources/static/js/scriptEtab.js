async function changePonctuel(id, value){
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
        await fetch("/api/etablissement/ponctuel/"+id+"/"+value, requestOptions);
    } else {
        // Si aucun JWT n'est trouvé, affiche un message d'erreur dans la console
        console.error("Pas de jwt");
    }
}