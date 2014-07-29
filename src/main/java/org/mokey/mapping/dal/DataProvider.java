package org.mokey.mapping.dal;

import java.util.List;

import org.mokey.mapping.tuples.Tuple;

public interface DataProvider {
	List<List<Tuple<?>>> getTargetCollection(List<Tuple<?>> res);
}
