import java.text.BreakIterator;
import java.util.Iterator;




public class DoublyLinkedList<T extends Comparable<T>> implements Iterable<T>{
    
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
        if(index == 0){

            ListNode<T> newhead = new ListNode<T>(t, null, head);
            if(head !=null)
                head.setbefore(newhead);
            head = newhead;
        }else{
            
            for(int i = 1; i < index; i++){
                curnode = curnode.getafter();
            }
            
            curnode.setafter(new ListNode<T>(t, curnode, curnode.getafter()));
        }
        size ++;
    }

    public String toString(){
        if(size != 0){
            
            
            ListNode<T> curnode = head;
            String out = "[";
            for(int i = 0; i < size; i++){
                out = out + curnode.get();
                if(curnode.getafter() == null)
                    break;
                out = out + ", ";
                curnode = curnode.getafter();
            }
            out = out + "]";
            return out;
        }
        return "";
    }

    public T get(int index){
        ListNode<T> curnode = head;
        for(int i = 0; i < index; i++){
            curnode = curnode.getafter();
        }
        return curnode.get();
    }
    public T getFirst(){
        return head.get();
    }
    public T getLast(){
        return get(size-1);
    }

    public int remove(T t){
        ListNode<T> curnode = head;
        int count = 0;
        for(int i = 0; i < size; i++){
            if(curnode.get() == t){
                count ++;
                if(curnode.getafter() != null)
                    curnode.getafter().setbefore(curnode.getbefore());
                        
                if(curnode.getbefore() != null)
                    curnode.getbefore().setafter(curnode.getafter());
                else
                    head = curnode.getafter();
                   
            }
            if(curnode.getafter() == null)
                break;
            curnode = curnode.getafter();
        }
        size = size - count;
        return count;
    }

    public T Remove(int index){
        ListNode<T> curnode = head;
        for(int i = 0; i < index; i++)
            curnode = curnode.getafter();

        if(curnode.getafter() != null)
            curnode.getafter().setbefore(curnode.getbefore());
        if(curnode.getbefore() != null)
            curnode.getbefore().setafter(curnode.getafter());
        else
            head = curnode.getafter();
        size--;
        return curnode.get();
    }

    public T removeLast(){
        ListNode<T> curnode = head;
        ListNode<T> removed;
        while(true){
            if(curnode.getafter() == null)
                break;
            curnode = curnode.getafter();
        }
        removed = curnode;
        curnode.getbefore().setafter(null);
        size--;
        if(size< 0)
            size = 0;
        return removed.get();

    }

    public T removeFirst(){
        ListNode<T> removed = head;
        if(head.getafter() != null){
            head.getafter().setbefore(null);
            head = head.getafter();
        }else
            head = null;
        size--;
        if(size< 0)
            size = 0;
        return removed.get();
        
    }

    public int size(){
        return size;
    }
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            int index = 0;
            public boolean hasNext(){
                ListNode<T> curnode = head;
                for(int i = 0; i < index; i++)
                    curnode = curnode.getafter();
                
                if(curnode != null)
                    return true;
                
                return false;

            }

            public T next(){
                ListNode<T> curnode = head;
                for(int i = 0; i < index; i++)
                    curnode = curnode.getafter();
                index++;
                return curnode.get();

            }
        };
    }

    public void clear(){
        head = null;
        size = 0;
    }

    public void reverse(){
        MyStack<T> stack = new MyStack<>();
        int count = 0;
        for(T t : this){
            count ++;
            stack.push(t);
        }
        this.clear();
        for(int i = 0; i < count; i++){
            
            this.add(stack.pop(), i);
        }
    
    }

     
    public void addAtFirstSmaller(T t){
        ListNode<T> curnode = head;
        ListNode<T> temp = new ListNode<T>(t);
        if(head == null){
            head = temp;
        }else{
            while(curnode.getafter() != null)
                curnode = curnode.getafter();


            while(true){
           

                if(curnode.get().compareTo(t) == -1){
                    temp.setafter(curnode.getafter());
                    temp.setbefore(curnode);

                    if(curnode.getafter() != null) 
                        curnode.getafter().setbefore(temp);

                    curnode.setafter(temp);
                    break;
                }

             if(curnode.getbefore() != null){
                    curnode = curnode.getbefore();
                } else{
                    temp.setafter(head);
                    head.setbefore(temp);
                    head = temp;
                    break;
                }
            }
        }
        
        size++;
    }


    //Varför inte 
   

    public static void main(String[] args) {
        int[] a = new int[10];
        a[0] = 3;
        a[1] = 5;
        a[2] = 9;
        a[3] = 2;
        a[4] = 3;
        a[5] = 5;
        a[6] = 3;
        a[7] = 7;
        a[8] = 1;
        a[9] = 2;
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + ", ");
        }
        //Sortering
        long start = System.currentTimeMillis();

        DoublyLinkedList<Integer> lista = new DoublyLinkedList<>();
        for(int i = 0; i < a.length; i++)
            lista.addAtFirstSmaller(a[i]);

        for(int i = 0; i < a.length; i++)
            a[i] = lista.get(i);

        long finish = System.currentTimeMillis();
        long computetime = finish - start;
        




        
        System.out.println("");
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + ", ");
        }
        System.out.println("");
        System.out.println(finish);
        System.out.println(start);

    }

}
