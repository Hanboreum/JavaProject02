package kr.book.search;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BookSearchMain {
    public static void main(String[] args) {
       try{
           Scanner sc = new Scanner(System.in);
           System.out.println("도서 제목을 입력하세요.");
           String bookTitle =sc.nextLine();
           List<Book> books = KakaoBookApi.searchBook(bookTitle);

           if(books.isEmpty()){
               System.out.println("검색 결과가 없습니다. ");
           }else{
               for(Book book : books) {
                   System.out.println(book);
               }
               String fileName = "도서목록.pdf";
              PdfGenerator.generateBookListPdf(books, fileName);
               System.out.println(fileName + "파일이 생성되었습니다");
           }
       }catch (IOException e){
           System.out.println("에러가 발생했습니다" + e.getMessage());
       }

    }
}
