import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class UDPSvr1 {
    public static void main(String[] args) {
        int port;
// obtener el puerto del servidor de los parámetros de entrada
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Missing server info");
            return;
        }
// creamos un socket UDP para el servidor
// el socket se cerrará al salir del bloque try
        try (DatagramSocket socket = new DatagramSocket(port);) {
            System.out.println("Waiting on port "
                    + socket.getLocalPort() + "....");
// array de bytes para recoger los datos del datagrama
            byte[] buffer = new byte[1024];
// objeto datagrama para la recepción del paquete del cliente
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
// esperamos la recepción de un mensaje del cliente
            socket.receive(packet);
// tamaño del dato
            int size = packet.getLength();
// obtenemos el dato recibido
            String msg = new String(packet.getData());
            System.out.println("Bytes received: " + size);
            System.out.println("Info received: " + msg.trim());
            String msgTmp = msg.toLowerCase();
            int contador = 0;
            for (int i = 0; i < msg.length(); i++) {
                if(msgTmp.charAt(i) == 'a'){
                    contador++;
                }
            }
            System.out.println("Salen " + contador + " letras (a) en el msg");
            System.out.println("IP:Port orig: "
                    + packet.getAddress() + ":" + packet.getPort());

        } catch (SocketException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}