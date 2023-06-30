package com.neprozorro.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageRequestDTO {

    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private Sort.Direction sort = Sort.Direction.DESC;
    private String sortByColumn = "lotTotalPrice";

    public Pageable getPageable() {
        return PageRequest.of(pageNo, pageSize, sort, sortByColumn);
    }
}
