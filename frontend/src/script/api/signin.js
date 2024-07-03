async function signin() {
    var username = document.getElementById('username-input').value;
    var password = document.getElementById('password-input').value;

    const input = { username, password };

    try {
        const response = await fetch('http://localhost:8080/signin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(input)
        });

        if(!response.ok) {
            throw new Error('Error signin in:' + response.status);
        }

        const data = await response.json();
        const html_message = document.getElementById('api-response');

        if(!data.success) {
            html_message.textContent = data.message;
            return;
        }

        localStorage.setItem('jwt', data.token);
        window.location.href = 'main.html';

    } catch(error) {
        console.error('Erro ao fazer login:', error);
        const html_message = document.getElementById("api-response");
        html_message.textContent = 'Error loginin';
    }
}