async function signin(username, password) {
    fetch('http://localhost:8080/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'   
        },
        body: JSON.stringify({ username, password })
    })
    .then(response => response.ok ? response.json() : response.status)
    .catch(error => {
        console.log("Error:" + error);
        return null;
    });
}