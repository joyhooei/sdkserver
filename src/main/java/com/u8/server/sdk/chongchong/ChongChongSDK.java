package com.u8.server.sdk.chongchong;

import com.u8.server.data.UChannel;
import com.u8.server.data.UOrder;
import com.u8.server.data.UUser;
import com.u8.server.sdk.ISDKOrderListener;
import com.u8.server.sdk.ISDKScript;
import com.u8.server.sdk.ISDKVerifyListener;
import com.u8.server.sdk.SDKVerifyResult;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * 虫虫助手 SDK
 * Created by ant on 2018/01/22.
 */
public class ChongChongSDK implements ISDKScript {

    private static Logger log = Logger.getLogger(ChongChongSDK.class.getName());
    
    @Override
    public void verify(final UChannel channel, String extension, final ISDKVerifyListener callback) {

        try {
            log.info("------------------> verify extension:" + extension);

            JSONObject json = JSONObject.fromObject(extension);
            String uid = json.getString("openid");
            String userName = json.getString("name");

            callback.onSuccess(new SDKVerifyResult(true,uid,userName,""));

        } catch (Exception e) {
            log.error("verify exception,the result is " + e.getMessage());
            e.printStackTrace();
            callback.onFailed(channel.getMaster().getSdkName() + " verify execute failed. the exception is " + e.getMessage());
        }
    }

    @Override
    public void onGetOrderID(UUser user, UOrder order, ISDKOrderListener callback) {

        if (callback != null) {
            JSONObject json = new JSONObject();
            json.put("orderId", order.getOrderID());

            callback.onSuccess(json.toString());
        }

    }
}
