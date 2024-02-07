package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.dto.PropertyCriteriaRequest;
import com.baseProject.cafofo.entity.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class PropertyMinMaxSearchDao {

    private final EntityManager em;
    public List<Property> findAllByCriteria(PropertyCriteriaRequest propertyCriteriaRequest){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<Property> rootProperty = criteriaQuery.from(Property.class);

        predicates.add(criteriaBuilder.equal(rootProperty.get("approvalStatus"), true));

        if(propertyCriteriaRequest.getDealType() != null)
            predicates.add(criteriaBuilder.equal(rootProperty.get("dealType"), propertyCriteriaRequest.getDealType()));

        if(propertyCriteriaRequest.getMaxArea() != null)
            predicates.add(criteriaBuilder.lessThanOrEqualTo(rootProperty.get("area"), propertyCriteriaRequest.getMaxArea()));

        if(propertyCriteriaRequest.getMinArea() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootProperty.get("area"), propertyCriteriaRequest.getMinArea()));

        if(propertyCriteriaRequest.getMaxPrice() != null)
            predicates.add(criteriaBuilder.lessThanOrEqualTo(rootProperty.get("price"), propertyCriteriaRequest.getMaxPrice()));

        if(propertyCriteriaRequest.getMinPrice() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootProperty.get("price"), propertyCriteriaRequest.getMinPrice()));

        if(propertyCriteriaRequest.getNumBed() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootProperty.get("numberOfBed"), propertyCriteriaRequest.getNumBed()));

        if(propertyCriteriaRequest.getNumBath() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootProperty.get("numberOfBathRoom"), propertyCriteriaRequest.getNumBath()));

        if(propertyCriteriaRequest.getHomeType() != null)
            predicates.add(criteriaBuilder.equal(rootProperty.get("homeType"), propertyCriteriaRequest.getHomeType()));

        if(propertyCriteriaRequest.getFactAndFactory() != null)
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower( rootProperty.get("factAndFeatures")),
                    "%"+propertyCriteriaRequest.getFactAndFactory().toLowerCase()+"%"));

        criteriaQuery.where(criteriaBuilder.and((Predicate[]) predicates.toArray(new Predicate[0])));
        criteriaQuery.select(rootProperty).distinct(true);

        return em.createQuery(criteriaQuery).getResultList();
    }


}
