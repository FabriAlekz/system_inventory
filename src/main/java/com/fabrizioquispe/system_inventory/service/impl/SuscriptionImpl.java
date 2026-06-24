package com.fabrizioquispe.system_inventory.service.impl;

import org.springframework.stereotype.Service;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.api.dto.SuscriptionFilterDTO;
import com.fabrizioquispe.system_inventory.domain.entity.SuscriptionEntity;
import com.fabrizioquispe.system_inventory.domain.helper.MatchesFilterHelper;
import com.fabrizioquispe.system_inventory.domain.helper.SuscriptionHelper;
import com.fabrizioquispe.system_inventory.infraestructure.SuscriptionRepository;
import com.fabrizioquispe.system_inventory.service.SuscriptionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SuscriptionImpl implements SuscriptionService {

    private final SuscriptionRepository suscriptionRepository;
    private final SuscriptionHelper suscriptionHelper;
    private final MatchesFilterHelper matchesFilterHelper;

    @Override
    public Flux<SuscriptionEntity> findAllSuscriptions() {
        return suscriptionRepository.findAll();
    }

    @Override
    public Flux<SuscriptionEntity> findAllSuscriptionFilter(SuscriptionFilterDTO filter) {
        return suscriptionRepository.findAll()
                .filter(s -> matchesFilterHelper.matchesFilter(s, filter));
    }

    @Override
    public Mono<ResponseHandler<SuscriptionEntity>> createSuscription(SuscriptionEntity suscriptionEntity) {
        return suscriptionHelper.validateSuscription(suscriptionEntity)
                .flatMap(validationResult -> {
                    if (validationResult.getStatus() != 200) {
                        return Mono.just(validationResult);
                    }
                    return suscriptionRepository.save(validationResult.getData())
                            .map(saved -> suscriptionHelper.buildSuccessResponse(saved))
                            .onErrorResume(error -> Mono.just(suscriptionHelper.buildErrorResponse(500,
                                    "Internal error while saving subscription")));
                });
    }

    @Override
    public Mono<ResponseHandler<SuscriptionEntity>> updateSuscription(Integer idSuscription,
            SuscriptionEntity suscriptionEntity) {
        return suscriptionRepository.findById(idSuscription)
                .flatMap(existing -> {
                    existing.setTypeSuscription(suscriptionEntity.getTypeSuscription());
                    existing.setPrice(suscriptionEntity.getPrice());
                    existing.setDurationDays(suscriptionEntity.getDurationDays());
                    existing.setStatus(suscriptionEntity.getStatus());
                    return suscriptionHelper.validateSuscription(existing);
                })
                .flatMap(validationResult -> {
                    if (validationResult.getStatus() != 200) {
                        return Mono.just(validationResult);
                    }
                    return suscriptionRepository.save(validationResult.getData())
                            .map(saved -> suscriptionHelper.buildSuccessResponse(saved))
                            .onErrorResume(error -> Mono.just(suscriptionHelper.buildErrorResponse(500,
                                    "Internal error while updating subscription")));
                })
                .defaultIfEmpty(suscriptionHelper.buildErrorResponse(404,
                        "Subscription with id " + idSuscription + " not found"));
    }

    @Override
    public Mono<ResponseHandler<SuscriptionEntity>> deleteSuscription(Integer idSuscription) {
        return suscriptionRepository.findById(idSuscription)
                .flatMap(existing -> {
                    existing.setStatus(0);
                    return suscriptionRepository.save(existing)
                            .map(saved -> suscriptionHelper.buildSuccessResponse(saved))
                            .onErrorResume(error -> Mono.just(suscriptionHelper.buildErrorResponse(500,
                                    "Internal error while deactivating subscription")));
                })
                .defaultIfEmpty(suscriptionHelper.buildErrorResponse(404,
                        "Subscription with id " + idSuscription + " not found"));
    }

}
