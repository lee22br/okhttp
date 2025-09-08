
import java.io.IOException;

import okhttp3.*;

public class GetCep {

    final OkHttpClient client = new OkHttpClient();

    String runSync(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    void runAsync(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    System.out.println(responseBody.string());
                }
            }
        });
    }
    public static void main(String[] args) throws IOException {
        GetCep example = new GetCep();
//        String response = example.runSync("https://viacep.com.br/ws/90550052/json/");
//        System.out.println(response);
        example.runAsync("https://viacep.com.br/ws/90550052/json/");
    }
}
