package com.fabrizioquispe.system_inventory.domain.helper;

import org.springframework.stereotype.Component;

import com.fabrizioquispe.system_inventory.api.dto.SuscriptionFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.SuscriptionEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MatchesFilterHelper {
    public boolean matchesFilter(SuscriptionEntity s, SuscriptionFilterDTO filter) {
        boolean matches = true;

        if (filter.getIdSuscription() != null) {
            matches &= filter.getIdSuscription().equals(s.getIdSuscription());
        }
        if (filter.getTypeSuscription() != null) {
            matches &= filter.getTypeSuscription().equalsIgnoreCase(s.getTypeSuscription());
        }
        if (filter.getPrice() != null) {
            matches &= filter.getPrice().compareTo(s.getPrice()) == 0;
        }
        if (filter.getDurationDays() != null) {
            matches &= filter.getDurationDays().equals(s.getDurationDays());
        }
        if (filter.getStatus() != null) {
            matches &= filter.getStatus().equals(s.getStatus());
        }
        if (filter.getCreationAt() != null) {
            matches &= filter.getCreationAt().equals(s.getCreationAt());
        }

        return matches;
    }

}
