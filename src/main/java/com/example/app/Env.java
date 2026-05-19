package com.example.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Env {
    private final Map<String, String> values = new HashMap<>();

    public Env() {
        loadDotEnv();
    }

    private void loadDotEnv() {
        File f = new File(".env");
        if (!f.exists()) return;
        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int idx = line.indexOf('=');
                if (idx <= 0) continue;
                String k = line.substring(0, idx).trim();
                String v = line.substring(idx + 1).trim();
                if ((v.startsWith("\"") && v.endsWith("\"")) || (v.startsWith("'") && v.endsWith("'"))) {
                    v = v.substring(1, v.length() - 1);
                }
                values.put(k, v);
            }
        } catch (IOException e) {
            System.err.println("Warning: could not read .env: " + e.getMessage());
        }
    }

    public String get(String key, String defaultValue) {
        return values.getOrDefault(key, defaultValue);
    }

    public String get(String key) {
        return values.get(key);
    }
}
