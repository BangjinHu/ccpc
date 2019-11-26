package com.ccpc.edu.xidian.cn.ccpc.example.thread.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class Run1 {

//    public static class MyTask extends TimerTask {
//
//        private SimpleDateFormat sdf;
//        private String dateString;
//
//        public MyTask(SimpleDateFormat sdf, String dateString) {
//            this.sdf = sdf;
//            this.dateString = dateString;
//        }
//
//        @Override
//        public void run() {
//            try{
//                Date dateRef = sdf.parse(dateString);
//                String newDateString = sdf.format(dateRef).toString();
//                if (!newDateString.equals(dateString)){
//                    System.out.println("thread name = " + this.get);
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public static void main(String[] args) {
//        try {
//            MyTask task = new MyTask();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String dateStr = "2019-10-18 16:06:16";
//            Date dateRef = new sdf.parse(dateStr);
//        }catch (ParseException e){
//
//        }
//    }
}
