package com.baseProject.cafofo.helper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.hibernate.collection.spi.PersistentBag;

@Component
public class ListMapper<T,E> {
    @Autowired
    ModelMapper modelMapper;

    public Collection<?> mapList(Collection<T> list, E convertTo){
        return list.stream()
                .map(e-> modelMapper.map(e,convertTo.getClass()))
                .collect(Collectors.toList());
    }
}
