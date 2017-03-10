package com.example.yangbang.miowner.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.anyan.client.model.ClientModel;
import com.anyan.client.sdk.AYClientSDKCallBack;
import com.anyan.client.sdk.JDeviceBasic;
import com.anyan.client.sdk.JHistory;
import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.anyan.PrintFormat;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.BaseActivity;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 描述
 *
 * @FileName: com.example.yangbang.miowner.activity.DevicePlayActivity.java
 * @author: Yangbang
 * @date: 2015-12-22 15:58
 */
public class DevicePlayActivity extends BaseActivity implements AYClientSDKCallBack, /*RecordRenderer.OnAudioRecordListener, */SurfaceHolder.Callback {
    public static String TAG = "__DevicePlay";
    private Context mContext;

    //    private TextView mTxtInfo, mTxtRate;
    private TextView mTxtRate;
    //    private ToggleButton mTbtnPlay, mTbtnVoice, mTbtnZoom;
    private ToggleButton mTbtnPlay, mTbtnVoice;
//    private Button mBtnUp, mBtnDown, mBtnLeft, mBtnRight;
//    private Button mBtnLive, mBtnStop, mBtnQuit, mBtnSnap;
//    private Button mBtnDeviceDetail, mBtnGrantUsers, mBtnDeviceOnline, mBtnCleanScreen;

    private Spinner mSpnResolution;
    private SeekBar mSkbProcess;
    private static final int MAXProcess = 24 * 60 * 60;
    private ProgressBar mProLoading;

    private RelativeLayout rl_device_top;
    private RelativeLayout rl_device_bottom;
    private RelativeLayout rl_device_orientation;
    private ImageView imgv_device_refresh;
    private ImageView imgv_device_back;
    private TextView tv_device_name;
    private ImageView imgv_device_screen_shot;
    private ImageView imgv_device_up;
    private ImageView imgv_device_down;
    private ImageView imgv_device_left;
    private ImageView imgv_device_right;

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    boolean bfirtIn = true;

    private int mRatePosition = 0;
    private boolean mUprate = true;
    private String mNetworkBits = " 0.00KB/S";
    private boolean mIsChangeLandscape;

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initData() {
        this.setIsShowTitleBar(false);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.device;
    }

    private void toast(String info) {
        Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
    }

    private Handler mRateUpdateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x01:
                    mTxtRate.setText(mNetworkBits);
                    mUprate = true;
                    break;
                case 0x02:
                    mProLoading.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Log.i(TAG, "onCreate");

        mContext = this;
        initView();

        initDeviceData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume ActivePlayer begin.");
        //ClientModel.getClientModel().ActivePlayer(mSurfaceHolder.getSurface());
        Log.w(TAG, "onResume ActivePlayer end.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "onPause DisActivePlayer begin.");
        //ClientModel.getClientModel().DisActivePlayer();
        Log.w(TAG, "onPause DisActivePlayer end.");
        mTbtnPlay.setChecked(false);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.e(TAG, "orientation=" + getRequestedOrientation());

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        } else {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (ClientModel.getClientModel().Quit()) {
                mRateUpdateHandler.removeMessages(0x01);
            } else {
            }

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {

        mTxtRate = (TextView) findViewById(R.id.txt_rate);
        mTxtRate.setText(mNetworkBits);

        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        ClientModel.getClientModel().SetSurface(mSurfaceHolder.getSurface());
        mSurfaceView.setOnClickListener(mClickListener);

        mProLoading = (ProgressBar) findViewById(R.id.pro_loading);

        mSkbProcess = (SeekBar) findViewById(R.id.skb_process);
        mSkbProcess.setMax(MAXProcess);

        rl_device_top = (RelativeLayout) findViewById(R.id.rl_device_top);
        rl_device_bottom = (RelativeLayout) findViewById(R.id.rl_device_bottom);
        rl_device_orientation = (RelativeLayout) findViewById(R.id.rl_device_orientation);
        imgv_device_refresh = (ImageView) findViewById(R.id.imgv_device_refresh);
        imgv_device_back = (ImageView) findViewById(R.id.imgv_device_back);
        tv_device_name = (TextView) findViewById(R.id.tv_device_name);
        imgv_device_screen_shot = (ImageView) findViewById(R.id.imgv_device_screen_shot);
        imgv_device_up = (ImageView) findViewById(R.id.imgv_device_up);
        imgv_device_down = (ImageView) findViewById(R.id.imgv_device_down);
        imgv_device_left = (ImageView) findViewById(R.id.imgv_device_left);
        imgv_device_right = (ImageView) findViewById(R.id.imgv_device_right);
        imgv_device_screen_shot.setOnClickListener(mClickListener);
        imgv_device_up.setOnClickListener(mClickListener);
        imgv_device_down.setOnClickListener(mClickListener);
        imgv_device_left.setOnClickListener(mClickListener);
        imgv_device_right.setOnClickListener(mClickListener);
        imgv_device_refresh.setOnClickListener(mClickListener);
        imgv_device_back.setOnClickListener(mClickListener);
        mTbtnPlay = (ToggleButton) findViewById(R.id.tbtn_control_play);
        mTbtnPlay.setOnClickListener(mClickListener);
        mTbtnVoice = (ToggleButton) findViewById(R.id.tbtn_control_voice);
        mTbtnVoice.setOnClickListener(mClickListener);
//        mTbtnZoom = (ToggleButton) findViewById(R.id.tbtn_control_zoom);
//        mTbtnZoom.setOnClickListener(mClickListener);


        mSpnResolution = (Spinner) findViewById(R.id.tbtn_control_resolution);
        mSpnResolution.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!ClientModel.getClientModel().CheckDeviceOnline()) {
                    mRateUpdateHandler.sendEmptyMessageDelayed(0x02, 0);
                    return;
                }
                TextView tv = (TextView) view;
                if (tv != null) {
                    mRatePosition = position;
                    tv.setTextColor(Color.WHITE);
                    tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
                    if (ClientModel.getClientModel().ChanageRate(position, mSurfaceHolder.getSurface())) {
                        mTbtnPlay.setChecked(true);

                        Calendar now = Calendar.getInstance();
                        mSkbProcess.setProgress(now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));

                        return;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mSkbProcess.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //ClientModel.getClientModel().SeekAndPlay(mSkbProcess.getProgress(), ClientModel.SEEK_DRAG);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mProLoading.setVisibility(View.VISIBLE);
                int cur = mSkbProcess.getProgress();
                int h = cur / 3600;
                int m = cur % 3600 / 60;
                int s = cur % 60;
                Calendar seektime = Calendar.getInstance();
                seektime.set(seektime.get(Calendar.YEAR), seektime.get(Calendar.MONTH), seektime.get(Calendar.DAY_OF_MONTH), h, m, s);
                ClientModel.getClientModel().SetSurface(mSurfaceHolder.getSurface());
                int iSeektime = (int) (seektime.getTimeInMillis() / 1000);
                Log.i(TAG, "SeekAndPlay:" + iSeektime);
                ClientModel.getClientModel().SeekAndPlay(iSeektime);
            }
        });
    }

    private void initDeviceData() {
        ClientModel.getClientModel().SetSDKCallback(this);
        //mRecordRenderer = new RecordRenderer(this);
        if (ClientModel.getClientModel().mCurDevice.getDeviceOwner() == JDeviceBasic.DeviceOwner.Device_Share) {
            mSkbProcess.setVisibility(View.GONE);
        }
        tv_device_name.setText(ClientModel.getClientModel().mCurDevice.getDeviceName());
        mSpnResolution.setPrompt(ClientModel.getClientModel().mCurDevice.getRates()[0].getRateName());
        ArrayAdapter<String> _Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ClientModel.getClientModel().GetRateList());
        mSpnResolution.setAdapter(_Adapter);

        if (ClientModel.getClientModel().QueryDeviceDetailInfo()) {
            if (ClientModel.getClientModel().mCurDevice.getChannelNum() > 1 && ClientModel.getClientModel().mCurDevice.getDeviceTypeId() != JDeviceBasic.DeviceTypeId.Device_IPC) {
                List<String> channels = new ArrayList<>();
                for (int i = 0; i < ClientModel.getClientModel().mCurDevice.getChannelNum(); i++) {
                    channels.add("channel " + (i + 1));
                }
                String channlestate = ClientModel.getClientModel().mCurDevice.getChannelMask();
                if (channlestate.length() <= 1) {
                    channlestate = "";
                    for (int i = 0; i < ClientModel.getClientModel().mCurDevice.getChannelNum(); i++) {
                        channlestate += "0";
                    }
                }
            } else {
            }

//            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            long timesdiff = 0;
//            try {
//                long starttime = dateformat.parse(ClientModel.getClientModel().getChannelFullInfo().get(0).getCloudStartTime()).getTime();
//                long endtime = dateformat.parse(ClientModel.getClientModel().getChannelFullInfo().get(0).getCloudEndTime()).getTime();
//                timesdiff = starttime - endtime;
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        play();
    }

//    private void ChangeLandscape() {
//        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
//    }

    private void play() {
        ClientModel.getClientModel().Quit();
        if (!ClientModel.getClientModel().CheckDeviceOnline()) {
            mRateUpdateHandler.sendEmptyMessageDelayed(0x02, 0);
            return;
        }
        if (ClientModel.getClientModel().LiveAndPlay()) {
            Calendar now = Calendar.getInstance();
            mSkbProcess.setProgress(now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));

            mTbtnPlay.setChecked(true);
        } else {
        }
    }

    private int mPosition = 1;
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // control line 1 -----------------------------------------
                case R.id.tbtn_control_play:
                    if (!ClientModel.getClientModel().CheckDeviceOnline()) {
                        mRateUpdateHandler.sendEmptyMessageDelayed(0x02, 0);
                        return;
                    }
                    if (mTbtnPlay.isChecked()) {
                        if (ClientModel.getClientModel().LiveAndPlay()) {
                            return;
                        }
                    } else {
                        if (ClientModel.getClientModel().StopPlay()) {
                            return;
                        }
                    }
                    break;
                case R.id.tbtn_control_voice:
                    if (mTbtnVoice.isChecked()) {
                        if (ClientModel.getClientModel().OpenAudio()) {
                            return;
                        }
                    } else {
                        if (ClientModel.getClientModel().CloseAudio()) {
                            return;
                        }
                    }
                    break;
//                case R.id.tbtn_control_zoom:
//                    ChangeLandscape();
//                    break;
                // button line 2 -----------------------------------------
                case R.id.imgv_device_refresh:
                    play();
                    break;
                case R.id.imgv_device_back:
                    ClientModel.getClientModel().Quit();
                    finishActivity();
                    break;

                case R.id.surfaceView:
                    if (rl_device_top.getVisibility() == View.VISIBLE && rl_device_bottom.getVisibility() == View.VISIBLE) {
                        rl_device_top.setVisibility(View.GONE);
                        rl_device_bottom.setVisibility(View.GONE);
                        rl_device_orientation.setVisibility(View.GONE);
                        imgv_device_screen_shot.setVisibility(View.GONE);
                    } else if (rl_device_top.getVisibility() == View.GONE && rl_device_bottom.getVisibility() == View.GONE) {
                        rl_device_top.setVisibility(View.VISIBLE);
                        rl_device_bottom.setVisibility(View.VISIBLE);
                        rl_device_orientation.setVisibility(View.VISIBLE);
                        imgv_device_screen_shot.setVisibility(View.VISIBLE);
                    }
                    break;

                case R.id.imgv_device_up:
                    if (ClientModel.getClientModel().MoveUp(5)) {
                        L.i("YANGBANG", "已向上旋转5度");
                        ClientModel.getClientModel().StopAction();
                    } else {
                        ToastUtil.show(DevicePlayActivity.this, "改摄像头暂不支持旋转操作");
                    }
                    break;
                case R.id.imgv_device_down:
                    if (ClientModel.getClientModel().MoveDown(3)) {
                        L.i("YANGBANG", "已向下旋转5度");
                        ClientModel.getClientModel().StopAction();
                    } else {
                        ToastUtil.show(DevicePlayActivity.this, "改摄像头暂不支持旋转操作");
                    }
                    break;
                case R.id.imgv_device_left:
                    if (ClientModel.getClientModel().MoveLeft(5)) {
                        L.i("YANGBANG", "已向左旋转5度");
                        ClientModel.getClientModel().StopAction();
                    } else {
                        ToastUtil.show(DevicePlayActivity.this, "改摄像头暂不支持旋转操作");
                    }
                    break;
                case R.id.imgv_device_right:
                    if (ClientModel.getClientModel().MoveRight(5)) {
                        L.i("YANGBANG", "已向右旋转5度");
                        ClientModel.getClientModel().StopAction();
                    } else {
                        ToastUtil.show(DevicePlayActivity.this, "改摄像头暂不支持旋转操作");
                    }
                    break;
                case R.id.imgv_device_screen_shot://截屏
                    File tempImgFile = new File(MyApplication.fileCache.getSaveFilePath(), "temp_pic_"
                            + SystemClock.currentThreadTimeMillis() + ".jpg");
                    if (ClientModel.getClientModel().GetScreenShot(tempImgFile.getAbsolutePath())) {
                        ToastUtil.show(DevicePlayActivity.this, "截屏保存在" + tempImgFile.getAbsolutePath());
                    } else {
                        ToastUtil.show(DevicePlayActivity.this, "暂时不支持截屏");
                    }
                    break;

                default:
                    break;

            }
        }
    };

    @Override
    public void OnStatusMsg(int status_code, String msg) {
        //UpdateLog.updateI(TAG, "Error:401");
        Log.i(TAG, "Error:401 001");
    }

    @Override
    public void OnPlaystateChange(String device_id, int idx, int rate, int state, String msg) {
        switch (state) {
            case MessageNum.AY_NET_STAT:
                if (mUprate) {
                    mUprate = false;
                    //mNetworkBits = msg.substring(0, msg.indexOf("/") + 2); //隐藏以缓冲数据大小
                    mNetworkBits = msg;
                    mRateUpdateHandler.sendEmptyMessageDelayed(0x01, 1000);
                }
                break;
            case MessageNum.AY_SESSION_RECV_KEY_FRAME:
                mRateUpdateHandler.sendEmptyMessageDelayed(0x02, 300);
                break;
            case MessageNum.AY_SESSION_RECV_TS:
                mRateUpdateHandler.sendEmptyMessageDelayed(0x02, 1000);
                break;
        }
    }

    @Override
    public void OnNvrHistoryList(String device_id, int channel_idx, int rate, int start_time, int[] start_history, int[] end_history) {
        Log.i(TAG, "OnNvrHistoryList CallBack.");
        Log.i(TAG, "start_time=" + start_time);
        Log.i(TAG, "list size = " + start_history.length);
        Log.i(TAG, "list size = " + end_history.length);

        PrintFormat.NVRHistory(JHistory.ParseJHistory(start_history, end_history));
    }

    @Override
    public void OnRecvOEMData(byte[] data, int data_len) {
        Log.i(TAG, "OnRecvOEMData CallBack.");
    }

    /*@Override
    public void onAudioRecord(byte[] audioByte) {
        if (audioByte != null) {
            if(ClientModel.getClientModel().Speak(audioByte, audioByte.length)) {
                UpdateLog.updateI(TAG, "【true】Speak state");
            } else {
                UpdateLog.updateI(TAG, "【false】Speak state");
                UpdateLog.updateLastError(TAG);
            }
        }
    }*/

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.w(TAG, "SurfaceHolder Created");
        if (bfirtIn == true) {
            bfirtIn = false;
        } else {
            ClientModel.getClientModel().ActivePlayer(mSurfaceHolder.getSurface());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.w(TAG, "SurfaceHolder Changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.w(TAG, "SurfaceHolder Destroyed");
        ClientModel.getClientModel().DisActivePlayer();
    }

    @Override
    public void onBackPressed() {
        ClientModel.getClientModel().Quit();
        super.onBackPressed();
    }
}
