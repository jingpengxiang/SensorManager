package com.example.jing.sensormanager;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private SensorManager sm;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private Button btnClick,btnBall;
    private Sensor sensor_light;
    private Sensor sensor_gravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor_gravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        setContentView(R.layout.activity_main);
        initView();
        getSensorList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sensor_light, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_gravity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }

    private void initView() {
        btnClick = (Button) findViewById(R.id.btn_click);
        btnBall = (Button) findViewById(R.id.btn_ball);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);
        tv11 = (TextView) findViewById(R.id.tv11);
        tv12 = (TextView) findViewById(R.id.tv12);
        btnClick.setOnClickListener(this);
        btnBall.setOnClickListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            int type = event.sensor.getType();
            float[] values = event.values;
            String value = "x:" + values[0] + ",Y:" + values[1] + ",Z:" + values[2];
            switch (type) {
                case Sensor.TYPE_LIGHT:
                    tv1.setText("光线：" + value);// values[0]表示光线强度
                    break;
                case Sensor.TYPE_GRAVITY:
                    tv2.setText("重力：" + value);
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    tv3.setText("加速度：" + value);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    tv4.setText("磁场：" + value);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    tv5.setText("定位：" + value);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    tv6.setText("陀螺仪：" + value);
                    break;
                case Sensor.TYPE_PRESSURE:
                    tv7.setText("压力：" + value);
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    tv8.setText("温度：" + value);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    tv9.setText("距离：" + value);
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    tv10.setText("线性加速度：" + value);
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    tv11.setText("旋转矢量：" + value);
                    break;
                default:
                    tv12.setText("NORMAL：" + value);
                    break;

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * 获取所有支持的传感器的列表
     */
    private void getSensorList() {
        // 获取传感器管理器
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // 获取全部传感器列表
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // 打印每个传感器信息
        StringBuilder strLog = new StringBuilder();
        int iIndex = 1;
        for (Sensor item : sensors) {
            strLog.append(iIndex + ".");
            strLog.append("	Sensor Type - " + item.getType() + "\r\n");
            strLog.append("	Sensor Name - " + item.getName() + "\r\n");
            strLog.append("	Sensor Version - " + item.getVersion() + "\r\n");
            strLog.append("	Sensor Vendor - " + item.getVendor() + "\r\n");
            strLog.append("	Maximum Range - " + item.getMaximumRange() + "\r\n");
            strLog.append("	Minimum Delay - " + item.getMinDelay() + "\r\n");
            strLog.append("	Power - " + item.getPower() + "\r\n");
            strLog.append("	Resolution - " + item.getResolution() + "\r\n");
            strLog.append("\r\n");
            iIndex++;
        }
        tv12.setText(strLog.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                startActivity(new Intent(this,CompassTestActivity.class));
                break;
            case R.id.btn_ball:
                startActivity(new Intent(this, BallActivity.class));
                break;
        }
    }
}
