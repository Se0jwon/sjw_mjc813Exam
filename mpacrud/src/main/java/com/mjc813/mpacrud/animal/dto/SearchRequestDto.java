package com.mjc813.mpacrud.animal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {
    private String searchType;  // 이름검색(name) / 종류검색(kind) 인지 결정
    private String searchWord;  // 검색에 포함할 단어, mysql WHERE ${searchType} LIKE CONCAT('%', #{searchWord}, '%')
    // mysql WHERE name LIKE '%검색단어%'
    // mysql WHERE kind LIKE '%검색단어%'
    private Integer rowsOnePage;    // 한페이지에 출력할 데이터행수, mysql select 쿼리의 LIMIT #{rowsOnePage} OFFSET #{offset} 에 사용될 값
    private Integer page;   // 현재 페이지 번호
    private Integer offset; // mysql select 쿼리의 LIMIT #{rowsOnePage} OFFSET #{offset} 에 사용될 값

    public String getSearchType() {
        return this.searchType == null || "".equals(this.searchType) ? "name" : this.searchType;
        // 검색타입(이름검색,종류검색)이 null 이면 이름검색이 적용되도록 한다.
    }

    public String getSearchWord() {
        return this.searchWord == null ? "" : this.searchWord;
        // 검색단어가 null 이면 검색단어를 "" 빈문자열로 처리한다.
    }

    public Integer getRowsOnePage() {
        return this.rowsOnePage == null ? 10 : this.rowsOnePage;
        // 한페이지에 출력할 데이터행수가 null 이면 기본으로 데이터행수 10개를 한페이지에 출력하도록 한다.
    }

    public Integer getPage() {
        return this.page == null ? 1 : this.page;
        // 현재페이지의 값이 null 이면 1 페이지를 현재 페이지로 출력하도록 한다.
    }

    public Integer getOffset() {
        return (this.getPage()-1) * this.getRowsOnePage();
        // mysql 의 LIMIT #{rowsOnePage} OFFSET #{offset} 문장의 변수 값으로 출력하도록 한다.
        // LIMIT 출력할데이터행갯수 OFFSET 몇번째행(0부터시작)부터출력시작
    }
}
