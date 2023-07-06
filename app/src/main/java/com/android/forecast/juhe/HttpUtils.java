package com.android.forecast.juhe;
//字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中。
// 创建字节数组输出流对象有以下几种方式。
//下面的构造方法创建一个32字节（默认大小）的缓冲区。
//OutputStream bOut = new ByteArrayOutputStream():32字节（默认大小）的缓冲区。
//public void reset()
//将此字节数组输出流的 count 字段重置为零，从而丢弃输出流中目前已累积的所有数据输出。
//public byte[] toByteArray()
//创建一个新分配的字节数组。数组的大小和当前输出流的大小，内容是当前输出流的拷贝。
//public String toString()
//将缓冲区的内容转换为字符串，根据平台的默认字符编码将字节转换成字符。
//public void write(int w)
// 将指定的字节写入此字节数组输出流。
//public void write(byte []b, int off, int len)
// 将指定字节数组中从偏移量 off 开始的 len 个字节写入此字节数组输出流。
//	public void writeTo(OutputStream outSt)
//将此字节数组输出流的全部内容写入到指定的输出流参数中。
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static String getJsonContent(String path){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            //类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针。
            // 资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，也就是我们常说的域名、浏览器访问地址。
            //在Java中就是使用URL访问网络资源，获取url对象的相关信息。
            //   根据 String 表示形式创建 URL 对象。
            URL url = new URL(path);
            //HttpURLConnection对象不能直接构造，需要通过URL类中的openConnection()
            // 方法来获得。
            //对HttpURLConnection对象的配置都需要在connect()方法执行之前完成，
            // 因为connect()会根据HttpURLConnection对象的配置值生成HTTP头部信息。
            //HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的TCP连接，
            // 并没有实际发送HTTP请求。HTTP请求实际上直到我们获取服务器响应数据
            // （如调用getInputStream()、getResponseCode()等方法）时才正式发送出去。
            //HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现。
            // 如果不设置超时（timeout），在网络异常的情况下，可能会导致程序僵死而不继续往下执行。
            //HTTP正文的内容是通过OutputStream流写入的， 向流中写入的数据不会立即发送到网络，
            // 而是存在于内存缓冲区中，待流关闭时，根据写入的内容生成HTTP正文。
            //调用getInputStream()方法时，返回一个输入流，用于从中读取服务器对于HTTP请求的
            // 返回信息。
            //我们可以使用HttpURLConnection.connect()方法手动的发送一个HTTP请求，
            // 但是如果要获取HTTP响应的时候，请求就会自动的发起，
            // 比如我们使用HttpURLConnection.getInputStream()方法的时候，
            // 所以完全没有必要调用connect()方法。
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //字节输入流，是一个抽象类
            InputStream is = conn.getInputStream();
            int hasRead = 0;
            byte[]buf = new byte[1024];
            while ((hasRead = is.read(buf))!=-1){
                //返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
                //发起连接请求
                baos.write(buf,0,hasRead);
            }
//程序中try{}catch(Exception e){e.printStackTrace() ;}中的e.printStackTrace() ;}
//当try语句中出现异常是时，会执行catch中的语句，java运行时系统会自动将catch括号中的Exception e 初始化，也就是实例化Exception类型的对象。
//e是此 对象引用名称,然后e（引用）会自动调用Exception类中指定的方法，也就出现了e.printStackTrace() ;。
//printStackTrace()方法的意思是：在命令行打印异常信息在程序中出错的位置及原因。
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回服务器响应数据
        return baos.toString();
    }
}
