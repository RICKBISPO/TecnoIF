const form = document.getElementById('form1');

form.addEventListener('submit', () => {

    const inputs = this.querySelectorAll('input[type="text"]');

    inputs.forEach(input => {
        if (/^\s*$/.test(input.value)) {

            const alertContainer = document.createElement('div');
            alertContainer.className = 'alert alert-danger alert-dismissible fade show';
            alertContainer.role = 'alert';
            alertContainer.innerHTML = `
                Preencha todos os campos
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            `;

            let form1 = document.getElementById('form1');

            form1.insertBefore(alertContainer, form.firstChild);
        }
    });
});