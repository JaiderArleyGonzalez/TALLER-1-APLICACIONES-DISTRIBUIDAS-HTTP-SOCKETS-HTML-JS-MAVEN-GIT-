package edu.arep.taller;
import java.net.*;
import java.util.HashMap;
import java.io.*;
/**
 * The HttpServer class represents a simple HTTP server.
 */
public class HttpServer {
    
    private ServerSocket serverSocket;
    private boolean running;
    /**
     * Constructs a new HttpServer object.
     */
    public HttpServer() {
        this.serverSocket = null;
        this.running = true;
    }
    /**
     * The main method to start the HTTP server.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(35000);
    }
    /**
     * Starts the HTTP server on the specified port.
     *
     * @param port the port number to listen on
     */
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            e.printStackTrace();
            System.exit(1);
        }

        while (running) {
            handleClientRequest();
        }
    }
    /**
     * Handles the client request.
     */
    private void handleClientRequest() {
        try (Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String inputLine, outputLine;
            boolean firstLine = true;
            String uriStr = "";

            while ((inputLine = in.readLine()) != null) {
                if (firstLine) {
                    uriStr = inputLine.split(" ")[1];
                    firstLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            outputLine = httpClientHtml();
            out.println(outputLine);

        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
    }
    /**
     * Stops the HTTP server.
     */
    public void stop() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing server socket: " + e.getMessage());
        }
    }
    
    /**
     * Generates HTML content for the HTTP response.
     *
     * @return the HTML content
     */
    public static String httpClientHtml() {
        String outputLine =
            "HTTP/1.1 200 OK\r\n" +
            "Content-Type:text/html\r\n" +
            "\r\n" +
            "<!DOCTYPE html>\r\n" +
            "<html>\r\n" +
            "<head>\r\n" +
            "    <title>FilmFinder</title>\r\n" +
            "    <meta charset=\"UTF-8\">\r\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" +
            "    <style>\r\n" +
            "        body {\r\n" +
            "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\r\n" +
            "            background-color: #f4f4f4;\r\n" +
            "            background-image: url('https://static.videezy.com/system/resources/thumbnails/000/043/897/original/200206_01_Particle-star-fall-seamless-loop.jpg');\r\n" +
            "            background-repeat: no-repeat;\r\n" +
            "            background-attachment: fixed; \r\n" +
            "            margin: 0;\r\n" +
            "            padding: 0;\r\n" +
            "            display: flex;\r\n" +
            "            justify-content: center;\r\n" +
            "        }\r\n" +
            "        .container {\r\n" +
            "            width: 80%;\r\n" +
            "            margin: 0 auto;\r\n" +
            "            margin-top: 50px;\r\n" +
            "            background-color: #fff;\r\n" +
            "            border-radius: 10px;\r\n" +
            "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" +
            "            overflow: hidden;\r\n" +
            "            display: flex;\r\n" +
            "            justify-content: center;\r\n" +
            "            align-items: center;\r\n" +
            "        }\r\n" +
            "        .form-container,\r\n" +
            "        .movie-details {\r\n" +
            "            width: 50%;\r\n" +
            "            padding: 20px;\r\n" +
            "        }\r\n" +
            "        .movie-details {\r\n" +
            "            background-color: #f9f9f9;\r\n" +
            "            border-radius: 10px;\r\n" +
            "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" +
            "            overflow: auto;\r\n" +
            "            position: relative;\r\n" +
            "            margin-left: 20px;\r\n" +
            "            height: 700px;\r\n" +
            "        }\r\n" +
            "        .poster-container {\r\n" +
            "            text-align: center;\r\n" +
            "            position: absolute;\r\n" +
            "            top: 0;\r\n" +
            "            right: 0;\r\n" +
            "            padding: 10px;\r\n" +
            "            margin-top: 90px;\r\n" +
            "        }\r\n" +
            "        .movie-details img {\r\n" +
            "            max-width: 300px;\r\n" +
            "            height: auto;\r\n" +
            "            border-radius: 5px;\r\n" +
            "            border: 2px solid #ddd;\r\n" +
            "            padding: 3px;\r\n" +
            "            background-color: #fff;\r\n" +
            "            display: none;\r\n" +
            "        }\r\n" +
            "        .movie-details .movie-info {\r\n" +
            "            margin-right: 150px;\r\n" +
            "        }\r\n" +
            "        .form-container input[type=text],\r\n" +
            "        .form-container select {\r\n" +
            "            width: calc(100% - 22px);\r\n" +
            "            padding: 10px;\r\n" +
            "            margin-bottom: 10px;\r\n" +
            "            border: 1px solid #ccc;\r\n" +
            "            border-radius: 4px;\r\n" +
            "            box-sizing: border-box;\r\n" +
            "        }\r\n" +
            "        .form-container input[type=button] {\r\n" +
            "            width: calc(100% - 22px);\r\n" +
            "            background-color: #4CAF50;\r\n" +
            "            color: white;\r\n" +
            "            padding: 14px 20px;\r\n" +
            "            margin: 8px 0;\r\n" +
            "            border: none;\r\n" +
            "            border-radius: 4px;\r\n" +
            "            cursor: pointer;\r\n" +
            "        }\r\n" +
            "        .form-container input[type=button]:hover {\r\n" +
            "            background-color: #45a049;\r\n" +
            "        }\r\n" +
            "        h1, h2 {\r\n" +
            "            font-family: 'Montserrat', sans-serif;\r\n" +
            "            text-align: center;\r\n" +
            "            color: #333;\r\n" +
            "        }\r\n" +
            "        h1 {\r\n" +
            "            font-size: 2.5rem;\r\n" +
            "            margin-bottom: 30px;\r\n" +
            "        }\r\n" +
            "        h2 {\r\n" +
            "            font-size: 2rem;\r\n" +
            "            margin-bottom: 20px;\r\n" +
            "        }\r\n" +
            "        label {\r\n" +
            "            font-size: 1.2rem;\r\n" +
            "        }\r\n" +
            "        p {\r\n" +
            "            font-size: 1.1rem;\r\n" +
            "            line-height: 1.5;\r\n" +
            "            color: #666;\r\n" +
            "        }\r\n" +
            "        li {\r\n" +
            "            font-size: 1.1rem;\r\n" +
            "            line-height: 1.5;\r\n" +
            "            color: #666;\r\n" +
            "        }\r\n" +
            "    </style>\r\n" +
            "</head>\r\n" +
            "<body>\r\n" +
            "    <div class=\"container\">\r\n" +
            
            "        <div class=\"form-container\">\r\n" +
            "            <h1>FilmFinder</h1>\r\n" +
            "            <form action=\"/hello\">\r\n" +
            "                <label for=\"title\">Title(*):</label><br>\r\n" +
            "                <input type=\"text\" id=\"title\" name=\"title\" required><br><br>\r\n" +
            "                <label for=\"type\">Type:</label><br>\r\n" +
            "                <select id=\"type\" name=\"type\">\r\n" +
            "                    <option value=\"\">-- Select --</option>\r\n" +
            "                    <option value=\"movie\">Movie</option>\r\n" +
            "                    <option value=\"series\">Series</option>\r\n" +
            "                    <option value=\"episode\">Episode</option>\r\n" +
            "                </select><br><br>\r\n" +
            "                <label for=\"year\">Year:</label><br>\r\n" +
            "                <input type=\"text\" id=\"year\" name=\"year\"><br><br>\r\n" +
            "                <label for=\"plot\">Plot:</label><br>\r\n" +
            "                <select id=\"plot\" name=\"plot\">\r\n" +
            "                    <option value=\"\">-- Select --</option>\r\n" +
            "                    <option value=\"short\">Short</option>\r\n" +
            "                    <option value=\"full\">Full</option>\r\n" +
            "                </select><br><br>\r\n" +
            "                <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\r\n" +
            "            </form>\r\n" +
            "        </div>\r\n" +
            "        <div class=\"movie-details\" id=\"movieDetails\">\r\n" +
            "            <div class=\"movie-info\">\r\n" +
            "            <h1>Welcome to FilmFinder!</h1>\r\n" +
            "            <p>Please enter the details of the movie you are looking for.</p>\r\n" +
            "            <p>FilmFinder uses the OMDb API (The Open Movie Database), which is a RESTful web service to obtain movie information. The content and images on the site are provided by the OMDb API.<br> <br> Developed by Jaider Gonzalez</p>\r\n" + 
            "            </div>\r\n" +
            "            <div class=\"poster-container\">\r\n" +
            "                <img src=\"\" alt=\"Poster\" id=\"posterImg\">\r\n" +
            "                <p id=\"noPoster\" style=\"display: none;\"></p>\r\n" +
            "            </div>\r\n" +
            "        </div>\r\n" +
            "    </div>\r\n" +
            "    <script>\r\n" +
            "        let cache = new Map();\r\n" +
            "        function loadGetMsg() {\r\n" +
            "            let titleVar = document.getElementById(\"title\").value;\r\n" +
            "            let typeVar = document.getElementById(\"type\").value;\r\n" +
            "            let yearVar = document.getElementById(\"year\").value;\r\n" +
            "            let plotVar = document.getElementById(\"plot\").value;\r\n" +
            "            let url = \"http://www.omdbapi.com/?apikey=1892fc8d&t=\" + titleVar;\r\n" +
            "            if (typeVar) { url += \"&type=\" + typeVar; }\r\n" +
            "            if (yearVar) { url += \"&y=\" + yearVar; }\r\n" +
            "            if (plotVar) { url += \"&plot=\" + plotVar; }\r\n" +
            "            if (cache.has(url)) {\r\n" +
            "                let data = cache.get(url);\r\n" +
            "                updateUI(data.html);\r\n" +
            "                updatePoster(data.poster);\r\n" +
            "            } else {\r\n" +
            "                const xhttp = new XMLHttpRequest();\r\n" +
            "                xhttp.onload = function() {\r\n" +
            "                    let movieData = JSON.parse(this.responseText);\r\n" +
            "                    if (movieData[\"Response\"] === \"True\") {\r\n" +
            "                        let formattedData = formatMovieDetails(movieData);\r\n" +
            "                        updateUI(formattedData.html);\r\n" +
            "                        updatePoster(movieData[\"Poster\"]);\r\n" +
            "                        cache.set(url, { html: formattedData.html, poster: movieData[\"Poster\"] });\r\n" +
            "                    } else {\r\n" +
            "                        let errorMessage = \"Sorry, the movie you searched for could not be found. Please try another one.\";\r\n" +
            "                        updateUI(errorMessage);\r\n" +
            "                        updatePoster(\"N/A\");\r\n" +
            "                    }\r\n" +
            "                }\r\n" +
            "                xhttp.open(\"GET\", url);\r\n" +
            "                xhttp.send();\r\n" +
            "            }\r\n" +
            "        }\r\n" +
            "        function updateUI(data) {\r\n" +
            "            document.querySelector(\".movie-info\").innerHTML = data;\r\n" +
            "        }\r\n" +
            "        function updatePoster(posterUrl) {\r\n" +
            "            let posterImg = document.getElementById(\"posterImg\");\r\n" +
            "            let noPoster = document.getElementById(\"noPoster\");\r\n" +
            "            if (posterUrl !== \"N/A\") {\r\n" +
            "                posterImg.src = posterUrl;\r\n" +
            "                noPoster.style.display = \"none\";\r\n" +
            "                posterImg.style.display = \"block\";\r\n" +
            "            } else {\r\n" +
            "                noPoster.style.display = \"block\";\r\n" +
            "                posterImg.style.display = \"none\";\r\n" +
            "            }\r\n" +
            "        }\r\n" +
            "        function formatMovieDetails(movie) {\r\n" +
            "            let html = \"\";\r\n" +
            "            html += \"<div class='movie-info'>\";\r\n" +
            "            html += \"<h2>Movie Details</h2>\";\r\n" +
            "            for (let key in movie) {\r\n" +
            "                if (movie[key] !== \"N/A\" && key !== \"Poster\" && key !== \"Response\" && key !== \"imdbID\") {\r\n" +
            "                    if (key === \"Ratings\") {\r\n" +
            "                        html += \"<p><strong>Ratings:</strong></p>\";\r\n" +
            "                        html += \"<ul>\";\r\n" +
            "                        for (let i = 0; i < movie[key].length; i++) {\r\n" +
            "                            html += \"<li>\" + movie[key][i].Source + \": \" + movie[key][i].Value + \"</li>\";\r\n" +
            "                        }\r\n" +
            "                        html += \"</ul>\";\r\n" +
            "                    } else {\r\n" +
            "                        html += \"<p><strong>\" + key + \":</strong> \" + movie[key] + \"</p>\";\r\n" +
            "                    }\r\n" +
            "                }\r\n" +
            "            }\r\n" +
            "            html += \"</div>\";\r\n" +
            "            return { html: html };\r\n" +
            "        }\r\n" +
            "    </script>\r\n" +
            "</body>\r\n" +
            "</html>";
        return outputLine;
    }

}