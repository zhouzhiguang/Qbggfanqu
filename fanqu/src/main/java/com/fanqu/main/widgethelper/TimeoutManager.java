package com.fanqu.main.widgethelper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class TimeoutManager {
    private long mLastTime;
    private Timeout mTimeout;
    private OnTimeRunListener mListener;
    private int MSG_GO = 1;
    private Thread mThread;

    public TimeoutManager(int day, int hour, int minute, int second, OnTimeRunListener listener) {
        mListener = listener;
        mTimeout = new Timeout(day, hour, minute, second);
        mLastTime = System.currentTimeMillis();
        new Thread(new TimeRun()).start();
    }



    public void resetTime(int day, int hour, int minute, int second) {
        mTimeout.resetTime(day, hour, minute, second);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_GO && null != mListener) {
                Bundle bundle = msg.getData();
                mListener.onTimeRun(bundle.getInt("day"), bundle.getInt("hour"), bundle.getInt("minute"), bundle.getInt("second"));
            }
        }
    };


    private void timeRun() {
        long curTime = System.currentTimeMillis();
        if (curTime - mLastTime > 1000) {
            mTimeout.goOneSecond();
            mLastTime += 1000;
        }
    }

    private class TimeRun implements Runnable {

        @Override
        public void run() {
            while (!mTimeout.isFinish()) {
                try {
                    Thread.sleep(100);
                    timeRun();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class Timeout {
        private int day;
        private int hour;
        private int minute;
        private int second;

        public Timeout(int day, int hour, int minute, int second) {

            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        public void goOneSecond() {
            if (isFinish() || null == mListener) {
                return;
            }
            second--;

            if (second < 0) {
                second += 60;
                minute -= 1;
            }

            if (minute < 0) {
                minute += 60;
                hour -= 1;
            }
            if (hour < 0) {
                hour += 60;
                day -= 1;
            }
            Message message = new Message();
            message.what = MSG_GO;
            Bundle bundle = new Bundle();
            bundle.putInt("day", day);
            bundle.putInt("hour", hour);
            bundle.putInt("minute", minute);
            bundle.putInt("second", second);
            message.setData(bundle);
            mHandler.sendMessage(message);
//            Log.d("DEBUG", hour+" : "+minute+" : "+second);
        }

        public boolean isFinish() {
            return day == 0 && hour == 0 && minute == 0 && second == 0;
        }

        public void resetTime(int day, int hour, int minute, int second) {
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
    }

    public interface OnTimeRunListener {
        void onTimeRun(int day, int hour, int minute, int second);
    }

}
