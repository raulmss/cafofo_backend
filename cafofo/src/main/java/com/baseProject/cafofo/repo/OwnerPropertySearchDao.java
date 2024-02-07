package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.dto.OwnerPropertyCriteriaRequest;
import com.baseProject.cafofo.dto.PropertyCriteriaRequest;
import com.baseProject.cafofo.entity.Owner;
import com.baseProject.cafofo.entity.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import com.baseProject.cafofo.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;


import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class OwnerPropertySearchDao {
//    public List<Property> findAllByCriteria(OwnerPropertyCriteriaRequest ownerPropertyCriteriaRequest){
//        return null;
//    }
    private final EntityManager em;
    public List<Property> findAllByCriteria(OwnerPropertyCriteriaRequest ownerPropertyCriteriaRequest){ // You can make a search request object for the input
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<Property> rootProperty = criteriaQuery.from(Property.class);
        Root<Owner> rootOwner = criteriaQuery.from(Owner.class);
        Root<User> rootUser = criteriaQuery.from(User.class);

        Join<Owner, Property> ownerProperty = rootOwner.join("properties", JoinType.INNER);

        if(ownerPropertyCriteriaRequest.getOwnerId() != null)
            predicates.add(criteriaBuilder.equal(rootUser.get("id"),ownerPropertyCriteriaRequest.getOwnerId()));

        if(ownerPropertyCriteriaRequest.getDealType() != null)
            predicates.add(criteriaBuilder.equal(rootProperty.get("dealType"), ownerPropertyCriteriaRequest.getDealType()));

        if(ownerPropertyCriteriaRequest.getMaxArea() != null)
            predicates.add(criteriaBuilder.lessThanOrEqualTo(rootProperty.get("area"), ownerPropertyCriteriaRequest.getMaxArea()));

        if(ownerPropertyCriteriaRequest.getMinArea() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootProperty.get("area"), ownerPropertyCriteriaRequest.getMinArea()));

        if(ownerPropertyCriteriaRequest.getMaxPrice() != null)
            predicates.add(criteriaBuilder.lessThanOrEqualTo(rootProperty.get("price"), ownerPropertyCriteriaRequest.getMaxPrice()));

        if(ownerPropertyCriteriaRequest.getMinPrice() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootProperty.get("price"), ownerPropertyCriteriaRequest.getMinPrice()));

        if(ownerPropertyCriteriaRequest.getNumBed() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootProperty.get("numberOfBed"), ownerPropertyCriteriaRequest.getNumBed()));

        if(ownerPropertyCriteriaRequest.getNumBath() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootProperty.get("numberOfBathRoom"), ownerPropertyCriteriaRequest.getNumBath()));

        if(ownerPropertyCriteriaRequest.getHomeType() != null)
            predicates.add(criteriaBuilder.equal(rootProperty.get("homeType"), ownerPropertyCriteriaRequest.getHomeType()));

        if(ownerPropertyCriteriaRequest.getFactAndFactory() != null)
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower( rootProperty.get("factAndFeatures")), "%"+ownerPropertyCriteriaRequest.getFactAndFactory().toLowerCase()+"%"));

        criteriaQuery.where(criteriaBuilder.and((Predicate[]) predicates.toArray(new Predicate[0])));
        criteriaQuery.select(rootProperty).distinct(true);

        return em.createQuery(criteriaQuery).getResultList();
    }


}
