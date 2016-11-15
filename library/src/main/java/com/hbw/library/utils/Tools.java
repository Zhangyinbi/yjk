package com.hbw.library.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hbw.library.R;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @author shine_sj
 */
@SuppressWarnings({"unchecked", "rawtypes", "deprecation", "static-access"})
public class Tools {
    /**
     * 获取sdk版本信息
     */
    public static String getSDKVersion(Context context) {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取运营商信息
     */
    public static String getCarrier(Context context) {
        TelephonyManager telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = telephony.getSubscriberId();
        if (imsi != null && !"".equals(imsi)) {
            if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
                return context.getString(R.string.china_y);
            } else if (imsi.startsWith("46001")) {
                return context.getString(R.string.china_l);
            } else if (imsi.startsWith("46003")) {
                return context.getString(R.string.china_d);
            } else {
                return "othes";
            }
        }

        return null;
    }

    /**
     * 获取手机相关信息
     *
     * @param con
     * @param type 0:手机UDID/IMEI号，1:手机号码（不一定能获取到）,2:手机SIM卡IMSI号
     * @return
     */
    public static String readTelephoneSerialNum(Context con, int type) {
        TelephonyManager telephonyManager = (TelephonyManager) con
                .getSystemService(Context.TELEPHONY_SERVICE);
        switch (type) {
            case 0:
                return telephonyManager.getDeviceId();
            case 1:
                return telephonyManager.getLine1Number();
            case 2:
                return telephonyManager.getSubscriberId();
            default:
                return telephonyManager.getDeviceId();

        }
    }

    /**
     * 将InputStream流转换为字符串
     *
     * @param instream
     * @return
     */
    public static String InputStreamToString(InputStream instream) {
        StringBuffer buffer = new StringBuffer();
        String s = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(instream, "UTF-8"));

            while ((s = bufferedReader.readLine()) != null) {
                buffer.append(s);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 将汉字转换为全拼
     *
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        // System.out.println(t1.length);
        String[] t2 = new String[t1.length];
        // System.out.println(t2.length);
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断能否为汉字字符
                // System.out.println(t1[i]);
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                } else {
                    // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                    t4 += Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return t4;
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        if (str == null || "".equals(str)) {
            return null;
        }
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    /**
     * 输入的字符是否是汉字
     *
     * @param a char
     * @return boolean
     */
    public static boolean isChinese(char a) {
        int v = (int) a;
        return (v >= 19968 && v <= 171941);
    }

    /**
     * 判断是否为汉字
     *
     * @param s
     * @return
     */
    public static boolean containsChinese(String s) {
        if (null == s || "".equals(s.trim()))
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (isChinese(s.charAt(i)))
                return true;
        }
        return false;
    }

    /**
     * 随机获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
     *
     * @return String
     */
    public static String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return r + g + b;
    }

    /**
     * 获取SD卡剩余空间，无卡则返回-1
     */
    public static long getSDSize() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            String path = Environment.getExternalStorageDirectory().getPath();
            StatFs statFs = new StatFs(path);
            long l = statFs.getBlockSize();
            return statFs.getAvailableBlocks() * l;
        }

        return -1;
    }

    /**
     * 获取当前时间
     */
    public static String getNowTime() {
        Date dat = new Date();
        String time = dat.getTime() + "";
        if (time.length() > 10) {
            time = time.substring(0, 10);
        }
        return time;
    }

    /**
     * 判断网络是否可用̬
     */
    public static boolean isAccessNetwork(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity.getActiveNetworkInfo() != null
                && connectivity.getActiveNetworkInfo().isAvailable()) {
            return true;
        }
        return false;
    }

    /**
     * 删除指定路径的文件
     */
    @SuppressWarnings("unused")
    public static void deleteAllFiles(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int len = 0;
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    deleteAllFiles(files[i].getPath());
                }
            }
        } else {
            file.deleteOnExit();
        }
    }

    /**
     * 检查邮箱格式
     *
     * @param emailAddress
     * @return
     */
    public static boolean isEmail(String emailAddress) {
        // String strPattern =
        // "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

        // 利用了Java里面的正则表达式
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(emailAddress);
        // 当目标string与传入的string完全匹配，返回true,否则返回false
        return m.matches();
    }

    /**
     * 验证身份证号是否符合规则
     */
    public static boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

    /**
     * 检查手机格式
     *
     * @param cellPhoneNumber
     * @return
     */
    public static boolean isCellPhoneNumber(String cellPhoneNumber) {
        // 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数
        String strPattern = "^[1][\\d]{10}";
        // 利用了Java里面的正则表达式
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(cellPhoneNumber);
        // 当目标string与传入的string完全匹配，手机号码返回true,不是手机号码否则返回false
        return m.matches();
    }

    /**
     * 获取android当前可用内存大小
     *
     * @param con
     * @return
     */
    public static String getAvailMemory(Context con) {

        ActivityManager am = (ActivityManager) con
                .getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        // mi.availMem; 当前系统的可用内存

        return Formatter.formatFileSize(con, mi.availMem);// 将获取的内存大小规格化
    }

    /**
     * 获取android当前总内存
     *
     * @param con
     * @return
     */
    public static String getTotalMemory(Context con) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (IOException e) {
        }
        return Formatter.formatFileSize(con, initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

    /**
     * 设置listView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * 设置gridview的高度
     */
    public static void setGridViewHeightBasedOnChildren(GridView gridview) {
        if (gridview == null) {
            return;
        }
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = gridview.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int lineCount = (listAdapter.getCount() - 1) / 3 + 1;
        View listItem = listAdapter.getView(0, null, gridview);
        if (listItem != null) {
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight() * lineCount; // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = gridview.getLayoutParams();
        params.height = totalHeight;
        // gridview.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        gridview.setLayoutParams(params);
    }

    /**
     * 设置gridview的高度
     *
     * @param gridview
     * @param spceHeight
     * @param columsNumber 每行显示个数 间距
     */
    public static void setGridViewHeightBasedOnChildren(GridView gridview,
                                                        int spceHeight, int columsNumber) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = gridview.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int lineCount = (listAdapter.getCount() - 1) / columsNumber + 1;
        View listItem = listAdapter.getView(0, null, gridview);
        listItem.measure(0, 0); // 计算子项View 的宽高
        totalHeight += (listItem.getMeasuredHeight() + spceHeight) * lineCount; // 统计所有子项的总高度

        ViewGroup.LayoutParams params = gridview.getLayoutParams();
        params.height = totalHeight;
        // gridview.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        gridview.setLayoutParams(params);
    }

    /**
     * 深拷贝1：递归方法
     *
     * @param src
     * @param dest
     */
    public static void copy(List src, List dest) {
        for (int i = 0; i < src.size(); i++) {
            Object obj = src.get(i);
            if (obj instanceof List) {
                dest.add(new ArrayList());
                copy((List) obj, (List) ((List) dest).get(i));
            } else {
                dest.add(obj);
            }
        }
    }

    /**
     * 深拷贝2:序列化|反序列化方法
     *
     * @param src
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List copyBySerialize(List src) throws IOException,
            ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(
                byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List dest = (List) in.readObject();
        return dest;
    }

    /**
     * 获取通知栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 隐藏键盘
     *
     * @param con
     */
    public static void HideKeyBoard(Activity con) {
        InputMethodManager imm = ((InputMethodManager) con
                .getSystemService(con.INPUT_METHOD_SERVICE));
        View v = con.getCurrentFocus();
        if (v != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 打开输入法键盘
     *
     * @param activity
     */
    public static void OpenInputWindow(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getApplication()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 打开输入法键盘，焦点注册到指定View
     *
     * @param view
     * @param activity
     */
    public static void OpenInputWindow(View view, Activity activity) {
        view.setFocusable(true);
        view.requestFocus();
        if (activity.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getApplication().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 影藏输入法键盘
     *
     * @param activity
     */
    public static void hideInputWindow(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getApplication()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 图片处理成圆形
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = bitmap.getWidth() / 2;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * MD5加密
     */
    public static String toMD5(String sInput) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        char[] charArray = sInput.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toLowerCase();
    }

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(byte[] b) { // String to byte
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            return toHexString(messageDigest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取控件高度宽度
     */
    public static LinearLayout measureXY(LinearLayout x) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        x.measure(w, h);
        return x;
    }


    /**
     * 从资源文件中获取bitmap
     *
     * @param context
     * @param res
     * @return
     */
    public static Bitmap getBitmapByRes(Context context, int res) {
        return BitmapFactory.decodeResource(context.getResources(), res);
    }


    /**
     * bimmap转byte[]
     *
     * @param bitmap
     * @return
     */
    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    /**
     * byte[]转bitmap
     *
     * @param b
     * @return
     */
    public static Bitmap byteArrayToBitmap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     * bitmap缩放
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    /**
     * Drawable缩放
     *
     * @param drawable
     * @param w
     * @param h
     * @return
     */
    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // drawable转换成bitmap
        Bitmap oldbmp = drawableToBitmap(drawable);
        // 创建操作图片用的Matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放比例
        float sx = ((float) w / width);
        float sy = ((float) h / height);
        // 设置缩放比例
        matrix.postScale(sx, sy);
        // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(newbmp);
    }

    /**
     * bitmap转换成Drawable
     *
     * @param context
     * @param bitmap
     * @return
     */
    public static Drawable bitmpaToDrawable(Context context, Bitmap bitmap) {
        //因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * Drawable 转 bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    /**
     * 获得带倒影的图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w,
                h / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
                Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, h, w, bitmapWithReflection.getHeight()
                + reflectionGap, paint);

        return bitmapWithReflection;
    }


    //生成二维码
    public static Bitmap createTwoDimensionCode(String url, int tdc_width,
                                                int tdc_height) {
        try {
            // 判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url,
                    BarcodeFormat.QR_CODE, tdc_width, tdc_height, hints);
            int[] pixels = new int[tdc_width * tdc_height];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < tdc_height; y++) {
                for (int x = 0; x < tdc_width; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * tdc_width + x] = 0xff000000;
                    } else {
                        pixels[y * tdc_width + x] = 0xffffffff;
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(tdc_width, tdc_height,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, tdc_width, 0, 0, tdc_width, tdc_height);
            // 显示到一个ImageView上面
            // sweepIV.setImageBitmap(bitmap);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取得手机屏幕分辨率信息
     *
     * @param context
     */
    public static String getScreenResolutionInfo(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        StringBuffer sb = new StringBuffer();
        sb.append("The info below");
        sb.append("\n手机型号：" + Build.MODEL);
        sb.append("\nwidthPixels=" + dm.widthPixels);
        sb.append("\nheightPixels=" + dm.heightPixels);
        sb.append("\ndensity=" + dm.density);
        sb.append("\ndensityDpi=" + dm.densityDpi);
        sb.append("\nscaledDensity=" + dm.scaledDensity);
        sb.append("\nxdpi=" + dm.xdpi);
        sb.append("\nydpi=" + dm.ydpi);
        return sb.toString();
    }

    /**
     * 正则手机号码
     */
    public static boolean isMobileNO(String mobiles) {

        String NSString = "";
        /**
         * 手机号码 移动34[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188
         * 联通30,131,132,152,155,156,185,186 电信33,1349,153,180,189
         */

        NSString = "^1(3[0-9]|5[0-35-9]|8[025-9])\\d{8}$";
        /**
         * 10 * 中国移动：China Mobile 11 *
         * 134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188 12
         */
        // NSString = "^1(34[0-8]|(3[5-9]|5[017-9]|8[278])\\d)\\d{7}$";
        /**
         * 15 * 中国联通：China Unicom 16 * 130,131,132,152,155,156,185,186 17
         */
        // NSString = "^1(3[0-2]|5[256]|8[56])\\d{8}$";
        /**
         * 20 * 中国电信：China Telecom 21 * 133,1349,153,180,189 22
         */
        // NSString = "^1((33|53|8[09])[0-9]|349)\\d{7}$";

        /**
         * 大陆地区固话及小灵通 10,020,021,022,023,024,025,027,028,029 号码：七位或八位
         */
        // NSString * PHS = @"^0(10|2[0-5789]|\\d{3})\\d{7,8}$";

        // NSString = "^[1]([0-9][0-9]{1}|59|58|88|89)[0-9]{8}$";

        Pattern p = Pattern.compile(NSString);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    /**
     * 强制隐藏键盘 mwy
     * @param v
     * @param context
     */
    public static void forceHideSoftInput(View v,Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
