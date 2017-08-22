package com.ruiyi.carassistant.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 2017/2/5.
 */
public abstract class PhoneNumUtil {
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4\\D])|(14[57])|(17[07])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static void main1(String[] args) {
        //System.out.println(PhoneNumUtil.isMobileNO("15528801837"));
        JSONArray parse = JSON.parseArray("[{\n" +
                "\t\"longtitude\":\t\"104.0615692\",\n" +
                "\t\"latitude\":\t\"30.5781097\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0598145\",\n" +
                "\t\"latitude\":\t\"30.5782356\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0586166\",\n" +
                "\t\"latitude\":\t\"30.5781555\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0585785\",\n" +
                "\t\"latitude\":\t\"30.5782318\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0572281\",\n" +
                "\t\"latitude\":\t\"30.5765324\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0573654\",\n" +
                "\t\"latitude\":\t\"30.5757084\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0533600\",\n" +
                "\t\"latitude\":\t\"30.5911732\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0497284\",\n" +
                "\t\"latitude\":\t\"30.5909977\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0489120\",\n" +
                "\t\"latitude\":\t\"30.5909576\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0452118\",\n" +
                "\t\"latitude\":\t\"30.5909271\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0369492\",\n" +
                "\t\"latitude\":\t\"30.5906944\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0320206\",\n" +
                "\t\"latitude\":\t\"30.5927658\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0319672\",\n" +
                "\t\"latitude\":\t\"30.6034298\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0354080\",\n" +
                "\t\"latitude\":\t\"30.6144295\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0405045\",\n" +
                "\t\"latitude\":\t\"30.6222935\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0442734\",\n" +
                "\t\"latitude\":\t\"30.6269836\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0407104\",\n" +
                "\t\"latitude\":\t\"30.6294727\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0368347\",\n" +
                "\t\"latitude\":\t\"30.6312370\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0293350\",\n" +
                "\t\"latitude\":\t\"30.6382599\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0233459\",\n" +
                "\t\"latitude\":\t\"30.6443291\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0168915\",\n" +
                "\t\"latitude\":\t\"30.6417160\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0128403\",\n" +
                "\t\"latitude\":\t\"30.6394539\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0139008\",\n" +
                "\t\"latitude\":\t\"30.6394501\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0139999\",\n" +
                "\t\"latitude\":\t\"30.6393909\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0199127\",\n" +
                "\t\"latitude\":\t\"30.6131916\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0258865\",\n" +
                "\t\"latitude\":\t\"30.6101742\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0274200\",\n" +
                "\t\"latitude\":\t\"30.6093197\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0321274\",\n" +
                "\t\"latitude\":\t\"30.6069164\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0356293\",\n" +
                "\t\"latitude\":\t\"30.6048565\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0382080\",\n" +
                "\t\"latitude\":\t\"30.6031418\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0425873\",\n" +
                "\t\"latitude\":\t\"30.6012688\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0464859\",\n" +
                "\t\"latitude\":\t\"30.6006126\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0498428\",\n" +
                "\t\"latitude\":\t\"30.6006584\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0537872\",\n" +
                "\t\"latitude\":\t\"30.6006317\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0612411\",\n" +
                "\t\"latitude\":\t\"30.6004467\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0650177\",\n" +
                "\t\"latitude\":\t\"30.5980721\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0651398\",\n" +
                "\t\"latitude\":\t\"30.5959034\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0653305\",\n" +
                "\t\"latitude\":\t\"30.5902805\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0656509\",\n" +
                "\t\"latitude\":\t\"30.5826359\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0656128\",\n" +
                "\t\"latitude\":\t\"30.5790176\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0634689\",\n" +
                "\t\"latitude\":\t\"30.5745010\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0570679\",\n" +
                "\t\"latitude\":\t\"30.5748978\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0580063\",\n" +
                "\t\"latitude\":\t\"30.5777607\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0616302\",\n" +
                "\t\"latitude\":\t\"30.5779495\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0616531\",\n" +
                "\t\"latitude\":\t\"30.5802002\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0616379\",\n" +
                "\t\"latitude\":\t\"30.5809383\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0615921\",\n" +
                "\t\"latitude\":\t\"30.5812225\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0615616\",\n" +
                "\t\"latitude\":\t\"30.5876274\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0615158\",\n" +
                "\t\"latitude\":\t\"30.5905323\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0614395\",\n" +
                "\t\"latitude\":\t\"30.5925636\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0613251\",\n" +
                "\t\"latitude\":\t\"30.5960388\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0611877\",\n" +
                "\t\"latitude\":\t\"30.5984592\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0606079\",\n" +
                "\t\"latitude\":\t\"30.5985374\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0606003\",\n" +
                "\t\"latitude\":\t\"30.5983181\"\n" +
                "},{\n" +
                "\t\"longtitude\":\t\"104.0606766\",\n" +
                "\t\"latitude\":\t\"30.5990582\"\n" +
                "}]\n");
        String str = "";
        for (Object o : parse) {
            JSONObject json = (JSONObject) o;
            str += json.getString("longtitude") + "," + json.getString("latitude") + "|";
        }
        System.out.println(str);
    }

    /**
     * 创建指定数量的随机字符串
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length){
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://120.24.152.177/resource/upload/temp";
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(url);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", new File("D:/QQ20170205090714.png"));

        HttpEntity reqEntity = builder.build();

        httppost.setEntity(reqEntity);

        CloseableHttpResponse resp = client.execute(httppost);

        String str = EntityUtils.toString(resp.getEntity());
        System.out.println(str);

        resp.close();
        client.close();

    }
}
