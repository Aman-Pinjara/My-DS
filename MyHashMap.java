import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashMap{
    static class HashMap<K,V>{
        private class Node{
            K key;
            V value;
            public Node(K key, V value){
                this.key = key;
                this.value = value;
            }
        }
        private int elements = 0;
        private int bucketsize = 4;

        @SuppressWarnings("unchecked")
        private LinkedList<Node>[] hasharray = new LinkedList[bucketsize];

        public HashMap(){
            for (int i = 0; i < hasharray.length; i++) {
                hasharray[i] = new LinkedList<>();
            }
        }

        private int hashFunction(K key){
            int bucketindex = key.hashCode();
            return Math.abs(bucketindex) % bucketsize;
        }

        private int searchInLinkedList(int bucketindex, K key){
            LinkedList<Node> ll = hasharray[bucketindex];
            for (int i = 0; i < ll.size(); i++) {
                if(ll.get(i).key==key) return i;
            }
            return -1;
        }

        @SuppressWarnings("unchecked")
        private void rehash(){
            LinkedList<Node>[] oldarrray = hasharray;
            bucketsize *= 2;
            hasharray = new LinkedList[bucketsize];
            for (int i = 0; i < hasharray.length; i++) {
                hasharray[i] = new LinkedList<>();
            }
            for(LinkedList<Node> c:oldarrray){
                for(Node element:c){
                    int bucketindex = hashFunction(element.key);
                    hasharray[bucketindex].add(element);
                }
            }
        }

        void put(K key, V value){
            int bucketindex = hashFunction(key);
            int llindex = searchInLinkedList(bucketindex, key);

            if(llindex == -1){
                hasharray[bucketindex].add(new Node(key, value));
                elements++;
                double lambda = (double) elements/bucketsize;
                if(lambda > 2.0){
                    // System.out.println("rehasing");
                    rehash();
                }
            }else{
                Node node = hasharray[bucketindex].get(llindex);
                node.value = value;
            }
        }
        V get(K key){
            int bucketindex = hashFunction(key);
            int llindex = searchInLinkedList(bucketindex, key);

            if(llindex == -1){
                return null;
            }else{
                Node node = hasharray[bucketindex].get(llindex);
                return node.value;
            }
        }
        V remove(K key){
            int bucketindex = hashFunction(key);
            int llindex = searchInLinkedList(bucketindex, key);

            if(llindex == -1){
                return null;
            }else{
                Node node = hasharray[bucketindex].remove(llindex);
                elements--;
                return node.value;
            }
        }
        ArrayList<K> mapKeys(){
            ArrayList<K> keys = new ArrayList<>();
            for (LinkedList<Node> ll : hasharray) {
                for (Node node : ll) {
                    keys.add(node.key);
                }
            }
            return keys;
        }
        boolean isEmpty(){
            return elements==0;
        }
        int size(){
            return elements;
        }
    }
    public static void main(String[] args){
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("Aman", 20);
        hm.put("Anas", 19);
        hm.put("Sahil", 20);
        hm.put("Ajmal", 15);
        hm.put("Fatima", 10);
        hm.put("Fatima", 11);
        hm.put("f", 10);
        hm.put("g", 10);
        hm.put("t", 10);
        hm.put("n", 10);
        hm.put("Muzaffar", 14);
        // ArrayList<String> keys = hm.mapKeys();
        // for(String c:keys){
        //     System.out.println(c+" : "+hm.remove(c));
        // }
            

        // System.out.println(hm.size());
        // System.out.println(hm.get("Anam"));

    }
}