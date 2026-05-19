package com.example.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService {
    private final HttpClient client = HttpClient.newHttpClient();

    public String fetchAddressByCep(String cep) {
        cep = cep.replaceAll("\\D", "");
        if (cep.length() != 8) return null;
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() != 200) return null;
            JsonObject obj = JsonParser.parseString(res.body()).getAsJsonObject();
            if (obj.has("erro") && obj.get("erro").getAsBoolean()) return null;
            String logradouro = obj.has("logradouro") ? obj.get("logradouro").getAsString() : "";
            String bairro = obj.has("bairro") ? obj.get("bairro").getAsString() : "";
            String local = obj.has("localidade") ? obj.get("localidade").getAsString() : "";
            String uf = obj.has("uf") ? obj.get("uf").getAsString() : "";
            return String.format("%s, %s - %s/%s", logradouro, bairro, local, uf).replaceAll("^,\\s*", "");
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }
}
