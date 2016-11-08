import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by ashleyjain on 06/11/16.
 */
public class receiver {

    public static void main(String args[]) throws IOException {

        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData;
        String send;
        int ACK = 0;

        while(true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String rec = new String(receivePacket.getData());
            System.out.println(rec);

            ACK = rec.substring(24).length();
            System.out.println(ACK+"");

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            send = ACK+"";
            sendData = send.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }

    }

}
