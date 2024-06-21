package com.carteleradaw.springboot.web.app.utils;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static com.carteleradaw.springboot.web.app.utils.Utils.invalidPosNumber;

/**
 * Componente para información de páginas.
 */
@Getter
@Setter
@NoArgsConstructor
@Component
public class PageInfo {

    private int totalPages;
    private int currentPage;
    private int pageSize;
    private int startPage;  // A mostrar en la navegación de la paginación.
    private int endPage;    // A mostrar en la navegación de la paginación.
    private int halfRange = 2; // Número de páginas antes y después de la página actual en la paginación.
    private int startElement;
    private int endElement;
    private int numberOfElements;
    private long totalElements;

    /**
     * Trata, compendia y devuelve la información relativa a la paginación de un Page dado.
     * @param page Page de la que obtener los datos.
     * @return El compendio de información sobre la paginación solicitada.
     */
    public PageInfo createFromPage(Page<?> page) {
        setTotalElements(page.getTotalElements());
        setNumberOfElements(page.getNumberOfElements());
        setStartElement(page.getNumber() * page.getSize() + 1);
        setEndElement(startElement + getNumberOfElements() - 1);
        setTotalPages(page.getTotalPages());
        setCurrentPage(page.getNumber());
        setPageSize(page.getSize());
        if (invalidPosNumber((long) getCurrentPage()) || getCurrentPage() < 0 || getCurrentPage() > getTotalPages()) setCurrentPage(0);
        if (invalidPosNumber((long) getPageSize()) || getPageSize() > getTotalPages()) setPageSize(10);
        setStartPage(Math.max(0, getCurrentPage() - halfRange));
        setEndPage(Math.min(getTotalPages(), getCurrentPage() + halfRange));
        return this;
    }
}
