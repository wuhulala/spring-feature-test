package com.wuhulala.springboot.chapter1.i18n.support;

import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class MapSupport<K, V> {

	private Map<K, V> map;
	
	public MapSupport() {
		this.map = new HashMap<K, V>();
	}

	public MapSupport(Map<K, V> map) {
		this.map = map;
	}

	/**
	 * 获取元素
	 * 
	 * @param key
	 * @return
	 */
	final public V get(K key) {
		return this.map.get(key);
	}

	/**
	 * 添加元素
	 * 
	 * @param key
	 * @param value
	 */
	final public void put(K key, V value) {
		this.map.put(key, value);
	}

	/**
	 * 合并元素
	 * 
	 * @param map
	 */
	final public void putAll(Map<K, V> map) {
		if (MapUtils.isNotEmpty(map)) {
			this.map.putAll(map);
		}
	}

	/**
	 * 判断是否包含键
	 * 
	 * @param key
	 * @return
	 */
	final public boolean containsKey(K key) {
		return this.map.containsKey(key);
	}

	/**
	 * 删除指定键值对
	 * 
	 * @param key
	 * @return
	 */
	final public V remove(K key) {
		return this.map.remove(key);
	}

	/**
	 * 获取键集合
	 * 
	 * @return
	 */
	final public Set<K> keySet() {
		return Collections.unmodifiableSet(this.map.keySet());
	}

	/**
	 * 获取值集合
	 * 
	 * @return
	 */
	final public Collection<V> values() {
		return Collections.unmodifiableCollection(this.map.values());
	}

	/**
	 * 清空元素
	 */
	final public void clear() {
		this.map.clear();
	}

	public void setMap(Map<K, V> map) {
		this.map = map;
	}
	
	public Map<K, V> getMap(){
		return this.map;
	}

}