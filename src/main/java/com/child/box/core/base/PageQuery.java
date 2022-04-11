package com.child.box.core.base;

import lombok.Data;

@Data
public class PageQuery {

    private int pageNum = 1;
    private int pageSize = 20;

    public void checkPageSize() {
        if (this.pageSize > 20) {
            this.pageSize = 20;
        }
    }

}
