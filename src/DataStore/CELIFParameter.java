package DataStore;

import java.util.ArrayList;
import java.util.List;

public class CELIFParameter {
    //电压输出1
    private int voltage_1;//0号
    //电压输出2
    private int voltage_2;//1号
    //激光器分频数
    private int laser_divisor;//2号
    //激光器周期
    private int laser_period;//3号
    //激光器占空比
    private int laser_duty;//4号
    //放大倍数
    private int magnify_1;//5号
    private int magnify_2;//6号
    //采样频率
    private int sampling_frequency_PSR;//7号
    private int sampling_frequency_ARR;//8号
    //文件名字
    private String name;
    //仪器编号
    private String id;

    public CELIFParameter(int voltage_1,int voltage_2,int laser_divisor,int laser_duty,int laser_period,int magnify_1,int magnify_2,int sampling_frequency_PSR,int sampling_frequency_ARR,String name,String id)
    {
        this.voltage_1 = voltage_1;
        this.voltage_2 = voltage_2;
        this.laser_divisor = laser_divisor;
        this.laser_duty = laser_duty;
        this.laser_period = laser_period;
        this.magnify_1 = magnify_1;
        this.magnify_2 = magnify_2;
        this.sampling_frequency_PSR = sampling_frequency_PSR;
        this.sampling_frequency_ARR = sampling_frequency_ARR;

        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getName()
    {
        return this.name;
    }
    public String getID(){return this.id;}





}
