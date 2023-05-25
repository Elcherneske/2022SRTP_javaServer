package ClineClass;

import java.util.ArrayList;
import java.util.List;

public class ClientGenerator {
    private List<Client> clineList = new ArrayList<Client>();


    static private ClientGenerator clientGenerator;

    static public ClientGenerator getInstance()
    {
        if(clientGenerator == null)
        {
            synchronized (ClientGenerator.class){
                if(clientGenerator==null){
                    clientGenerator = new ClientGenerator();
                }
            }
        }
        return clientGenerator;
    }

    public ClientGenerator() {}


    public void addClient(Client client){
        this.clineList.add(client);
    }

    public void checkClients()
    {
        for(int i=0;i<this.clineList.size();i++){
            if(!this.clineList.get(i).isLink()){
                this.clineList.remove(i);
                i--;
            }
        }
    }



}
