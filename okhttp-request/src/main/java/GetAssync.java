import okhttp3.*;

import java.io.IOException;

public class GetAssync {
    final OkHttpClient client = new OkHttpClient();

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

    public static void main(String[] args) throws IOException, InterruptedException {
        GetAssync example = new GetAssync();
        example.runAsync("http://httpbin.org/delay/2");
        System.out.println("Main thread still running");
        String aux = ".";
        for (int i = 0; i < 10; i++){
            aux = aux.concat(".");
            System.out.println(aux+i);
            Thread.sleep(500);
        }
        System.exit(0);
    }
}
