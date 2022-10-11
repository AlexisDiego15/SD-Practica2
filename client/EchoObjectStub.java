
 // conectarse y construir socket
        //escribir y leer en socket
//desconectarse 
package client;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.EchoInterface;

public class EchoObjectStub implements EchoInterface{
    private Socket echoSocket = null;
    private PrintWriter os = null;
    private BufferedReader is = null;
    private String host = "localhost";
    private int port=4000;
    private String output = "Error";
    private boolean connected = false;
    public void setHostAndPort(String host, int port) {
        this.host= host; this.port =port;
    }
    public String echo(String pago, String tarjeta, String cvv)throws java.rmi.RemoteException 
    {	
    	/*
    	// toma la fecha y hora 
        // escribe la fecha y la hora, nombre de compuadora y lo regresa
        Date h = new Date();
        // obtengo la fecha y hora actual
        String fecha = DateFormat.getTimeInstance(3,Locale.FRANCE).format(h);
        */
        
        int estado;
        
        try {
            connect();
            } catch (IOException ex) 
                {
                Logger.getLogger(EchoObjectStub.class.getName()).log(Level.SEVERE, null, ex);
                }
        if (echoSocket != null && os != null && is != null) 
            {
            try {
            		// Escribir en el socket
                	os.println(pago);
                	os.println(tarjeta);
                	os.println(cvv);
                
                	os.flush();// limpia el canal de comunicacion del socket
                	output= is.readLine(); // lee del socket
                	estado = Integer.parseInt(output);
                	//System.out.println(output);
                	
                	if(estado ==1)
                	{
                		System.out.println("Compra exitosa! Su pedido acaba de ser enviado");
                	}
                	else if(estado==0)
                	{
                		System.out.println("Transaccion no exitosa. Posibles razones: ");
                		System.out.println("\t[1] Fondos insuficientes \n\t[2] Numero de tarjeta y/o CVV incorrectos");
                	}
                	/*
                	else
                	{
                		System.out.println("El pago fue declinado, numero de tarjeta y cvv no coinciden.\nIntente con otro metodo de pago.");
                	}
                	*/
                } catch (IOException e) 
                {
                System.err.println("I/O Fallo al leer /escribir socket");
                throw new java.rmi.RemoteException("I/O failed in reading/writing socket");
                }
           }
           try {
               disconnect();
               } catch (IOException ex) 
                  {
                  Logger.getLogger(EchoObjectStub.class.getName()).log(Level.SEVERE, null, ex);
                  }
    return output;
    }



    private synchronized void connect() throws
    java.rmi.RemoteException, IOException {
    //EJERCICIO: Implemente el método connect, que aqui ya esta implementado
        echoSocket = new Socket(host, port);
        os=new PrintWriter(echoSocket.getOutputStream());
        is=new BufferedReader( new InputStreamReader(echoSocket.getInputStream()));
    }
    private synchronized void disconnect() throws IOException{
    //EJERCICIO: Implemente el método disconnect
        os.close();
        is.close();
        echoSocket.close();
    }
}
