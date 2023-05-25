package AndroidConnection;

import ClineClass.Client;
import ClineClass.ClientGenerator;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AndroidService {
    private ExecutorService executorservice;

    private ServerSocket server;

    private ClientGenerator clientGenerator;

    private final int port = 10023;

    static private AndroidService androidService;



    public static void main(String[] args){
        AndroidService.getInstance();
    }


    static public AndroidService getInstance()
    {
        if(androidService == null) {
            synchronized (AndroidService.class)
            {
                if(androidService == null){
                    androidService = new AndroidService();
                }
            }
        }
        return androidService;
    }


    public AndroidService()
    {
        this.executorservice = Executors.newCachedThreadPool();
        this.clientGenerator = ClientGenerator.getInstance();
        formSocketServer();
        formSocketChecker();
    }









    //private function

    private void formSocketServer()
    {
        try {
            this.server = new ServerSocket(port);
            System.out.println("服务器运行中~");
            //form a thread to wait the client
            new Thread(){
                @Override
                public void run()
                {
                    Socket client = null;
                    try{
                        while(true){
                            client = server.accept();   //get the cline
                            Client cline_object = new Client(client);
                            clientGenerator.addClient(cline_object);
                            System.out.println("有客户端连入");

                            //Service is to output the data
                            //Listen is to get the parameter and command

                            executorservice.execute(new Listen(cline_object));

                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void formSocketChecker()
    {
        new Thread(){
            @Override
            public void run()
            {
                while(true){
                    clientGenerator.checkClients();
                    try {
                        sleep(200);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }



}
