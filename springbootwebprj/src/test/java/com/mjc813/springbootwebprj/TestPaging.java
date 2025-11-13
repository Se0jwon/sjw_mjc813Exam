package com.mjc813.springbootwebprj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPaging {
    @Test
    public void testPaging() {
        // 전체페이지수   // 현재페이지수   // 시작페이지    // 마지막페이지
        // 4            // 1            // 1           // 4
        // 2            // 1            // 1           // 2
        // 1            // 1            // 1           // 1
        // 21           // 13           // 11          // 15
        // 21           // 2            // 1           // 5
        // 21           // 9            // 7           // 11
        // 21           // 20           // 18          // 21

        Integer endPage = 1;
        Integer startPage = 1;

        // 21           // 13           // 11          // 15
        endPage = getEndPage(21, 13);
        startPage = getStartPage(13);
        assertThat(startPage).isEqualTo(11);
        assertThat(endPage).isEqualTo(15);

        // 21           // 2            // 1           // 4
        endPage = getEndPage(21, 2);
        startPage = getStartPage(2);
        assertThat(startPage).isEqualTo(1);
        assertThat(endPage).isEqualTo(5);

        // 21           // 9            // 7           // 11
        endPage = getEndPage(21, 9);
        startPage = getStartPage(9);
        assertThat(startPage).isEqualTo(7);
        assertThat(endPage).isEqualTo(11);

        // 21           // 20           // 18          // 21
        endPage = getEndPage(21, 20);
        startPage = getStartPage(20);
        assertThat(startPage).isEqualTo(18);
        assertThat(endPage).isEqualTo(21);

        // 2            // 1            // 1           // 2
        endPage = getEndPage(2, 1);
        startPage = getStartPage(1);
        assertThat(startPage).isEqualTo(1);
        assertThat(endPage).isEqualTo(2);

        // 1            // 1            // 1           // 1
        endPage = getEndPage(1, 1);
        startPage = getStartPage(1);
        assertThat(startPage).isEqualTo(1);
        assertThat(endPage).isEqualTo(1);

        // 4            // 1            // 1           // 4
        endPage = getEndPage(4, 1);
        startPage = getStartPage(1);
        assertThat(startPage).isEqualTo(1);
        assertThat(endPage).isEqualTo(4);
    }

    private Integer getStartPage(Integer curPage) {
        return curPage <= 3 ? 1 : curPage - 2;
    }

    private Integer getEndPage(Integer totalPage, Integer curPage) {
        Integer page = curPage + 2;
        if ( page > totalPage ) {
            // 현재페이지 + 2 > 전체페이지
            return totalPage;
        } else {
            if ( totalPage < 5 ) {
                return totalPage;
            } else {
                if ( page < 5 ) {
                    page = 5;
                }
            }
        }
        return page;
    }
}
