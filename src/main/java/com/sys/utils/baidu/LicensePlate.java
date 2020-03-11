package com.sys.utils.baidu;

import com.sys.utils.baidu.utils.Base64Util;
import com.sys.utils.baidu.utils.FileUtil;
import com.sys.utils.baidu.utils.HttpUtil;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 车牌识别
 */
public class LicensePlate {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     *
     * 参数	类型	是否必须	说明
     * image	string	true	图像数据，base64编码后进行urlencode，要求base64编码和urlencode后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/jpeg/png/bmp格式
     * multi_detect	boolen	false	是否检测多张车牌，默认为false，当置为true的时候可以对一张图片内的多张车牌进行识别
     *
     * 返回参数
     *
     * 参数	类型	是否必须	说明
     * log_id	uint64	是	请求标识码，随机数，唯一。
     * color	string	是	车牌颜色：支持blue、green、yellow
     * number	string	是	车牌号码
     * probability	string	是	车牌中每个字符的置信度，区间为0-1
     * vertexes_location	string	是	返回文字外接多边形顶点位置
     *
     * {
     * 	"errno": 0,
     * 	"msg": "success",
     * 	"data": {
     * 		"log_id": "5327722537189137631",
     * 		"words_result": {
     * 			"color": "green",
     * 			"number": "苏AD12267",
     * 			"probability": [
     * 				1,
     * 				0.9999977350235,
     * 				0.99999630451202,
     * 				0.99999868869781,
     * 				0.99998331069946,
     * 				0.99999988079071,
     * 				0.9531751871109,
     * 				0.99922955036163
     * 			],
     * 			"vertexes_location": [
     *                                {
     * 					"y": 223,
     * 					"x": 170
     *                },
     *                {
     * 					"y": 223,
     * 					"x": 282
     *                },
     *                {
     * 					"y": 256,
     * 					"x": 282
     *                },
     *                {
     * 					"y": 256,
     * 					"x": 170
     *                }
     * 			]
     * 		}
     * 	}
     * }
     */
    public static String licensePlate() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
        try {
            // 本地文件路径
            String filePath = "C:\\tmp\\timg.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            //token获取 https://ai.baidu.com/ai-doc/REFERENCE/Ck3dwjhhu
            String accessToken = "24.657ef817e3cbf1bbcbee2da70f509d68.2592000.1582966463.282335-18357486";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String licensePlateUtils(File file, String accessToken) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
        try {
            byte[] imgData = FileUtil.readFileByBytes(file);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            //token获取 https://ai.baidu.com/ai-doc/REFERENCE/Ck3dwjhhu
            //String accessToken = "24.657ef817e3cbf1bbcbee2da70f509d68.2592000.1582966463.282335-18357486";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        LicensePlate.licensePlate();
    }
}
