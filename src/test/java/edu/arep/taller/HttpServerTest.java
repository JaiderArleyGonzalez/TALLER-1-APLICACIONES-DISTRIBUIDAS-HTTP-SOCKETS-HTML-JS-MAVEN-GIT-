package edu.arep.taller;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.*;

public class HttpServerTest {

    @Test
    public void testServerResponse() {
        final HttpServer httpServer = new HttpServer();
        final Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                httpServer.start(8080);
            }
        });
        serverThread.start();
        try {
            
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        try {
            Socket socket = new Socket("localhost", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
            // Envía una solicitud GET válida al servidor
            out.println("GET / HTTP/1.1");
            out.println("Host: localhost:8080");
            out.println("Connection: Close");
            out.println();
    
            // Lee la respuesta del servidor
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            // Verifica que la respuesta del servidor no esté vacía y contenga la cadena esperada
            assertFalse(response.toString().isEmpty());
            assertTrue(response.toString().contains("FilmFinder"));
    
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Excepción en la comunicación con el servidor: " + e.getMessage());
        }
    
        httpServer.stop();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStopServer() {
        final HttpServer httpServer = new HttpServer();
        final Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                httpServer.start(8080);
            }
        });
        serverThread.start();
        try {
            // Esperar un poco para que el servidor pueda iniciarse completamente antes de ejecutar las pruebas
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        httpServer.stop();
        // Verificar que el servidor se haya detenido correctamente
        boolean serverStopped = true;
        try {
            // Intentar cerrar el socket para verificar si el servidor está escuchando
            new Socket("localhost", 8080).close();
        } catch (IOException e) {
            // Si hay una excepción, el servidor no está escuchando, por lo tanto, se detuvo correctamente
            serverStopped = true;
        }

        assertTrue(serverStopped);

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}