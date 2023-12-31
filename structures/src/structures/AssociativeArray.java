package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Jinny Eo
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> cloneArr = new AssociativeArray<>();

    for (int  i = 0; i < this.size; i++) {
      cloneArr.pairs[i] = this.pairs[i].clone();
    }
    
    cloneArr.size = this.size;  
    
    return cloneArr;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    String str = "{";

    for (int i = 0; i < this.size; i++) {
      str += this.pairs[i].toString();
      if (i < this.size - 1) {
        str += ",";
      }
    }
    str += "}";
    return str; 
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value) {
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i].key == key) {
        this.pairs[i].value = value;
      }
    }

  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException when the key does not appear in the 
   * associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i].key == key) {
        return this.pairs[i].value;
      }
    }
    throw new KeyNotFoundException();
  } // get(K)

  /*
   * Get the array of KVPairs, as strings (for AACCategories)
   */
  public KVPair<String, String>[] getPairs() {
    @SuppressWarnings("unchecked")
    KVPair<String, String>[] stringPairs = new KVPair[pairs.length];
    
    for (int i = 0; i < pairs.length; i++) {
      stringPairs[i] = (KVPair<String, String>) pairs[i];
    }
    return stringPairs;
  } // getPairs()

  /*
   * Return list of keys of associative arrray
   */
  public K[] keys() {
    K[] keyArr = (K[]) new Object[this.size];

    for (int i = 0; i < this.size; i++) {
      keyArr[i] = this.pairs[i].key;
    }
    return keyArr;

  } // keys()

  /**
   * Determine if key appears in the associative array.
   */
  public boolean hasKey(K key) {
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i].key == key) {
        return true;
      }
    }
    return false; 
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    for (int i = 0; i < this.size; i++) {
      if (pairs[i].key.equals(key)) {
        this.pairs[i] = null;
      }
    }

    
  } // remove(K)

  /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return i;
      }
    }
    throw new KeyNotFoundException();   
  } // find(K)

} // class AssociativeArray
