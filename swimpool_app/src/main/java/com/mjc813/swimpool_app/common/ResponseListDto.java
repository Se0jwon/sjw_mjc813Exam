package com.mjc813.swimpool_app.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseListDto {
    private Integer totalRow;
    private Integer curPage;
    private Integer rowsPage;
    private Object data;

    public Integer getCurPage() {
        return this.curPage == null || this.curPage <= 1 ? 1 : this.curPage;
    }

    public Integer getTotalRow() {
        return this.totalRow == null || this.totalRow <= 0 ? 0 : this.totalRow;
    }

    public Integer getRowsPage() {
        return this.rowsPage == null || this.rowsPage <= 3 ? 3 : this.rowsPage >= 91 ? 90 : this.rowsPage;
    }
}
