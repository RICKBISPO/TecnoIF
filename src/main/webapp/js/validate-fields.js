document.getElementById('form1').addEventListener('submit', function(event) {
    event.preventDefault();

    const inputs = this.querySelectorAll('input[type="text"]');

    inputs.forEach(input => {
        if (/^\s*$/.test(input.value)) {
            input.style.borderColor = 'red';
        } else {
            input.style.borderColor = '';
        }
    });
});