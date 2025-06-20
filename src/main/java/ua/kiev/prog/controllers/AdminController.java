package ua.kiev.prog.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.prog.dto.LocationDTO;
import ua.kiev.prog.dto.PageCountDTO;
import ua.kiev.prog.services.LocationService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final int PAGE_SIZE = 5;

    private final LocationService locationService;

    public AdminController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("count") // /admin/count
    public PageCountDTO count(@RequestParam(required = false) String city) {
        return PageCountDTO.of(locationService.count(city), PAGE_SIZE);
    }

    @GetMapping("geo") // /admin/geo
    public List<LocationDTO> locations(@RequestParam(required = false)String city,
                                       @RequestParam(defaultValue = "0") int page) {
        return locationService.getLocations(
                city,
                PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "id"));
    }

//    тестовые IP

    @GetMapping("/test")
    public void testInsert() {
        LocationDTO dto = new LocationDTO();
        dto.setIp("8.8.8.8");
        dto.setCity("Lviv");
        dto.setRegion("Lvivska");
        dto.setCountry("Ukraine");
        locationService.save(dto);
    }

    @GetMapping("/test2")
    public void testInsertTwo() {
        LocationDTO dto = new LocationDTO();
        dto.setIp("9.9.9.9");
        dto.setCity("Wroclaw");
        dto.setRegion("Dolnoslaskie");
        dto.setCountry("Polska");
        locationService.save(dto);
    }

}
