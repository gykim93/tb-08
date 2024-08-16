package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {
    Scanner scanner;
    int lastQuotationid;
    List<Quotation> quotations;
    App(){
        lastQuotationid = 0;
        scanner = new Scanner(System.in);
        quotations = new ArrayList<>();
    }
    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령 ) ");

            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
                System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationid);

            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제?")) {
                actionRemove(cmd);
            }
        }
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        lastQuotationid++;

        int id = lastQuotationid;
        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);
    }

    void actionList() {
        System.out.println("번호  /  작가  /  명언");

        System.out.println("-----------------------");

        if (quotations.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        }
        for (int i = quotations.size() - 1; i >= 0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.id, quotation.authorName, quotation.content);
        }
    }

    void actionRemove(String cmd){
        String idStr = cmd.replace("삭제?id=",""); //"삭제?id=1", "삭제?id=" 이 부분을 빈 문자열로 대체, ID
        int id = Integer.parseInt(idStr);
        System.out.printf("%d번 명언을 삭제합니다\n", id);
    }
}
