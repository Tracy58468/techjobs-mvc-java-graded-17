package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.JobSheets;
import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobsmvc.models.JobData.findAll;
import static org.launchcode.techjobsmvc.models.JobData.findByColumnAndValue;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    /** Renders form defined by search.html **/
    @GetMapping(value = "")
    public String search (Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results")
    public String displaySearchResults (Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        ArrayList<Job> jobs; // instantiates "jobs"

        if (searchTerm.equals("") || searchTerm.equals("all")) {

            jobs = findAll();
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);

        } else {

            jobs = findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchTerm", searchTerm);
            model.addAttribute("columns", columnChoices);

        }

        return "search";

    }

}

// if (column.equals("all")){
//         jobs = JobData.findAll();
//         model.addAttribute("title", "All Jobs");
//         }

/** Add a displaySearchResults handler method to SearchController:

 DONE 1. Use the correct annotation for the method. To configure the correct mapping type and mapping route, refer to the form tag in the search.html template. (Use @GetMapping or @PostMapping rather than @RequestMapping.)

 DONE 2. The displaySearchResults method should take in a Model parameter.

 DONE 3. The method should also take in two other parameters, specifying the type of search and the search term.

 DONE 4. In order for these last two parameters to be properly passed in by Spring Boot, you need to use the correct annotation. Also, you need to name them appropriately, based on the corresponding form field names defined in search.html.

 5. If the user enters “all” in the search box, or if they leave the box empty, call the findAll() method from JobData. Otherwise, send the search information to findByColumnAndValue. In either case, store the results in a jobs ArrayList.

 **/