import java.util.Iterator;

public class MyStack<T extends Comparable<T>> implements Iterable<T> {
    private int size;
    private DoublyLinkedList<T> stack;

    MyStack(){
        size = 0;
        stack = new DoublyLinkedList<T>();
    }
    
    public boolean isEmpty(){
        if(size == 0)
            return true;
        else 
            return false;
    }

    public T peek(){
        return stack.getFirst();
    }

    public T pop(){
        T val = stack.getFirst();

        stack.removeFirst();
        size--;
        if(size < 0)
            size = 0;
            
        return val;
    }

    public void push(T t){
        stack.add(t, 0);
        size++;
    }

    public Iterator<T> iterator(){
        return new Iterator<T>() {
            int index = 0;
            
            public boolean hasNext(){
                ListNode<T> curnode = stack.head;
                for(int i = 0; i < index; i++)
                    curnode = curnode.getafter();
                
                if(curnode != null)
                    return true;
                return false;

            }

            public T next(){
                ListNode<T> curnode = stack.head;
                for(int i = 0; i < index; i++){
                    curnode = curnode.getafter();
                }
                index++;
                return curnode.get();
            }
        };
    }

    public void print(){
        String out = "";
        ListNode<T> curnode = stack.head;
        for(int i = 0; i < size; i++){
            
            out = out + " " + curnode.get();
            curnode = curnode.getafter();
        }
        System.out.println(out);
    }

    public static void main(String[] args) {
        MyStack<String> st = new MyStack<>();
        st.push("First");
        st.push("Second");
        st.push("Third");
        st.print();
        
        Iterator it = st.iterator();
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());

        System.out.println(it.next());
        System.out.println(it.hasNext());

        System.out.println(it.next());
        System.out.println(it.hasNext());

        

    }

}
