package com.android.forecast.base;

import android.support.v4.app.Fragment;
//回调机制（CallBack）就是A类中调用B类中的某个方法C，然后B类中反过来调用A类中的方法D，D这个方法就叫回调方法。
//回调方法的实现过程就叫Android（Java）的回调机制。
import org.xutils.common.Callback;
//
import org.xutils.http.RequestParams;
import org.xutils.x;

/*xutils加载网络数据的步骤
* 1.声明整体模块
*  2.执行网络请求操作
*步骤一：需要在项目的build.gradle里面引入xutils 3这个工具，代码如下：implementation 'org.xutils:xutils:3.6.19'
步骤二：传递附带的参数和文件数据即可，代码如下：RequestParams params = new RequestParams(UserLoginDialog.BASE_URL+"/api/uploadVoice");
params.setMultipart(true);
//携带的一些额外参数
params.addBodyParameter("jsonStr", json);
//需要上传的文件
params.addBodyParameter("file", new File(voice_url));
x.http().post(params, new Callback.CommonCallback() {
@Override
public void onSuccess(String result) {
//上传成功返回结果，需要后台返回JSON字符串数据
}
@Override
public void onError(Throwable ex, boolean isOnCallback) {
}
@Override
public void onCancelled(CancelledException cex) {
}
@Override
public void onFinished() {
}
});

* */
public class BaseFragment extends Fragment implements Callback.CommonCallback<String>{

    public void loadData(String path){
        RequestParams params = new RequestParams(path);
        x.http().get(params,this);
    }
//    获取数据成功时，会回调的接口
    @Override
    public void onSuccess(String result) {

    }
//  获取数据失败时，会调用的接口
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }
//  取消请求时，会调用的接口
    @Override
    public void onCancelled(CancelledException cex) {

    }
//  请求完成后，会回调的接口
    @Override
    public void onFinished() {

    }
}
