package org.mokey.mapping.dal;

import java.util.List;

import org.mokey.mapping.tuples.Tuple;

public interface DataProvider {
	/**
	 * Get the target candidate collection, according to the n tuples which has greatest weight.
	 * @param res
	 * 		The input tuples
	 * @return
	 * 		The target candidate collection.
	 */
	List<List<Tuple<?>>> getTargetCollection(List<Tuple<?>> res);
}
