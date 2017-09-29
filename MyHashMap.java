public class MyHashMap<K, V>{
	public class Entry<K, V> {
		Entry<K, V> next;
		K key;
		V value;
		int hash;

		public Entry(K k, V v, int hash){
			this.key = k;
			this.value = v;
			this.hash = hash;
			this.next = null;
		}

	}

	private int init_capacity = 0;
	private double load_factor = 0;
	private int curSize = 0;
	private int maxSize = 0;
	private Entry<K, V> dct[] = null;

	public MyHashMap(int init_capacity, double load_factor){
		// init_capacity, load_factor invalid
		this.load_factor = load_factor;
		this.init_capacity = init_capacity;
		this.maxSize = (int)(init_capacity * load_factor);
		this.dct = new Entry[init_capacity];
	}

	public MyHashMap(){
		this(16, 0.75);
	}

	public int indexFor(int hash){
		return hash & (dct.length - 1);
	}

	public boolean put(K k, V v){
		int hash = k.hashCode();
		Entry<K, V> tmp = new Entry<>(k, v, hash);
		return setEntry(tmp);

	}
	// this has some problem, because after resize, the index may also change, so a possible fix is to resize() after every setEntry.
	public boolean setEntry(Entry<K, V> tmp){
		int index = indexFor(tmp.hash);
		Entry<K, V> head = new Entry<>(tmp.key, tmp.value, tmp.hash);
		head.next = dct[index];
		Entry<K, V> p = head;
		if(p.next == null){
			setFirstEntry(tmp, index);
			return true;
		}
		
		while(p.next != null){
			if(tmp.key == p.next.key || tmp.key.equals(p.next.key)){
				p.next.value = tmp.value;
				return true;
			}
			p = p.next;
		}
		setNextEntry(tmp, p);
		return true;
	}

	public void setNextEntry(Entry<K, V> tmp, Entry<K, V> pre){		
		curSize++;
		pre.next = tmp;
		if(curSize > maxSize){
			resize(dct.length * 4);
		}
	}

	public void setFirstEntry(Entry<K, V> tmp, int index){
		curSize++;
		dct[index] = tmp;
		if(curSize > maxSize){
			resize(dct.length * 4);
		}	
	}

	public void resize(int cur_capacity){
		Entry<K, V> newDct[] = new Entry[cur_capacity];
		maxSize = (int)(cur_capacity * load_factor);
		for(int i = 0; i < dct.length; i++){
			Entry<K, V> tmp = dct[i];
			while(tmp != null){
				Entry<K, V> next = tmp.next;
				tmp.next = null;
				setEntry(tmp);

				tmp = next;
			}
		}
		dct = newDct;
	}

	public V get(K k){
		int hash = k.hashCode();
		int index = indexFor(hash);
		Entry<K, V> tmp = dct[index];
		while(tmp != null){
			if(k == tmp.key || k.equals(tmp.key)){
				return tmp.value;
			}
			tmp = tmp.next;
		}
		return null;
	}



}
