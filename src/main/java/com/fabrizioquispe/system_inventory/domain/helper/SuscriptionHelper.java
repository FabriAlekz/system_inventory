package com.fabrizioquispe.system_inventory.domain.helper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.fabrizioquispe.system_inventory.api.dto.ResponseHandler;
import com.fabrizioquispe.system_inventory.domain.entity.SucursalesEntity;
import com.fabrizioquispe.system_inventory.domain.entity.SuscriptionEntity;
import com.fabrizioquispe.system_inventory.infraestructure.SucursalRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SuscriptionHelper {

    public static final Integer DURATION_SUSCRIPTION = 30;
    public static final Integer DURATION_DEMO = 15;

    public final SucursalRepository sucursalRepository;

    /**
     * Calculates the end date of a subscription cycle
     * based on its start date and duration in days.
     */
    public LocalDateTime calculateEndDate(LocalDateTime startDate, Integer durationDays) {
        if (startDate == null || durationDays == null) {
            throw new IllegalArgumentException("startDate and durationDays must not be null");
        }
        return startDate.plusDays(durationDays);
    }

    /**
     * Convenience overload using the standard 30-day duration.
     */
    public LocalDateTime calculateStandardEndDate(LocalDateTime startDate) {
        return calculateEndDate(startDate, DURATION_SUSCRIPTION);
    }

    /**
     * Convenience overload using the 15-day demo duration.
     */
    public LocalDateTime calculateDemoEndDate(LocalDateTime startDate) {
        return calculateEndDate(startDate, DURATION_DEMO);
    }

    /**
     * Checks if a subscription is still active right now.
     */
    public boolean isActive(LocalDateTime endDate) {
        if (endDate == null) {
            return false; // no subscription assigned = not active
        }
        return LocalDateTime.now().isBefore(endDate);
    }

    /**
     * Days remaining until the subscription expires.
     * Returns 0 if already expired or null.
     */
    public long daysRemaining(LocalDateTime endDate) {
        if (endDate == null || !isActive(endDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(LocalDateTime.now(), endDate);
    }

    /**
     * Fetches a branch by id and checks if its subscription is currently active.
     * Reactive, since it goes through the repository.
     */
    public Mono<Boolean> isSucursalSuscriptionActive(Integer idSucursal) {
        return sucursalRepository.findById(idSucursal)
                .map(sucursal -> isActive(sucursal.getCreatedFinished()))
                .defaultIfEmpty(false);
    }

    /**
     * Fetches a branch and returns the full info needed for an access check:
     * active status + days remaining.
     */
    public Mono<SuscriptionStatus> getSuscriptionStatus(Integer idSucursal) {
        return sucursalRepository.findById(idSucursal)
                .map(this::buildStatus)
                .defaultIfEmpty(SuscriptionStatus.inactive());
    }

    public SuscriptionStatus buildStatus(SucursalesEntity sucursal) {
        boolean active = isActive(sucursal.getCreatedFinished());
        long remaining = daysRemaining(sucursal.getCreatedFinished());
        return new SuscriptionStatus(active, remaining, sucursal.getCreatedFinished());
    }

    /**
     * Small value object to return active status + remaining days together,
     * useful for frontend banners like "Your demo expires in 3 days".
     */
    public record SuscriptionStatus(boolean active, long daysRemaining, LocalDateTime expiresAt) {
        public static SuscriptionStatus inactive() {
            return new SuscriptionStatus(false, 0, null);
        }
    }

    public Mono<ResponseHandler<SuscriptionEntity>> validateSuscription(SuscriptionEntity suscription) {
        if (suscription.getTypeSuscription() == null || suscription.getTypeSuscription().isBlank()) {
            return Mono.just(buildError(400, "typeSuscription is required"));
        }
        if (suscription.getPrice() == null || suscription.getPrice().signum() < 0) {
            return Mono.just(buildError(400, "price must be a non-negative value"));
        }
        if (suscription.getDurationDays() == null || suscription.getDurationDays() <= 0) {
            return Mono.just(buildError(400, "durationDays must be greater than zero"));
        }

        if (suscription.getStatus() == null) {
            suscription.setStatus(1); // default to active if not provided
        }
        if (suscription.getCreationAt() == null) {
            suscription.setCreationAt(LocalDateTime.now());
        }

        return Mono.just(buildSuccess(suscription));
    }

    public ResponseHandler<SuscriptionEntity> buildError(int status, String message) {
        ResponseHandler<SuscriptionEntity> response = new ResponseHandler<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status);
        response.setData(null);
        response.setMessage(message);
        return response;
    }

    public ResponseHandler<SuscriptionEntity> buildSuccess(SuscriptionEntity suscription) {
        ResponseHandler<SuscriptionEntity> response = new ResponseHandler<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(200);
        response.setData(suscription);
        response.setMessage("Validation passed");
        return response;
    }

    public ResponseHandler<SuscriptionEntity> buildSuccessResponse(SuscriptionEntity saved) {
        ResponseHandler<SuscriptionEntity> response = new ResponseHandler<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(201);
        response.setData(saved);
        response.setMessage("Suscripción creada exitosamente");
        return response;
    }

    public ResponseHandler<SuscriptionEntity> buildErrorResponse(int status, String message) {
        ResponseHandler<SuscriptionEntity> response = new ResponseHandler<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status);
        response.setData(null);
        response.setMessage(message);
        return response;
    }

}