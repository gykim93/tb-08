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

    void actionRemove(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);
        if (id == 0){
            System.out.println("id를 정확히 입력해주세요");
            return;
        }
        System.out.printf("%d번 명언을 삭제합니다\n", id);
    }

    int getParamAsInt(String cmd, String paramName, int defaultValue) {
        String[] cmdBits = cmd.split("\\?", 2); //cmd에 삭제?id=1&archive=true
        String action = cmdBits[0]; // 삭제
        String queryString = cmdBits[1]; // id=1&archive=true

        String[] queryStringBits = queryString.split("&");
        // queryStringBtis[0] id=1
        // queryStringBtis[1] archive=true

        int id = 0;

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);
            String _paramName = queryParamStrBits[0]; // id
            String paramValue = queryParamStrBits[1];// 1

            if (_paramName.equals(paramName)) {
                try {
                    //문제가 없을 경우
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    // 문제가 생긴 경우
                    return defaultValue;
                }
            }
        }
        return defaultValue;
    }
}
