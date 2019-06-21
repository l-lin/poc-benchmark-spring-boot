package lin.louis.poc.benchmark;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cats", path = "cats")
public interface CatRepository extends PagingAndSortingRepository<Cat, Long> {
}
