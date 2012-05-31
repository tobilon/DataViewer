package com.util.tools;

import java.io.Serializable;  
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Set;  
import java.util.concurrent.atomic.AtomicInteger;  
import java.util.concurrent.atomic.AtomicLong;  
import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReentrantLock;  
  
/** 
 *  
 * @author dennis  
 * ��˵������������Ŀ����ʱ�����û�������Ĵ�ͳLRU�㷨 
 * @param <K> 
 * @param <V> 
 */  
public class LRUCache<K, V> implements Serializable {  
  
    private static final int DEFAULT_CAPACITY = 100;  
  
    protected Map<K, ValueEntry> map;  
  
    private final Lock lock = new ReentrantLock();  
  
    private final transient int maxCapacity;  
  
    private static int MINI_ACCESS = 10;  
  
    public LRUCache() {  
        this(DEFAULT_CAPACITY);  
    }  
  
    public LRUCache(int capacity) {  
        if (capacity <= 0)  
            throw new RuntimeException("������������С��0");  
        this.maxCapacity = capacity;  
        this.map = new HashMap<K, ValueEntry>(maxCapacity);  
    }  
  
    public boolean ContainsKey(K key) {  
        try {  
            lock.lock();  
            return this.map.containsKey(key);  
        } finally {  
            lock.unlock();  
        }  
    }  
  
    public V put(K key, V value) {  
        try {  
            lock.lock();  
            if ((map.size() > maxCapacity - 1) && !map.containsKey(key)) {  
                // System.out.println("��ʼ");  
                Set<Map.Entry<K, ValueEntry>> entries = this.map.entrySet();  
                removeRencentlyLeastAccess(entries);  
            }  
            ValueEntry valueEntry = map.put(key, new ValueEntry(value));  
            if (valueEntry != null)  
                return valueEntry.value;  
            else  
                return null;  
        } finally {  
            lock.unlock();  
        }  
    }  
  
    /** 
     * �Ƴ�������ٷ��� 
     */  
    protected void removeRencentlyLeastAccess(  
            Set<Map.Entry<K, ValueEntry>> entries) {  
        // ��Сʹ�ô���  
        int least = 0;  
        // ���û�б�����  
        long earliest = 0;  
        K toBeRemovedByCount = null;  
        K toBeRemovedByTime = null;  
        Iterator<Map.Entry<K, ValueEntry>> it = entries.iterator();  
        if (it.hasNext()) {  
            Map.Entry<K, ValueEntry> valueEntry = it.next();  
            least = valueEntry.getValue().count.get();  
            toBeRemovedByCount = valueEntry.getKey();  
            earliest = valueEntry.getValue().lastAccess.get();  
            toBeRemovedByTime = valueEntry.getKey();  
        }  
        while (it.hasNext()) {  
            Map.Entry<K, ValueEntry> valueEntry = it.next();  
            if (valueEntry.getValue().count.get() < least) {  
                least = valueEntry.getValue().count.get();  
                toBeRemovedByCount = valueEntry.getKey();  
            }  
            if (valueEntry.getValue().lastAccess.get() < earliest) {  
                earliest = valueEntry.getValue().count.get();  
                toBeRemovedByTime = valueEntry.getKey();  
            }  
        }  
        // System.out.println("remove:" + toBeRemoved);  
        // �������ʹ�ô�������MINI_ACCESS����ô�Ƴ�����ʱ���������(Ҳ�������û�б����ʵ��  
        if (least > MINI_ACCESS) {  
            map.remove(toBeRemovedByTime);  
        } else {  
            map.remove(toBeRemovedByCount);  
        }  
    }  
  
    public V get(K key) {  
        try {  
            lock.lock();  
            V value = null;  
            ValueEntry valueEntry = map.get(key);  
            if (valueEntry != null) {  
                // ���·���ʱ���  
                valueEntry.updateLastAccess();  
                // ���·��ʴ���  
                valueEntry.count.incrementAndGet();  
                value = valueEntry.value;  
            }  
            return value;  
        } finally {  
            lock.unlock();  
        }  
    }  
  
    public void clear() {  
        try {  
            lock.lock();  
            map.clear();  
        } finally {  
            lock.unlock();  
        }  
    }  
  
    public int size() {  
        try {  
            lock.lock();  
            return map.size();  
        } finally {  
            lock.unlock();  
        }  
    }  
  
    public Collection<Map.Entry<K, V>> getAll() {  
        try {  
            lock.lock();  
            Set<K> keys = map.keySet();  
            Map<K, V> tmp = new HashMap<K, V>();  
            for (K key : keys) {  
                tmp.put(key, map.get(key).value);  
            }  
            return new ArrayList<Map.Entry<K, V>>(tmp.entrySet());  
        } finally {  
            lock.unlock();  
        }  
    }  
  
    class ValueEntry implements Serializable {  
        private V value;  
  
        private AtomicInteger count;  
  
        private AtomicLong lastAccess;  
  
        public ValueEntry(V value) {  
            this.value = value;  
            this.count = new AtomicInteger(0);  
            lastAccess = new AtomicLong(System.nanoTime());  
        }  
          
        public void updateLastAccess() {  
            this.lastAccess.set(System.nanoTime());  
        }  
  
    }  
}  

