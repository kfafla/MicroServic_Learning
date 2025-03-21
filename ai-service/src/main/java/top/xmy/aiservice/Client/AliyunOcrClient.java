package top.xmy.aiservice.client;

import com.aliyun.ocr_api20210707.Client;
import com.aliyun.ocr_api20210707.models.RecognizeAllTextRequest;
import com.aliyun.ocr_api20210707.models.RecognizeAllTextResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliyunOcrClient {

    @Value("${aliyun.ocr.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.ocr.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 通过 URL 调用阿里云 OCR API 进行图片识别
     * @param imageUrl 图片的 URL 地址
     * @return 识别结果的 JSON 字符串
     * @throws Exception 如果调用过程中出现异常
     */
    public String recognizeTextByUrl(String imageUrl) throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret)
                .setEndpoint("ocr-api.cn-hangzhou.aliyuncs.com");

        Client client = new Client(config);

        // 构造请求
        RecognizeAllTextRequest request = new RecognizeAllTextRequest()
                .setUrl(imageUrl)  // 设置图片 URL
                .setType("General");  // 设置识别类型，例如 "General" 表示通用文字识别

        RuntimeOptions runtime = new RuntimeOptions();

        // 发送请求并获取响应
        RecognizeAllTextResponse response = client.recognizeAllTextWithOptions(request, runtime);

        // 返回识别结果
        return response.getBody().toString();
    }
}