package ds.project1task3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
public class ClickerModel {
    private HashMap<String, Integer> hashMap = new HashMap<>();
    // save answer distribution to the hashmap
    public void updateResults(String answer){
        if (hashMap.containsKey(answer)) {
            hashMap.put(answer, hashMap.get(answer)+1);
        } else {
            hashMap.put(answer, 1);
        }
    }
    // get results
    public HashMap<String, Integer> getResults() {
        return hashMap;
    }
    // clear the results
    public void clear() {
        this.hashMap = new HashMap<>();
    }
}
