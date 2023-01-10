import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
public class UDPCli1 {
    public static void main(String[] args) {
        String svrIP;
        int svrPort;
// obtener el puerto del servidor de los parámetros de entrada
        try {
            svrIP = args[0];
            svrPort = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println("Missing server info");
            return;
        }
// creamos un objeto socket
// como es el cliente no determinamos el puerto
// y el sistema le dará un puerto libre
// el socket se cerrará al salir del bloque try
        try (DatagramSocket socket = new DatagramSocket();) {
// dirección y puertos de destino (servidor)
            InetAddress dest = InetAddress.getByName(svrIP);
// mensaje, será un array de bytes
            byte[] msg = "Hello server!!aaaaaaaaaaaaaaaaaaaaaa".getBytes();
// creamos el datagrama con el mensaje, la longitud
// , ip y puerto de destino
            DatagramPacket packet = new DatagramPacket(msg
                    , msg.length, dest, svrPort);
            System.out.println("Sending datagram to the server...");
// enviar el mensaje
            socket.send(packet);
            System.out.println("Closing connection...");
        } catch (UnknownHostException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SocketException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Terminated");
    }
}