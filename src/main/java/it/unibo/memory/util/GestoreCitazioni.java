package it.unibo.memory.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GestoreCitazioni {

    // Indirizzo preciso a cui chiediamo la citazione (Endpoint)
    private static final String URL_API = "https://dummyjson.com/quotes/random";

    /**
     * Creo una richiesta HTTP GET per recuperare una citazione casuale.
     * @return La stringa della citazione o una frase di backup in caso di errore.
     */
    public static String getCitazioneCasuale() {
        try {
            // 1. Preparo la connessione (Request)
            URL url = new URL(URL_API);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000); // Timeout di 5 secondi per non bloccare il gioco

            // 2. Controllo del Response Code (tramite codice di stato http 200 mi accerto che la connessione sia andata a buon fine)
            if (conn.getResponseCode() != 200) {
                return "La logica ti porterà da A a B. L'immaginazione ovunque.";
            }

            // 3. Lettura del flusso di dati (InputStream mi occupo di leggere il flusso di byte trasformandolo in una stringa leggibile riga dopo riga)
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder risposta = new StringBuilder();
            while (scanner.hasNextLine()) {
                risposta.append(scanner.nextLine());
            }
            scanner.close();

            // 4. Estrazione manuale della stringa dal JSON
            String json = risposta.toString();
            // Cerco la chiave "quote" nel formato JSON
            int inizio = json.indexOf("\"quote\":\"") + 9;
            int fine = json.indexOf("\"", inizio);

            return json.substring(inizio, fine);

        } catch (Exception e) {
            // In caso di errore (es. mancanza di internet), stampiamo la frase di default
            e.printStackTrace();
            return "Non mollare mai! Il successo è un viaggio, non una meta.";
        }
    }
}