package com.unikom.partnerservice.service;

import com.unikom.partnerservice.dto.PartnerDTO;
import com.unikom.partnerservice.dto.request.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPartnerService {

    PartnerDTO save(PartnerDTO partnerDTO);
    PartnerDTO delete(Long id);
    Page<PartnerDTO> search(Search search, Pageable pageable);
    PartnerDTO findById(Long id);
    PartnerDTO update(Long id, PartnerDTO partnerDTO);
    long count(Search search);

}
