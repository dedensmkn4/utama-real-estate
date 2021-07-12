package com.utama.deden.reza.libraries.utility;

import java.util.List;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;

public class BeanMapperHelper {
  private static final MapperFactory mapperFactory = (new Builder()).build();
  private static final MapperFacade mapper;

  public BeanMapperHelper() {
  }

  public static <S, C> C map(S source, Class<C> clazz) {
    return source == null ? null : mapper.map(source, clazz);
  }

  public static <S, C> List<C> mapAsList(Iterable<S> source, Class<C> clazz) {
    return source == null ? null : mapper.mapAsList(source, clazz);
  }

  static {
    mapper = mapperFactory.getMapperFacade();
  }
}