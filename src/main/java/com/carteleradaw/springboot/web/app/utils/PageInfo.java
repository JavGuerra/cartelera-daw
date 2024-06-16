package com.carteleradaw.springboot.web.app.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static com.carteleradaw.springboot.web.app.utils.Utils.invalidPosNumber;

@Getter
@Setter
@Component
public class PageInfo {

    private int totalPages;
    private int currentPage;
    private int pageSize;
    private int startPage;  // A mostrar en la navegación de la paginación.
    private int endPage;    // A mostrar en la navegación de la paginación.
    private int startElement;
    private int endElement;
    private int numberOfElements;
    private long totalElements;

    public static PageInfo createFromPage(Page<?> page) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotalElements(page.getTotalElements());
        pageInfo.setNumberOfElements(page.getNumberOfElements());
        pageInfo.setStartElement(page.getNumber() * page.getSize() + 1);
        pageInfo.setEndElement(pageInfo.startElement + pageInfo.getNumberOfElements() - 1);
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setCurrentPage(page.getNumber());
        pageInfo.setPageSize(page.getSize());
        if (invalidPosNumber((long) pageInfo.getCurrentPage()) || pageInfo.getCurrentPage() < 0 || pageInfo.getCurrentPage() > pageInfo.getTotalPages()) pageInfo.setCurrentPage(0);
        if (invalidPosNumber((long) pageInfo.getPageSize()) || pageInfo.getPageSize() > pageInfo.getTotalPages()) pageInfo.setPageSize(10);
        int halfRange = 2; // número de páginas antes y después de la página actual en la paginación.
        pageInfo.setStartPage(Math.max(0, pageInfo.getCurrentPage() - halfRange));
        pageInfo.setEndPage(Math.min(pageInfo.getTotalPages(), pageInfo.getCurrentPage() + halfRange));
        return pageInfo;
    }
}
