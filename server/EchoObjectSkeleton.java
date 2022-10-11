package server;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
import rmi.EchoInterface;

public class EchoObjectSkeleton implements EchoInterface {
    String myURL="localhost";
       
    // Sueldo asociado a las tarjetas
    double monto1 = 2500.99d;
    double monto2 = 1300.50;
    double monto3 = 10000.90;
    
    // Informacioncliente
    long tarjeta_cliente;
    int cvv_cliente;
    double pago_cliente;
    int validacion;
    	
    //Constructor de la clase EchoObjectSkeleton
    public EchoObjectSkeleton()
    {
        try {
	// obtengo el nombre del equipo donde estoy ejecutando y lo guardo en la propiedad MyURL
            myURL=InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) 
               {
                // si no pude conocer el nombre del equipo, mantengo el nombre localhost para MyURL
               myURL="localhost";
              }
    }
    // el Metodo Echo que es la implementacion de la interfaz EchoInterface
    public String echo(String pago, String tarjeta, String cvv) throws java.rmi.RemoteException 
    {
    	String tarjeta1 = "1111222233334444";
    	String tarjeta2 = "5555666677778888";
    	String tarjeta3 = "2222444466668888";
    	
    	int cvv1 = 123;
    	int cvv2 = 231;
    	int cvv3 = 312;
    	
    	double total1;
        double total2;
        double total3;
        
        pago_cliente = Double.parseDouble(pago);
        cvv_cliente = Integer.parseInt(cvv);
        
        System.out.println("En proceso de atencion a cliente");
        String estado;
        DecimalFormat formato=new DecimalFormat("#.##");
        if(tarjeta.equals(tarjeta1) && cvv_cliente == cvv1)
        {
        	total1=monto1 - pago_cliente;
        	
        	if(total1>=0)
        	{
        		monto1=total1;
        		//System.out.println(monto1);
        		estado="1";
        	}
        	else
        	{
        		//System.out.println(monto1);
        		estado="2";
        	}
        	System.out.println("Monto: '"+ pago + "' No.tarjeta: '" + tarjeta + "' CVV: '" + cvv + "' \nSaldo disponible: '" + formato.format(monto1) +"' ");
        }
        else if(tarjeta.equals(tarjeta2) && cvv_cliente==cvv2)
        {
        	total2 = monto2-pago_cliente;
        	
        	if(total2>=0)
        	{
        		monto2=total2;
        		estado="1";
        	}
        	else
        	{
        		estado="0";
        	}
        	System.out.println("Monto: '"+ pago + "' No.tarjeta: '" + tarjeta + "' CVV: '" + cvv + "' \nSaldo disponible: '" + formato.format(monto2) +"' ");
        }
        else if(tarjeta.equals(tarjeta3) && cvv_cliente==cvv3)
        {
        	total3 = monto3-pago_cliente;
        	
        	if(total3 >= 0)
        	{
        		monto3=total3;
        		estado="1";
        	}
        	else
        	{
        		estado="0";
        	}
        	System.out.println("Monto: '"+ pago + "' No.tarjeta: '" + tarjeta + "' CVV: '" + cvv + "' \nSaldo disponible: '" + formato.format(monto3) +"' ");
        }
        else
        {
        	estado="0";
        	System.out.println("Monto: '"+ pago + "' No.tarjeta: '" + tarjeta + "' CVV: '" + cvv + "' ");
        } 
        try {
            Thread.sleep(3000); // hilo actual
            //ret = ret + " (retrasada 3 segundos)";
        } catch (InterruptedException e) {}
        System.out.println("Servicio terminado.\n");
        //return "0";
        return estado;
    }
   }
