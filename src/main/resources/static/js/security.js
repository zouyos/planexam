document.querySelector('#login-form').addEventListener('submit', function (event) {
  event.preventDefault(); // Empêche l'envoi du formulaire par défaut

  // Récupérer les valeurs des champs
  const email = document.querySelector('#username').value;
  const password = document.querySelector('#password').value;
  const csrfToken = document.getElementById('csrfToken').value;

  // Construire l'objet de données pour la requête
  const data = {
    email: email,
    password: password,
    csrfToken: csrfToken
  };

  // Envoyer la requête Fetch
  fetch('/authenticate', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'X-CSRF-TOKEN': csrfToken
    },
    body: JSON.stringify(data)
  })

    .then(response => {
      console.log(response)
      return response.json()
    })
    .then(data => {
      sessionStorage.setItem("jwt",data.token)
      // Gérer la réponse, par exemple, rediriger l'utilisateur vers une nouvelle page
      //
      console.log(data); // Vous pouvez afficher la réponse dans la console
      // Rediriger l'utilisateur ou effectuer d'autres actions en fonction de la réponse
      document.querySelector('#login-form').submit();
    })
    .catch(error => {
      console.error('Erreur:', error);
      document.querySelector('#login-form').submit();
      // Gérer les erreurs, par exemple, afficher un message d'erreur à l'utilisateur
    });
});