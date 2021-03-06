package lt.javainiai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lt.javainiai.model.PollingDistrictEntity;
import lt.javainiai.service.PollingDistrictService;
import lt.javainiai.utils.DistrictVotersActivity;

@RestController
@RequestMapping("/polling-districts/")
public class PollingDistrictController {

    private PollingDistrictService pollingDistrictService;

    @Autowired
    public PollingDistrictController(PollingDistrictService pollingDistrictService) {
        this.pollingDistrictService = pollingDistrictService;
    }

    // Register or update
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PollingDistrictEntity saveOrUpdate(@Valid @RequestBody PollingDistrictEntity pollingDistrict) {
        return pollingDistrictService.saveOrUpdate(pollingDistrict);
    }

    // Find all
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PollingDistrictEntity> findAll() {
        return pollingDistrictService.findAll();
    }

    // Find one
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PollingDistrictEntity findById(@Valid @PathVariable("id") Long id) {
        return pollingDistrictService.findById(id);
    }

    // Delete one
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Valid @PathVariable("id") Long id) {
        pollingDistrictService.deleteById(id);
    }

    // Voters activity (3 following methods)
    @RequestMapping(value = "activity/{districtId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Long getVotersActivityInUnitsInDistrict(@Valid @PathVariable("districtId") Long districtId) {
        return pollingDistrictService.getVotersActivityInUnitsInDistrict(districtId);
    }

    @RequestMapping(value = "activity-percent/{districtId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Double getVotersActivityInPercentInDistrict(@Valid @PathVariable("districtId") Long districtId) {
        return pollingDistrictService.getVotersActivityInPercentInDistrict(districtId);
    }

    @RequestMapping(value = "activity/all/{constituencyId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictVotersActivity> getVotersActivityInAllDistrictsOfConstituency(
            @Valid @PathVariable("constituencyId") Long constituencyId) {
        return pollingDistrictService.getVotersActivityInAllDistrictsOfConstituency(constituencyId);
    }

    @RequestMapping(value = "single-spoiled-ballots/{districtId}", method = RequestMethod.POST)
    public PollingDistrictEntity postSingleSpoiledBallots(@Valid @PathVariable("districtId") Long districtId,
    		@RequestParam(value = "single") Long single) {
        return pollingDistrictService.postSingleSpoiledBallots(districtId, single);
    }
    
    @RequestMapping(value = "multi-spoiled-ballots/{districtId}", method = RequestMethod.POST)
    public PollingDistrictEntity postMultiSpoiledBallots(@Valid @PathVariable("districtId") Long districtId,
    		@RequestParam(value = "multi") Long multi) {
        return pollingDistrictService.postMultiSpoiledBallots(districtId, multi);
    }

    // Get all districts that did not submit results
    @RequestMapping(value = "no-single-results", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PollingDistrictEntity> getDistrictsNotSubmittedSingleResults() {
        return pollingDistrictService.getDistrictsNotSubmittedSingleResults();
    }

    @RequestMapping(value = "no-multi-results", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PollingDistrictEntity> getDistrictsNotSubmittedMultiResults() {
        return pollingDistrictService.getDistrictsNotSubmittedMultiResults();
    }

}