package com.mjc813.swimpool_app.swimpool.dto;

import com.mjc813.swimpool_app.common.ResponseListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SwimpoolSearchingDto extends SwimpoolDto {
    private Integer offset;
    private ResponseListDto resultList;

    public Integer getOffset() {
        return (this.resultList.getCurPage()-1) * this.resultList.getRowsPage();
    }
}
