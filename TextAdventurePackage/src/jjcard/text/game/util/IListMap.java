package jjcard.text.game.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
/**
 * Interface for having map with multiple objects per key. with default methods for such.
 *
 * @param <K>
 * @param <V>
 */
public interface IListMap<K, V> extends Map<K, List<V>> {
	/**
	 * Add Item to given key. By default uses {@link LinkedList} for new lists.
	 * @param key
	 * @param value
	 * @return List for key
	 */
	public default List<V> add(K key, V value){
		List<V> list = get(key);
		if (list == null){
			list = new LinkedList<V>();
			put(key, list);
		}
		list.add(value);
		return list;
	}	
	/**
	 * Add Item to given key. If not found, uses given supplier.
	 * @param key
	 * @param value
	 * @param listSupplier
	 * @return List for key
	 */
	public default List<V> add(K key, V value, Supplier<List<V>> listSupplier){
		List<V> list = get(key);
		if (list == null){
			list = listSupplier.get();
			put(key, list);
		}
		list.add(value);
		return list;
	}
	/**
	 * Adds all given values to underlying list. By default uses {@link LinkedList} for new lists.
	 * @param key
	 * @param values
	 * @return List for key
	 */
	public default List<V> addAll(K key, List<V> values) {
		List<V> list = get(key);
		if (list == null) {
			list = new LinkedList<>();
			put(key, list);
		}
		list.addAll(values);
		
		return list;
	}
	/**
	 * Adds all given values to underlying list. If no list found, uses given supplier.
	 * @param key
	 * @param values
	 * @return List for key
	 */
	public default List<V> addAll(K key, List<V> values, Supplier<List<V>> listSupplier) {
		List<V> list = get(key);
		if (list == null) {
			list = listSupplier.get();
			put(key, list);
		}
		list.addAll(values);
		
		return list;
	}
	/**
	 * Return size of list for given key, or 0 if no list exists.
	 * @param key
	 * @return
	 */
	public default int getListSize(K key){
		List<V> list = get(key);
		return list == null? 0: list.size();
	}
	/**
	 * If Map contains a list for key, return it, otherwise generates new List, adds it to the map and returns it. By default uses {@link LinkedList} for new lists.
	 * @param key
	 * @return List from map, existing or new.
	 */
	public default List<V> getOrNew(K key){
		List<V> list = get(key);
		if (list == null){
			list = new LinkedList<V>();
			put(key, list);
		}
		return list;
	}
	/**
	 * If Map contains a list for key, return it, otherwise generates new List, adds it to the map and returns it. Uses supplier for new list.
	 * @param key
	 * @param listSupplier
	 * @return
	 */
	public default List<V> getOrNew(K key, Supplier<List<V>> listSupplier){
		List<V> list = get(key);
		if (list == null){
			list = listSupplier.get();
			put(key, list);
		}
		return list;
	}
	public static class ListHashMap<K, V> extends HashMap<K, List<V>> implements IListMap<K, V>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702963975522014415L;
		
	}
	public static class ListTreeMap<K, V> extends TreeMap<K, List<V>>  implements IListMap<K, V>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8071210173902756898L;
		
	}
	public static <K, V> ListHashMap<K, V> newListHashMap(){
		return new ListHashMap<>();
	}
	public static <K, V> ListTreeMap<K, V> newListTreeMap(){
		return new ListTreeMap<>();
	}
}
