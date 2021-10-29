package com.iliyan.main.States.MenuStates;

public class moneyAndScoreHandler {
    public static String convertUnlockedBooleanToString(boolean[] unlockedColors){
        StringBuilder result = new StringBuilder();
            for(int i = 0;i<unlockedColors.length;i++){
                if(unlockedColors[i])
                    result.append("1");
                else
                    result.append("0");
            }
        return result.toString();
    }
    public static boolean[] convertUnlockedStringToBoolean(String string){
        boolean[] result = new boolean[6];
            for(int i = 0;i<result.length;i++){
                if(string.charAt(i)=='0'){
                    result[i] = false;
                }else result[i] = true;
            }
        return result;
    }
}