# EnergyAlert - Global Solution: SOA e Web Services

O projeto é uma aplicação que serve como um sistema de alerta comunitário para falhas e quedas de energia. Seu principal objetivo é de ajudar comunidades a se organizarem em relação às quedas de energia e manterem-se informados sobre os locais onde podem ocorrer mais riscos com chuvas fortes.

A plataforma permite que cidadãos se cadastrem fornecendo seus dados básicos e o CEP de sua residência, e que técnicos autorizados emitam alertas utilizando informações em tempo real fornecidas por uma API pública de clima.

### Principais funcionalidades

- Cadastro de Cidadãos e Técnicos com informações essenciais
- Integração automática com a API ViaCEP para preenchimento de endereço
- Emissão de alertas com base na condição climática atual, consultada via OpenWeather
- Associação de cada alerta ao técnico responsável
- Visualização de alertas por CEP, permitindo que os cidadãos acompanhem os riscos em sua região
- Gerenciamento de dados via banco de dados SQL, com persistência das informações

---

## 🌨️ Integrantes

Aline Fernandes - RM97966

Camilly Ishida - RM551474

Julia Leite - RM550201

---

## 🌨️ Guia de Requisições Postman - Serviço de Alerta

### 1. Requisitos

Execute a classe `Main` para ter o servidor ativo em: `http://localhost:8080`

## 2. Cadastrar Cidadão

- **URL:** `POST http://localhost:8080/cidadaos/cadastro`
- **Body (JSON): Exemplo**
```json
{
"nome": "João Souza",
"email": "joao@email.com",
"senha": "123456",
"cep": "01311000"
}
```
- A requisição deve retornar essas informações e adicionar automaticamente UF, bairro e cidade, por causa da API ViaCEP

![image](https://github.com/user-attachments/assets/3a25bf5f-4744-4da6-897e-cc0bb5576a4f)


## 3. Cadastrar Técnico

- **URL:** `POST http://localhost:8080/tecnicos/cadastro`
- **Body (JSON): Exemplo**
```json
{
  "nome": "Julia Leite",
  "email": "exemplo@email.com",
  "senha": "senha123",
  "administrador": true
}
```

![image](https://github.com/user-attachments/assets/8fed29ed-ce84-4e8a-bb81-7ef6cc8f0fc7)


## 4. Listar Cidadãos

No navegador:
- **URL:** `http://localhost:8080/cidadaos`

![image](https://github.com/user-attachments/assets/cabe5d5a-6e31-4927-905a-fa667775115f)

Ou no Postman:
- **URL:** `GET http://localhost:8080/cidadaos`

![image](https://github.com/user-attachments/assets/3d47f0c0-ae37-47da-951b-3151bda38918)


## 5. Emitir Alerta

- **URL:** `POST http://localhost:8080/alertas/emitir`
- **Body (JSON): Exemplo**
```json
{
  "cep": "01311000",
  "tecnicoEmail": "exemplo@email.com"
}
```

![image](https://github.com/user-attachments/assets/4a93c2bf-1b5d-4dbb-ab48-00b9c6fa1398)


## 6. Ver Alertas Por CEP

No navegador:
- **URL:** `http://localhost:8080/alertas/cep/{cep}`
- **Exemplo:** `http://localhost:8080/alertas/cep/01311000`

![image](https://github.com/user-attachments/assets/0773ab83-3f9b-4105-964c-b2747f11a0a2)

Ou no Postman:
- **URL:** `GET http://localhost:8080/alertas/cep/{cep}`
- **Exemplo:** `GET http://localhost:8080/alertas/cep/01311000`

![image](https://github.com/user-attachments/assets/297bdddd-0f83-4cae-9753-9797914c9457)



## 7. Deletar Alerta

- **URL:** `DELETE http://localhost:8080/alertas/{id}`
- **Exemplo:** `DELETE http://localhost:8080/alertas/1`

![image](https://github.com/user-attachments/assets/b7e4df36-a5f0-4ce6-8989-5fe3da4cf7fd)
