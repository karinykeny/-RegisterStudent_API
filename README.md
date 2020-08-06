# RegisterStudent_API

> Projeto API Rest para cadastro de estudantes
- Headers: 
> Content-Type: application/json
- Authorization: 
> Type: Basic Auth
> Username: studantapi
> Password: @studant@123

##### Criar cadastro de estudante
 
> URI: http://localhost:8080/estudantes
> Method: POST
> Body:

```json
{
    "nome": "Teste",
    "sobrenome": "da Silva Dantas",
    "telefones": [
        {
            "numeroTelefone": "81999999999"
        },
        {
            "numeroTelefone": "81988888888"
        }
    ],
    "email": "teste@teste.com.br",
    "cpf": "34198577056",
    "endereco": {
        "rua": "Rua teste1",
        "numero": 10,
        "bairro": "bairro teste1",
        "cidade": "Olinda",
        "uf": "PE",
        "cep": "50000000",
        "complemento": "Casa"
    }
}
```

- Response:
> Status Code: 201

##### Consultar estudante

> URI: http://localhost:8080/estudantes/2
> Method: GET

- Response:
> Status Code: 200

##### Alterar estudante

> URI: http://localhost:8080/estudantes/2
> Method: PUT

- Response:
> Status Code: 200

##### Listar estudantes

> URI: http://localhost:8080/estudantes
> Method: GET

- Response:
> Status Code: 200

##### Excluir estudante

> URI: http://localhost:8080/estudantes/1
> Method: DELETE

- Response:
> Status Code: 204



##### Filtrar estudantes por multiplos parâmetros

> URI: http://localhost:8080/estudantes?filter&nome=teste2&uf=PE
> Method: GET

- Response:
> Status Code: 200
