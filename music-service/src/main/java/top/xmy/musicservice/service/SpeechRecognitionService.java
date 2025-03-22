package top.xmy.musicservice.service;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xmy.musicservice.config.AliyunConfig;

import java.io.File;
import java.io.IOException;

@Service
public class SpeechRecognitionService {

    @Autowired
    private AliyunConfig config;

    public String recognize(String filePath) throws IOException {
        OkHttpClient client = new OkHttpClient();
        File file = new File(filePath);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("audio/wav"), file))
                .build();

        Request request = new Request.Builder()
                .url("https://nls-gateway.cn-shanghai.aliyuncs.com/stream/v1/asr")
                .header("X-NLS-Token", config.getAccessToken())
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
