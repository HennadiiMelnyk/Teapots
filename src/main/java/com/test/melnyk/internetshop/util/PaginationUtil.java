package com.test.melnyk.internetshop.util;

public final class PaginationUtil {

    private static int currentPageIndex;

    public static int setPageNumber(String page, String end) {
        if (page == null) {
            currentPageIndex = 1;
            return 1;
        }

        if (page.equals("previous")) {
            currentPageIndex--;
            validatePageNumber(currentPageIndex, Integer.parseInt(end));
            return currentPageIndex;
        }

        if (page.equals("next")) {
            currentPageIndex++;
            validatePageNumber(currentPageIndex, Integer.parseInt(end));
            return currentPageIndex;
        }

        currentPageIndex = Integer.parseInt(page);
        validatePageNumber(currentPageIndex, Integer.parseInt(end));

        return currentPageIndex;
    }

    public static void validatePageNumber(int number, int end) {
        if (number < 1 || number > end) {
            currentPageIndex = 1;
        }
    }
}
