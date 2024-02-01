package auditor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Auditor class represents an application that listens for UDP datagrams from musicians and provides information
 * about active musicians over a TCP-based protocol.
 *
 * @author Kilian Demont
 * @author Julien Holzer
 */
public class Auditor {
    private static final String MULTICAST_ADDRESS = "239.255.22.5";
    private static final int PORT = 9904;
    private static final int TCP_PORT = 2205;

    private List<ActiveMusician> activeMusicians;

    /**
     * Constructs an Auditor object with an empty list of active musicians.
     */
    public Auditor() {
        this.activeMusicians = new ArrayList<>();
    }

    /**
     * Starts the Auditor application by initiating UDP and TCP listeners.
     */
    public void start() {
        startUdpListener();
        startTcpServer();
    }

    /**
     * Initiates the UDP listener to receive datagrams from musicians.
     * The received data is processed to update the list of active musicians.
     */
    private void startUdpListener() {
        new Thread(() -> {
            try (MulticastSocket socket = new MulticastSocket(PORT)) {
                InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
                socket.joinGroup(group);

                while (true) {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);

                    String jsonData = new String(packet.getData(), 0, packet.getLength());
                    processUdpMessage(jsonData);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Processes the received UDP message containing musician data.
     * The musician information is extracted and used to update the list of active musicians.
     *
     * @param jsonData The JSON-formatted data received from a musician.
     */
    private void processUdpMessage(String jsonData) {
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(jsonData, JsonObject.class);

        UUID uuid = UUID.fromString(json.get("uuid").getAsString());
        String sound = json.get("sound").getAsString();

        // Determine the instrument based on the sound
        Instrument instrument = Instrument.fromSound(sound);

        ActiveMusician activeMusician = new ActiveMusician();
        activeMusician.setUuid(uuid);
        activeMusician.setInstrument(instrument);
        activeMusician.setLastActivity(System.currentTimeMillis());

        updateActiveMusicians(activeMusician);
    }

    /**
     * Updates the list of active musicians by removing inactive musicians and adding a new musician.
     *
     * @param newActiveMusician The musician to be added to the list of active musicians.
     */
    private void updateActiveMusicians(ActiveMusician newActiveMusician) {
        activeMusicians.removeIf(musician -> System.currentTimeMillis() - musician.getLastActivity() > 5000);
        activeMusicians.add(newActiveMusician);
    }

    /**
     * Initiates the TCP server to handle client requests for information about active musicians.
     */
    private void startTcpServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TCP_PORT)) {
                System.out.println("TCP Server started. Listening on port " + TCP_PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    handleTcpRequest(clientSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Handles a TCP client request by sending information about active musicians to the client.
     *
     * @param clientSocket The socket connected to the TCP client.
     */
    private void handleTcpRequest(Socket clientSocket) {
        try {
            Gson gson = new Gson();
            String jsonData = gson.toJson(activeMusicians);

            clientSocket.getOutputStream().write(jsonData.getBytes());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main entry point of the Auditor application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Auditor auditor = new Auditor();
        auditor.start();
    }
}
