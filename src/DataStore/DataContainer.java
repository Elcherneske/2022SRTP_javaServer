package DataStore;

import ClineClass.Client;
import DBlibs.DBUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class DataContainer {
    private ArrayList<Data> dataBase;
    private Client client;
    private final String path = "..\\DataBase\\";
    private BufferedWriter out;//store the data


    public DataContainer(){
        this.dataBase = new ArrayList<Data>();
    }
    public DataContainer(Client client){
        this.dataBase = new ArrayList<Data>();
        this.client = client;
    }



    /*two different way to add data*/
    public void addData(int data , int time)
    {
        synchronized (this){
            Data obj_data = new Data(data,time);
            this.dataBase.add(obj_data);
        }
    }
    public void addData(Data data)
    {
        synchronized (this){
            this.dataBase.add(data);
        }
    }

    public void storeAllData()
    {

        try {
            File file = new File(path + client.getParameter().getName() + ".csv");
            out = new BufferedWriter(new FileWriter(file));
        }
        catch (Exception e){
            e.printStackTrace();
        }


        synchronized (this.dataBase)
        {
            DBUtil db = new DBUtil();

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss");
            String data_string = formatter.format(date);
            String device_ID = client.getParameter().getID();
            String table_name = device_ID + data_string;

            //按照名字创建table
            String sql = "create table " + table_name + "("+ "value1 int, value2 int" + ");";
            db.db_execute(sql);

            //store 分两块，一个是形成txt文件存储，一个是导入到数据库中
            try {
                //把创建的table加入tablenametable中
                {
                    Object[] obj = {table_name};
                    db.update("insert into tablenametable value(?)",obj);
                }

                for(int i = 0; i < dataBase.size(); i++)
                {
                    out.write(String.valueOf(dataBase.get(i).getTime()) + "," + String.valueOf(dataBase.get(i).getData()) + "\n");
                    Object[] obj = {dataBase.get(i).getTime(),dataBase.get(i).getData()};
                    db.update("insert into " + table_name + " value(?,?)",obj);
                }
                out.flush();
            }
            catch (Exception e){e.printStackTrace();}


        }
    }

}
