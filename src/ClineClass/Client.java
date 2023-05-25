package ClineClass;

import DataStore.CELIFParameter;
import DataStore.DataContainer;

import java.net.Socket;

public class Client {
    //通讯借口
    private Socket socket;
    //参数对象
    private CELIFParameter CELIF_parameter = null;
    private DataContainer dataBase;
    private int kind;


    //构造函数
    public Client(Socket socket)
    {
        this.socket = socket;
        this.dataBase = new DataContainer(this);
    }

    //设置参数
    public void setCELIFParameter(CELIFParameter parameter) {
        this.CELIF_parameter = parameter;
    }
    public Socket getSocket() {
        return socket;
    }
    public CELIFParameter getParameter() {
        return this.CELIF_parameter;
    }
    public DataContainer getDataBase(){return this.dataBase;}

    public void setKind(int kind){
        this.kind = kind;
    }


    public boolean isLink()
    {
        if(socket == null)return false;
        else {
            try{
                if(!socket.isConnected()||!socket.isClosed())return false;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }







}
