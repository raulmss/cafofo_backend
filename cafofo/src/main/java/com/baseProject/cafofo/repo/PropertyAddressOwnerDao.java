package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.dto.AddressOwnerPropertyCriterialRequest;
import com.baseProject.cafofo.dto.AddressPropertyCriterialRequest;
import com.baseProject.cafofo.entity.Address;
import com.baseProject.cafofo.entity.Owner;
import com.baseProject.cafofo.entity.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor

public class PropertyAddressOwnerDao {

    private final EntityManager em;
    public List<Property> findAllByCriteria(AddressOwnerPropertyCriterialRequest addressPropertyCriterialRequest){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<Property> rootProperty = criteriaQuery.from(Property.class);
        Root<Address> rootAddress = criteriaQuery.from(Address.class);
        Root<Owner> rootUser = criteriaQuery.from(Owner.class);

        Join<Property, Address> addressProperty = rootProperty.join("address", JoinType.INNER);

        if(addressPropertyCriterialRequest.getOwnerid() != null)
            predicates.add(criteriaBuilder.equal(rootUser.get("id"),addressPropertyCriterialRequest.getOwnerid()));

        if(addressPropertyCriterialRequest.getCountry() != null)
            predicates.add(criteriaBuilder.equal(addressProperty.get("country"), addressPropertyCriterialRequest.getCountry()));

        if(addressPropertyCriterialRequest.getState() != null)
            predicates.add(criteriaBuilder.equal(addressProperty.get("state"), addressPropertyCriterialRequest.getState()));

        if(addressPropertyCriterialRequest.getCity() != null)
            predicates.add(criteriaBuilder.equal(addressProperty.get("city"), addressPropertyCriterialRequest.getCity()));

        if(addressPropertyCriterialRequest.getZip() != null)
            predicates.add(criteriaBuilder.equal(addressProperty.get("zip"), addressPropertyCriterialRequest.getZip()));

        if(addressPropertyCriterialRequest.getNumber() != null)
            predicates.add(criteriaBuilder.like(addressProperty.get("number"), "%"+addressPropertyCriterialRequest.getNumber()+"%"));

        criteriaQuery.where(criteriaBuilder.and((Predicate[]) predicates.toArray(new Predicate[0])));
        criteriaQuery.select(rootProperty).distinct(true);

        return em.createQuery(criteriaQuery).getResultList();
    }


}
