package DataProcess;

import DataStore.Data;

import java.util.ArrayList;

public class PeakProcess {
    private ArrayList<ArrayList<Data>> peakData;
    private final int sharp_standard = 25;
    private final int plane_standard = 5;
    private final int increase_standard = 5;
    private final int decrease_standard = 5;
    private final int fetch_number = 20;


    public PeakProcess(ArrayList<Data> allData)
    {
        ArrayList<Data> singlePeak = new ArrayList<Data>();
        boolean record = false;
        if(allData==null||allData.size()<=fetch_number){
            this.peakData = null;
            return;
        }

        for(int i=0;i<allData.size()-fetch_number;i++){
            switch (checkPeak(allData,i)){
                case 0:
                case 1:
            }

        }





    }













    //private function

    int checkPeak(ArrayList<Data> allData, int index)
    {
        int increase = 0,decrease = 0,plane = 0 ;
        int change;
        for(int i=0;i<fetch_number-1;i++){
            change = allData.get(index+i).getData()-allData.get(index+i+1).getData();
            if(change > increase_standard)increase++;
            else if(change < decrease_standard) decrease++;
            else plane++;
        }

        return 0;
    }
}
