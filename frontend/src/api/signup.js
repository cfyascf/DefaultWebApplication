async function signup(username, fullname, email, role, department, password, confirmpassword) {
    const token = localStorage.getItem("jwt");

    fetch('http://localhost:8080/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token   
        },
        body: JSON.stringify({ username, fullname, email, role, department, password, confirmpassword })
    })
    .then(response => {
        if(response.status == 400) {
            throw new Error('Bad request.');

        } else if(response.status == 401) {
            throw new Error('Unauthorized.');

        } else if(response.status == 403) {
            throw new Error('Forbidden.');
        }

        return response.json();
    })
    .catch(error => {
        console.log("Error:" + error);
        return null;
    });
}