const OpenAPIClientAxios = require("openapi-client-axios").default;

const api = new OpenAPIClientAxios({
    definition: "http://localhost:8080/v3/api-docs",
});

api.init().then((client) => client.getAlunoByPath(2).then((res) => console.log("Resultado:", res.data)))

