
import java.io.IOException;

import okhttp3.*;

public class SimpleGet {

    final OkHttpClient client = new OkHttpClient();

    String runSync(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Response: "+ response.body().string());
            return "";
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleGet example = new SimpleGet();
        example.runSync("https://viacep.com.br/ws/90550052/json/");

        System.out.println("Main thread stop");
        System.out.println(".....");
        System.out.println("........");
    }
}
