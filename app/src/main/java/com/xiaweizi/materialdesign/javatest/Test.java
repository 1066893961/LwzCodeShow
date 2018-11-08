package com.xiaweizi.materialdesign.javatest;

/**
 * Created by lwz on 2018/4/9.
 */

public class Test {
    public static void main(String args[]){
        boolean flag = validateVersion("3.9", "3.7.2.8.8.8.9");
        System.out.println(flag);
    }


    public static boolean validateVersion(String curVer,String speVer){
        boolean flag = true;
        String[] curVers = curVer.split("\\.");
        String[] speVers = speVer.split("\\.");

        int[] iCur = new int[curVers.length];
        int[] iSper = new int[speVers.length];

        for(int i = 0;i < curVers.length;i++){
            iCur[i] = Integer.parseInt(curVers[i]);
        }
        for(int i = 0;i < speVers.length;i++){
            iSper[i] = Integer.parseInt(speVers[i]);
        }

        int iFlag = 0;
        if(curVers.length < speVers.length){
            iFlag = validata(iSper, iCur);
            if(iFlag > 0){
                flag = true;
            }else{
                flag = false;
            }
        }else{
            iFlag = validata(iCur, iSper);
            if(iFlag >= 0){
                flag = false;
            }else{
                flag = true;
            }
        }
        return flag;
    }
    /**
     *
     * @param bigOne
     * @param smallOne
     * @return 1 说明 bigOne > smallOne
     * -1 说明bigOne < smallOne
     * 0 说明bigOne == smallOne
     */
    public static int validata(int[] bigOne,int[] smallOne){
        int flag = 0;
        int temp = 0;
        for(int i = 0;i < bigOne.length;i++){
            if(i > smallOne.length-1){
                temp = 0;
            }else{
                temp = smallOne[i];
            }
            if(temp < bigOne[i]){
                flag = 1;
                return flag;
            }else if(temp > bigOne[i]){
                flag = -1;
                return flag;
            }else{
                if(i == bigOne.length-1){
                    flag = 0;
                }else{
                    continue;
                }
            }
        }
        return flag;
    }
}
