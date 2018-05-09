import java.util.concurrent.atomic.AtomicIntegerArray;

class GetNSet implements State {
    private AtomicIntegerArray value;
    private byte maxval;
    
    GetNSet(byte[] v) 
    { 
        maxval = 127;
        value = new AtomicIntegerArray(v.length);

        for (int i=0;i<v.length;i++) { 
            value.set (i,(int) v[i]);
        }

    }

    GetNSet(byte[] v, byte m) 
    { 
        maxval = m; 
        value = new AtomicIntegerArray(v.length);

        for (int i=0;i<v.length;i++) { 
            value.set (i,(int) v[i]);
            System.out.println(value.get(i));
        }
    }

    public int size() { return value.length(); }

    public byte[] current() { 

        byte[] bytes = new byte[value.length()]; 

        for(int i=0;i<value.length();i++) {
            bytes[i] = (byte) value.get(i);
        } 
        return bytes; 
    }

    public boolean swap(int i, int j) {
	if (value.get(i) <= 0 || value.get(j) >= maxval) {
	    return false;
	}

	value.getAndDecrement(i);
	value.getAndIncrement(j);
	return true;
    }
}
