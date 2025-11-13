package com.mjc813.studyjava;

import com.mjc813.mjc_library.IMarketInfo;

public class CWCMarket implements IMarketInfo {
    @Override
    public String getMessageOfMarketInfo() {
        return "+++++ 최원철상점입니다. 행복하세요. 부자되세요 +++++";
    }

    @Override
    public String getMessageOfNotValidData() {
        return "정확한 데이터 아닙니다. 최원철상점의 관리자(T. 02-0202-1111) 문의하세요";
    }
}
