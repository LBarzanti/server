package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class SrvThread extends Thread
{
    Socket s;
    Messaggio m1;
    public SrvThread(Socket s, Messaggio m1)
    {
        this.s = s;
        this.m1=m1;
    }

    public void run()
    {
        try
        {
            BufferedReader byClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream toClient = new DataOutputStream(s.getOutputStream());
            String x = byClient.readLine();
            toClient.writeBytes(serializza(m1));
            Messaggio richiesti = new Messaggio();
            richiesti = deserializza(byClient.readLine());
            Messaggio approvati = new Messaggio();
            for (int i = 0; i < richiesti.biglietti.size(); i++) 
            {
                for (int index = 0; index < m1.biglietti.size(); index++) 
                {
                    if (richiesti.biglietti.get(i).ID==m1.biglietti.get(index).ID) 
                    {
                        approvati.biglietti.add(m1.biglietti.get(index));
                        m1.biglietti.remove(index);
                    }
                }
            }
            toClient.writeBytes(serializza(approvati));
            if (byClient.readLine().equals("chiudo")) 
            {
                s.close();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //System.exit(1);
        }
    }

    public String serializza(Messaggio m1) throws Exception
    {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(m1);
    }

    public Messaggio deserializza(String mess) throws Exception
    {
        XmlMapper xmlmapper2 = new XmlMapper();
        Messaggio m2 = xmlmapper2.readValue(mess, Messaggio.class);
        return m2;
    }
}