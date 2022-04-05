public class DoublyLinkedList<T extends Comparable<T>>/* implements Iterable<T> */{ /* Uncoment when iterator is implemented*/
    
    private int size;
    ListNode<T> head;


    DoublyLinkedList(){
        head = null;
        size = 0;
    }

    

    public void add(T t){
        ListNode<T> curnode = head;
        if (size == 0)
            head = new ListNode<T>(t, null, null);
        else{
            curnode = head.getafter();
            for(int i = 0; i <= size; i++){
                if(curnode.getafter() == null){
                    curnode.setafter(new ListNode<T>(t, curnode, null));
                    break;
                }
                curnode = curnode.getafter();
            }
        }
        size++;
    }

    public void add(T t, int index){
        ListNode<T> curnode = head;
        for(int i = 0; i <= index; i++){
            curnode = curnode.getafter();
        }
    }

    public String tostring(){
        if(size != 0){
            head.get();
            
            ListNode<T> curnode = head;
            String out = "[";
            for(int i = 0; i < size; i++){
                out = out + "";
                if(curnode.getafter() == null)
                    break;
                out = out + ", ";
            }
            
            return out;
        }
        return "";
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        

        System.out.println(list.tostring());


        
    }

}
