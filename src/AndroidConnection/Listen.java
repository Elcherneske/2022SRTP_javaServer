package AndroidConnection;

import ClineClass.Client;
import DataStore.CELIFParameter;

import java.io.*;
import java.net.Socket;

public class Listen implements Runnable{
    private BufferedReader in;

    private PrintWriter out;

    private Socket socket;

    private Client client;

    public Listen(Client client)
    {
        this.client = client;
        try {
            this.socket = client.getSocket();
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")),true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }




    @Override
    public void run(){
        String input;
        try{
            while(true){
                if(socket.isConnected()){
                    if(!socket.isInputShutdown()){
                        if((input = in.readLine())!=null){
                            System.out.println(input);
                            String[] items = input.split("\\s+");
                            switch (items[0]){
                                case "Parameter":
                                    formParameter(items);
                                    break;
                                case "value":
                                    client.getDataBase().addData(Integer.valueOf(items[2]), Integer.valueOf(items[1]));
                                    break;
                                case "end":
                                    client.getDataBase().storeAllData();
                                    //client.getSocket().close();//手机端点击停止，关闭socket通道，解构这个client
                                    break;
                                default:

                            }
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    private void formParameter(String[] items)
    {
        switch (items[1]){
            case "CELIF":
                client.setKind(0);
                this.client.setCELIFParameter(new CELIFParameter(Integer.valueOf(items[2]),Integer.valueOf(items[3]),
                        Integer.valueOf(items[4]), Integer.valueOf(items[5]),Integer.valueOf(items[6]),
                        Integer.valueOf(items[7]),Integer.valueOf(items[8]),Integer.valueOf(items[9]),Integer.valueOf(items[10]),items[11],items[12]));
                break;
            case "LIF":
                client.setKind(1);
                break;
            default:
        }
    }



}
