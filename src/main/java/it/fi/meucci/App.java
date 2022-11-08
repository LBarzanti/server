package it.fi.meucci;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String args[]) throws Exception
    {
        /* creaione della lista di biglietti */
        Messaggio m1 = new Messaggio();
        Biglietto b1 = new Biglietto(1, "tribuna-1a");
        Biglietto b2 = new Biglietto(2, "tribuna-2a");
        Biglietto b3 = new Biglietto(3, "tribuna-3a");
        Biglietto b4 = new Biglietto(4, "palco-1a");
        Biglietto b5 = new Biglietto(5, "palco-2a");
        Biglietto b6 = new Biglietto(6, "palco-3a");
        m1.biglietti.add(b1);
        m1.biglietti.add(b2);
        m1.biglietti.add(b3);
        m1.biglietti.add(b4);
        m1.biglietti.add(b5);
        m1.biglietti.add(b6);

        /* creazione del server e del socket */
        ServerSocket ss = new ServerSocket(25565);
        for (int index = 1; index > 0; index++) 
        {
            Socket s = ss.accept();
            System.out.println("connessione effettuata");
            SrvThread t = new SrvThread(s, m1);
            t.start();
        }
        ss.close();
    }
}


