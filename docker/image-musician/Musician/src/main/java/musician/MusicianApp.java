package musician;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

public class MusicianApp {
    private static final String MULTICAST_ADDRESS = "239.255.22.5";
    private static final int PORT = 9904;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MusicianApp <instrument>");
            System.exit(1);
        }

        Instrument instrument = Instrument.valueOf(args[0]);
        Musician musician = new Musician(instrument);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sendDatagram(musician);
            }
        }, 0, 1000);
    }

    private static void sendDatagram(Musician musician) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            String jsonData = MusicianSerializer.serialize(musician);
            byte[] data = jsonData.getBytes();

            DatagramPacket packet = new DatagramPacket(data, data.length, group, PORT);
            socket.send(packet);

            System.out.println("Sent UDP datagram: " + jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
