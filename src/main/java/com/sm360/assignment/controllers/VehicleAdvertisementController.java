package com.sm360.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sm360.assignment.model.Listing;
import com.sm360.assignment.model.ListingState;
import com.sm360.assignment.requests.ListingCreateRequest;
import com.sm360.assignment.requests.ListingPublishRequest;
import com.sm360.assignment.requests.ListingUpdateRequest;
import com.sm360.assignment.responses.JsonResponse;
import com.sm360.assignment.responses.ListingCreateResponse;
import com.sm360.assignment.responses.ListingGetResponse;
import com.sm360.assignment.responses.ListingUpdateResponse;
import com.sm360.assignment.services.VehicleAdvertisementService;

import jakarta.validation.Valid;

import java.util.List;

@RequestMapping(path = "api/v1/listings")
@RestController
public class VehicleAdvertisementController {

    private final VehicleAdvertisementService vehicleAdvertisementService;

    @Autowired
    public VehicleAdvertisementController(VehicleAdvertisementService vehicleAdvertisementService) {
        this.vehicleAdvertisementService = vehicleAdvertisementService;
    }

    @GetMapping
    ResponseEntity<JsonResponse> getListing(@RequestParam Long dealerId, @RequestParam ListingState state){
        List<ListingGetResponse> response = vehicleAdvertisementService.getListing(dealerId, state);
        return ResponseEntity.ok(
            JsonResponse.builder().data(response)
            .status(HttpStatus.OK.getReasonPhrase())
            .statusCode(HttpStatus.OK.value())
            .data(response)
            .build()
        );
    }

    @PostMapping
	public ResponseEntity<JsonResponse> createListing(@RequestBody @Valid ListingCreateRequest request)  {
		ListingCreateResponse response = vehicleAdvertisementService.createListing(request);
        return ResponseEntity.ok(
            JsonResponse.builder().data(response)
            .status(HttpStatus.CREATED.getReasonPhrase())
            .statusCode(HttpStatus.CREATED.value())
            .data(response)
            .build()
        );
    }

    @PutMapping(path = "{listingId}")
	public ResponseEntity<JsonResponse> updateListing(@PathVariable("listingId") Long id, @RequestBody @Valid ListingUpdateRequest request)  {
		ListingUpdateResponse response = vehicleAdvertisementService.updateListing(id, request);
        return ResponseEntity.ok(
            JsonResponse.builder().data(response)
            .status(HttpStatus.OK.getReasonPhrase())
            .statusCode(HttpStatus.OK.value())
            .data(response)
            .build()
        );
    }

    @PutMapping("{listingId}/publish")
	public ResponseEntity<JsonResponse> publishListing(@PathVariable("listingId") Long id,@RequestBody @Valid ListingPublishRequest request)  {
		Listing listing = vehicleAdvertisementService.publishListing(id, request);
        return ResponseEntity.ok(
            JsonResponse.builder().data(listing)
            .status(HttpStatus.OK.getReasonPhrase())
            .statusCode(HttpStatus.OK.value())
            .data(listing)
            .message("Listing is published")
            .build()
        );
    }
    
	@PutMapping("{listingId}/unpublish")
	public ResponseEntity<JsonResponse> unpublishListing(@PathVariable("listingId") Long id)  {
		Listing listing = vehicleAdvertisementService.unpublishListing(id);
        return ResponseEntity.ok(
            JsonResponse.builder().data(listing)
            .status(HttpStatus.OK.getReasonPhrase())
            .statusCode(HttpStatus.OK.value())
            .data(listing)
            .message("Listing is unpublished")
            .build()
        );
    }
}
