package com.sm360.assignment.controllers;

/*import inc.kreo.elbilad.kpanel.models.Category;
import inc.kreo.elbilad.kpanel.payloads.CreateCategoryRequest;
import inc.kreo.elbilad.kpanel.payloads.UpdateCategoryRequest;
import inc.kreo.elbilad.kpanel.services.CategoryService;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sm360.assignment.model.ListingState;
import com.sm360.assignment.requests.ListingCreateRequest;
import com.sm360.assignment.requests.ListingUpdateRequest;
import com.sm360.assignment.responses.ListingCreateResponse;
import com.sm360.assignment.responses.ListingGetResponse;
import com.sm360.assignment.responses.ListingUpdateResponse;
import com.sm360.assignment.services.VehicleAdvertisementService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "api/v1/listings")
@RestController
public class VehicleAdvertisementController {

    private final VehicleAdvertisementService vehicleAdvertisementService;

    @Autowired
    public VehicleAdvertisementController(VehicleAdvertisementService vehicleAdvertisementService) {
        this.vehicleAdvertisementService = vehicleAdvertisementService;
    }

    @GetMapping
    ResponseEntity<List<ListingGetResponse>> getListing(@RequestParam Long dealerId, @RequestParam ListingState state){
        List<ListingGetResponse> response = vehicleAdvertisementService.getListing(dealerId, state);
        return ResponseEntity.ok(response);
    }

    @PostMapping
	public ResponseEntity<ListingCreateResponse> createListing(@RequestBody @Valid ListingCreateRequest request)  {
		//log.info("VehicleAdvertisementController#saveListing start");
		ListingCreateResponse response = vehicleAdvertisementService.createListing(request);
		//log.info("VehicleAdvertisementController#saveListing end");
		return ResponseEntity.ok(response);
    }

    @PutMapping(path = "{listingId}")
	public ResponseEntity<ListingUpdateResponse> updateListing(@PathVariable("listingId") Long id, @RequestBody @Valid ListingUpdateRequest request)  {
		//log.info("VehicleAdvertisementController#saveListing start");
		ListingUpdateResponse response = vehicleAdvertisementService.updateListing(id, request);
		//log.info("VehicleAdvertisementController#saveListing end");
		return ResponseEntity.ok(response);
    }
	
	/*@Operation(summary = "Update a listing, you must enter a valid id in order to update.")
	@PutMapping("listing")
	public ResponseEntity<ListingUpdateResponse> updateListing(@RequestBody @Valid final ListingUpdateRequest request)  {
		log.info("VehicleAdvertisementController#updateListing start");
		val response = vehicleAdvertisementService.updateListing(request);
		log.info("VehicleAdvertisementController#updateListing end");
		return ResponseEntity.ok(response);
    }
	
	@Operation(summary = "Get a listing by dealerId and state.")
	@GetMapping("listing")
	public ResponseEntity<List<ListingGetResponse>> getListing(@RequestParam final UUID dealerId, @RequestParam final ListingState state)  {
		log.info("VehicleAdvertisementController#getListing start");
		val response = vehicleAdvertisementService.getListing(dealerId, state);
		log.info("VehicleAdvertisementController#getListing end");
		return ResponseEntity.ok(response);
    }*/
	

    /*@GetMapping(path = "{categoryId}")
    Optional<Category> getCategories(@PathVariable("categoryId") Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping(path = "{categoryId}")
    Optional<Category> updateCategory(@PathVariable("categoryId") Long id, @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return categoryService.updateCategory(id,updateCategoryRequest);
    }

    @DeleteMapping(path = "{categoryId}")
    void deleteCategory(@PathVariable("categoryId") Long id) {
        categoryService.deleteCategory(id);
    }*/
}
