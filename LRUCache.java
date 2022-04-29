import java.util.*;

import org.json.JSONArray;
//import org.json.JSONObject;

public class LRUCache {
    private int LIMIT;
    private LinkedList<String> searchKeys;
    private HashMap<String, JSONArray> searchData;
    private int hitCount = 0;
    private int missCount = 0;

    // key value pairs where key: search query, value: search results (in array list of JSON objects)

    // key = "batman"
    // value = [{the batman},{batman begins}...]

    // limit based constructor
    public LRUCache(int limitInput) {
        this.LIMIT = limitInput;
        this.searchKeys = new LinkedList<>();
        this.searchData = new HashMap<>();
    }

    public void setLimit(int limitInput) {
        this.LIMIT = limitInput;
    }

    public boolean check(String searchInput) {
        if (this.searchData.containsKey(searchInput) == true) {
            return true;
        }
        else  {
            return false;
        } 
    } 

    public JSONArray get(String searchInput) {
        // update linked list
        this.searchKeys.remove(searchInput);
        this.searchKeys.addFirst(searchInput);
        // return data
        return this.searchData.get(searchInput);
    }

    public void update(String searchInput, JSONArray results) {
        
        // Linked List updates
        this.searchKeys.remove(searchInput);
        this.searchKeys.addFirst(searchInput);

        //Hash Map Updates
        if (this.searchData.containsKey(searchInput) == false) {
            this.searchData.put(searchInput, results);
        }

        // Limit checks
        if (this.searchKeys.size() > this.LIMIT) {
            String temp = this.searchKeys.getLast();
            this.searchKeys.removeLast();
            this.searchData.remove(temp);
        }
    }

    public void showContents() {
        System.out.println(this.searchKeys.toString());
       // System.out.println(this.searchData.toString());
    }

    public double getHitRate() {
        return this.hitCount;
    }

    public double getMissRate() {
        return this.missCount;
    }

    public void hit() {
        this.hitCount += 1;
    }

    public void miss() {
        this.missCount += 1;
    }

    public double calcEfficacy() {

        return (this.hitCount / (this.hitCount + this.missCount));
    }
}
