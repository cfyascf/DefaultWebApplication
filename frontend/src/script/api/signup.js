async function signup() {
    var username = document.getElementById('username-input').value;
    var fullname = document.getElementById('fullname-input').value;
    var email = document.getElementById('email-input').value;
    var password = document.getElementById('password-input').value;
    var confirmpassword = document.getElementById('confirmpassword-input').value;

    const input = { username, fullname, email, password, confirmpassword };
    const token = localStorage.getItem('jwt');
    console.log(token);

    try {
        const response = await fetch('http://localhost:8080/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(input)
        });

        if(!response.ok) {
            throw new Error('Error registering:' + response.status);
        }

        const data = await response.json();
        const html_message = document.getElementById('api-response');

        if(!data.success) {
            html_message.textContent = data.message;
            return;
        }

        window.location.href = 'main.html';

    } catch(error) {
        console.error('Error registering:', error);
        const html_message = document.getElementById("api-response");
        html_message.textContent = 'Error registering';
    }
}