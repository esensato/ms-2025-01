const OpenAPIClientAxios = require("openapi-client-axios").default;

const api = new OpenAPIClientAxios({
    definition: "http://localhost:8084/v3/api-docs",
});
api.init()
    .then((client) =>
        client.matricular({ rm: "200", idDisciplina: "200" })
    )
    .then((res) => console.log("Resultado:", res.data));
