package org.jhipster.repository.search;

import org.jhipster.domain.SubCategory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SubCategory entity.
 */
public interface SubCategorySearchRepository extends ElasticsearchRepository<SubCategory, Long> {
}
