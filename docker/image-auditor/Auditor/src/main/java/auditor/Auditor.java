package auditor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Auditor {
    private static final String MULTICAST_ADDRESS = "239.255.22.5";
    private static final int PORT = 9904;
    private static final int TCP_PORT = 2205;

    private List<ActiveMusician> activeMusicians;

    public Auditor() {
        this.activeMusicians = new ArrayList<>();
    }

    public void start() {
        startUdpListener();
        startTcpServer();
    }

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

        System.out.println(activeMusician);
        //System.out.println(activeMusicians);

        updateActiveMusicians(activeMusician);
    }

    private void updateActiveMusicians(ActiveMusician newActiveMusician) {
        activeMusicians.removeIf(musician -> System.currentTimeMillis() - musician.getLastActivity() > 5000);
        activeMusicians.add(newActiveMusician);
    }

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

    public static void main(String[] args) {
        Auditor auditor = new Auditor();
        auditor.start();
    }
}
