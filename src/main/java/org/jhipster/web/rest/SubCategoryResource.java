package org.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.domain.SubCategory;

import org.jhipster.repository.SubCategoryRepository;
import org.jhipster.repository.search.SubCategorySearchRepository;
import org.jhipster.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing SubCategory.
 */
@RestController
@RequestMapping("/api")
public class SubCategoryResource {

    private final Logger log = LoggerFactory.getLogger(SubCategoryResource.class);

    private static final String ENTITY_NAME = "subCategory";

    private final SubCategoryRepository subCategoryRepository;

    private final SubCategorySearchRepository subCategorySearchRepository;

    public SubCategoryResource(SubCategoryRepository subCategoryRepository, SubCategorySearchRepository subCategorySearchRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.subCategorySearchRepository = subCategorySearchRepository;
    }

    /**
     * POST  /sub-categories : Create a new subCategory.
     *
     * @param subCategory the subCategory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subCategory, or with status 400 (Bad Request) if the subCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sub-categories")
    @Timed
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory) throws URISyntaxException {
        log.debug("REST request to save SubCategory : {}", subCategory);
        if (subCategory.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new subCategory cannot already have an ID")).body(null);
        }
        SubCategory result = subCategoryRepository.save(subCategory);
        subCategorySearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sub-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-categories : Updates an existing subCategory.
     *
     * @param subCategory the subCategory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subCategory,
     * or with status 400 (Bad Request) if the subCategory is not valid,
     * or with status 500 (Internal Server Error) if the subCategory couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sub-categories")
    @Timed
    public ResponseEntity<SubCategory> updateSubCategory(@RequestBody SubCategory subCategory) throws URISyntaxException {
        log.debug("REST request to update SubCategory : {}", subCategory);
        if (subCategory.getId() == null) {
            return createSubCategory(subCategory);
        }
        SubCategory result = subCategoryRepository.save(subCategory);
        subCategorySearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subCategory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sub-categories : get all the subCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of subCategories in body
     */
    @GetMapping("/sub-categories")
    @Timed
    public List<SubCategory> getAllSubCategories() {
        log.debug("REST request to get all SubCategories");
        return subCategoryRepository.findAll();
        }

    /**
     * GET  /sub-categories/:id : get the "id" subCategory.
     *
     * @param id the id of the subCategory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subCategory, or with status 404 (Not Found)
     */
    @GetMapping("/sub-categories/{id}")
    @Timed
    public ResponseEntity<SubCategory> getSubCategory(@PathVariable Long id) {
        log.debug("REST request to get SubCategory : {}", id);
        SubCategory subCategory = subCategoryRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(subCategory));
    }

    /**
     * DELETE  /sub-categories/:id : delete the "id" subCategory.
     *
     * @param id the id of the subCategory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sub-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        log.debug("REST request to delete SubCategory : {}", id);
        subCategoryRepository.delete(id);
        subCategorySearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sub-categories?query=:query : search for the subCategory corresponding
     * to the query.
     *
     * @param query the query of the subCategory search
     * @return the result of the search
     */
    @GetMapping("/_search/sub-categories")
    @Timed
    public List<SubCategory> searchSubCategories(@RequestParam String query) {
        log.debug("REST request to search SubCategories for query {}", query);
        return StreamSupport
            .stream(subCategorySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
