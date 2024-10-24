document.getElementById('postalCode')
    .addEventListener('input', (e) => {

    const cepValue = e.target.value.replace(/\D/g, '');

    if (cepValue.length === 8) {
        buscarEndereco(cepValue);
    }
});

async function buscarEndereco(cep) {

    const url = `https://viacep.com.br/ws/${cep}/json/`;

    try {
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error('Erro ao buscar o endereço');
        }

        const endereco = await response.json();

        document.getElementById('streetName').value = endereco.logradouro;
        document.getElementById('neighborhood').value = endereco.bairro;
        document.getElementById('city').value = endereco.localidade;
        document.getElementById('state').value = endereco.uf;

    } catch (error) {
        console.error('Erro:', error);
    }
}


