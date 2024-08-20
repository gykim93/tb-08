package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {
    Scanner scanner;
    int lastQuotationid;
    List<Quotation> quotations;

    App() {
        lastQuotationid = 0;
        scanner = new Scanner(System.in);
        quotations = new ArrayList<>();
    }

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령 ) ");

            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionRemove(rq);
                    break;
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

        System.out.printf("%d번 명언이 등록 되었습니다.\n",lastQuotationid);
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

    void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요");
            return;
        }
        int index = getIndexOfQuotationById(id);
        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",id);
            return;
        }
        quotations.remove(index);

        System.out.printf("%d번 명언을 삭제합니다\n", id);
    }
    int getIndexOfQuotationById(int id){
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);
            if (quotation.id == id) {
                return i;
            }
        }
        return -1;
    }
}
