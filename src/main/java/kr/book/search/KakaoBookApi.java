package kr.book.search;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KakaoBookApi {
    //오픈 api를 사용하기 위해, Rest key 값
    private static final String API_KEY = "d59dfcc50e06f6ed66d78dedc43146fb";
    //open api url 주소
    private static final String API_BASE_URL = "https://dapi.kakao.com/v3/search/book ";
    //위 url에 접속하는 api class
    private static final OkHttpClient client = new OkHttpClient(); //서버연결하는 api
    private static final Gson gson = new Gson();

    //책 검색 메서드
    //searchBook은 () 안으로 책 제목을 입력 받는다.
    public static List<Book> searchBook(String title) throws IOException{
        //url 생성, 연결하는 객체. parse ()안에 API_BASE_URL 넣으면 연결
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_BASE_URL).newBuilder();
        //query라는 변수에 title을 넘겨줌
        urlBuilder.addQueryParameter("query",title);

        //요청함.Builder라는 객체를 사용해서 제작.
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("Authorization","KakaoAK " + API_KEY)
                .build();

        try(Response response = client.newCall(request).execute()){ //newCall(request)연결.execute()요청.
            if( !response.isSuccessful()) throw new IOException("Request failed" + response);

            //받은 stream을 jsonobject로 바꿔 jsonResponse 변수에 담는다.
            JsonObject jsonResponse = gson.fromJson(response.body().charStream(),JsonObject.class);
            JsonArray documents = jsonResponse.getAsJsonArray("documents");
            
            List<Book>books = new ArrayList<>();
            for(JsonElement document : documents){
                JsonObject bookJson = document.getAsJsonObject();
                Book book = new Book(
                        bookJson.get("title").getAsString(),
                        bookJson.get("authors").getAsJsonArray().toString(),
                        bookJson.get("thumbnail").getAsString()
                );
                books.add(book);
            }
            return books;
        }
    }

}
