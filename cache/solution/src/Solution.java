import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        Cache cache = new Cache(cacheSize);

        for (int i = 0; i < cities.length; i++) {
            cache.addCache(cities[i].toLowerCase(Locale.ROOT));
        }
        answer = cache.executionTime;

        return answer;
    }


    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(0, new String[]{"Jeju", "jeju","jeju"}));
    }

    class Cache {
        List<String> cache;

        int executionTime;

        int size;

        public Cache(int size) {
            cache = new ArrayList<>();
            executionTime = 0;
            this.size = size;
        }

        public void addCache(String data) {
            int index = cache.indexOf(data);
            if (index != -1) {
                cache.remove(index);
                cache.add(0, data);
                executionTime++;
            } else {
                if (cache.size() == size && size != 0) {
                    cache.remove(cache.size() - 1);
                    cache.add(0, data);
                } else {
                    if (size > cache.size()) {
                        cache.add(0, data);
                    }
                }
                executionTime += 5;
            }
        }
    }

}